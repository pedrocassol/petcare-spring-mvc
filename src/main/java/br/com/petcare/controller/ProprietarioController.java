package br.com.petcare.controller;

import br.com.petcare.model.Proprietario;
import br.com.petcare.service.ProprietarioService;
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
public class ProprietarioController {

    private final ProprietarioService proprietarioService = new ProprietarioService();

    @GetMapping("/proprietarios")
    public String exibirCadastro(HttpSession session, Model model) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }

        if (!model.containsAttribute("proprietario")) {
            model.addAttribute("proprietario", new Proprietario());
        }

        return "proprietarios";
    }

    @PostMapping("/proprietario")
    public String cadastrar(@Valid @ModelAttribute("proprietario") Proprietario proprietario,
                            BindingResult bindingResult,
                            HttpSession session,
                            Model model,
                            RedirectAttributes redirectAttributes) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("erros", bindingResult.getAllErrors());
            return "proprietarios";
        }

        try {
            if (!proprietarioService.inserir(proprietario)) {
                model.addAttribute("erro", "Não foi possível cadastrar o proprietário.");
                return "proprietarios";
            }

            redirectAttributes.addFlashAttribute("sucesso", "Proprietário cadastrado com sucesso.");
            return "redirect:/listarProprietarios";
        } catch (RuntimeException e) {
            model.addAttribute("erro", "Não foi possível acessar o banco de dados.");
            return "proprietarios";
        }
    }

    @GetMapping("/listarProprietarios")
    public String listar(HttpSession session, Model model) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }

        try {
            model.addAttribute("proprietarios", proprietarioService.listar());
        } catch (RuntimeException e) {
            model.addAttribute("proprietarios", Collections.emptyList());
            model.addAttribute("erro", "Não foi possível carregar os proprietários.");
        }

        return "listarProprietarios";
    }

    @GetMapping("/editarProprietario")
    public String exibirEdicao(@RequestParam int id,
                               HttpSession session,
                               RedirectAttributes redirectAttributes,
                               Model model) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }

        try {
            Proprietario proprietario = proprietarioService.buscarPorId(id);

            if (proprietario == null) {
                redirectAttributes.addFlashAttribute("erro", "Proprietário não encontrado.");
                return "redirect:/listarProprietarios";
            }

            model.addAttribute("proprietario", proprietario);
            return "editarProprietario";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("erro", "Não foi possível carregar o proprietário.");
            return "redirect:/listarProprietarios";
        }
    }

    @PostMapping("/editarProprietario")
    public String atualizar(@Valid @ModelAttribute("proprietario") Proprietario proprietario,
                            BindingResult bindingResult,
                            HttpSession session,
                            Model model,
                            RedirectAttributes redirectAttributes) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("erros", bindingResult.getAllErrors());
            return "editarProprietario";
        }

        try {
            if (!proprietarioService.atualizar(proprietario)) {
                model.addAttribute("erro", "Proprietário não encontrado.");
                return "editarProprietario";
            }

            redirectAttributes.addFlashAttribute("sucesso", "Proprietário atualizado com sucesso.");
            return "redirect:/listarProprietarios";
        } catch (RuntimeException e) {
            model.addAttribute("erro", "Não foi possível atualizar o proprietário.");
            return "editarProprietario";
        }
    }

    @PostMapping("/excluirProprietario")
    public String excluir(@RequestParam int id,
                          HttpSession session,
                          RedirectAttributes redirectAttributes) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }

        try {
            if (proprietarioService.excluir(id)) {
                redirectAttributes.addFlashAttribute("sucesso", "Proprietário excluído com sucesso.");
            } else {
                redirectAttributes.addFlashAttribute("erro", "Proprietário não encontrado.");
            }
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("erro", e.getMessage());
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("erro", "Não foi possível excluir o proprietário.");
        }

        return "redirect:/listarProprietarios";
    }
}
