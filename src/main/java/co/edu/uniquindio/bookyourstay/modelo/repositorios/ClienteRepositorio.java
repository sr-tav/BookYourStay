package co.edu.uniquindio.bookyourstay.modelo.repositorios;
import co.edu.uniquindio.bookyourstay.modelo.Cliente;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepositorio {

    private final List<Cliente> clientes;

    public ClienteRepositorio() {
        this.clientes = new ArrayList<>();
    }

    public void agregar(Cliente cliente) {
        clientes.add(cliente);
    }

    public Cliente obtener(String identificacion) {
        for (Cliente cliente : clientes) {
            if (cliente.getCedula().equals(identificacion)) {
                return cliente;
            }
        }
        return null;
    }

    public void actualizarCliente(String nombre, String cedula, String telefono, String email, String contrasenia, boolean estadoCuenta) throws Exception{
        for(Cliente cliente : clientes){
            if(cliente.getCedula().equals(cedula)){
                cliente.setNombre(nombre);
                cliente.setCedula(cedula);
                cliente.setTelefono(telefono);
                cliente.setEmail(email);
                cliente.setContrasenia(contrasenia);
                cliente.setEstadoCuenta(estadoCuenta);
                break;
            }
        }
    }

    public List<Cliente> listar() {
        return clientes;
    }
}
