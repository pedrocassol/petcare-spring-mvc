package br.com.petcare.dao;

import br.com.petcare.model.Proprietario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProprietarioDAO {

    public boolean inserir(Proprietario proprietario) {
        String sql = "INSERT INTO proprietario (nome, telefone, email, endereco) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            preencherCampos(stmt, proprietario);
            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível cadastrar o proprietário.", e);
        }
    }

    public List<Proprietario> listar() {
        String sql = "SELECT id_proprietario, nome, telefone, email, endereco " +
                "FROM proprietario ORDER BY nome";
        List<Proprietario> proprietarios = new ArrayList<>();

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                proprietarios.add(mapear(rs));
            }
            return proprietarios;
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível listar os proprietários.", e);
        }
    }

    public int contar() {
        String sql = "SELECT COUNT(*) AS total FROM proprietario";

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            return rs.next() ? rs.getInt("total") : 0;
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível contar os proprietários.", e);
        }
    }

    public Proprietario buscarPorId(int id) {
        String sql = "SELECT id_proprietario, nome, telefone, email, endereco " +
                "FROM proprietario WHERE id_proprietario = ?";

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível buscar o proprietário.", e);
        }
    }

    public boolean atualizar(Proprietario proprietario) {
        String sql = "UPDATE proprietario SET nome = ?, telefone = ?, email = ?, endereco = ? " +
                "WHERE id_proprietario = ?";

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            preencherCampos(stmt, proprietario);
            stmt.setInt(5, proprietario.getId());
            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível atualizar o proprietário.", e);
        }
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM proprietario WHERE id_proprietario = ?";

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            if ("23503".equals(e.getSQLState())) {
                throw new IllegalStateException(
                        "O proprietário possui pets vinculados e não pode ser excluído.", e
                );
            }
            throw new RuntimeException("Não foi possível excluir o proprietário.", e);
        }
    }

    private void preencherCampos(PreparedStatement stmt, Proprietario proprietario) throws SQLException {
        stmt.setString(1, proprietario.getNome());
        stmt.setString(2, proprietario.getTelefone());
        stmt.setString(3, proprietario.getEmail());
        stmt.setString(4, proprietario.getEndereco());
    }

    private Proprietario mapear(ResultSet rs) throws SQLException {
        Proprietario proprietario = new Proprietario();
        proprietario.setId(rs.getInt("id_proprietario"));
        proprietario.setNome(rs.getString("nome"));
        proprietario.setTelefone(rs.getString("telefone"));
        proprietario.setEmail(rs.getString("email"));
        proprietario.setEndereco(rs.getString("endereco"));
        return proprietario;
    }
}
