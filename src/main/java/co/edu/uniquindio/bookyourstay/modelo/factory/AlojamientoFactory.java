package co.edu.uniquindio.bookyourstay.modelo.factory;

import java.util.List;

public class AlojamientoFactory {

    public static Alojamiento crearAlojamiento(String tipo, String id, String nombre, String ciudad,
                                               String descripcion, float precioNoche, byte capacidadMaxima,
                                               List<String> servicios) throws Exception {
        switch (tipo.toLowerCase()){
            case "casa":
                return new Casa(id, nombre, ciudad, descripcion, precioNoche, capacidadMaxima, servicios);
            case "apartamento":
                return new Apartamento(id, nombre, ciudad, descripcion, precioNoche, capacidadMaxima, servicios);
            case "hotel":
                return new Hotel(id, nombre, ciudad, descripcion, precioNoche, capacidadMaxima, servicios);
            default:
                throw new Exception("tipo de alojamiento no valido");
        }
    }
}
