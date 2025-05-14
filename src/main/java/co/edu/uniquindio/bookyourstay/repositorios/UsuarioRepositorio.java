package co.edu.uniquindio.bookyourstay.repositorios;

import co.edu.uniquindio.bookyourstay.modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositorio {

    private List<Usuario> usuarios;

    public UsuarioRepositorio() {
        this.usuarios = new ArrayList<>();
    }

    public void agregar(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void eliminar(Usuario usuario) {
        usuarios.remove(usuario);
    }

    public Usuario obtenerPorCedula(String cedula) {
        for (Usuario u : usuarios) {
            if (u.getCedula().equals(cedula)) {
                return u;
            }
        }
        return null;
    }

    public Usuario obtenerPorEmail(String email) {
        for (Usuario u : usuarios) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return u;
            }
        }
        return null;
    }

    public List<Usuario> listar() {
        return new ArrayList<>(usuarios);
    }
}
