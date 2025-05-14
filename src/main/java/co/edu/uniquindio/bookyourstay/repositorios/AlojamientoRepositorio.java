package co.edu.uniquindio.bookyourstay.repositorios;

import co.edu.uniquindio.bookyourstay.modelo.factory.Alojamiento;

import java.util.ArrayList;
import java.util.List;

public class AlojamientoRepositorio {

    private List<Alojamiento> alojamientos;

    public AlojamientoRepositorio() {
        this.alojamientos = new ArrayList<>();
    }

    public void agregar(Alojamiento alojamiento) {
        alojamientos.add(alojamiento);
    }

    public void eliminar(Alojamiento alojamiento) {
        alojamientos.remove(alojamiento);
    }

    public Alojamiento obtenerPorId(String id) {
        for (Alojamiento a : alojamientos) {
            if (a.getId().equals(id)) {
                return a;
            }
        }
        return null;
    }

    public List<Alojamiento> listar() {
        return new ArrayList<>(alojamientos);
    }
}
