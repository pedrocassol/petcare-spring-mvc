package br.com.petcare.controller;

import br.com.petcare.model.Usuario;
import br.com.petcare.service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final LoginService loginService = new LoginService();

    @GetMapping({"/", "/login"})
    public String exibirLogin(HttpSession session) {
        if (session.getAttribute("usuario") != null) {
            return "redirect:/dashboard";
        }

        return "login";
    }

    @PostMapping("/login")
    public String autenticar(@RequestParam(required = false) String email,
                             @RequestParam(required = false) String senha,
                             HttpSession session,
                             Model model) {
        try {
            Usuario usuario = loginService.autenticar(email, senha);

            if (usuario == null) {
                model.addAttribute("erro", "E-mail ou senha inválidos.");
                model.addAttribute("email", email);
                return "login";
            }

            session.setAttribute("usuario", usuario);
            return "redirect:/dashboard";
        } catch (RuntimeException e) {
            model.addAttribute("erro", "Não foi possível acessar o banco de dados.");
            model.addAttribute("email", email);
            return "login";
        }
    }
}
