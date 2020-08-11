package com.cesc.restfull_tienda.controller;

import com.cesc.restfull_tienda.dto.Departamento;
import com.cesc.restfull_tienda.service.departamento_service.DepartamentoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class DepartamentoController {

    @Autowired
    DepartamentoServiceImpl departamentoService;

    @GetMapping("/departamento")
    public List<Departamento> listarDepartamento() {
        return departamentoService.listarDepartamento();
    }

    @PostMapping("/departamento")
    public List<Departamento> salvarDepartamento(@RequestBody Departamento departamento) {
        departamentoService.guardarDepartamento(departamento);
        return departamentoService.listarDepartamento();
    }

    @GetMapping("/departamento/{id}")
    public Departamento departamentoXId(@PathVariable(name = "id") Long id) {
        Departamento departamento_xid = new Departamento();
        departamento_xid = departamentoService.departamentoXID(id);
        return departamento_xid;
    }

    @PutMapping("/departamento/{id}")
    public List<Departamento> actualizarDepartamento(@PathVariable Long id, @RequestBody Departamento departamento) {
        Departamento departamento_old = departamentoService.departamentoXID(id);
        departamento_old.setNombre(departamento.getNombre());
        departamento_old.setProduct(departamento.getProduct());
        Departamento departamento_new = departamentoService.actualizarDepartamento(departamento_old);
        return departamentoService.listarDepartamento();
    }

    @DeleteMapping("/departamento/{id}")
    public List<Departamento> eliminarDepartamento(@PathVariable(name = "id") Long id) {
        departamentoService.eliminarDepartamento(id);
        return departamentoService.listarDepartamento();
    }
}
