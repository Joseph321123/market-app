package com.tecdesoftware.market.persistence.entity;


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
    private String estado;
}
