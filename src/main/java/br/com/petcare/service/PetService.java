package br.com.petcare.service;

import br.com.petcare.dao.PetDAO;
import br.com.petcare.model.Pet;

import java.util.List;

public class PetService {

    private final PetDAO petDAO;

    public PetService() {
        this(new PetDAO());
    }

    PetService(PetDAO petDAO) {
        this.petDAO = petDAO;
    }

    public boolean inserir(Pet pet) {
        return petDAO.inserir(pet);
    }

    public List<Pet> listar() {
        return petDAO.listar();
    }

    public int contar() {
        return petDAO.contar();
    }

    public Pet buscarPorId(int id) {
        return petDAO.buscarPorId(id);
    }

    public boolean atualizar(Pet pet) {
        return petDAO.atualizar(pet);
    }

    public boolean excluir(int id) {
        return petDAO.excluir(id);
    }
}
