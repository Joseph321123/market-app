// Importación de persistencia de datos
package com.tecdesoftware.market.persistence.entity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table (name="categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_categoria")
    private Integer idCategoria;
    private Integer descripcion;
    private Boolean estado;


    @OneToMany (mappedBy= "Categoria")
    private List<Producto> productos;


    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Integer getDescription() {
        return descripcion;
    }

    public void setDescription(Integer description) {
        this.descripcion = description;
    }

    public Boolean getEstado() {//PREGUNTA DE EXAMEN
        return estado;//PREGUNTA DE EXAMEN
    }

    public void setEstado(Boolean estado) {//PREGUNTA DE EXAMEN
        this.estado = estado;    //PREGUNTA DE EXAMEN
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public Integer getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(Integer descripcion) {
        this.descripcion = descripcion;
    }
}
