package com.gimnasio.service;

import com.gimnasio.dao.ProductoDao;
import com.gimnasio.model.Producto;

import java.math.BigDecimal;
import java.util.List;

public class BodegaService {
    private final ProductoDao productoDao = new ProductoDao();
    public void registrarProducto(String nombre, String codigo, BigDecimal precioCaja, int restriccionEdad) {
        if (productoDao.findByCodigo(codigo) != null) throw new RuntimeException("Código ya existe");
        Producto p = new Producto();
        p.setNombreProducto(nombre);
        p.setCodigoProducto(codigo);
        p.setPrecioCaja(precioCaja);
        p.setRestriccionEdad(restriccionEdad);
        p.setExistencias(0);
        productoDao.save(p);
    }
    public void ingresarCajas(String codigo, int cajas) {
        Producto p = productoDao.findByCodigo(codigo);
        if (p == null) throw new RuntimeException("Producto no existe");
        p.setExistencias(p.getExistencias() + cajas);
        productoDao.update(p);
    }
    public void sacarCajas(String codigo, int cajas) {
        Producto p = productoDao.findByCodigo(codigo);
        if (p == null) throw new RuntimeException("Producto no existe");
        if (cajas > p.getExistencias()) throw new RuntimeException("No hay suficientes existencias");
        p.setExistencias(p.getExistencias() - cajas);
        productoDao.update(p);
    }
    public List<Producto> listarProductos() { return productoDao.findAll(); }
}

