package co.edu.uniquindio.bookyourstay.repositorios;

import co.edu.uniquindio.bookyourstay.modelo.Factura;
import co.edu.uniquindio.bookyourstay.modelo.Resenia;
import co.edu.uniquindio.bookyourstay.util.Constantes;
import co.edu.uniquindio.bookyourstay.util.Persistencia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReseniaRepositorio {

    private List<Resenia> resenas;

    public ReseniaRepositorio() {
        this.resenas = leerDatos();
    }

    public void agregar(Resenia resena) {
        resenas.add(resena);
        guardarDatos(resenas);
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

    public void guardarDatos(List<Resenia> resenas) {
        try {
            Persistencia.serializarObjeto(Constantes.RUTA_RESENIAS, resenas);
        } catch (IOException e) {
            System.err.println("Error guardando reseñas: " + e.getMessage());
        }
    }


    public List<Resenia> leerDatos() {
        try {
            Object datos = Persistencia.deserializarObjeto(Constantes.RUTA_RESENIAS);
            if (datos != null) {
                return (List<Resenia>) resenas;
            }
        } catch (Exception e) {
            System.err.println("Error cargando reseñas: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}
