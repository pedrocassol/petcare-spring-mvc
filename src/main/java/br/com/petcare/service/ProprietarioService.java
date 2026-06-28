package br.com.petcare.service;

import br.com.petcare.dao.ProprietarioDAO;
import br.com.petcare.model.Proprietario;

import java.util.List;

public class ProprietarioService {

    private final ProprietarioDAO proprietarioDAO;

    public ProprietarioService() {
        this(new ProprietarioDAO());
    }

    ProprietarioService(ProprietarioDAO proprietarioDAO) {
        this.proprietarioDAO = proprietarioDAO;
    }

    public boolean inserir(Proprietario proprietario) {
        return proprietarioDAO.inserir(proprietario);
    }

    public List<Proprietario> listar() {
        return proprietarioDAO.listar();
    }

    public int contar() {
        return proprietarioDAO.contar();
    }

    public Proprietario buscarPorId(int id) {
        return proprietarioDAO.buscarPorId(id);
    }

    public boolean atualizar(Proprietario proprietario) {
        return proprietarioDAO.atualizar(proprietario);
    }

    public boolean excluir(int id) {
        return proprietarioDAO.excluir(id);
    }
}
