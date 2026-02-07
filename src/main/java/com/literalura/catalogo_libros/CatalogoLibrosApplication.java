package com.literalura.catalogo_libros;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class CatalogoLibrosApplication implements CommandLineRunner {

    private final LibroService libroService;
    private final Scanner scanner = new Scanner(System.in);
    private LibroEntity libro;

    public CatalogoLibrosApplication(LibroService libroService) {
        this.libroService = libroService;
    }

    public static void main(String[] args) {
        SpringApplication.run(CatalogoLibrosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        int opcion;
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine();
            ejecutarOpcion(opcion);
        } while (opcion != 0);

    }

    private void mostrarMenu() {
        System.out.println("---LiterAlura---");
        System.out.println("1. Buscar y guardar libro por titulo");
        System.out.println("2. Listar todos los libros guardados");
        System.out.println("3. Filtrar libros por autor");
        System.out.println("4. Filtrar libros por idioma");
        System.out.println("5. Mostrar top 5 libros más descargados");
        System.out.println("0. Salir");
        System.out.println("Por favor elige una opción: ");

    }

    private void ejecutarOpcion(int opcion) throws Exception {
        switch (opcion) {
            case 1 -> buscarYGuardarLibro();
            case 2 -> listarLibros();
            case 3 -> filtrarPorAutor();
            case 4 -> filtrarPorIdioma();
            case 5 -> mostrarTopDescargas();
            case 0 -> System.out.println("¡Hasta la próxima!");
            default -> System.out.println("Opcion invalida");
        }
    }

    private void buscarYGuardarLibro() throws Exception {
        System.out.print("Ingrese el título del libro: ");
        String titulo = scanner.nextLine();
        libroService.guardarLibrosDesdeApi(titulo);
        System.out.println("Libros encontrados y guardados en la BD.");
    }

    private void listarLibros() {
        System.out.println("\n=== Lista de libros guardados ===");
        libroService.listarLibros().forEach(libro ->
                System.out.println(libro.getTitulo() + " - " + libro.getAutor() + " (" + libro.getIdioma() + ")"));

    }

    private void filtrarPorIdioma() {
        System.out.print("Ingrese el idioma (ej: en, es, fr): ");
        String idioma = scanner.nextLine();
        libroService.filtrarPorIdioma(idioma).forEach(libro ->
                System.out.println(libro.getTitulo() + " - " + libro.getIdioma()));
    }

    private void mostrarTopDescargas() {
        System.out.println("\n=== Top 5 libros más descargados ===");
        libroService.listarLibros().stream()
                .sorted((l1, l2) -> Integer.compare(l2.getDescargas(), l1.getDescargas()))
                .limit(5)
                .forEach(libro ->
                        System.out.println(libro.getTitulo() + " - Descargas: " + libro.getDescargas()));
    }

    private void filtrarPorAutor() {
        System.out.print("Ingrese el nombre del autor: ");
        String autor = scanner.nextLine();
        libroService.filtrarPorAutor(autor).forEach(libro ->
                System.out.println(libro.getTitulo() + " - " + libro.getAutor()));
    }



    // libroService.guardarLibrosDesdeApi("Sherlock Holmes");

        //System.out.println("--Libros guardados en la BD--");
        //libroService.listarLibros().forEach(Libro ->
        //        System.out.println(libro.getTitulo() + "-" + libro.getAutor()));


}
