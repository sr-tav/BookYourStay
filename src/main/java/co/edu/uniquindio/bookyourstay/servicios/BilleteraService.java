package co.edu.uniquindio.bookyourstay.servicios;

import co.edu.uniquindio.bookyourstay.modelo.BilleteraVirtual;

public class BilleteraService {

    public void recargar(BilleteraVirtual billetera, float monto) {
        billetera.setSaldo(billetera.getSaldo() + monto);
    }

    public boolean realizarPago(BilleteraVirtual billetera, float monto) {
        if (billetera.getSaldo() >= monto) {
            billetera.setSaldo(billetera.getSaldo() - monto);
            return true;
        }
        return false;
    }
}
