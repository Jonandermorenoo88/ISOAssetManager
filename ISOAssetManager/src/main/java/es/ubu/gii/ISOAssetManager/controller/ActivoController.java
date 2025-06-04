package es.ubu.gii.ISOAssetManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.ubu.gii.ISOAssetManager.model.Activo;
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
    
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoActivo(@RequestParam Long empresaId, Model model) {
        model.addAttribute("empresaId", empresaId);
        return "activos-nuevo";
    }

    @GetMapping("/empresa/{id}")
    public String verActivosDeEmpresa(@PathVariable Long id, Model model) {
        Empresa empresa = empresaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));

        model.addAttribute("empresa", empresa);
        model.addAttribute("activos", activoRepository.findByEmpresaId(id));

        return "activosempresa";
    }

    @PostMapping("/guardar")
    public String guardarActivo(@RequestParam Long empresaId,
                                 @RequestParam String nombre,
                                 @RequestParam String tipo,
                                 @RequestParam String descripcion,
                                 @RequestParam double valor) {

        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new IllegalArgumentException("Empresa no encontrada"));

        Activo activo = new Activo();
        activo.setNombre(nombre);
        activo.setTipo(tipo);
        activo.setDescripcion(descripcion);
        activo.setValor(valor);
        activo.setEmpresa(empresa);

        activoRepository.save(activo);

        return "redirect:/activos/empresa/" + empresaId;
    }
}

