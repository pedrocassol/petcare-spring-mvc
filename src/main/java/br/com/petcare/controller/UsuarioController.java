package br.com.petcare.controller;

import br.com.petcare.model.Usuario;
import br.com.petcare.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UsuarioController {

    private final UsuarioService usuarioService = new UsuarioService();

    @GetMapping("/cadastro")
    public String exibirCadastro(Model model) {
        if (!model.containsAttribute("usuario")) {
            model.addAttribute("usuario", new Usuario());
        }

        return "cadastro";
    }

    @PostMapping("/usuario")
    public String cadastrar(@Valid @ModelAttribute("usuario") Usuario usuario,
                            BindingResult bindingResult,
                            Model model,
                            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("erros", bindingResult.getAllErrors());
            return "cadastro";
        }

        try {
            if (!usuarioService.inserir(usuario)) {
                model.addAttribute("erro", "Não foi possível cadastrar o usuário.");
                return "cadastro";
            }

            redirectAttributes.addFlashAttribute("sucesso", "Cadastro realizado com sucesso. Faça seu login.");
            return "redirect:/login";
        } catch (IllegalStateException e) {
            model.addAttribute("erro", e.getMessage());
            return "cadastro";
        } catch (RuntimeException e) {
            model.addAttribute("erro", "Não foi possível acessar o banco de dados.");
            return "cadastro";
        }
    }
}
