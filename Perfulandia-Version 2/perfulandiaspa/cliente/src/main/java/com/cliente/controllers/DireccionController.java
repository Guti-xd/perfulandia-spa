package com.cliente.controllers;

import com.cliente.models.Direccion;
import com.cliente.service.DireccionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Indica que esta clase responde a peticiones REST
@RequestMapping("/api/v1/direccion") // Ruta base para este controlador
public class DireccionController {

    @Autowired // Inyección del servicio
    private DireccionService direccionService;

    // Obtener todos (GET)
    @GetMapping
    public ResponseEntity<List<Direccion>> getAll() {
        return ResponseEntity.ok(direccionService.getAll());
    }

    // Obtener por ID (GET)
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Direccion direccion = direccionService.getById(id);
        if (direccion != null) {
            return ResponseEntity.ok(direccion);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("direccion no encontrada");
        }
    }

    // Crear   (POST)
    @PostMapping
    public ResponseEntity<?> add(@RequestBody Direccion direccion) {
        Direccion nueva = direccionService.add(direccion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    // Actualizar existente (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Direccion direccion) {
        Direccion actualizada = direccionService.update(id, direccion);
        if (actualizada != null) {
            return ResponseEntity.ok(actualizada);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("direccion no encontrada");
        }
    }

    // Eliminar  (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Direccion eliminada = direccionService.delete(id);
        if (eliminada != null) {
            return ResponseEntity.ok(eliminada);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("direccion no encontrada");
        }
    }
}
