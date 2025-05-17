package co.edu.uniquindio.bookyourstay.modelo.factory;

import co.edu.uniquindio.bookyourstay.modelo.Alojamiento;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Casa extends Alojamiento {
    public Casa(String id, String nombre, String ciudad, String descripcion, float precioNoche, byte capacidadMaxima) {
        super(id, nombre, ciudad, descripcion, precioNoche, capacidadMaxima);
    }


    @Override
    public String getTipo() {
        return "casa";
    }

}
