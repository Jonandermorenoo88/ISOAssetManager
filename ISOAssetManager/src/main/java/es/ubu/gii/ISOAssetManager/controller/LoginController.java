package es.ubu.gii.ISOAssetManager.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.ubu.gii.ISOAssetManager.model.Usuario;
import es.ubu.gii.ISOAssetManager.repository.UsuarioRepository;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam String username,
                                 @RequestParam String password,
                                 RedirectAttributes redirectAttributes,
                                 Model model) {

        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(username);

        if (usuarioOpt.isPresent() && usuarioOpt.get().getPassword().equals(password)) {
            Usuario usuario = usuarioOpt.get();
            boolean esAdmin = usuario.getRoles().stream()
                .anyMatch(r -> r.getNombre().equals("ADMIN"));

            redirectAttributes.addAttribute("email", username);

            if (esAdmin) {
                return "redirect:/panel";
            } else {
                return "redirect:/empresas";
            }
        }

        model.addAttribute("error", "Email o contraseña incorrectos");
        return "login";
    }

}

