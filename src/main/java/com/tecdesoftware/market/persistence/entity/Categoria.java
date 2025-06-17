package com.tecdesoftware.market.persistence.entity;

//package persistence.entity;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table (name="categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_categoria")
    private Integer idCategoria;
    private Integer description;
    private Boolean estado;

    @OneToMany (mappedBy="Categoria")
    private List<Producto> productos;

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Integer getDescription() {
        return description;
    }

    public void setDescription(Integer description) {
        this.description = description;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
