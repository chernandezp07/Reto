package com.cesc.restfull_tienda.service.producto_service;

import com.cesc.restfull_tienda.dto.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cesc.restfull_tienda.dao.IProductoDAO;

import java.util.List;

@Service
public class ProductoServiceImpl implements IProductoService {

    @Autowired
    IProductoDAO iProductoDAO;

    @Override
    public List<Producto> listarProducto() {
        return iProductoDAO.findAll();
    }

    @Override
    public Producto guardarProducto(Producto producto) {
        return iProductoDAO.save(producto);
    }

    @Override
    public Producto productoXID(Long id) {
        return iProductoDAO.findById(id).get();
    }

    @Override
    public Producto actualizarProducto(Producto producto) {
        return iProductoDAO.save(producto);
    }

    @Override
    public void eliminarProducto(Long id) {
        iProductoDAO.deleteById(id);
    }
}
