package es.ubu.gii.ISOAssetManager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Usuario {
	
    @Id @GeneratedValue
    private Long id;

    private String nombre;
    private String password;
    
    public Usuario(String nombre, String password){
    	this.nombre = nombre;
    	this.password = password;
    }
    
    public Usuario() {}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
}

