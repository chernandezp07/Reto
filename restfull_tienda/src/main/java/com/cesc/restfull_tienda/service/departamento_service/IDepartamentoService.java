package com.cesc.restfull_tienda.service.departamento_service;

import com.cesc.restfull_tienda.dto.Departamento;
import java.util.List;

public interface IDepartamentoService {

    public List<Departamento> listarDepartamento();

    public Departamento guardarDepartamento(Departamento departamento);

    public Departamento departamentoXID(Long id);

    public Departamento actualizarDepartamento(Departamento departamento);

    public void eliminarDepartamento(Long id);
}
