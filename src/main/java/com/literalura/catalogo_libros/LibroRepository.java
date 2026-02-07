package com.literalura.catalogo_libros;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibroRepository extends JpaRepository<LibroEntity, Long> {
    List<LibroEntity> findByAutor(String autor);

    List<LibroEntity> findByIdioma(String idioma);

}