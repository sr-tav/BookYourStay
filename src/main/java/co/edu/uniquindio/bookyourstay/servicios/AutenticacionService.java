package co.edu.uniquindio.bookyourstay.servicios;

import co.edu.uniquindio.bookyourstay.modelo.Usuario;
import co.edu.uniquindio.bookyourstay.repositorios.UsuarioRepositorio;

public class AutenticacionService {

    private final UsuarioRepositorio usuarioRepositorio;

    public AutenticacionService(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    public Usuario iniciarSesion(String email, String contrasena) {
        Usuario usuario = usuarioRepositorio.obtenerPorEmail(email);
        if (usuario != null && usuario.getContrasenia().equals(contrasena)) {
            if (!usuario.getEstadoCuenta()) {
                throw new IllegalStateException("La cuenta no está activada. Verifica tu correo.");
            }
            return usuario;
        }
        return null;
    }

    public void cambiarContrasena(Usuario usuario, String nuevaContrasena) {
        usuario.setContrasenia(nuevaContrasena);
    }
}
