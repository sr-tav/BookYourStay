package co.edu.uniquindio.bookyourstay.repositorios;

import co.edu.uniquindio.bookyourstay.modelo.Resenia;

import java.util.ArrayList;
import java.util.List;

public class ReseniaRepositorio {

    private List<Resenia> resenas;

    public ReseniaRepositorio() {
        this.resenas = new ArrayList<>();
    }

    public void agregar(Resenia resena) {
        resenas.add(resena);
    }

    public List<Resenia> obtenerPorAlojamiento(String idAlojamiento) {
        List<Resenia> resultado = new ArrayList<>();
        for (Resenia r : resenas) {
            if (r.getAlojamiento().getId().equals(idAlojamiento)) {
                resultado.add(r);
            }
        }
        return resultado;
    }

    public List<Resenia> listar() {
        return new ArrayList<>(resenas);
    }
}
