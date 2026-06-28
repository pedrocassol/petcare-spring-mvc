package br.com.petcare.service;

import br.com.petcare.dao.UsuarioDAO;
import br.com.petcare.model.Usuario;

public class UsuarioService {

    private final UsuarioDAO usuarioDAO;

    public UsuarioService() {
        this(new UsuarioDAO());
    }

    UsuarioService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public boolean inserir(Usuario usuario) {
        return usuarioDAO.inserir(usuario);
    }
}
