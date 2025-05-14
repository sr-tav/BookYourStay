package co.edu.uniquindio.bookyourstay.servicios;

import co.edu.uniquindio.bookyourstay.modelo.Cliente;
import co.edu.uniquindio.bookyourstay.modelo.Factura;
import co.edu.uniquindio.bookyourstay.modelo.Reserva;
import co.edu.uniquindio.bookyourstay.modelo.factory.Alojamiento;
import co.edu.uniquindio.bookyourstay.modelo.factory.Apartamento;
import co.edu.uniquindio.bookyourstay.modelo.factory.Casa;
import co.edu.uniquindio.bookyourstay.repositorios.ReservaRepositorio;
import co.edu.uniquindio.bookyourstay.repositorios.UsuarioRepositorio;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

public class ReservaService {

    private final ReservaRepositorio reservaRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;

    public ReservaService(ReservaRepositorio reservaRepositorio, UsuarioRepositorio usuarioRepositorio) {
        this.reservaRepositorio = reservaRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
    }

    public boolean validarDisponibilidad(Alojamiento alojamiento, LocalDate inicio, LocalDate fin) {
        List<Reserva> reservas = reservaRepositorio.listar();

        for (Reserva r : reservas) {
            if (r.getAlojamiento().getId().equals(alojamiento.getId())) {
                boolean invalido = !(fin.isBefore(r.getFechaInicio()) || inicio.isAfter(r.getFechaFin()));
                if (invalido) {
                    return false; // Ya hay una reserva que se topa con las fechas
                }
            }
        }

        return true;
    }

    public boolean validarCapacidad(Alojamiento alojamiento, int cantidad) {
        return alojamiento.getCapacidadMaxima() >= cantidad;
    }

    private float calcularSubtotal(Reserva reserva) {
        Alojamiento alojamiento = reserva.getAlojamiento();
        LocalDate inicio = reserva.getFechaInicio();
        LocalDate fin = reserva.getFechaFin();

        long dias = ChronoUnit.DAYS.between(inicio, fin);
        if (dias == 0) dias = 1;

        float subtotal = dias * alojamiento.getPrecioNoche();

        if (alojamiento instanceof Casa || alojamiento instanceof Apartamento) {
            subtotal += 50000; // Costo adicional fijo por limpieza
        }

        return subtotal;
    }

    public Factura generarFactura(Reserva reserva) {
        if (reserva == null) {
            throw new IllegalArgumentException("La reserva no puede ser nula");
        }

        float subtotal = calcularSubtotal(reserva);
        float iva = subtotal * 0.16f; // Ejemplo: 16% de IVA
        float total = subtotal + iva;

        String uuid = UUID.randomUUID().toString();
        LocalDate fechaEmision = LocalDate.now(); // Usando el API moderno


        return new Factura(
                uuid,
                fechaEmision,
                subtotal,
                total
        );
    }

    public Reserva realizarReserva(Cliente cliente, Alojamiento alojamiento, LocalDate inicio, LocalDate fin, int huespedes, Factura factura) {
        if (!validarCapacidad(alojamiento, huespedes)) return null;
        if (!validarDisponibilidad(alojamiento, inicio, fin)) return null;

        Reserva reserva = new Reserva(UUID.randomUUID().toString(), cliente, alojamiento, inicio, fin, huespedes, factura);

        Factura factura = generarFactura(reserva);
        reserva.setFactura(factura);

        reservaRepositorio.agregar(reserva);
        return reserva;
    }

    public void cancelarReserva(Reserva reserva) {
        reservaRepositorio.eliminar(reserva);
    }
}
