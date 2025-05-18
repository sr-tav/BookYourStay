package co.edu.uniquindio.bookyourstay.modelo.factory;

import javafx.scene.image.Image;

import java.util.List;

public class Apartamento extends Alojamiento {

    public Apartamento(String id, String nombre, String ciudad, String descripcion, float precioNoche, byte capacidadMaxima, Image imagenAlojamiento, List<String> servicios) {
        super(id, nombre, ciudad, descripcion, precioNoche, capacidadMaxima, imagenAlojamiento, servicios);
    }

    @Override
    public String getTipo() {
        return "apartamento";
    }

    @Override
    public List<String> getServiciosDisponible() {
        return List.of("wifi", "Aire acondicionado", "lavandería");
    }


}
