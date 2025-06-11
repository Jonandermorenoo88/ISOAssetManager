package es.ubu.gii.ISOAssetManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import es.ubu.gii.ISOAssetManager.model.Usuario;
import es.ubu.gii.ISOAssetManager.repository.UsuarioRepository;


@Controller
public class PanelController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/panel")
    public String panel(Model model, org.springframework.security.core.Authentication authentication) {
        String email = authentication.getName();  // Spring Security ya lo sabe
        Usuario usuario = usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        boolean esAdmin = usuario.getRoles().stream()
            .anyMatch(r -> r.getNombre().equals("ADMIN"));

        if (!esAdmin) {
            return "redirect:/empresas";
        }

        model.addAttribute("usuario", usuario);
        model.addAttribute("esAdmin", true);
        return "panel";
    }


    
    
}
