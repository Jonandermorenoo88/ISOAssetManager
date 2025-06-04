package es.ubu.gii.ISOAssetManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import es.ubu.gii.ISOAssetManager.model.Empresa;
import es.ubu.gii.ISOAssetManager.repository.ActivoRepository;
import es.ubu.gii.ISOAssetManager.repository.EmpresaRepository;

@Controller
@RequestMapping("/activos")
public class ActivoController {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private ActivoRepository activoRepository;

    @GetMapping("/empresa/{id}")
    public String verActivosDeEmpresa(@PathVariable Long id, Model model) {
        Empresa empresa = empresaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));

        model.addAttribute("empresa", empresa);
        model.addAttribute("activos", activoRepository.findByEmpresaId(id));

        return "activosempresa";
    }
}

