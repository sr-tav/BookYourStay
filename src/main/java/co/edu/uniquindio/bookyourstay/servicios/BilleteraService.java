package co.edu.uniquindio.bookyourstay.servicios;

import co.edu.uniquindio.bookyourstay.modelo.BilleteraVirtual;
import co.edu.uniquindio.bookyourstay.modelo.Cliente;
import co.edu.uniquindio.bookyourstay.repositorios.UsuarioRepositorio;

public class BilleteraService {

    private UsuarioRepositorio usuarioRepositorio;

    public BilleteraService(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

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

    public void procesarPago(String cedulaCliente, float monto) throws Exception {
        Cliente cliente = (Cliente) usuarioRepositorio.obtenerPorCedula(cedulaCliente);
        if (cliente == null) {
            throw new Exception("Cliente no encontrado.");
        }

        if (cliente.getBilleteraVirtual().getSaldo() < monto) {
            throw new Exception("Fondos insuficientes.");
        }

        cliente.getBilleteraVirtual().setSaldo(cliente.getBilleteraVirtual().getSaldo() - monto);
    }
}
