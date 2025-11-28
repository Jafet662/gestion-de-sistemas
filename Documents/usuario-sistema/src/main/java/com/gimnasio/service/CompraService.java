package com.gimnasio.service;

import com.gimnasio.dao.CompraDao;
import com.gimnasio.dao.ProductoDao;
import com.gimnasio.dao.UsuarioDao;
import com.gimnasio.model.Compra;
import com.gimnasio.model.Producto;
import com.gimnasio.model.Usuario;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CompraService {
    private final ProductoDao productoDao = new ProductoDao();
    private final UsuarioDao usuarioDao = new UsuarioDao();
    private final CompraDao compraDao = new CompraDao();
    public void comprar(Long usuarioId, String codigoProducto, int cajas) {
        Usuario u = usuarioDao.findById(usuarioId);
        if (u == null) throw new RuntimeException("Usuario no existe");
        Producto p = productoDao.findByCodigo(codigoProducto);
        if (p == null) throw new RuntimeException("Producto no existe");
        if (u.getEdad() < p.getRestriccionEdad()) throw new RuntimeException("No cumple restricción de edad");
        if (cajas <= 0) throw new RuntimeException("Cajas inválidas");
        if (cajas > p.getExistencias()) throw new RuntimeException("No hay suficientes existencias");
        BigDecimal total = p.getPrecioCaja().multiply(new BigDecimal(cajas));
        if (u.getDinero().compareTo(total) < 0) throw new RuntimeException("Dinero insuficiente");
        u.setDinero(u.getDinero().subtract(total));
        p.setExistencias(p.getExistencias() - cajas);
        Compra c = new Compra();
        c.setUsuario(u);
        c.setProducto(p);
        c.setCajas(cajas);
        c.setTotal(total);
        c.setFecha(LocalDateTime.now());
        usuarioDao.update(u);
        productoDao.update(p);
        compraDao.save(c);
    }
}

