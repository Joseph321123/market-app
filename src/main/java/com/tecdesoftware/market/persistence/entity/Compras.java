// Importación de persistencia de datos
package com.tecdesoftware.market.persistence.entity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="compras")
public class Compras {
    @Id
    //valor unico autoincrementable
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column (name='id_producto')
    private Integer id_compra;
    private String id_cliente;
    private LocalDateTime fecha;
    private String medio_pago;
    private String comentario;
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name = "id_cliente", insertable=false, updatable=false)
    private Cliente cliente;

    @OneToMany(mappedBy="producto")
    private List<CompraProducto> productos;

    public Integer getId_compra() {
        return id_compra;
    }

    public void setId_compra(Integer id_compra) {
        this.id_compra = id_compra;
    }

    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getMedio_pago() {
        return medio_pago;
    }

    public void setMedio_pago(String medio_pago) {
        this.medio_pago = medio_pago;
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