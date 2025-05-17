package co.edu.uniquindio.bookyourstay.modelo.factory;

import co.edu.uniquindio.bookyourstay.modelo.Alojamiento;

public class Apartamento extends Alojamiento {

    public Apartamento(String id, String nombre, String ciudad, String descripcion, float precioNoche, byte capacidadMaxima){
        super(id, nombre, ciudad, descripcion, precioNoche, capacidadMaxima);
    }

    @Override
    public String getTipo() {
        return "apartamento";
    }


}
