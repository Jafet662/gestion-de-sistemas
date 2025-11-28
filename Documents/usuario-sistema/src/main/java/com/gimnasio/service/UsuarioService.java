package com.gimnasio.service;

import com.gimnasio.dao.UsuarioDao;
import com.gimnasio.model.TipoUsuario;
import com.gimnasio.model.Usuario;
import com.gimnasio.util.HashUtil;

import java.math.BigDecimal;
import java.util.List;

public class UsuarioService {
    private final UsuarioDao usuarioDao = new UsuarioDao();
    public void crearUsuario(String nombres, String correo, String password, int edad, TipoUsuario tipo, BigDecimal dinero) {
        if (usuarioDao.findByCorreo(correo) != null) throw new RuntimeException("Correo ya existe");
        Usuario u = new Usuario();
        u.setNombres(nombres);
        u.setCorreo(correo);
        u.setPasswordMd5(HashUtil.md5(password));
        u.setEdad(edad);
        u.setTipoUsuario(tipo);
        u.setDinero(dinero);
        usuarioDao.save(u);
    }
    public List<Usuario> listarUsuarios() { return usuarioDao.findAll(); }
    public void editarUsuario(Long id, String nombres, String correo, String password, int edad, TipoUsuario tipo, BigDecimal dinero) {
        Usuario u = usuarioDao.findById(id);
        if (u == null) throw new RuntimeException("Usuario no existe");
        if (!u.getCorreo().equals(correo) && usuarioDao.findByCorreo(correo) != null) throw new RuntimeException("Correo ya existe");
        u.setNombres(nombres);
        u.setCorreo(correo);
        if (password != null && !password.isEmpty()) u.setPasswordMd5(HashUtil.md5(password));
        u.setEdad(edad);
        u.setTipoUsuario(tipo);
        u.setDinero(dinero);
        usuarioDao.update(u);
    }
    public void eliminarUsuario(Long id) {
        Usuario u = usuarioDao.findById(id);
        if (u == null) throw new RuntimeException("Usuario no existe");
        usuarioDao.delete(u);
    }
    public void modificarUsuarioBasico(Long id, String nombres, String password) {
        Usuario u = usuarioDao.findById(id);
        if (u == null) throw new RuntimeException("Usuario no existe");
        u.setNombres(nombres);
        if (password != null && !password.isEmpty()) u.setPasswordMd5(HashUtil.md5(password));
        usuarioDao.update(u);
    }
}

