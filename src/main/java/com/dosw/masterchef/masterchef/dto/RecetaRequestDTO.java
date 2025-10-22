package com.dosw.masterchef.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public class RecetaRequestDTO {

    @NotBlank(message = "El título es obligatorio")
    private String titulo;

    @NotEmpty(message = "Debe tener al menos un ingrediente")
    private List<String> ingredientes;

    @NotEmpty(message = "Debe tener al menos un paso de preparación")
    private List<String> pasos;

    @NotBlank(message = "El nombre del chef es obligatorio")
    private String nombreChef;

    @NotBlank(message = "Debe especificar el tipo de chef")
    private String tipoChef;

    private Integer temporada;

    // ✅ Constructor vacío (ya existía implícitamente, pero lo dejamos explícito)
    public RecetaRequestDTO() {}

    // ✅ Constructor completo (lo que usan los tests)
    public RecetaRequestDTO(String titulo, List<String> ingredientes, List<String> pasos,
                            String nombreChef, String tipoChef, Integer temporada) {
        this.titulo = titulo;
        this.ingredientes = ingredientes;
        this.pasos = pasos;
        this.nombreChef = nombreChef;
        this.tipoChef = tipoChef;
        this.temporada = temporada;
    }

    // ✅ Getters y setters
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public List<String> getIngredientes() { return ingredientes; }
    public void setIngredientes(List<String> ingredientes) { this.ingredientes = ingredientes; }

    public List<String> getPasos() { return pasos; }
    public void setPasos(List<String> pasos) { this.pasos = pasos; }

    public String getNombreChef() { return nombreChef; }
    public void setNombreChef(String nombreChef) { this.nombreChef = nombreChef; }

    public String getTipoChef() { return tipoChef; }
    public void setTipoChef(String tipoChef) { this.tipoChef = tipoChef; }

    public Integer getTemporada() { return temporada; }
    public void setTemporada(Integer temporada) { this.temporada = temporada; }
}
