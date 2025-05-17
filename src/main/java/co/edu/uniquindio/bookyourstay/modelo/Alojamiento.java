package co.edu.uniquindio.bookyourstay.modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public abstract class Alojamiento {

    private String id, nombre, ciudad, descripcion;
    public float precioNoche;
    private byte capacidadMaxima;

    public abstract String getTipo();

}


