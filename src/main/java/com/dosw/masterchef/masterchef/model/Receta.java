package com.dosw.masterchef.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "recetas")
public class Receta {
    @Id
    private String id;
    private String titulo;
    private List<String> ingredientes;
    private List<String> pasos;
    private String nombreChef;
    private String tipoChef; // participante, jurado, televidente
    private Integer temporada; // solo si es participante

    public Receta() {}

    public Receta(String titulo, List<String> ingredientes, List<String> pasos,
                  String nombreChef, String tipoChef, Integer temporada) {
        this.titulo = titulo;
        this.ingredientes = ingredientes;
        this.pasos = pasos;
        this.nombreChef = nombreChef;
        this.tipoChef = tipoChef;
        this.temporada = temporada;
    }

    // Getters y setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

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
