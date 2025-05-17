package co.edu.uniquindio.bookyourstay.modelo.factory;

import co.edu.uniquindio.bookyourstay.modelo.Alojamiento;

public class AlojamientoFactory {

    public static Alojamiento crearAlojamiento(String tipo, String id, String nombre, String ciudad,
                                               String descripcion, float precioNoche, byte capacidadMaxima) throws Exception {
        switch (tipo.toLowerCase()){
            case "casa":
                return new Casa(id, nombre, ciudad, descripcion, precioNoche, capacidadMaxima);
            case "apartamento":
                return new Apartamento(id, nombre, ciudad, descripcion, precioNoche, capacidadMaxima);
            case "hotel":
                return new Hotel(id, nombre, ciudad, descripcion, precioNoche, capacidadMaxima);
            default:
                throw new Exception("tipo de alojamiento no valido");
        }
    }
}
