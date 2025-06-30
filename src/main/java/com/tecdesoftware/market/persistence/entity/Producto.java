//package persistence.entity;
package com.tecdesoftware.market.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name="productos")
public class Producto {
    @Id //llave primaria
    //valor unico autoincrementable
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="id_producto")
    private Integer idProducto;
    private String nombre;
    private Integer id_categoria;
    private String codigo_barras;
    private Double precio_venta;
    private Integer cantidad_stock;
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name="id_categoria", insertable=false, updatable = false) //insertar sin modificar
    private Categoria categoria;

    public Integer getIdProducto() {return idProducto;}

    public void setIdProducto(Integer idProducto) {this.idProducto = idProducto;}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(Integer id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getCodigo_barras() {
        return codigo_barras;
    }

    public void setCodigo_barras(String codigo_barras) {
        this.codigo_barras = codigo_barras;
    }

    public Double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(Double precio_venta) {
        this.precio_venta = precio_venta;
    }

    public Integer getCantidad_stock() {
        return cantidad_stock;
    }

    public void setCantidad_stock(Integer cantidad_stock) {
        this.cantidad_stock = cantidad_stock;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}