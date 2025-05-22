package co.edu.uniquindio.bookyourstay.repositorios;

import co.edu.uniquindio.bookyourstay.modelo.Factura;
import co.edu.uniquindio.bookyourstay.modelo.Oferta;
import co.edu.uniquindio.bookyourstay.util.Constantes;
import co.edu.uniquindio.bookyourstay.util.Persistencia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OfertaRepositorio {

    private List<Oferta> ofertas;

    public OfertaRepositorio() {
        this.ofertas = leerDatos();
    }

    public void agregar(Oferta oferta) {
        ofertas.add(oferta);
        guardarDatos(ofertas);
    }

    public void eliminar(Oferta oferta) {
        ofertas.remove(oferta);
        guardarDatos(ofertas);
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

    public void guardarDatos(List<Oferta> datos) {
        try {
            Persistencia.serializarObjeto(Constantes.RUTA_OFERTAS, datos);
        } catch (IOException e) {
            System.err.println("Error guardando ofertas: " + e.getMessage());
        }
    }


    public List<Oferta> leerDatos() {
        try {
            Object datos = Persistencia.deserializarObjeto(Constantes.RUTA_OFERTAS);
            if (datos != null) {
                return (List<Oferta>) datos;
            }
        } catch (Exception e) {
            System.err.println("Error cargando ofertas: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}
