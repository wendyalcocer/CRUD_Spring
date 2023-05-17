package com.sinfloo.demo.controler;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



import com.sinfloo.demo.interfaceService.IpersonaService;
import com.sinfloo.demo.model.Persona;


import jakarta.validation.Valid;

@Controller
@RequestMapping
public class Controlador {
	
	
	
	@Autowired
	private IpersonaService service;

	@GetMapping("/listar")
	public String listar(Model model) {
		List<Persona> personas = service.listar();
		model.addAttribute("personas", personas);
		return "index";
	}
	
	@GetMapping("/new")
	public String agregar(Model model) {
		model.addAttribute("persona", new Persona());
		return "form";
	}
	
	
	@PostMapping("/save")
	public String save( Persona persona)
	{
		System.out.println("obo");
		service.save(persona);
		
		return "redirect:/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable int id,Model model) {
		Optional<Persona>persona=service.listarId(id);
		System.out.println("se edito");
		model.addAttribute("persona",persona);
		return "form";
	}
	@GetMapping("/eliminar/{id}")
	public String delete(Model model, @PathVariable int id) {
		service.delete(id);
		System.out.println("se elimino");
		return "redirect:/listar";
	}
	
	


		
	
}

