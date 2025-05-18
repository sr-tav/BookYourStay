package co.edu.uniquindio.bookyourstay.servicios;

import co.edu.uniquindio.bookyourstay.modelo.*;
import co.edu.uniquindio.bookyourstay.modelo.factory.Alojamiento;
import co.edu.uniquindio.bookyourstay.repositorios.AlojamientoRepositorio;
import co.edu.uniquindio.bookyourstay.repositorios.ReseniaRepositorio;
import co.edu.uniquindio.bookyourstay.repositorios.ReservaRepositorio;
import co.edu.uniquindio.bookyourstay.repositorios.UsuarioRepositorio;
import co.edu.uniquindio.bookyourstay.util.EmailUtil;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

@AllArgsConstructor
public class ClienteService{

    private final UsuarioRepositorio usuarioRepositorio;
    private final AutenticacionService autenticacionService;
    private final AlojamientoRepositorio alojamientoRepositorio;
    private final ReseniaRepositorio reseniaRepositorio;
    private final ReservaRepositorio reservaRepositorio;


    public ClienteService() {
        this.usuarioRepositorio = new UsuarioRepositorio();
        this.reseniaRepositorio = new ReseniaRepositorio();
        this.alojamientoRepositorio = new AlojamientoRepositorio();
        this.reservaRepositorio = new ReservaRepositorio();
        this.autenticacionService = new AutenticacionService();
    }

    /**
     * metodo para registrar el cliente
     * @param nombre
     * @param cedula
     * @param telefono
     * @param email
     * @param contrasenia
     * @throws Exception
     */
    public void registrarCliente(String nombre, String cedula, String telefono, String email, String contrasenia) throws Exception {

        String mensajesValidacion = "";

        if (nombre == null || nombre.isEmpty()) {
            mensajesValidacion += "Debe ingresar el nombre\n";
        }

        if (!esSoloNumeros(cedula)) {
            mensajesValidacion += "La identificación solo debe contener números\n";
        }

        if (telefono == null || telefono.isEmpty()) {
            mensajesValidacion += "Debe ingresar el teléfono\n";
        }

        if (contrasenia == null || contrasenia.isEmpty()) {
            mensajesValidacion += "Debe ingresar la contraseña\n";
        }

        if (!esEmailValido(email)) {
            mensajesValidacion += "El correo ingresado no es válido\n";
        }

        if (!mensajesValidacion.isEmpty()) {
            throw new Exception(mensajesValidacion);
        }

        if (buscarPorCedula(cedula) != null) {
            throw new Exception("Ya existe un cliente con la identificación ingresada");
        }

        // Generar código de activación
        String codigo = autenticacionService.generarCodigo();

        Cliente cliente = Cliente.builder()
                .nombre(nombre)
                .cedula(cedula)
                .telefono(telefono)
                .email(email)
                .contrasenia(contrasenia)
                .estadoCuenta(false)
                .codigoActivacion(codigo)
                .billeteraVirtual(new BilleteraVirtual(0))
                .build();

        usuarioRepositorio.agregar(cliente); // guardar el cliente

        String mensaje = "Hola " + nombre + ",\n\nTu código de activación es: " + codigo;
        EmailUtil.enviarNotificacion(cliente.getEmail(), "Código de activación - BookYourStay", mensaje);
    }

    /**
     * metodo para buscar usuarios por cedula
     * @param cedula
     * @return
     */
    public Cliente buscarPorCedula(String cedula) {
        return (Cliente) usuarioRepositorio.obtenerPorCedula(cedula);
    }

    /**
     * metodos para verificar que la cedula es solo numeros
     * @param input
     * @return
     */
    public boolean esSoloNumeros(String input) {
        return Pattern.compile("^[0-9]+$").matcher(input).matches();
    }

    /**
     * metodo para verificar que el email sea valido
     * @param email
     * @return
     */
    public boolean esEmailValido(String email) {
        Pattern pattern =  Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$", Pattern.CASE_INSENSITIVE);
        return pattern.matcher(email).matches();
    }

    /**
     * metodo que agrega una reseña
     * @param cedulaCliente
     * @param idAlojamiento
     * @param comentario
     * @param puntuacion
     * @throws Exception
     */
    public void agregarResena(String cedulaCliente, String idAlojamiento, String comentario, int puntuacion) throws Exception {
        Cliente cliente = (Cliente) usuarioRepositorio.obtenerPorCedula(cedulaCliente);
        if (cliente == null) {
            throw new Exception("Cliente no encontrado");
        }

        Alojamiento alojamiento = alojamientoRepositorio.obtenerPorId(idAlojamiento);
        if (alojamiento == null) {
            throw new Exception("Alojamiento no encontrado");
        }

        if (puntuacion < 1 || puntuacion > 5) {
            throw new Exception("La puntuación debe estar entre 1 y 5");
        }

        List<Reserva> reservas = reservaRepositorio.listar();
        boolean hospedado = reservas.stream()
                .anyMatch(r -> r.getCliente().getCedula().equals(cedulaCliente)
                        && r.getAlojamiento().getId().equals(idAlojamiento)
                        && r.getFechaFin().isBefore(LocalDate.now()));

        if (!hospedado) {
            throw new Exception("Solo se puede dejar una reseña si ya finalizó la estadía en este alojamiento");
        }

        Resenia resenia = Resenia.builder()
                .cliente(cliente)
                .alojamiento(alojamiento)
                .comentario(comentario)
                .fecha(LocalDate.now())
                .calificacion(puntuacion)
                .build();

        reseniaRepositorio.agregar(resenia);
    }
}
