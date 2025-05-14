package co.edu.uniquindio.bookyourstay.repositorios;

import co.edu.uniquindio.bookyourstay.modelo.Reseña;

import java.util.ArrayList;
import java.util.List;

public class ReseñaRepositorio {

    private List<Reseña> resenas;

    public ReseñaRepositorio() {
        this.resenas = new ArrayList<>();
    }

    public void agregar(Reseña resena) {
        resenas.add(resena);
    }

    public List<Reseña> obtenerPorAlojamiento(String idAlojamiento) {
        List<Reseña> resultado = new ArrayList<>();
        for (Reseña r : resenas) {
            if (r.getAlojamiento().getId().equals(idAlojamiento)) {
                resultado.add(r);
            }
        }
        return resultado;
    }

    public List<Reseña> listar() {
        return new ArrayList<>(resenas);
    }
}
