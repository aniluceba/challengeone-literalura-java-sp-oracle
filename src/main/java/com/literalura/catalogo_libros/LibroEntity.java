package com.literalura.catalogo_libros;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LibroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String autor;
    private String idioma;
    private int descargas;

    public LibroEntity() {}
    public LibroEntity(String titulo, String autor, String idioma, int descargas) {
        this.titulo = titulo;
        this.autor = autor;
        this.idioma = idioma;
        this.descargas = descargas;

    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String Titulo) {
        this.titulo = titulo;
    }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getIdioma() { return idioma; }
    public void setIdioma(String idioma) { this.idioma = idioma; }

    public int getDescargas() { return descargas; }
    public void setDescargas(int descargas) { this.descargas = descargas; }

}

