package com.gabriel.traceacessibilidade.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gabriel on 20/11/16.
 */

public class TransportAll implements Serializable {

    private List<PublicTransport> publicTransports;

    public List<PublicTransport> getPublicTransports() {
        return publicTransports;
    }

    public void setPublicTransports(List<PublicTransport> publicTransports) {
        this.publicTransports = publicTransports;
    }
}
