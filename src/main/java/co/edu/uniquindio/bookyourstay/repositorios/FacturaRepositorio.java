package co.edu.uniquindio.bookyourstay.repositorios;

import co.edu.uniquindio.bookyourstay.modelo.Factura;
import co.edu.uniquindio.bookyourstay.modelo.factory.Alojamiento;
import co.edu.uniquindio.bookyourstay.util.Constantes;
import co.edu.uniquindio.bookyourstay.util.Persistencia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FacturaRepositorio {

    private List<Factura> facturas;

    public FacturaRepositorio() {
        this.facturas = leerDatos();
    }

    public void agregar(Factura factura) {
        facturas.add(factura);
        guardarDatos(facturas);
    }

    public Factura obtenerPorUUID(String id) {
        for (Factura f : facturas) {
            if (f.getId().equals(id)) {
                return f;
            }
        }
        return null;
    }

    public List<Factura> listar() {
        return new ArrayList<>(facturas);
    }

    public void guardarDatos(List<Factura> facturas) {
        try {
            Persistencia.serializarObjeto(Constantes.RUTA_FACTURAS, facturas);
        } catch (IOException e) {
            System.err.println("Error guardando facturas: " + e.getMessage());
        }
    }


    public List<Factura> leerDatos() {
        try {
            Object datos = Persistencia.deserializarObjeto(Constantes.RUTA_FACTURAS);
            if (datos != null) {
                return (List<Factura>) datos;
            }
        } catch (Exception e) {
            System.err.println("Error cargando facturas: " + e.getMessage());
        }
        return new ArrayList<>();
    }


}
