package co.edu.uniquindio.bookyourstay.servicios;

public abstract class UsuarioService {

    public abstract void iniciarSesion(String email, String contraseña);

    public abstract void cambiarContrasenia();
}
