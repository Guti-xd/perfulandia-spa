package com.cliente.controllers;

import com.cliente.models.HistorialCompra;
import com.cliente.service.HistorialCompraService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Indica que esta clase responde a peticiones REST
@RequestMapping("/api/v1/historial_compra") // Ruta base para este controlador
public class HistorialCompraController {

    @Autowired // Inyección del servicio
    private HistorialCompraService historialcompraService;

    // Obtener todos (GET)
    @GetMapping
    public ResponseEntity<List<HistorialCompra>> getAll() {
        return ResponseEntity.ok(historialcompraService.getAll());
    }

    // Obtener por ID (GET)
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        HistorialCompra historialcompra = historialcompraService.getById(id);
        if (historialcompra != null) {
            return ResponseEntity.ok(historialcompra);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("historial de compra no encontrada");
        }
    }

    // Crear   (POST)
    @PostMapping
    public ResponseEntity<?> add(@RequestBody HistorialCompra historialcompra) {
        HistorialCompra nueva = historialcompraService.add(historialcompra);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    // Actualizar existente (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody HistorialCompra direccion) {
        HistorialCompra actualizada = historialcompraService.update(id, direccion);
        if (actualizada != null) {
            return ResponseEntity.ok(actualizada);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("historial de compra no encontrada");
        }
    }

    // Eliminar  (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        HistorialCompra eliminada = historialcompraService.delete(id);
        if (eliminada != null) {
            return ResponseEntity.ok(eliminada);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("historial de compra no encontrada");
        }
    }
}
