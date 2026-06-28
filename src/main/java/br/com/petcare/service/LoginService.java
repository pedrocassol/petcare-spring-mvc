package br.com.petcare.service;

import br.com.petcare.dao.UsuarioDAO;
import br.com.petcare.model.Usuario;

public class LoginService {

    private final UsuarioDAO usuarioDAO;

    public LoginService() {
        this(new UsuarioDAO());
    }

    LoginService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public Usuario autenticar(String email, String senha) {
        if (email == null || email.isBlank() || senha == null || senha.isBlank()) {
            return null;
        }

        return usuarioDAO.autenticar(email.trim(), senha);
    }
}
