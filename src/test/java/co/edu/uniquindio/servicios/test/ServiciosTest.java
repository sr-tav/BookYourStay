package co.edu.uniquindio.servicios.test;

import co.edu.uniquindio.bookyourstay.modelo.BilleteraVirtual;
import co.edu.uniquindio.bookyourstay.modelo.Cliente;
import co.edu.uniquindio.bookyourstay.modelo.Usuario;
import co.edu.uniquindio.bookyourstay.modelo.factory.Alojamiento;
import co.edu.uniquindio.bookyourstay.repositorios.AlojamientoRepositorio;
import co.edu.uniquindio.bookyourstay.repositorios.OfertaRepositorio;
import co.edu.uniquindio.bookyourstay.repositorios.ReservaRepositorio;
import co.edu.uniquindio.bookyourstay.repositorios.UsuarioRepositorio;
import co.edu.uniquindio.bookyourstay.servicios.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ServiciosTest {

    private AdministradorService adminService;
    private AlojamientoService alojamientoService;
    private AlojamientoRepositorio alojamientoRepositorio;
    private AutenticacionService autenticacionService;
    private UsuarioRepositorio usuarioRepositorio;
    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        alojamientoRepositorio = new AlojamientoRepositorio();
        alojamientoService = new AlojamientoService(alojamientoRepositorio);
        usuarioRepositorio = new UsuarioRepositorio();
        autenticacionService = new AutenticacionService(usuarioRepositorio);

        cliente = new Cliente("María Pérez", "111111111", "321654987", "maria@correo.com", "clave123", null, null, false);
        usuarioRepositorio.agregar(cliente);

        // Agregar alojamientos ficticios
        for (int i = 1; i <= 5; i++) {
            alojamientoRepositorio.agregar(new Alojamiento(
                    "A" + i, "Casa " + i, "Pereira", "Descripción " + i, 150_000, 4, null, List.of("WiFi")
            ) {
                @Override
                public String getTipo() {
                    return "";
                }
            });
        }
    }

    @BeforeEach
    public void inicializar() {
        adminService = new AdministradorService(
                new AlojamientoRepositorio(),
                new OfertaRepositorio(),
                new ReservaRepositorio()
        );
    }

    //Este test valida que al pasar todos los parámetros correctamente, el método no lanza ninguna excepción
    @Test
    public void registrarAlojamientoExitosoTest() {
        assertDoesNotThrow(() -> {
            adminService.registrarAlojamiento(
                    "Casa",
                    "Casa Campestre",
                    "Armenia",
                    "Alojamiento rural con zonas verdes",
                    200_000,
                    8,
                    null, // Suponemos que la imagen puede ser null en este contexto de prueba
                    List.of("WiFi", "Parqueadero")
            );
        });
    }

    // Este test valida que el método lance una excepción si los campos obligatorios están vacíos o mal ingresados
    @Test
    public void registrarAlojamientoInvalidoTest() {
        Exception excepcion = assertThrows(Exception.class, () -> {
            adminService.registrarAlojamiento(
                    "Apartamento",
                    "",        // nombre vacío
                    "",        // ciudad vacía
                    "",        // descripción vacía
                    -100_000,  // precio negativo
                    0,         // capacidad inválida
                    null,
                    List.of("TV")
            );
        });

        String mensaje = excepcion.getMessage();
        assertTrue(mensaje.contains("Debe ingresar el nombre del alojamiento"));
        assertTrue(mensaje.contains("Debe ingresar la ciudad"));
        assertTrue(mensaje.contains("Debe ingresar una descripción"));
        assertTrue(mensaje.contains("El precio por noche debe ser mayor que 0"));
        assertTrue(mensaje.contains("La capacidad máxima debe ser mayor que 0"));
    }

    //Este test valida que el método retorne una lista de tamaño correcto, sin exceder el total disponible
    @Test
    public void obtenerAlojamientosAleatoriosTest() {
        List<Alojamiento> aleatorios = alojamientoService.obtenerAlojamientosAleatorios(3);

        assertNotNull(aleatorios);
        assertEquals(3, aleatorios.size());
    }

    //Este test verifica que los tipos de alojamiento predefinidos estén presentes
    @Test
    public void obtenerTiposAlojamientoTest() {
        List<String> tipos = alojamientoService.obtenerTiposAlojamiento();

        assertNotNull(tipos);
        assertTrue(tipos.contains("Hotel"));
        assertTrue(tipos.contains("Apartamento"));
        assertTrue(tipos.contains("Casa"));
        assertEquals(3, tipos.size());
    }

    //Verifica que el método cambiarContrasena() actualice correctamente la contraseña de un cliente
    @Test
    public void cambiarContrasenaTest() throws Exception {
        autenticacionService.cambiarContrasena(cliente, "nuevaClave456");

        assertEquals("nuevaClave456", cliente.getContrasenia());
    }

    //Comprueba que las credenciales del administrador funcionen correctamente
    @Test
    public void autenticarAdminTest() {
        Usuario usuario = autenticacionService.autenticar("admin@bookyourstay.com", "admin123");

        assertNotNull(usuario);
        assertEquals("Administrador", usuario.getNombre());
        assertEquals("admin@bookyourstay.com", usuario.getEmail());
    }

    //Validar un correo electrónico válido
    @Test
    public void validarEmailCorrectoTest() {
        ClienteService clienteService = new ClienteService();

        String emailValido = "cliente123@correo.com";

        boolean resultado = clienteService.esEmailValido(emailValido);

        assertTrue(resultado);
    }

    //Validar si una cédula contiene solo números
    @Test
    public void validarCedulaSoloNumerosTest() {
        ClienteService clienteService = new ClienteService();

        String cedula = "123456789";

        boolean resultado = clienteService.esSoloNumeros(cedula);

        assertTrue(resultado);
    }


}




