package co.edu.uniquindio.bookyourstay.repositorios;

import co.edu.uniquindio.bookyourstay.modelo.Oferta;

import java.util.ArrayList;
import java.util.List;

public class OfertaRepositorio {

    private List<Oferta> ofertas;

    public OfertaRepositorio() {
        this.ofertas = new ArrayList<>();
    }

    public void agregar(Oferta oferta) {
        ofertas.add(oferta);
    }

    public void eliminar(Oferta oferta) {
        ofertas.remove(oferta);
    }

    public List<Oferta> obtenerPorAlojamiento(String idAlojamiento) {
        List<Oferta> resultado = new ArrayList<>();
        for (Oferta o : ofertas) {
            if (o.getAlojamiento().getId().equals(idAlojamiento)) {
                resultado.add(o);
            }
        }
        return resultado;
    }

    public List<Oferta> listar() {
        return new ArrayList<>(ofertas);
    }
}
