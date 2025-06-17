package com.tecdesoftware.market.persistence.entity;

import java.util.List;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table (name = "compras")
public class compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_compra")
    private Integer idCompra;

    @Column (name = "id_cliente")
    private String idCliente;

    private LocalDate fecha;

    @Column (name = "medio_pago")
    private String medioPago;

    private String comentario;
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name = "id_cliente", insertable=false, updatable=false)
    private Cliente cliente;

    @OneToMany(mappedBy="producto")
    private List<CompraProducto> productos;

    public Integer getId_compra() {
        return idCompra;
    }

    public void setId_compra(Integer id_compra) {
        this.idCompra = id_compra;
    }

    public String getId_cliente() {
        return idCliente;
    }

    public void setId_cliente(String id_cliente) {
        this.idCliente = id_cliente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getMedio_pago() {
        return medioPago;
    }

    public void setMedio_pago(String medio_pago) {
        this.medioPago = medio_pago;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

}
