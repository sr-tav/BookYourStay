package co.edu.uniquindio.bookyourstay.servicios;

import co.edu.uniquindio.bookyourstay.modelo.factory.Alojamiento;
import co.edu.uniquindio.bookyourstay.repositorios.AlojamientoRepositorio;
import lombok.AllArgsConstructor;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
public class AlojamientoService {

    private final AlojamientoRepositorio alojamientoRepositorio;

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

    public List<Alojamiento> obtenerAlojamientosAleatorios(int cantidad) {
        List<Alojamiento> todos = listarTodos(); // ya existente
        Collections.shuffle(todos);
        return todos.subList(0, Math.min(cantidad, todos.size()));
    }
}
