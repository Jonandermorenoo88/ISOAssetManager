package es.ubu.gii.ISOAssetManager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsuarioController {

    @GetMapping("/")
    public String mostrarInicio(Model model) {
        model.addAttribute("usuario", "Jon Ander");
        return "inicio"; 
    }
}

