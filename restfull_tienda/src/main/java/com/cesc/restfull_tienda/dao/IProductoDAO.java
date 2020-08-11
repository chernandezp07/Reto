package com.cesc.restfull_tienda.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cesc.restfull_tienda.dto.Producto;

public interface IProductoDAO extends JpaRepository<Producto, Long> {
}
