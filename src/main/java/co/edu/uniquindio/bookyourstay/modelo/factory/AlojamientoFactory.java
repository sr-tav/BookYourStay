package co.edu.uniquindio.bookyourstay.modelo.factory;

import javafx.scene.image.Image;

import java.util.List;

public class AlojamientoFactory {

    public static Alojamiento crearAlojamiento(String tipo, String id, String nombre, String ciudad,
                                               String descripcion, float precioNoche, int capacidadMaxima, Image imagenAlojamiento, List<String> servicios) throws Exception {
        switch (tipo.toLowerCase()){
            case "Casa":
                return new Casa(id, nombre, ciudad, descripcion, precioNoche, capacidadMaxima, imagenAlojamiento, servicios);
            case "Apartamento":
                return new Apartamento(id, nombre, ciudad, descripcion, precioNoche, capacidadMaxima, imagenAlojamiento, servicios);
            case "Hotel":
                return new Hotel(id, nombre, ciudad, descripcion, precioNoche, capacidadMaxima, imagenAlojamiento, servicios);
            default:
                throw new Exception("tipo de alojamiento no valido");
        }
    }

    /**
     *
     * @param tipo
     * @return
     * @throws Exception
    public static List<String> obtenerServiciosPorTipo(String tipo) throws Exception {
        Alojamiento alojamiento = crearAlojamiento(
                tipo,
                "tmp",              // ID temporal
                "tmp",              // Nombre temporal
                "tmp",              // Ciudad
                "tmp",              // Descripción
                0,                  // Precio
                1,                  // Capacidad
                null,               // Imagen
                List.of()           // Lista vacía para no alterar nada
        );
        return alojamiento.getServiciosDisponibles();
    }
    */

    public static List<String> obtenerTiposAlojamiento() {
        return List.of("Hotel", "Apartamento", "Casa");
    }
}
