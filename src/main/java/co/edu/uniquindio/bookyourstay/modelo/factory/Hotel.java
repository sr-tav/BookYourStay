package co.edu.uniquindio.bookyourstay.modelo.factory;

import co.edu.uniquindio.bookyourstay.modelo.Habitacion;
import javafx.scene.image.Image;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Hotel extends Alojamiento {

    List<Habitacion> listaHabitaciones = new ArrayList<>();

    public Hotel(String id, String nombre, String ciudad, String descripcion, float precioNoche, int capacidadMaxima, Image imagenAlojamiento, List<String> servicios) {
        super(id, nombre, ciudad, descripcion, precioNoche, capacidadMaxima, imagenAlojamiento, servicios);
    }


    @Override
    public String getTipo() {
        return "hotel";
    }



}
