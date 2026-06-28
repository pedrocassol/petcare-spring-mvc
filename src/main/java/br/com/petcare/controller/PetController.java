package br.com.petcare.controller;

import br.com.petcare.model.Pet;
import br.com.petcare.service.PetService;
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
public class PetController {

    private final PetService petService = new PetService();
    private final ProprietarioService proprietarioService = new ProprietarioService();

    @GetMapping("/pets")
    public String exibirCadastro(HttpSession session, Model model) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }

        if (!model.containsAttribute("pet")) {
            model.addAttribute("pet", new Pet());
        }

        carregarProprietarios(model);
        return "pets";
    }

    @PostMapping("/pet")
    public String cadastrar(@Valid @ModelAttribute("pet") Pet pet,
                            BindingResult bindingResult,
                            HttpSession session,
                            Model model,
                            RedirectAttributes redirectAttributes) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("erros", bindingResult.getAllErrors());
            carregarProprietarios(model);
            return "pets";
        }

        try {
            if (!petService.inserir(pet)) {
                model.addAttribute("erro", "Não foi possível cadastrar o pet.");
                carregarProprietarios(model);
                return "pets";
            }

            redirectAttributes.addFlashAttribute("sucesso", "Pet cadastrado com sucesso.");
            return "redirect:/listarPets";
        } catch (IllegalStateException e) {
            model.addAttribute("erro", e.getMessage());
        } catch (RuntimeException e) {
            model.addAttribute("erro", "Não foi possível acessar o banco de dados.");
        }

        carregarProprietarios(model);
        return "pets";
    }

    @GetMapping("/listarPets")
    public String listar(HttpSession session, Model model) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }

        try {
            model.addAttribute("pets", petService.listar());
        } catch (RuntimeException e) {
            model.addAttribute("pets", Collections.emptyList());
            model.addAttribute("erro", "Não foi possível carregar os pets.");
        }

        return "listarPets";
    }

    @GetMapping("/editarPet")
    public String exibirEdicao(@RequestParam int id,
                               HttpSession session,
                               Model model,
                               RedirectAttributes redirectAttributes) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }

        try {
            Pet pet = petService.buscarPorId(id);

            if (pet == null) {
                redirectAttributes.addFlashAttribute("erro", "Pet não encontrado.");
                return "redirect:/listarPets";
            }

            model.addAttribute("pet", pet);
            carregarProprietarios(model);
            return "editarPet";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("erro", "Não foi possível carregar o pet.");
            return "redirect:/listarPets";
        }
    }

    @PostMapping("/editarPet")
    public String atualizar(@Valid @ModelAttribute("pet") Pet pet,
                            BindingResult bindingResult,
                            HttpSession session,
                            Model model,
                            RedirectAttributes redirectAttributes) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("erros", bindingResult.getAllErrors());
            carregarProprietarios(model);
            return "editarPet";
        }

        try {
            if (!petService.atualizar(pet)) {
                model.addAttribute("erro", "Pet não encontrado.");
                carregarProprietarios(model);
                return "editarPet";
            }

            redirectAttributes.addFlashAttribute("sucesso", "Pet atualizado com sucesso.");
            return "redirect:/listarPets";
        } catch (IllegalStateException e) {
            model.addAttribute("erro", e.getMessage());
        } catch (RuntimeException e) {
            model.addAttribute("erro", "Não foi possível atualizar o pet.");
        }

        carregarProprietarios(model);
        return "editarPet";
    }

    @PostMapping("/excluirPet")
    public String excluir(@RequestParam int id,
                          HttpSession session,
                          RedirectAttributes redirectAttributes) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/login";
        }

        try {
            if (petService.excluir(id)) {
                redirectAttributes.addFlashAttribute("sucesso", "Pet excluído com sucesso.");
            } else {
                redirectAttributes.addFlashAttribute("erro", "Pet não encontrado.");
            }
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("erro", e.getMessage());
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("erro", "Não foi possível excluir o pet.");
        }

        return "redirect:/listarPets";
    }

    private void carregarProprietarios(Model model) {
        try {
            model.addAttribute("proprietarios", proprietarioService.listar());
        } catch (RuntimeException e) {
            model.addAttribute("proprietarios", Collections.emptyList());

            if (!model.containsAttribute("erro")) {
                model.addAttribute("erro", "Não foi possível carregar os proprietários.");
            }
        }
    }
}
