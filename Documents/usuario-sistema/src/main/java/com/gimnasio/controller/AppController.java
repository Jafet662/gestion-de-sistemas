package com.gimnasio.controller;

import com.gimnasio.model.Producto;
import com.gimnasio.model.TipoUsuario;
import com.gimnasio.model.Usuario;
import com.gimnasio.service.AuthService;
import com.gimnasio.service.BodegaService;
import com.gimnasio.service.CompraService;
import com.gimnasio.service.UsuarioService;
import com.gimnasio.util.InputUtil;

import java.math.BigDecimal;
import java.util.List;

public class AppController {
    private final AuthService authService = new AuthService();
    private final UsuarioService usuarioService = new UsuarioService();
    private final BodegaService bodegaService = new BodegaService();
    private final CompraService compraService = new CompraService();
    private final InputUtil input = new InputUtil();

    public void start() {
        authService.seedAdmin();
        while (true) {
            String correo = input.nextLine("Correo");
            String pass = input.nextLine("Contraseña");
            Usuario u = authService.login(correo, pass);
            if (u == null) continue;
            if (u.getTipoUsuario() == TipoUsuario.ADMINISTRADOR) adminMenu(u); else userMenu(u);
        }
    }

    private void adminMenu(Usuario admin) {
        while (true) {
            System.out.println("1) Usuarios 2) Bodega 3) Salir");
            String op = input.nextLine("Opción");
            if (op.equals("1")) adminUsuarios();
            else if (op.equals("2")) adminBodega();
            else if (op.equals("3")) break;
        }
    }

    private void adminUsuarios() {
        while (true) {
            System.out.println("1) Agregar 2) Listar 3) Editar 4) Eliminar 5) Volver");
            String op = input.nextLine("Opción");
            if (op.equals("1")) {
                String nombres = input.nextLine("Nombres");
                String correo = input.nextLine("Correo");
                String pass = input.nextLine("Contraseña");
                int edad = input.nextInt("Edad");
                String tipo = input.nextLine("Tipo (ADMINISTRADOR/USUARIO)");
                BigDecimal dinero = input.nextBigDecimal("Dinero");
                usuarioService.crearUsuario(nombres, correo, pass, edad, TipoUsuario.valueOf(tipo), dinero);
            } else if (op.equals("2")) {
                List<Usuario> list = usuarioService.listarUsuarios();
                for (Usuario u : list) System.out.println(u.getId()+" "+u.getNombres()+" "+u.getCorreo()+" "+u.getTipoUsuario()+" $"+u.getDinero());
            } else if (op.equals("3")) {
                Long id = Long.valueOf(input.nextLine("ID"));
                String nombres = input.nextLine("Nombres");
                String correo = input.nextLine("Correo");
                String pass = input.nextLine("Contraseña (dejar vacío para mantener) ");
                int edad = input.nextInt("Edad");
                String tipo = input.nextLine("Tipo (ADMINISTRADOR/USUARIO)");
                BigDecimal dinero = input.nextBigDecimal("Dinero");
                usuarioService.editarUsuario(id, nombres, correo, pass, edad, TipoUsuario.valueOf(tipo), dinero);
            } else if (op.equals("4")) {
                Long id = Long.valueOf(input.nextLine("ID"));
                usuarioService.eliminarUsuario(id);
            } else if (op.equals("5")) break;
        }
    }

    private void adminBodega() {
        while (true) {
            System.out.println("1) Registrar producto 2) Ingresar cajas 3) Sacar cajas 4) Listar 5) Volver");
            String op = input.nextLine("Opción");
            if (op.equals("1")) {
                String nombre = input.nextLine("Nombre del producto");
                String codigo = input.nextLine("Código de producto");
                BigDecimal precio = input.nextBigDecimal("Precio de la caja");
                int restriccion = input.nextInt("Restricción de edad");
                bodegaService.registrarProducto(nombre, codigo, precio, restriccion);
            } else if (op.equals("2")) {
                String codigo = input.nextLine("Código de producto");
                int cajas = input.nextInt("Número de cajas");
                bodegaService.ingresarCajas(codigo, cajas);
            } else if (op.equals("3")) {
                String codigo = input.nextLine("Código de producto");
                int cajas = input.nextInt("Número de cajas");
                bodegaService.sacarCajas(codigo, cajas);
            } else if (op.equals("4")) {
                List<Producto> list = bodegaService.listarProductos();
                for (Producto p : list) System.out.println(p.getId()+" "+p.getNombreProducto()+" "+p.getCodigoProducto()+" cajas:"+p.getExistencias()+" $"+p.getPrecioCaja()+" edad:"+p.getRestriccionEdad());
            } else if (op.equals("5")) break;
        }
    }

    private void userMenu(Usuario user) {
        while (true) {
            System.out.println("1) Modificar usuario 2) Realizar compra 3) Salir");
            String op = input.nextLine("Opción");
            if (op.equals("1")) {
                String nombres = input.nextLine("Nombres");
                String pass = input.nextLine("Contraseña");
                usuarioService.modificarUsuarioBasico(user.getId(), nombres, pass);
            } else if (op.equals("2")) {
                List<Producto> list = bodegaService.listarProductos();
                for (Producto p : list) System.out.println(p.getCodigoProducto()+" "+p.getNombreProducto()+" cajas:"+p.getExistencias()+" $"+p.getPrecioCaja()+" edad:"+p.getRestriccionEdad());
                String codigo = input.nextLine("Código de producto");
                int cajas = input.nextInt("Número de cajas");
                compraService.comprar(user.getId(), codigo, cajas);
            } else if (op.equals("3")) break;
        }
    }
}

