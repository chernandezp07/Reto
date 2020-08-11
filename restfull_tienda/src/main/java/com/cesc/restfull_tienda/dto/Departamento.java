package com.cesc.restfull_tienda.dto;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "departamento")
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_departamento;
    private String nombre;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "depart_product", joinColumns = @JoinColumn(name = "id_departamento"), inverseJoinColumns = @JoinColumn(name = "id_producto"))
    private Set<Producto> product = new HashSet<>();

    public Departamento() {
    }

    public Departamento(String nombre, Set<Producto> product) {
        this.nombre = nombre;
        this.product = product;
    }

    public Long getId_departamento() {
        return id_departamento;
    }

    public void setId_departamento(Long id_departamento) {
        this.id_departamento = id_departamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Producto> getProduct() {
        return product;
    }

    public void setProduct(Set<Producto> product) {
        this.product = product;
    }
}
