package co.edu.uniquindio.bookyourstay.repositorios;

import co.edu.uniquindio.bookyourstay.modelo.Reserva;

import java.util.ArrayList;
import java.util.List;

public class ReservaRepositorio {

    private List<Reserva> reservas;

    public ReservaRepositorio() {
        this.reservas = new ArrayList<>();
    }

    public void agregar(Reserva reserva) {
        reservas.add(reserva);
    }

    public void eliminar(Reserva reserva) {
        reservas.remove(reserva);
    }

    public Reserva obtenerPorId(String id) {
        for (Reserva reserva : reservas) {
            if (reserva.getId().equals(id)) {
                return reserva;
            }
        }
        return null;
    }

    public List<Reserva> obtenerPorCliente(String cedulaCliente) {
        List<Reserva> resultado = new ArrayList<>();
        for (Reserva reserva : reservas) {
            if (reserva.getCliente().getCedula().equals(cedulaCliente)) {
                resultado.add(reserva);
            }
        }
        return resultado;
    }

    public List<Reserva> listar() {
        return new ArrayList<>(reservas);
    }
}
