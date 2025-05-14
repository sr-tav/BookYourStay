package co.edu.uniquindio.bookyourstay.servicios;

import co.edu.uniquindio.bookyourstay.modelo.Oferta;
import co.edu.uniquindio.bookyourstay.modelo.factory.Alojamiento;
import co.edu.uniquindio.bookyourstay.repositorios.AlojamientoRepositorio;
import co.edu.uniquindio.bookyourstay.repositorios.OfertaRepositorio;

import java.util.List;

public class AdministradorService {

    private final AlojamientoRepositorio alojamientoRepositorio;
    private final OfertaRepositorio ofertaRepositorio;

    public AdministradorService(AlojamientoRepositorio alojamientoRepositorio, OfertaRepositorio ofertaRepositorio) {
        this.alojamientoRepositorio = alojamientoRepositorio;
        this.ofertaRepositorio = ofertaRepositorio;
    }

    public void agregarAlojamiento(Alojamiento alojamiento) {
        alojamientoRepositorio.agregar(alojamiento);
    }

    public void eliminarAlojamiento(Alojamiento alojamiento) {
        alojamientoRepositorio.eliminar(alojamiento);
    }

    public void agregarOferta(Oferta oferta) {
        ofertaRepositorio.agregar(oferta);
    }

    public void eliminarOferta(Oferta oferta) {
        ofertaRepositorio.eliminar(oferta);
    }

    public List<Alojamiento> listarAlojamientos() {
        return alojamientoRepositorio.listar();
    }



}
