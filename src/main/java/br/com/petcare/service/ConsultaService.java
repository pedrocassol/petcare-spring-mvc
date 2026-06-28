package br.com.petcare.service;

import br.com.petcare.dao.ConsultaDAO;
import br.com.petcare.model.Consulta;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ConsultaService {

    private final ConsultaDAO consultaDAO;

    public ConsultaService() {
        this(new ConsultaDAO());
    }

    ConsultaService(ConsultaDAO consultaDAO) {
        this.consultaDAO = consultaDAO;
    }

    public boolean inserir(Consulta consulta) {
        return consultaDAO.inserir(consulta);
    }

    public List<Consulta> listar() {
        return consultaDAO.listar();
    }

    public List<Consulta> listarRecentes(int limite) {
        return consultaDAO.listarRecentes(limite);
    }

    public List<Consulta> listarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        if (dataInicio != null && dataFim != null && dataInicio.isAfter(dataFim)) {
            throw new IllegalArgumentException("A data inicial não pode ser posterior à data final.");
        }

        return consultaDAO.listarPorPeriodo(dataInicio, dataFim);
    }

    public BigDecimal calcularValorTotal(List<Consulta> consultas) {
        if (consultas == null) {
            return BigDecimal.ZERO;
        }

        BigDecimal total = BigDecimal.ZERO;

        for (Consulta consulta : consultas) {
            if (consulta.getValorEstimado() != null) {
                total = total.add(consulta.getValorEstimado());
            }
        }

        return total;
    }

    public int contar() {
        return consultaDAO.contar();
    }

    public Consulta buscarPorId(int id) {
        return consultaDAO.buscarPorId(id);
    }

    public boolean atualizar(Consulta consulta) {
        return consultaDAO.atualizar(consulta);
    }

    public boolean excluir(int id) {
        return consultaDAO.excluir(id);
    }
}
