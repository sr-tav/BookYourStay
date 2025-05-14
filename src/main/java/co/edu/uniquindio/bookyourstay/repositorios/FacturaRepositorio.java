package co.edu.uniquindio.bookyourstay.repositorios;

import co.edu.uniquindio.bookyourstay.modelo.Factura;

import java.util.ArrayList;
import java.util.List;

public class FacturaRepositorio {

    private List<Factura> facturas;

    public FacturaRepositorio() {
        this.facturas = new ArrayList<>();
    }

    public void agregar(Factura factura) {
        facturas.add(factura);
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

}
