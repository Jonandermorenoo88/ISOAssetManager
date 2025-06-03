package es.ubu.gii.ISOAssetManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.ubu.gii.ISOAssetManager.model.Empresa;
import es.ubu.gii.ISOAssetManager.repository.EmpresaRepository;

@Controller
public class EmpresaController {

    @Autowired
    private EmpresaRepository empresaRepository;

    @GetMapping("/login/empresas")
    public String mostrarFormularioEmpresa() {
        return "empresa";
    }

    @PostMapping("/login/empresas/nueva")
    public String registrarEmpresa(@RequestParam String nombre,
                                   @RequestParam String sector,
                                   @RequestParam String direccion) {

        Empresa empresa = new Empresa();
        empresa.setNombre(nombre);
        empresa.setSector(sector);
        empresa.setDireccion(direccion);

        empresaRepository.save(empresa);

        return "redirect:/empresas/nueva";
    }
}

