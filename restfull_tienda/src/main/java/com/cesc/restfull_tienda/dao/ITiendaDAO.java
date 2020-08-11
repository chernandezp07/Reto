package com.cesc.restfull_tienda.dao;

import com.cesc.restfull_tienda.dto.Tienda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITiendaDAO extends JpaRepository<Tienda, Long> {
}
