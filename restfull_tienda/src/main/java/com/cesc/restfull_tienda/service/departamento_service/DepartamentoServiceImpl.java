package com.cesc.restfull_tienda.service.departamento_service;

import com.cesc.restfull_tienda.dao.IDepartamentoDAO;
import com.cesc.restfull_tienda.dto.Departamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DepartamentoServiceImpl implements IDepartamentoService {

    @Autowired
    IDepartamentoDAO iDepartamentoDAO;

    @Override
    public List<Departamento> listarDepartamento() {
        return iDepartamentoDAO.findAll();
    }

    @Override
    public Departamento guardarDepartamento(Departamento departamento) {
        return iDepartamentoDAO.save(departamento);
    }

    @Override
    public Departamento departamentoXID(Long id) {
        return iDepartamentoDAO.findById(id).get();
    }

    @Override
    public Departamento actualizarDepartamento(Departamento departamento) {
        return iDepartamentoDAO.save(departamento);
    }

    @Override
    public void eliminarDepartamento(Long id) {
        iDepartamentoDAO.deleteById(id);
    }
}
