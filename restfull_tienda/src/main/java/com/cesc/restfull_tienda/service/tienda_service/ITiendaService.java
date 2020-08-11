package com.cesc.restfull_tienda.service.tienda_service;

import com.cesc.restfull_tienda.dto.Tienda;

import java.util.List;

public interface ITiendaService {

    public List<Tienda> listarTienda();

    public Tienda guardarTienda(Tienda tienda);

    public Tienda tiendaXID(Long id);

    public Tienda actualizarTienda(Tienda tienda);

    public void eliminarTienda(Long id);
}
