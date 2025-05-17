package co.edu.uniquindio.bookyourstay.servicios;

import co.edu.uniquindio.bookyourstay.modelo.BilleteraVirtual;

public class BilleteraService {

    /**
     * metodo que recarga billetera
     * @param billetera
     * @param monto
     */
    public void recargar(BilleteraVirtual billetera, float monto) {
        billetera.setSaldo(billetera.getSaldo() + monto);
    }

    /**
     * metodo que realiza pagos
     * @param billetera
     * @param monto
     * @return
     */
    public boolean realizarPago(BilleteraVirtual billetera, float monto) {
        if (billetera.getSaldo() >= monto) {
            billetera.setSaldo(billetera.getSaldo() - monto);
            return true;
        }
        return false;
    }
}
