package co.edu.uniquindio.bookyourstay.servicios;


import co.edu.uniquindio.bookyourstay.modelo.Factura;
import co.edu.uniquindio.bookyourstay.modelo.factory.Alojamiento;
import co.edu.uniquindio.bookyourstay.modelo.repositorios.ClienteRepositorio;

import java.time.LocalDate;

public class ClienteService extends UsuarioService implements IClienteService{

    private final ClienteRepositorio clienteRepositorio;

    public ClienteService(){
        this.clienteRepositorio=new ClienteRepositorio();
    }


    @Override
    public void iniciarSesion(String email, String contraseña) {

    }

    @Override
    public void cambiarContrasenia(String contraseña) {

    }

    @Override
    public void editarDatos(String nombre, String cedula, String telefono, String email, String contrasenia, boolean estadoCuenta) throws Exception {
        clienteRepositorio.actualizarCliente(nombre, cedula, telefono, email, contrasenia, estadoCuenta );
    }

    @Override
    public Alojamiento buscarAlojamientos(String ciudad) {
        return null;
    }

    @Override
    public void realizarReserva(String cedula, String id, LocalDate fechaInicio, LocalDate fechaFin, int numHuespedes, Factura factura) {

    }

    @Override
    public void cancelarReserva() {

    }

    @Override
    public void agregarResenia(String id, String mensaje) {

    }
}
