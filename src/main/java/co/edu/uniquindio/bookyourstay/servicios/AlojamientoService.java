package co.edu.uniquindio.bookyourstay.servicios;

import co.edu.uniquindio.bookyourstay.modelo.Alojamiento;
import co.edu.uniquindio.bookyourstay.repositorios.AlojamientoRepositorio;

import java.time.LocalDate;
import java.util.List;

public class AlojamientoService {

    private final AlojamientoRepositorio alojamientoRepositorio;

    public AlojamientoService(AlojamientoRepositorio alojamientoRepositorio) {
        this.alojamientoRepositorio = alojamientoRepositorio;
    }

    /**
     * metodo que lista alojamientos
     * @return
     */
    public List<Alojamiento> listarTodos() {
        return alojamientoRepositorio.listar();
    }

    /**
     * metodo que busca alojamientos por su id
     * @param id
     * @return
     */
    public Alojamiento buscarPorId(String id) {
        return alojamientoRepositorio.obtenerPorId(id);
    }

    /**
     * metodo que busca alojamientos por su ciudad
     * @param ciudad
     * @return
     */
    public List<Alojamiento> buscarPorCiudad(String ciudad) {
        return alojamientoRepositorio.obtenerPorCiudad(ciudad);
    }
}
