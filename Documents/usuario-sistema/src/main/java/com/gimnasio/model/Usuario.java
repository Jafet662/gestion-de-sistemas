package com.gimnasio.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "usuarios", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"correo"})
})
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nombres;
    @Column(nullable = false)
    private String correo;
    @Column(nullable = false)
    private String passwordMd5;
    @Column(nullable = false)
    private int edad;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoUsuario tipoUsuario;
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal dinero;

    public Long getId() { return id; }
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getPasswordMd5() { return passwordMd5; }
    public void setPasswordMd5(String passwordMd5) { this.passwordMd5 = passwordMd5; }
    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }
    public TipoUsuario getTipoUsuario() { return tipoUsuario; }
    public void setTipoUsuario(TipoUsuario tipoUsuario) { this.tipoUsuario = tipoUsuario; }
    public BigDecimal getDinero() { return dinero; }
    public void setDinero(BigDecimal dinero) { this.dinero = dinero; }
}

