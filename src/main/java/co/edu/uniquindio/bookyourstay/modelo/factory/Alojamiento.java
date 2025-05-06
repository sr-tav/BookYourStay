package co.edu.uniquindio.bookyourstay.modelo.factory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public abstract class Alojamiento {

    private String id, nombre, ciudad, descripcion;
    private float precioNoche;
    private byte capacidadMaxima;
    private List<String> servicios = new ArrayList<>();

    public abstract String getTipo();
}
