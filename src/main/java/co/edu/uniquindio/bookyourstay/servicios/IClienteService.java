package co.edu.uniquindio.bookyourstay.servicios;

import co.edu.uniquindio.bookyourstay.modelo.Factura;
import co.edu.uniquindio.bookyourstay.modelo.factory.Alojamiento;

import java.time.LocalDate;

public interface IClienteService {

    void editarDatos(String nombre, String cedula, String telefono, String email, String contraseña, boolean estadoCuenta) throws Exception;

    Alojamiento buscarAlojamientos(String ciudad);

    void realizarReserva(String cedula, String id, LocalDate fechaInicio, LocalDate fechaFin, int numHuespedes, Factura factura);

    void cancelarReserva();

    void agregarResenia(String id, String mensaje);

}
