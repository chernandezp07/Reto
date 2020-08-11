package com.cesc.restfull_tienda.service.producto_service;

import com.cesc.restfull_tienda.dto.Producto;

import java.util.List;

public interface IProductoService {

    public List<Producto> listarProducto();

    public Producto guardarProducto(Producto producto);

    public Producto productoXID(Long id);

    public Producto actualizarProducto(Producto producto);

    public void eliminarProducto(Long id);
}
