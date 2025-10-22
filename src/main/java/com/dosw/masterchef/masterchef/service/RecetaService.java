package com.dosw.masterchef.service;

import com.dosw.masterchef.dto.RecetaRequestDTO;
import com.dosw.masterchef.dto.RecetaResponseDTO;
import com.dosw.masterchef.model.Receta;
import com.dosw.masterchef.repository.RecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecetaService {

    @Autowired
    private RecetaRepository repository;

    // Convertir entidad → DTO
    private RecetaResponseDTO toDTO(Receta receta) {
        return new RecetaResponseDTO(
                receta.getId(),
                receta.getTitulo(),
                receta.getIngredientes(),
                receta.getPasos(),
                receta.getNombreChef(),
                receta.getTipoChef(),
                receta.getTemporada()
        );
    }

    // Convertir DTO → entidad
    private Receta toEntity(RecetaRequestDTO dto) {
        return new Receta(
                dto.getTitulo(),
                dto.getIngredientes(),
                dto.getPasos(),
                dto.getNombreChef(),
                dto.getTipoChef(),
                dto.getTemporada()
        );
    }

    public RecetaResponseDTO registrarReceta(RecetaRequestDTO dto) {
        Receta guardada = repository.save(toEntity(dto));
        return toDTO(guardada);
    }

    public List<RecetaResponseDTO> obtenerTodas() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<RecetaResponseDTO> obtenerPorId(String id) {
        return repository.findById(id).map(this::toDTO);
    }

    public List<RecetaResponseDTO> obtenerPorTipoChef(String tipoChef) {
        return repository.findByTipoChef(tipoChef).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<RecetaResponseDTO> obtenerPorTemporada(Integer temporada) {
        return repository.findByTemporada(temporada).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // 🔧 Corregido: ahora usa el método correcto del Repository
    public List<RecetaResponseDTO> buscarPorIngrediente(String ingrediente) {
        return repository.findByIngredientesContainingIgnoreCase(ingrediente)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public void eliminarReceta(String id) {
        repository.deleteById(id);
    }

    public RecetaResponseDTO actualizarReceta(String id, RecetaRequestDTO dto) {
        return repository.findById(id).map(receta -> {
            receta.setTitulo(dto.getTitulo());
            receta.setIngredientes(dto.getIngredientes());
            receta.setPasos(dto.getPasos());
            receta.setNombreChef(dto.getNombreChef());
            receta.setTipoChef(dto.getTipoChef());
            receta.setTemporada(dto.getTemporada());
            return toDTO(repository.save(receta));
        }).orElseThrow(() -> new RuntimeException("Receta no encontrada"));
    }
}
