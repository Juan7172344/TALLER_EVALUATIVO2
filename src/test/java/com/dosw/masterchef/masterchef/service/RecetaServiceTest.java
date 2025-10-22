package com.dosw.masterchef.service;

import com.dosw.masterchef.dto.RecetaRequestDTO;
import com.dosw.masterchef.dto.RecetaResponseDTO;
import com.dosw.masterchef.model.Receta;
import com.dosw.masterchef.repository.RecetaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecetaServiceTest {

    @Mock
    private RecetaRepository repository;

    @InjectMocks
    private RecetaService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // ✅ 1. Validar que se pueda registrar una receta
    @Test
    void registrarReceta_DeberiaGuardarYDevolverDTO() {
        // Arrange
        RecetaRequestDTO dto = new RecetaRequestDTO(
                "Arroz con pollo",
                List.of("Arroz", "Pollo", "Verduras"),
                List.of("Cocinar el arroz", "Saltear el pollo", "Mezclar todo"),
                "Juan Pérez",
                "televidente",
                null
        );

        Receta recetaGuardada = new Receta();
        recetaGuardada.setId("1");
        recetaGuardada.setTitulo(dto.getTitulo());
        recetaGuardada.setNombreChef(dto.getNombreChef());
        recetaGuardada.setTipoChef(dto.getTipoChef());
        recetaGuardada.setIngredientes(dto.getIngredientes());
        recetaGuardada.setPasos(dto.getPasos());

        when(repository.save(any(Receta.class))).thenReturn(recetaGuardada);

        // Act
        RecetaResponseDTO respuesta = service.registrarReceta(dto);

        // Assert
        assertNotNull(respuesta);
        assertEquals("Arroz con pollo", respuesta.getTitulo());
        verify(repository, times(1)).save(any(Receta.class));
    }

    // ✅ 2. Validar que la búsqueda por ingrediente devuelva resultados correctos
    @Test
    void buscarPorIngrediente_DeberiaDevolverListaCorrecta() {
        // Arrange
        Receta receta = new Receta();
        receta.setId("1");
        receta.setTitulo("Tacos de carne");
        receta.setIngredientes(List.of("Carne", "Tortilla", "Cebolla"));

        when(repository.findByIngredientesContainingIgnoreCase("carne"))
                .thenReturn(List.of(receta));

        // Act
        List<RecetaResponseDTO> resultado = service.buscarPorIngrediente("carne");

        // Assert
        assertFalse(resultado.isEmpty());
        assertEquals("Tacos de carne", resultado.get(0).getTitulo());
        verify(repository, times(1)).findByIngredientesContainingIgnoreCase("carne");
    }

    // ✅ 3. Validar que se devuelva error si se consulta una receta inexistente
    @Test
    void obtenerPorId_CuandoNoExiste_DeberiaDevolverVacio() {
        // Arrange
        String id = "999";
        when(repository.findById(id)).thenReturn(Optional.empty());

        // Act
        Optional<RecetaResponseDTO> resultado = service.obtenerPorId(id);

        // Assert
        assertTrue(resultado.isEmpty());
        verify(repository, times(1)).findById(id);
    }
}
