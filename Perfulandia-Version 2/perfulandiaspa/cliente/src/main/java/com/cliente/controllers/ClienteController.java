package com.cliente.controllers;

import com.cliente.models.Cliente;
import com.cliente.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Indica que esta clase responde a peticiones REST
@RequestMapping("/api/v1/cliente") // Ruta base para este controlador
public class ClienteController {

    @Autowired // Inyección del servicio de cliente
    private ClienteService clienteService;

    // Obtener todas las clientes (GET)
    @GetMapping
    public ResponseEntity<List<Cliente>> getAll() {
        return ResponseEntity.ok(clienteService.getAll());
    }

    // Obtener cliente por ID (GET)
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Cliente cliente = clienteService.getById(id);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
        }
    }

    // Crear nuevo cliente (POST)
    @PostMapping
    public ResponseEntity<?> add(@RequestBody Cliente cliente) {
        Cliente nueva = clienteService.add(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    // Actualizar cliente existente (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Cliente cliente) {
        Cliente actualizada = clienteService.update(id, cliente);
        if (actualizada != null) {
            return ResponseEntity.ok(actualizada);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
        }
    }

    // Eliminar cliente (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Cliente eliminada = clienteService.delete(id);
        if (eliminada != null) {
            return ResponseEntity.ok(eliminada);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
        }
    }
}
