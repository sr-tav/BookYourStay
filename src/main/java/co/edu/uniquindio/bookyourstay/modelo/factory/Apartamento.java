package co.edu.uniquindio.bookyourstay.modelo.factory;

import java.util.List;

public class Apartamento extends Alojamiento {
    public Apartamento(String id, String nombre, String ciudad, String descripcion, float precioNoche, byte capacidadMaxima, List<String> servicios) {
        super(id, nombre, ciudad, descripcion, precioNoche, capacidadMaxima, servicios);
    }

    @Override
    public String getTipo() {
        return "apartamento";
    }
}
