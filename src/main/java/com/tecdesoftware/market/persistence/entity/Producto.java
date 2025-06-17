package com.tecdesoftware.market.persistence.entity;

import jakarta.persistence.*;
// import jakarta.persistence.*;

@Entity
@Table(name = "productos")
public class Producto {
    @Id
    //valor unico autoincrementable
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column (name = "id_producto")
    private Integer idProducto;

    private String nombre;

    @Column (name = "id_categoria")
    private Integer idCategoria;

    @Column (name = "codigo_barras")
    private String codigoBarra;

    @Column (name = "precio_venta")
    private Double  precioVenta;

    @Column (name = "cantidad_stock")
    private Integer cantidadStock;

    private Boolean estado;

    @ManyToOne
    @JoinColumn(name="id_categoria", insertable=false, updatable = false) //insertar sin modificarMore actions
    private Categoria categoria;

    public Integer getId_producto() {
        return idProducto;
    }

    public void setId_producto(Integer id_producto) {
        this.idProducto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId_categoria() {
        return idCategoria;
    }

    public void setId_categoria(Integer id_categoria) {
        this.idCategoria = id_categoria;
    }

    public String getCodigo_barras() {
        return codigoBarra;
    }

    public void setCodigo_barras(String codigo_barras) {
        this.codigoBarra = codigo_barras;
    }

    public Double getPrecio_venta() {
        return precioVenta;
    }

    public void setPrecio_venta(Double precio_venta) {
        this.precioVenta = precio_venta;
    }

    public Integer getCantidad_stock() {
        return cantidadStock;
    }

    public void setCantidad_stock(Integer cantidad_stock) {
        this.cantidadStock = cantidad_stock;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

}
