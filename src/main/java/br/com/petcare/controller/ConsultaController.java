package br.com.petcare.controller;

import br.com.petcare.model.Consulta;
import br.com.petcare.service.ConsultaService;
import br.com.petcare.service.PetService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;

@Controller
public class ConsultaController {

    private final ConsultaService consultaService = new ConsultaService();
    private final PetService petService = new PetService();

    @GetMapping("/consultas")
    public String exibirCadastro(HttpSession session, Model model) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }

        if (!model.containsAttribute("consulta")) {
            model.addAttribute("consulta", new Consulta());
        }

        carregarPets(model);
        return "consultas";
    }

    @PostMapping("/consulta")
    public String cadastrar(@Valid @ModelAttribute("consulta") Consulta consulta,
                            BindingResult bindingResult,
                            HttpSession session,
                            Model model,
                            RedirectAttributes redirectAttributes) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("erros", bindingResult.getAllErrors());
            carregarPets(model);
            return "consultas";
        }

        try {
            if (!consultaService.inserir(consulta)) {
                model.addAttribute("erro", "Não foi possível cadastrar a consulta.");
                carregarPets(model);
                return "consultas";
            }

            redirectAttributes.addFlashAttribute("sucesso", "Consulta cadastrada com sucesso.");
            return "redirect:/listarConsultas";
        } catch (IllegalStateException | IllegalArgumentException e) {
            model.addAttribute("erro", e.getMessage());
        } catch (RuntimeException e) {
            model.addAttribute("erro", "Não foi possível acessar o banco de dados.");
        }

        carregarPets(model);
        return "consultas";
    }

    @GetMapping("/listarConsultas")
    public String listar(HttpSession session, Model model) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }

        try {
            model.addAttribute("consultas", consultaService.listar());
        } catch (RuntimeException e) {
            model.addAttribute("consultas", Collections.emptyList());
            model.addAttribute("erro", "Não foi possível carregar as consultas.");
        }

        return "listarConsultas";
    }

    @GetMapping("/editarConsulta")
    public String exibirEdicao(@RequestParam int id,
                               HttpSession session,
                               Model model,
                               RedirectAttributes redirectAttributes) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }

        try {
            Consulta consulta = consultaService.buscarPorId(id);

            if (consulta == null) {
                redirectAttributes.addFlashAttribute("erro", "Consulta não encontrada.");
                return "redirect:/listarConsultas";
            }

            model.addAttribute("consulta", consulta);
            carregarPets(model);
            return "editarConsulta";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("erro", "Não foi possível carregar a consulta.");
            return "redirect:/listarConsultas";
        }
    }

    @PostMapping("/editarConsulta")
    public String atualizar(@Valid @ModelAttribute("consulta") Consulta consulta,
                            BindingResult bindingResult,
                            HttpSession session,
                            Model model,
                            RedirectAttributes redirectAttributes) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("erros", bindingResult.getAllErrors());
            carregarPets(model);
            return "editarConsulta";
        }

        try {
            if (!consultaService.atualizar(consulta)) {
                model.addAttribute("erro", "Consulta não encontrada.");
                carregarPets(model);
                return "editarConsulta";
            }

            redirectAttributes.addFlashAttribute("sucesso", "Consulta atualizada com sucesso.");
            return "redirect:/listarConsultas";
        } catch (IllegalStateException | IllegalArgumentException e) {
            model.addAttribute("erro", e.getMessage());
        } catch (RuntimeException e) {
            model.addAttribute("erro", "Não foi possível atualizar a consulta.");
        }

        carregarPets(model);
        return "editarConsulta";
    }

    @PostMapping("/excluirConsulta")
    public String excluir(@RequestParam int id,
                          HttpSession session,
                          RedirectAttributes redirectAttributes) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }

        try {
            if (consultaService.excluir(id)) {
                redirectAttributes.addFlashAttribute("sucesso", "Consulta excluída com sucesso.");
            } else {
                redirectAttributes.addFlashAttribute("erro", "Consulta não encontrada.");
            }
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("erro", "Não foi possível excluir a consulta.");
        }

        return "redirect:/listarConsultas";
    }

    private void carregarPets(Model model) {
        try {
            model.addAttribute("pets", petService.listar());
        } catch (RuntimeException e) {
            model.addAttribute("pets", Collections.emptyList());

            if (!model.containsAttribute("erro")) {
                model.addAttribute("erro", "Não foi possível carregar os pets.");
            }
        }
    }
}
