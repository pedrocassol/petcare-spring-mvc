package br.com.petcare.dao;

import br.com.petcare.model.Pet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class PetDAO {

    private static final String SELECT_BASE =
            "SELECT p.id_pet, p.nome, p.especie, p.raca, p.idade, " +
            "p.sexo, p.observacoes, p.id_proprietario, pr.nome AS nome_proprietario " +
            "FROM pet p " +
            "INNER JOIN proprietario pr ON pr.id_proprietario = p.id_proprietario ";

    public boolean inserir(Pet pet) {
        String sql = "INSERT INTO pet " +
                "(nome, especie, raca, idade, sexo, observacoes, id_proprietario) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            preencherCampos(stmt, pet);
            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            if ("23503".equals(e.getSQLState())) {
                throw new IllegalStateException("O proprietário selecionado não existe.", e);
            }
            throw new RuntimeException("Não foi possível cadastrar o pet.", e);
        }
    }

    public List<Pet> listar() {
        String sql = SELECT_BASE + "ORDER BY p.nome";
        List<Pet> pets = new ArrayList<>();

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                pets.add(mapear(rs));
            }
            return pets;
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível listar os pets.", e);
        }
    }

    public int contar() {
        String sql = "SELECT COUNT(*) AS total FROM pet";

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            return rs.next() ? rs.getInt("total") : 0;
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível contar os pets.", e);
        }
    }

    public Pet buscarPorId(int id) {
        String sql = SELECT_BASE + "WHERE p.id_pet = ?";

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível buscar o pet.", e);
        }
    }

    public boolean atualizar(Pet pet) {
        String sql = "UPDATE pet SET nome = ?, especie = ?, raca = ?, idade = ?, " +
                "sexo = ?, observacoes = ?, id_proprietario = ? WHERE id_pet = ?";

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            preencherCampos(stmt, pet);
            stmt.setInt(8, pet.getId());
            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            if ("23503".equals(e.getSQLState())) {
                throw new IllegalStateException("O proprietário selecionado não existe.", e);
            }
            throw new RuntimeException("Não foi possível atualizar o pet.", e);
        }
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM pet WHERE id_pet = ?";

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            if ("23503".equals(e.getSQLState())) {
                throw new IllegalStateException(
                        "O pet possui consultas vinculadas e não pode ser excluído.", e
                );
            }
            throw new RuntimeException("Não foi possível excluir o pet.", e);
        }
    }

    private void preencherCampos(PreparedStatement stmt, Pet pet) throws SQLException {
        stmt.setString(1, pet.getNome());
        stmt.setString(2, pet.getEspecie());
        stmt.setString(3, pet.getRaca());

        if (pet.getIdade() == null) {
            stmt.setNull(4, Types.INTEGER);
        } else {
            stmt.setInt(4, pet.getIdade());
        }

        stmt.setString(5, pet.getSexo());
        stmt.setString(6, pet.getObservacoes());
        stmt.setObject(7, pet.getIdProprietario(), Types.INTEGER);
    }

    private Pet mapear(ResultSet rs) throws SQLException {
        Pet pet = new Pet();
        pet.setId(rs.getInt("id_pet"));
        pet.setNome(rs.getString("nome"));
        pet.setEspecie(rs.getString("especie"));
        pet.setRaca(rs.getString("raca"));
        pet.setIdade(rs.getObject("idade", Integer.class));
        pet.setSexo(rs.getString("sexo"));
        pet.setObservacoes(rs.getString("observacoes"));
        pet.setIdProprietario(rs.getInt("id_proprietario"));
        pet.setNomeProprietario(rs.getString("nome_proprietario"));
        return pet;
    }
}
