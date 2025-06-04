package es.ubu.gii.ISOAssetManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.ubu.gii.ISOAssetManager.model.Empresa;
import es.ubu.gii.ISOAssetManager.repository.EmpresaRepository;

@Controller
public class EmpresaController {

    @Autowired
    private EmpresaRepository empresaRepository;

    @GetMapping("/empresas")
    public String mostrarEmpresas(Model model) {
        List<Empresa> empresas = empresaRepository.findAll();
        model.addAttribute("empresas", empresas);
        return "empresa";
    }

    @GetMapping("/empresas/nueva")
    public String mostrarFormularioEmpresa() {
        return "añadirempresa"; //
    }
    
    @PostMapping("/empresas/eliminar/{id}")
    public String eliminarEmpresa(@PathVariable Long id) {
        empresaRepository.deleteById(id);
        return "redirect:/empresas";
    }


    @PostMapping("/empresas/guardar")
    public String registrarEmpresa(@RequestParam String nombre,
                                   @RequestParam String sector,
                                   @RequestParam String direccion) {

        Empresa empresa = new Empresa();
        empresa.setNombre(nombre);
        empresa.setSector(sector);
        empresa.setDireccion(direccion);

        empresaRepository.save(empresa);

        return "redirect:/empresas";
    }
}


