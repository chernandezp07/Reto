package com.cesc.restfull_tienda.controller;

import com.cesc.restfull_tienda.peyload.request.CompraRequest;
import com.cesc.restfull_tienda.peyload.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cesc.restfull_tienda.dto.Producto;
import com.cesc.restfull_tienda.service.producto_service.ProductoServiceImpl;

import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ProductoController {

    @Autowired
    ProductoServiceImpl productoService;

    @GetMapping("/producto")
    public List<Producto> listarProductos() {
        return productoService.listarProducto();
    }

    @PostMapping("/producto")
    public List<Producto> salvarProducto(@RequestBody Producto producto) {
        productoService.guardarProducto(producto);
        return productoService.listarProducto();
    }

    @GetMapping("/producto/{id}")
    public Producto productoXId(@PathVariable(name = "id") Long id) {
        Producto producto_xid = new Producto();
        producto_xid = productoService.productoXID(id);
        return producto_xid;
    }

    @PutMapping("/producto/{id}")
    public List<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        Producto producto_old = productoService.productoXID(id);
        producto_old.setNombre(producto.getNombre());
        producto_old.setDescripcion(producto.getDescripcion());
        producto_old.setPrecio(producto.getPrecio());
        producto_old.setFoto(producto.getFoto());
        producto_old.setCantDisp(producto.getCantDisp());
        Producto producto_new = productoService.actualizarProducto(producto_old);
        return productoService.listarProducto();
    }

    @DeleteMapping("/producto/{id}")
    public List<Producto> eliminarProducto(@PathVariable(name = "id") Long id) {
        productoService.eliminarProducto(id);
        return productoService.listarProducto();
    }

    @PutMapping("/producto/compra")
    public ResponseEntity<?> comprarProducto(@RequestBody CompraRequest compraRequest) {
        Set<Producto> listProd = compraRequest.getLostProCompr();
        for (Producto anObjet : listProd) {
            actualizarProducto(anObjet.getId_producto(), anObjet);
        }
        return ResponseEntity
                .ok(new MessageResponse("Compra satisfactoría"));
    }
}
