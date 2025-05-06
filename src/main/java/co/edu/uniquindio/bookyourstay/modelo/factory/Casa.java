package co.edu.uniquindio.bookyourstay.modelo.factory;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Casa extends Alojamiento {
    public Casa(String id, String nombre, String ciudad, String descripcion, float precioNoche, byte capacidadMaxima, List<String> servicios) {
        super(id, nombre, ciudad, descripcion, precioNoche, capacidadMaxima, servicios);
    }


    @Override
    public String getTipo() {
        return "casa";
    }
}
