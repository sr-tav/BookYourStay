package co.edu.uniquindio.bookyourstay.servicios;

import co.edu.uniquindio.bookyourstay.modelo.Cliente;
import co.edu.uniquindio.bookyourstay.repositorios.UsuarioRepositorio;


public class ClienteService{

    private final UsuarioRepositorio usuarioRepositorio;

    public ClienteService(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    public void registrarCliente(Cliente cliente) {
        usuarioRepositorio.agregar(cliente);
    }

    public Cliente buscarClientePorEmail(String email) {
        return (Cliente) usuarioRepositorio.obtenerPorEmail(email);
    }
}
