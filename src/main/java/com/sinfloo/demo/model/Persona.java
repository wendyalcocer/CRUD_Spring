package com.sinfloo.demo.model;

import java.io.File;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="persona")
public class Persona {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private String name;
private String telefono;
private File archivo;
public Persona(){ 
	
	
}

public Persona(int id, String name, String telefono , File archivo) {
	super();
	this.id = id;
	this.name = name;
	this.telefono = telefono;
	this.archivo= archivo;
	
}



public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getTelefono() {
	return telefono;
}

public void setTelefono(String telefono) {
	this.telefono = telefono;
}

public File getArchivo() {
	return archivo;
}

public void setArchivo(File archivo) {
	this.archivo = archivo;
}

}
