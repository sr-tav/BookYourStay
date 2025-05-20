package co.edu.uniquindio.bookyourstay.modelo.factory;

import javafx.scene.image.Image;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Casa extends Alojamiento {


    public Casa(String id, String nombre, String ciudad, String descripcion, float precioNoche, int capacidadMaxima, Image imagenAlojamiento, List<String> servicios) {
        super(id, nombre, ciudad, descripcion, precioNoche, capacidadMaxima, imagenAlojamiento, servicios);
    }

    @Override
    public String getTipo() {
        return "casa";
    }

    @Override
    public List<String> getServiciosDisponible() {
        return List.of("Piscina", "Wifi", "lavanderia", "Aire acondicionado");
    }

}
