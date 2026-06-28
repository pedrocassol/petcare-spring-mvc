package br.com.petcare.controller;

import br.com.petcare.model.Consulta;
import br.com.petcare.service.ConsultaService;
import br.com.petcare.service.PetService;
import br.com.petcare.service.ProprietarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.List;

@Controller
public class RelatorioController {

    private final PetService petService = new PetService();
    private final ProprietarioService proprietarioService = new ProprietarioService();
    private final ConsultaService consultaService = new ConsultaService();

    @GetMapping("/relatorios")
    public String exibirRelatorio(@RequestParam(required = false) String dataInicio,
                                  @RequestParam(required = false) String dataFim,
                                  HttpSession session,
                                  Model model) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }

        model.addAttribute("dataInicio", dataInicio);
        model.addAttribute("dataFim", dataFim);
        model.addAttribute("totalPets", 0);
        model.addAttribute("totalProprietarios", 0);
        model.addAttribute("totalConsultas", 0);
        model.addAttribute("valorTotal", BigDecimal.ZERO);
        model.addAttribute("consultas", Collections.emptyList());

        try {
            model.addAttribute("totalPets", petService.contar());
            model.addAttribute("totalProprietarios", proprietarioService.contar());

            LocalDate inicio = converterData(dataInicio);
            LocalDate fim = converterData(dataFim);
            List<Consulta> consultas = consultaService.listarPorPeriodo(inicio, fim);

            model.addAttribute("consultas", consultas);
            model.addAttribute("totalConsultas", consultas.size());
            model.addAttribute("valorTotal", consultaService.calcularValorTotal(consultas));
        } catch (IllegalArgumentException e) {
            model.addAttribute("erro", e.getMessage());
        } catch (RuntimeException e) {
            model.addAttribute("erro", "Não foi possível carregar o relatório.");
        }

        return "relatorios";
    }

    private LocalDate converterData(String data) {
        if (data == null || data.isBlank()) {
            return null;
        }

        try {
            return LocalDate.parse(data);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Informe datas válidas.", e);
        }
    }
}
