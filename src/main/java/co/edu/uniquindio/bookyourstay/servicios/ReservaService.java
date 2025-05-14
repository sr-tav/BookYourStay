package co.edu.uniquindio.bookyourstay.servicios;

import co.edu.uniquindio.bookyourstay.modelo.Cliente;
import co.edu.uniquindio.bookyourstay.modelo.Factura;
import co.edu.uniquindio.bookyourstay.modelo.Reserva;
import co.edu.uniquindio.bookyourstay.modelo.factory.Alojamiento;
import co.edu.uniquindio.bookyourstay.repositorios.ReservaRepositorio;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class ReservaService {

    private final ReservaRepositorio reservaRepositorio;

    public ReservaService(ReservaRepositorio reservaRepositorio) {
        this.reservaRepositorio = reservaRepositorio;
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

    public Reserva realizarReserva(Cliente cliente, Alojamiento alojamiento, LocalDate inicio, LocalDate fin, int huespedes) {
        if (!validarCapacidad(alojamiento, huespedes)) {
            return null;
        }

        if(!validarDisponibilidad(alojamiento, inicio, fin)){
            return null;
        }

        String id = UUID.randomUUID().toString();
        Factura factura = null;
        Reserva reserva = new Reserva(id, cliente, alojamiento, inicio, fin, huespedes, factura);
        reservaRepositorio.agregar(reserva);
        return reserva;
    }

    public void cancelarReserva(Reserva reserva) {
        reservaRepositorio.eliminar(reserva);
    }
}
