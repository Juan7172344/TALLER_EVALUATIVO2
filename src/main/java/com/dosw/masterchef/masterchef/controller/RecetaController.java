package com.dosw.masterchef.masterchef.controller;

import com.dosw.masterchef.dto.RecetaRequestDTO;
import com.dosw.masterchef.dto.RecetaResponseDTO;
import com.dosw.masterchef.service.RecetaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/recetas")
public class RecetaController {

    @Autowired
    private RecetaService service;

    // ✅ Registrar una receta
    @PostMapping
    public ResponseEntity<RecetaResponseDTO> crearReceta(@Valid @RequestBody RecetaRequestDTO dto) {
        return ResponseEntity.ok(service.registrarReceta(dto));
    }

    // ✅ Obtener todas las recetas
    @GetMapping
    public ResponseEntity<List<RecetaResponseDTO>> obtenerTodas() {
        return ResponseEntity.ok(service.obtenerTodas());
    }

    // ✅ Obtener receta por ID (corregido)
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable String id) {
        return service.obtenerPorId(id)
                .<ResponseEntity<?>>map(receta -> ResponseEntity.ok(receta))
                .orElseGet(() -> ResponseEntity.status(404).body("Receta no encontrada"));
    }

    // ✅ Obtener recetas por tipo de chef (participante, televidente, jurado)
    @GetMapping("/tipo/{tipoChef}")
    public ResponseEntity<List<RecetaResponseDTO>> obtenerPorTipoChef(@PathVariable String tipoChef) {
        return ResponseEntity.ok(service.obtenerPorTipoChef(tipoChef));
    }

    // ✅ Obtener recetas por temporada
    @GetMapping("/temporada/{temporada}")
    public ResponseEntity<List<RecetaResponseDTO>> obtenerPorTemporada(@PathVariable Integer temporada) {
        return ResponseEntity.ok(service.obtenerPorTemporada(temporada));
    }

    // ✅ Buscar recetas por ingrediente
    @GetMapping("/ingrediente/{ingrediente}")
    public ResponseEntity<List<RecetaResponseDTO>> buscarPorIngrediente(@PathVariable String ingrediente) {
        return ResponseEntity.ok(service.buscarPorIngrediente(ingrediente));
    }

    // ✅ Eliminar receta
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable String id) {
        service.eliminarReceta(id);
        return ResponseEntity.ok("Receta eliminada correctamente");
    }

    // ✅ Actualizar receta
    @PutMapping("/{id}")
    public ResponseEntity<RecetaResponseDTO> actualizar(@PathVariable String id,
                                                        @Valid @RequestBody RecetaRequestDTO dto) {
        return ResponseEntity.ok(service.actualizarReceta(id, dto));
    }
}
