package com.cesc.restfull_tienda.dao;

import com.cesc.restfull_tienda.dto.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDepartamentoDAO extends JpaRepository<Departamento, Long> {
}
