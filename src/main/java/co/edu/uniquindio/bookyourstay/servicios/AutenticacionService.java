package co.edu.uniquindio.bookyourstay.servicios;

import co.edu.uniquindio.bookyourstay.modelo.Cliente;
import co.edu.uniquindio.bookyourstay.modelo.Usuario;
import co.edu.uniquindio.bookyourstay.repositorios.UsuarioRepositorio;
import co.edu.uniquindio.bookyourstay.util.EmailUtil;

import java.util.Random;
import java.util.UUID;

public class AutenticacionService {

    private final UsuarioRepositorio usuarioRepositorio;

    public AutenticacionService() {
        this.usuarioRepositorio = new UsuarioRepositorio();
    }

    public AutenticacionService(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }


    /**
     * metodo para activar la cuenta
     * @param codigoIngresado
     * @return
     * @throws Exception
     */
    public boolean activarCuenta(String cedula, String codigoIngresado) throws Exception {
        Cliente cliente = (Cliente) usuarioRepositorio.obtenerPorCedula(cedula);

        if (!cliente.getCodigoActivacion().equals(codigoIngresado)) {
            throw new Exception("El código de activación no es válido");
        }

        cliente.setEstadoCuenta(true);
        cliente.setCodigoActivacion(null);
        return true;
    }

    /**
     * metodo para cambiar contraseña
     * @param usuario
     * @param nuevaContrasena
     * @throws Exception
     */
    public void cambiarContrasena(Usuario usuario, String nuevaContrasena) throws Exception {
        if (nuevaContrasena == null || nuevaContrasena.length() < 6) {
            throw new Exception("La contraseña debe tener al menos 6 caracteres");
        }
        usuario.setContrasenia(nuevaContrasena);
    }

    /**
     * metodo para enviar codigo de recuperacion
     * @param email
     * @throws Exception
     */
    public void enviarCodigoRecuperacion(String email) throws Exception {
        Usuario usuario = usuarioRepositorio.obtenerPorEmail(email);

        if (usuario == null) {
            throw new Exception("No existe un usuario con ese correo.");
        }

        String codigo = generarCodigo();
        usuario.setCodigoRecuperacion(codigo);

        EmailUtil.enviarNotificacion(usuario.getEmail(),
                "Código para cambiar contraseña - BookYourStay",
                "Hola " + usuario.getNombre() + ",\n\nTu código para cambiar la contraseña es: " + codigo +
                        "\n\nSi no solicitaste esto, ignora este mensaje.\n\nEquipo BookYourStay");
    }

    /**
     * metodo para confimrar el cambio de la contraseña
     * @param email
     * @param codigoIngresado
     * @param nuevaContrasena
     * @throws Exception
     */
    public void confirmarCambioContrasena(String email, String codigoIngresado, String nuevaContrasena) throws Exception {
        Usuario usuario = usuarioRepositorio.obtenerPorEmail(email);

        if (usuario == null) {
            throw new Exception("Usuario no encontrado.");
        }

        if (usuario.getCodigoRecuperacion() == null || !usuario.getCodigoRecuperacion().equals(codigoIngresado)) {
            throw new Exception("El código de verificación no es válido.");
        }

        if (nuevaContrasena == null || nuevaContrasena.length() < 6) {
            throw new Exception("La nueva contraseña debe tener al menos 6 caracteres.");
        }

        usuario.setContrasenia(nuevaContrasena);
        usuario.setCodigoRecuperacion(null); // Código ya usado
    }

    /**
     * metodo que genera el codigo
     * @return
     * @throws Exception
     */
    public String generarCodigo() throws Exception {

        int codigo = new Random().nextInt(999999);
        String codigoFormateado = String.format("%06d", codigo);

        return codigoFormateado;

    }
 }
