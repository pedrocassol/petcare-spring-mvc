package br.com.petcare.dao;

import br.com.petcare.model.Consulta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO {

    private static final String SELECT_BASE =
            "SELECT c.id_consulta, c.id_pet, c.data_hora, c.veterinario, " +
            "c.descricao, c.valor_estimado, c.status, c.observacoes, " +
            "p.nome AS nome_pet, pr.nome AS nome_proprietario " +
            "FROM consulta c " +
            "INNER JOIN pet p ON p.id_pet = c.id_pet " +
            "INNER JOIN proprietario pr ON pr.id_proprietario = p.id_proprietario ";

    public boolean inserir(Consulta consulta) {
        String sql = "INSERT INTO consulta " +
                "(id_pet, data_hora, veterinario, descricao, valor_estimado, status, observacoes) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            preencherCampos(stmt, consulta);
            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            if ("23503".equals(e.getSQLState())) {
                throw new IllegalStateException("O pet selecionado não existe.", e);
            }
            throw new RuntimeException("Não foi possível cadastrar a consulta.", e);
        }
    }

    public List<Consulta> listar() {
        return executarListagem(SELECT_BASE + "ORDER BY c.data_hora DESC", null, null, null);
    }

    public List<Consulta> listarRecentes(int limite) {
        String sql = SELECT_BASE + "ORDER BY c.data_hora DESC LIMIT ?";
        return executarListagem(sql, null, null, Math.max(1, limite));
    }

    public List<Consulta> listarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        StringBuilder sql = new StringBuilder(SELECT_BASE);

        if (dataInicio != null || dataFim != null) {
            sql.append("WHERE ");

            if (dataInicio != null) {
                sql.append("c.data_hora >= ? ");
            }

            if (dataInicio != null && dataFim != null) {
                sql.append("AND ");
            }

            if (dataFim != null) {
                sql.append("c.data_hora < ? ");
            }
        }

        sql.append("ORDER BY c.data_hora DESC");
        return executarListagem(sql.toString(), dataInicio, dataFim, null);
    }

    public int contar() {
        String sql = "SELECT COUNT(*) AS total FROM consulta";

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            return rs.next() ? rs.getInt("total") : 0;
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível contar as consultas.", e);
        }
    }

    public Consulta buscarPorId(int id) {
        String sql = SELECT_BASE + "WHERE c.id_consulta = ?";

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? mapear(rs) : null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível buscar a consulta.", e);
        }
    }

    public boolean atualizar(Consulta consulta) {
        String sql = "UPDATE consulta SET id_pet = ?, data_hora = ?, veterinario = ?, " +
                "descricao = ?, valor_estimado = ?, status = ?, observacoes = ? " +
                "WHERE id_consulta = ?";

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            preencherCampos(stmt, consulta);
            stmt.setInt(8, consulta.getId());
            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            if ("23503".equals(e.getSQLState())) {
                throw new IllegalStateException("O pet selecionado não existe.", e);
            }
            throw new RuntimeException("Não foi possível atualizar a consulta.", e);
        }
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM consulta WHERE id_consulta = ?";

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível excluir a consulta.", e);
        }
    }

    private List<Consulta> executarListagem(String sql, LocalDate dataInicio,
                                            LocalDate dataFim, Integer limite) {
        List<Consulta> consultas = new ArrayList<>();

        try (Connection conn = ConexaoDB.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            int indice = 1;

            if (dataInicio != null) {
                stmt.setTimestamp(indice++, Timestamp.valueOf(dataInicio.atStartOfDay()));
            }

            if (dataFim != null) {
                stmt.setTimestamp(indice++, Timestamp.valueOf(dataFim.plusDays(1).atStartOfDay()));
            }

            if (limite != null) {
                stmt.setInt(indice, limite);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    consultas.add(mapear(rs));
                }
            }

            return consultas;
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível listar as consultas.", e);
        }
    }

    private void preencherCampos(PreparedStatement stmt, Consulta consulta) throws SQLException {
        stmt.setObject(1, consulta.getIdPet(), Types.INTEGER);
        stmt.setTimestamp(2, converterDataHora(consulta.getDataHora()));
        stmt.setString(3, consulta.getVeterinario());
        stmt.setString(4, consulta.getDescricao());
        stmt.setBigDecimal(5, consulta.getValorEstimado());
        stmt.setString(6, consulta.getStatus());
        stmt.setString(7, consulta.getObservacoes());
    }

    private Consulta mapear(ResultSet rs) throws SQLException {
        Consulta consulta = new Consulta();
        consulta.setId(rs.getInt("id_consulta"));
        consulta.setIdPet(rs.getInt("id_pet"));

        Timestamp dataHora = rs.getTimestamp("data_hora");
        consulta.setDataHora(dataHora == null ? null : dataHora.toLocalDateTime().toString());

        consulta.setVeterinario(rs.getString("veterinario"));
        consulta.setDescricao(rs.getString("descricao"));
        consulta.setValorEstimado(rs.getBigDecimal("valor_estimado"));
        consulta.setStatus(rs.getString("status"));
        consulta.setObservacoes(rs.getString("observacoes"));
        consulta.setNomePet(rs.getString("nome_pet"));
        consulta.setNomeProprietario(rs.getString("nome_proprietario"));
        return consulta;
    }

    private Timestamp converterDataHora(String dataHora) {
        try {
            return Timestamp.valueOf(LocalDateTime.parse(dataHora));
        } catch (DateTimeParseException | NullPointerException e) {
            throw new IllegalArgumentException("Data e hora inválidas.", e);
        }
    }
}
