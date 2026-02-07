package com.literalura.catalogo_libros;

import lombok.Data;
import tools.jackson.databind.JsonNode;

import java.util.List;

@Data
public class Libro {
    private String titulo;
    private List<String> autores;
    private String idioma;
    private int descargas;

    public void setDescargas(int descargas) {
        this.descargas = descargas;
    }

    public int getDescargas() {
        return descargas;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setAutores(List<String> autores) {
        this.autores = autores;
    }

    public List<String> getAutores() {
        return autores;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }
}
