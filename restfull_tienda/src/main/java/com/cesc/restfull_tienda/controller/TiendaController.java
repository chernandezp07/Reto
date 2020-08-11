package com.cesc.restfull_tienda.controller;

import com.cesc.restfull_tienda.dto.Tienda;
import com.cesc.restfull_tienda.service.tienda_service.TiendaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class TiendaController {

    @Autowired
    TiendaServiceImpl tiendaService;

    @GetMapping("/tienda")
    public List<Tienda> listarTiendas() {
        return tiendaService.listarTienda();
    }

    @PostMapping("/tienda")
    public List<Tienda> salvarTienda(@RequestBody Tienda tienda) {
        tiendaService.guardarTienda(tienda);
        return tiendaService.listarTienda();
    }

    @GetMapping("/tienda/{id}")
    public Tienda tiendaXId(@PathVariable(name = "id") Long id) {
        Tienda tienda_xid = new Tienda();
        tienda_xid = tiendaService.tiendaXID(id);
        return tienda_xid;
    }

    @PutMapping("/tienda/{id}")
    public List<Tienda> actualizarTienda(@PathVariable Long id, @RequestBody Tienda tienda) {
        Tienda tienda_old = tiendaService.tiendaXID(id);
        tienda_old.setNombre(tienda.getNombre());
        tienda_old.setDepart(tienda.getDepart());
        Tienda tienda_new = tiendaService.actualizarTienda(tienda_old);
        return tiendaService.listarTienda();
    }

    @DeleteMapping("/tienda/{id}")
    public List<Tienda> eliminarTienda(@PathVariable(name = "id") Long id) {
        tiendaService.eliminarTienda(id);
        return tiendaService.listarTienda();
    }
}
