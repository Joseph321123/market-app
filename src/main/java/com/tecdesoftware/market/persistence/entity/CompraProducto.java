// Importación de persistencia de datos
package com.tecdesoftware.market.persistence.entity;
import jakarta.persistence.*;

@Entity
@Table (name="compras_productos")
public class CompraProducto {

    @EmbeddedId
    private CompraProductoPK id;

    private Integer cantidad;
    private Double total;
    private Boolean estado;

    //Llave compuesta
    //Final de relación con compra
    @ManyToOne
    @MapsId("idCompra") // Enlaza con el campo del embeddable
    @JoinColumn(name="id_compra", insertable=false, updatable=false)
    private Compra compra;

    @ManyToOne
    @MapsId("idProducto") // Enlaza con el campo del embeddable
    @JoinColumn(name="id_producto", insertable=false, updatable=false)
    private Producto producto;

    public CompraProductoPK getId() {
        return id;
    }

    public void setId(CompraProductoPK id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }



}
