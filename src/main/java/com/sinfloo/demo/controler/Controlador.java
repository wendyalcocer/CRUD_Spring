package com.sinfloo.demo.controler;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sinfloo.demo.interfaceService.IpersonaService;
import com.sinfloo.demo.model.Persona;

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
    public String save(Persona persona) {
        System.out.println("obo");
        service.save(persona);

        return "redirect:/listar";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {
        Optional<Persona> persona = service.listarId(id);
        System.out.println("se edito");
        model.addAttribute("persona", persona);
        return "form";
    }

    @GetMapping("/eliminar/{id}")
    public String delete(Model model, @PathVariable int id) {
        service.delete(id);
        System.out.println("se elimino");
        return "redirect:/listar";
    }

    @GetMapping("/descargar/{id}")
    public ResponseEntity<InputStreamResource> descargarArchivo(@PathVariable int id) throws IOException {
        Optional<Persona> personaOptional = service.listarId(id);

        if (personaOptional.isPresent()) {
            Persona persona = personaOptional.get();
            File archivo = persona.getArchivo();

            // Abre el archivo como un InputStream
            InputStream inputStream = new FileInputStream(archivo);

            // Crea un objeto InputStreamResource para enviar el archivo como respuesta
            InputStreamResource resource = new InputStreamResource(inputStream);

            // Configura los encabezados de la respuesta
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + archivo.getName());

            // Devuelve una respuesta con el archivo y los encabezados
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        }

        // Si no se encuentra la persona, devuelve una respuesta de error
        return ResponseEntity.notFound().build();
    }





}

