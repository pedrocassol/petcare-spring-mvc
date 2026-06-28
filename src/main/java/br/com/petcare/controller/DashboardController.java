package br.com.petcare.controller;

import br.com.petcare.service.ConsultaService;
import br.com.petcare.service.PetService;
import br.com.petcare.service.ProprietarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;

@Controller
public class DashboardController {

    private final PetService petService = new PetService();
    private final ProprietarioService proprietarioService = new ProprietarioService();
    private final ConsultaService consultaService = new ConsultaService();

    @GetMapping("/dashboard")
    public String exibirDashboard(HttpSession session, Model model) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }

        try {
            model.addAttribute("totalPets", petService.contar());
            model.addAttribute("totalProprietarios", proprietarioService.contar());
            model.addAttribute("totalConsultas", consultaService.contar());
            model.addAttribute("consultasRecentes", consultaService.listarRecentes(5));
        } catch (RuntimeException e) {
            model.addAttribute("totalPets", 0);
            model.addAttribute("totalProprietarios", 0);
            model.addAttribute("totalConsultas", 0);
            model.addAttribute("consultasRecentes", Collections.emptyList());
            model.addAttribute("erro", "Não foi possível carregar os dados do dashboard.");
        }

        return "dashboard";
    }
}
