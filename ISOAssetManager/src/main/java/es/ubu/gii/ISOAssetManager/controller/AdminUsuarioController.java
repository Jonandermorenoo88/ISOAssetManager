package es.ubu.gii.ISOAssetManager.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import es.ubu.gii.ISOAssetManager.model.Rol;
import es.ubu.gii.ISOAssetManager.model.Usuario;
import es.ubu.gii.ISOAssetManager.repository.RolRepository;
import es.ubu.gii.ISOAssetManager.repository.UsuarioRepository;

@Controller
@RequestMapping("/usuarios")
public class AdminUsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    // Mostrar todos los usuarios
    @GetMapping
    public String listarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        model.addAttribute("usuarios", usuarios);
        return "modificarusuario";
    }

    // Asignar rol a usuario
    @PostMapping("/asignar-rol")
    public String asignarRol(@RequestParam Long usuarioId, @RequestParam String rolNombre) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Rol rol = rolRepository.findByNombre(rolNombre)
            .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        Set<Rol> roles = new HashSet<>(usuario.getRoles());
        roles.add(rol);
        usuario.setRoles(roles);

        usuarioRepository.save(usuario);

        return "redirect:/usuarios";
    }

    // Eliminar usuario
    @PostMapping("/eliminar")
    public String eliminarUsuario(@RequestParam Long id) {
        usuarioRepository.deleteById(id);
        return "redirect:/usuarios";
    }

    // Eliminar un rol del usuario
    @PostMapping("/eliminar-rol")
    public String eliminarRol(@RequestParam Long usuarioId, @RequestParam String rolNombre) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Rol rol = rolRepository.findByNombre(rolNombre)
            .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        usuario.getRoles().remove(rol);
        usuarioRepository.save(usuario);

        return "redirect:/usuarios";
    }
}
