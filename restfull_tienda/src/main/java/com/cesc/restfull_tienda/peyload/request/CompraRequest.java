package com.cesc.restfull_tienda.peyload.request;

import com.cesc.restfull_tienda.dto.Producto;
import java.util.HashSet;
import java.util.Set;

public class CompraRequest {
    private Set<Producto> lostProCompr = new HashSet<>();;

    public Set<Producto> getLostProCompr() {
        return lostProCompr;
    }

    public void setLostProCompr(Set<Producto> lostProCompr) {
        this.lostProCompr = lostProCompr;
    }
}
