package com.predic8.membrane.starter.servlet;

import com.predic8.membrane.core.transport.Transport;
import com.predic8.membrane.core.transport.ssl.SSLProvider;

import java.io.IOException;

public class ServletTransport extends Transport {
    private boolean removeContextRoot = true;

    public boolean isRemoveContextRoot() {
        return removeContextRoot;
    }

    public void setRemoveContextRoot(boolean removeContextRoot) {
        this.removeContextRoot = removeContextRoot;
    }

    @Override
    public void openPort(String ip, int port, SSLProvider sslProvider) throws IOException {
    }

    @Override
    public void closeAll() throws IOException {
    }

    @Override
    public boolean isOpeningPorts() {
        return false;
    }
}