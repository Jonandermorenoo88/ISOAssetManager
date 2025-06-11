package es.ubu.gii.ISOAssetManager;

import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import es.ubu.gii.ISOAssetManager.model.Rol;
import es.ubu.gii.ISOAssetManager.model.Usuario;
import es.ubu.gii.ISOAssetManager.repository.RolRepository;
import es.ubu.gii.ISOAssetManager.repository.UsuarioRepository;

@SpringBootApplication
public class IsoAssetManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(IsoAssetManagerApplication.class, args);
	}

	@Bean
	CommandLineRunner initData(UsuarioRepository usuarioRepo, RolRepository rolRepo, PasswordEncoder passwordEncoder) {
	    return args -> {
	        Rol rolAdmin = rolRepo.findByNombre("ADMIN")
	            .orElseGet(() -> rolRepo.save(new Rol("ADMIN")));

	        if (usuarioRepo.findByEmail("admin@empresa.com").isEmpty()) {
	            Usuario admin = new Usuario(
	                "Admin",
	                "admin@empresa.com",
	                passwordEncoder.encode("adminpass")
	            );
	            admin.setRoles(Set.of(rolAdmin));
	            usuarioRepo.save(admin);
	            System.out.println(">>> ADMIN creado con contraseña encriptada.");
	        }
	    };
	}
	
	@Bean
	CommandLineRunner initRoles(RolRepository rolRepo) {
	    return args -> {
	        if (rolRepo.findByNombre("AUDITOR").isEmpty()) {
	            rolRepo.save(new Rol("AUDITOR"));
	            System.out.println(">>> Rol AUDITOR creado.");
	        }
	    };
	}


}

