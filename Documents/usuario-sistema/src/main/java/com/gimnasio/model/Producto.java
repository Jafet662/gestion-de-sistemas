package com.gimnasio.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "productos", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"codigoProducto"})
})
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nombreProducto;
    @Column(nullable = false)
    private String codigoProducto;
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal precioCaja;
    @Column(nullable = false)
    private int restriccionEdad;
    @Column(nullable = false)
    private int existencias;

    public Long getId() { return id; }
    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }
    public String getCodigoProducto() { return codigoProducto; }
    public void setCodigoProducto(String codigoProducto) { this.codigoProducto = codigoProducto; }
    public BigDecimal getPrecioCaja() { return precioCaja; }
    public void setPrecioCaja(BigDecimal precioCaja) { this.precioCaja = precioCaja; }
    public int getRestriccionEdad() { return restriccionEdad; }
    public void setRestriccionEdad(int restriccionEdad) { this.restriccionEdad = restriccionEdad; }
    public int getExistencias() { return existencias; }
    public void setExistencias(int existencias) { this.existencias = existencias; }
}

