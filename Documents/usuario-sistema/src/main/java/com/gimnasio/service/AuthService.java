package com.gimnasio.service;

import com.gimnasio.dao.UsuarioDao;
import com.gimnasio.model.TipoUsuario;
import com.gimnasio.model.Usuario;
import com.gimnasio.util.HashUtil;

import java.math.BigDecimal;

public class AuthService {
    private final UsuarioDao usuarioDao = new UsuarioDao();
    public Usuario login(String correo, String password) {
        Usuario u = usuarioDao.findByCorreo(correo);
        if (u == null) return null;
        String h = HashUtil.md5(password);
        if (!h.equals(u.getPasswordMd5())) return null;
        return u;
    }
    public void seedAdmin() {
        Usuario u = usuarioDao.findByCorreo("admin");
        if (u != null) return;
        Usuario admin = new Usuario();
        admin.setNombres("admin");
        admin.setCorreo("admin");
        admin.setPasswordMd5(HashUtil.md5("admin"));
        admin.setEdad(30);
        admin.setTipoUsuario(TipoUsuario.ADMINISTRADOR);
        admin.setDinero(new BigDecimal("0"));
        usuarioDao.save(admin);
    }
}

