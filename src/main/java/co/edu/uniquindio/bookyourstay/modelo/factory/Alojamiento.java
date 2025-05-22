package co.edu.uniquindio.bookyourstay.modelo.factory;

import javafx.scene.image.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public abstract class Alojamiento implements Serializable {

    private String id, nombre, ciudad, descripcion;
    public float precioNoche;
    private int capacidadMaxima;
    private Image imagenAlojamiento;
    private List<String> servicios;

    public abstract String getTipo();

    /**
     * metodo toString
     * @return
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + nombre;
    }
}


