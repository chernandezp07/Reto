package com.cesc.restfull_tienda.service.tienda_service;

import com.cesc.restfull_tienda.dao.ITiendaDAO;
import com.cesc.restfull_tienda.dto.Tienda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TiendaServiceImpl implements ITiendaService {

    @Autowired
    ITiendaDAO iTiendaDAO;

    @Override
    public List<Tienda> listarTienda() {
        return iTiendaDAO.findAll();
    }

    @Override
    public Tienda guardarTienda(Tienda tienda) {
        return iTiendaDAO.save(tienda);
    }

    @Override
    public Tienda tiendaXID(Long id) {
        return iTiendaDAO.findById(id).get();
    }

    @Override
    public Tienda actualizarTienda(Tienda tienda) {
        return iTiendaDAO.save(tienda);
    }

    @Override
    public void eliminarTienda(Long id) {
        iTiendaDAO.deleteById(id);
    }
}
