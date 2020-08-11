package com.cesc.restfull_tienda.dto;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tienda")
public class Tienda {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_tienda;
    private String nombre;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tiend_depart", joinColumns = @JoinColumn(name = "id_tienda"), inverseJoinColumns = @JoinColumn(name = "id_departamento"))
    private Set<Departamento> depart = new HashSet<>();

    public Tienda() {
    }

    public Tienda(String nombre, Set<Departamento> depart) {
        this.nombre = nombre;
        this.depart = depart;
    }

    public Long getId_tienda() {
        return id_tienda;
    }

    public void setId_tienda(Long id_tienda) {
        this.id_tienda = id_tienda;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Departamento> getDepart() {
        return depart;
    }

    public void setDepart(Set<Departamento> depart) {
        this.depart = depart;
    }
}
