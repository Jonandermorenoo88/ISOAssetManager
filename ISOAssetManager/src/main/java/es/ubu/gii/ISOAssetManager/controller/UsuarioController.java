package es.ubu.gii.ISOAssetManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import es.ubu.gii.ISOAssetManager.repository.UsuarioRepository;

@RestController
public class UsuarioController {
	@Autowired
	private UsuarioRepository usuRepo;
	
	@GetMapping("")
	public String prueba() {
		return "Inicio";
	}

}
