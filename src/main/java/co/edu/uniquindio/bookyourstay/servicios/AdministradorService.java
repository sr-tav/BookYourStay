package co.edu.uniquindio.bookyourstay.servicios;

import co.edu.uniquindio.bookyourstay.modelo.Oferta;
import co.edu.uniquindio.bookyourstay.modelo.Alojamiento;
import co.edu.uniquindio.bookyourstay.modelo.Reserva;
import co.edu.uniquindio.bookyourstay.modelo.factory.AlojamientoFactory;
import co.edu.uniquindio.bookyourstay.repositorios.AlojamientoRepositorio;
import co.edu.uniquindio.bookyourstay.repositorios.OfertaRepositorio;
import co.edu.uniquindio.bookyourstay.repositorios.ReservaRepositorio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AdministradorService {

    private final AlojamientoRepositorio alojamientoRepositorio;
    private final OfertaRepositorio ofertaRepositorio;
    private final ReservaRepositorio reservaRepositorio;

    public AdministradorService(AlojamientoRepositorio alojamientoRepositorio, OfertaRepositorio ofertaRepositorio, ReservaRepositorio reservaRepositorio) {
        this.alojamientoRepositorio = alojamientoRepositorio;
        this.ofertaRepositorio = ofertaRepositorio;
        this.reservaRepositorio = reservaRepositorio;
    }

    /**
     * metodo para agregar un alojamiento
     * @param tipo
     * @param id
     * @param nombre
     * @param ciudad
     * @param descripcion
     * @param precioNoche
     * @param capacidadMaxima
     * @throws Exception
     */
    public void registrarAlojamiento(String tipo, String id, String nombre, String ciudad,
                                     String descripcion, float precioNoche, byte capacidadMaxima) throws Exception {

        String mensajesValidacion = "";

        if (id == null || id.isEmpty()) {
            mensajesValidacion += "Debe ingresar el ID del alojamiento\n";
        }

        if (nombre == null || nombre.isEmpty()) {
            mensajesValidacion += "Debe ingresar el nombre del alojamiento\n";
        }

        if (ciudad == null || ciudad.isEmpty()) {
            mensajesValidacion += "Debe ingresar la ciudad\n";
        }

        if (descripcion == null || descripcion.isEmpty()) {
            mensajesValidacion += "Debe ingresar una descripción\n";
        }

        if (precioNoche <= 0) {
            mensajesValidacion += "El precio por noche debe ser mayor que 0\n";
        }

        if (capacidadMaxima <= 0) {
            mensajesValidacion += "La capacidad máxima debe ser mayor que 0\n";
        }

        if (!mensajesValidacion.isEmpty()) {
            throw new Exception(mensajesValidacion);
        }

        if (alojamientoRepositorio.obtenerPorId(id) != null) {
            throw new Exception("Ya existe un alojamiento con ese ID");
        }

        Alojamiento alojamiento = AlojamientoFactory.crearAlojamiento(
                tipo, id, nombre, ciudad, descripcion, precioNoche, capacidadMaxima
        );

        alojamientoRepositorio.agregar(alojamiento);
    }

    /**
     * metodo para eliminar un alojamiento
     * @param alojamiento
     */
    public void eliminarAlojamiento(Alojamiento alojamiento) {
        alojamientoRepositorio.eliminar(alojamiento);
    }

    /**
     * metodo para crear ofertas
     * @param idAlojamiento
     * @param inicio
     * @param fin
     * @param porcentaje
     * @throws Exception
     */
    public void crearOfertaEspecial(String idAlojamiento, LocalDate inicio, LocalDate fin, float porcentaje) throws Exception {
        Alojamiento alojamiento = alojamientoRepositorio.obtenerPorId(idAlojamiento);

        if (alojamiento == null) {
            throw new Exception("No se encontró el alojamiento con el ID especificado");
        }

        if (inicio.isAfter(fin)) {
            throw new Exception("La fecha de inicio no puede ser posterior a la fecha final");
        }

        Oferta oferta = new Oferta(idAlojamiento, alojamiento, inicio, fin, porcentaje);
        ofertaRepositorio.agregar(oferta);
    }

    /**
     * metodo para listar ofertas por alojamiento
     * @param idAlojamiento
     * @return
     */
    public List<Oferta> listarOfertasPorAlojamiento(String idAlojamiento) {
        List<Oferta> todas = ofertaRepositorio.listar();
        List<Oferta> filtradas = new ArrayList<>();

        for (Oferta o : todas) {
            if (o.getAlojamiento().getId().equals(idAlojamiento)) {
                filtradas.add(o);
            }
        }

        return filtradas;
    }

    /**
     * metodo para obtener los alojamientos mas populares en una ciudad
     * @return
     */
    public Map<String, List<Alojamiento>> obtenerAlojamientosMasPopularesPorCiudad() {
        List<Reserva> reservas = reservaRepositorio.listar();
        Map<String, Map<Alojamiento, Long>> contador = new HashMap<>();

        for (Reserva reserva : reservas) {
            Alojamiento alojamiento = reserva.getAlojamiento();
            String ciudad = alojamiento.getCiudad();

            contador.putIfAbsent(ciudad, new HashMap<>());
            Map<Alojamiento, Long> mapaCiudad = contador.get(ciudad);
            mapaCiudad.put(alojamiento, mapaCiudad.getOrDefault(alojamiento, 0L) + 1);
        }

        Map<String, List<Alojamiento>> popularesPorCiudad = new HashMap<>();

        for (String ciudad : contador.keySet()) {
            List<Alojamiento> ordenados = contador.get(ciudad).entrySet().stream()
                    .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());

            popularesPorCiudad.put(ciudad, ordenados);
        }

        return popularesPorCiudad;
    }

    /**
     * metodo para eliminar una oferta
     * @param oferta
     */
    public void eliminarOferta(Oferta oferta) {
        ofertaRepositorio.eliminar(oferta);
    }

    /**
     * metodo para listar alojamientos
     * @return
     */
    public List<Alojamiento> listarAlojamientos() {
        return alojamientoRepositorio.listar();
    }

}
