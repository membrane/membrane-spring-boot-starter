package com.predic8.membrane.starter.servlet;

import com.predic8.membrane.core.exchange.Exchange;
import com.predic8.membrane.core.http.*;
import com.predic8.membrane.core.transport.Transport;
import com.predic8.membrane.core.transport.http.AbortException;
import com.predic8.membrane.core.transport.http.AbstractHttpHandler;
import com.predic8.membrane.core.transport.http.EOFWhileReadingFirstLineException;
import com.predic8.membrane.core.util.DNSCache;
import com.predic8.membrane.core.util.EndOfStreamException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Enumeration;

class HttpServletHandler extends AbstractHttpHandler {
    private static final Logger log = LoggerFactory.getLogger(HttpServletHandler.class);

    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final InetAddress remoteAddr, localAddr;

    public HttpServletHandler(HttpServletRequest request, HttpServletResponse response,
                              Transport transport) throws IOException {

        super(transport);
        this.request = request;
        this.response = response;
        remoteAddr = InetAddress.getByName(request.getRemoteAddr());
        localAddr = InetAddress.getByName(request.getLocalAddr());
        exchange = new Exchange(this);

        exchange.setProperty(Exchange.HTTP_SERVLET_REQUEST, request);
    }

    public void run() {
        try {
            srcReq = createRequest();

            exchange.received();

            try {
                DNSCache dnsCache = getTransport().getRouter().getDnsCache();
                String ip = dnsCache.getHostAddress(remoteAddr);
                exchange.setRemoteAddrIp(ip);
                exchange.setRemoteAddr(getTransport().isReverseDNS() ? dnsCache.getHostName(remoteAddr) : ip);

                exchange.setRequest(srcReq);
                exchange.setOriginalRequestUri(srcReq.getUri());

                invokeHandlers();
            } catch (AbortException e) {
                exchange.finishExchange(true, exchange.getErrorMessage());
                writeResponse(exchange.getResponse());
                return;
            }

            exchange.getRequest().readBody(); // read if not alread read
            writeResponse(exchange.getResponse());
            exchange.setCompleted();
        } catch (EndOfStreamException e) {
            log.debug("stream closed");
        } catch (EOFWhileReadingFirstLineException e) {
            log.debug("Client connection terminated before line was read. Line so far: (" + e.getLineSoFar() + ")");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            exchange.detach();
        }
    }

    @SuppressWarnings("deprecation")
    protected void writeResponse(Response res) throws Exception {
        response.setStatus(res.getStatusCode(), res.getStatusMessage());
        for (HeaderField header : res.getHeader().getAllHeaderFields()) {
            if (header.getHeaderName().equals(Header.TRANSFER_ENCODING))
                continue;
            response.addHeader(header.getHeaderName().toString(), header.getValue());
        }

        ServletOutputStream out = response.getOutputStream();
        res.getBody().write(new PlainBodyTransferrer(out));
        out.flush();

        response.flushBuffer();

        exchange.setTimeResSent(System.currentTimeMillis());
        exchange.collectStatistics();
    }

    private Request createRequest() throws IOException {
        Request srcReq = new Request();

        String pathQuery = request.getRequestURI();
        if (request.getQueryString() != null)
            pathQuery += "?" + request.getQueryString();

        if (getTransport().isRemoveContextRoot()) {
            String contextPath = request.getContextPath();
            if (contextPath.length() > 0 && pathQuery.startsWith(contextPath))
                pathQuery = pathQuery.substring(contextPath.length());
        }

        srcReq.create(
            request.getMethod(),
            pathQuery,
            request.getProtocol(),
            createHeader(),
            request.getInputStream());
        return srcReq;
    }

    private Header createHeader() {
        Header header = new Header();
        Enumeration<?> e = request.getHeaderNames();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            Enumeration<?> e2 = request.getHeaders(key);
            while (e2.hasMoreElements()) {
                String value = (String) e2.nextElement();
                header.add(key, value);
            }
        }
        return header;
    }

    @Override
    public void shutdownInput() throws IOException {
        request.getInputStream().close();
    }

    @Override
    public InetAddress getLocalAddress() {
        return localAddr;
    }

    @Override
    public int getLocalPort() {
        return request.getLocalPort();
    }

    @Override
    public ServletTransport getTransport() {
        return (ServletTransport) super.getTransport();
    }

    @Override
    public boolean isMatchLocalPort() {
        return false;
    }

    @Override
    public String getContextPath(Exchange exc) {
        return ((HttpServletRequest) exc.getProperty(Exchange.HTTP_SERVLET_REQUEST)).getContextPath();
    }

}