package com.literalura.catalogo_libros;

import org.springframework.boot.jackson.autoconfigure.JacksonProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class LibroService {

    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper objectMapper = new ObjectMapper();
    private final LibroRepository libroRepository;



    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }





    public void guardarLibrosDesdeApi(String titulo) throws IOException {
        String url = "https://gutendex.com/books/?search=" + titulo;
        String jsonResponse = restTemplate.getForObject(url, String.class);

        JsonNode root = objectMapper.readTree(jsonResponse);
        JsonNode results = root.get("results");

        for (JsonNode node : results) {
            String tituloLibro = node.get("title").asText();
            String autor = node.get("authors").get(0).get("name").asText();
            String idioma = node.get("languages").get(0).asText();
            int descargas = node.get("download_count").asInt();

            LibroEntity libroEntity = new LibroEntity(tituloLibro, autor, idioma, descargas);
            libroRepository.save(libroEntity);

        }

    //public List<Libro> buscarLibros(String titulo) throws IOException {
    //    String url = "https://gutendex.com/books/?search=" + titulo;
    //    String jsonResponse = restTemplate.getForObject(url, String.class);

        //JsonNode root = objectMapper.readTree(jsonResponse);
        //JsonNode results = root.get("results");

        List<Libro> libros = new ArrayList<>();
        for (JsonNode node : results) {
            Libro libro = new Libro();
            libro.setTitulo(node.get("title").asText());
            //libro.setTitulo(node.get("title")).asText();

            //Busqueda por actores
            List<String> autores = new ArrayList<>();
            for (JsonNode autorNode : node.get("authors")) {
                autores.add(autorNode.get("name").asText());
            }
            libro.setAutores(autores);

            //Busqueda por idioma
            libro.setIdioma(node.get("languages").get(0).asText());

            //Busqueda por descargas
            libro.setDescargas(node.get("download_count").asInt());

            libros.add(libro);

        }

    }
    public List<LibroEntity> listarLibros() {
        return libroRepository.findAll();

    }

    public List<LibroEntity> filtrarPorAutor(String autor) {
        return libroRepository.findByAutor(autor);
    }

    public List<LibroEntity> filtrarPorIdioma(String idioma) {
        return libroRepository.findByIdioma(idioma);
    }

}
