package com.miapp.biblioteca.service;

import com.miapp.biblioteca.Libro;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class LibroServicio {
	private static final Logger LOGGER = Logger.getLogger(LibroServicio.class.getName());
	private List<Libro> libros;
	
	public LibroServicio() {
		this.libros = new ArrayList<>();
	}

	
	//CRUD: Create/Agregar libro (Método)
	public String agregarLibro(Libro nuevoLibro) {
	    if (nuevoLibro != null) {
	        libros.add(nuevoLibro);
	        LOGGER.log(Level.INFO, "Libro agregado correctamente: " + nuevoLibro.toString());
	        return nuevoLibro.getISBN(); 
	    } else {
	        LOGGER.log(Level.WARNING, "Error al agregar el libro: el libro proporcionado es nulo");
	        return null; 
	     }
	}
	
	//CRUD: Read/Consultar-Obtener todos los libros (Método)
	public List<Libro> obtenerListaLibros(){
		return libros;
	}

	//CRUD: Read/Consultar-Obtener libro por ISBN (Método)
	public Libro consultarLibroPorISBN (String isbn) {
		for (Libro libro : libros) {
			if (libro.getISBN().equals(isbn)) {
				LOGGER.log(Level.INFO, "Libro encontrado: " + libro.toString());
				return libro;
			}
		}
		LOGGER.log(Level.WARNING, "No se pudo encontrar el libro con ISBN: " + isbn);
		return null;
	}
	
	//CRUD: Read/Consultar-Obtener libro por titulo (Método)
	public List<Libro> consultarLibrosPorTitulo(String titulo){
		List<Libro> librosEncontrados = new ArrayList<>();
		for (Libro libro : libros) {
			if (libro.getTitulo().equalsIgnoreCase(titulo)) {
				librosEncontrados.add(libro);
			}
		}
		return librosEncontrados;
	}
	
	//CRUD: Update/Actualizar-Modificar libro (Método)
	public void modificarLibro(String ISBN, Libro libroModificado) {
		Libro libroExistente = consultarLibroPorISBN(ISBN);
		if(libroExistente != null) {
			libroExistente.setTitulo(libroModificado.getTitulo());
			libroExistente.setAutor(libroModificado.getAutor());
			libroExistente.setISBN(libroModificado.getISBN());
			libroExistente.setGenero(libroModificado.getGenero());
			libroExistente.setDisponible(libroModificado.isDisponible());
			
			LOGGER.log(Level.INFO, "Libro actualizado/modificado correctamente: " + libroModificado.toString());
		} else {
			LOGGER.log(Level.WARNING, "No se pudo encontrar el libro con ISBN: " + ISBN + "para actualizar/modificar.");
		}
		
	}
	
	//CRUD: Delete/Eliminar libro (Método)
	public void eliminarLibro(String ISBN) {
		libros.removeIf(libro -> libro.getISBN().equals(ISBN));
		LOGGER.log(Level.INFO, "Libro eliminado correctamente con ISBN: " + ISBN);
	}
	
	//Disponibilidad de libros por ISBN (Método)
	public boolean verificarDisponibilidad(String ISBN) {
		for (Libro libro : libros) {
			if (libro.getISBN().equals(ISBN)) {
				return libro.isDisponible();
			}
		}
		//Si el libro no se encuentra, se asume que no esta disponible.
		return false;
	}
	
	//Consultar todos los libros disponibles (Método)
	public List<Libro> obtenerLibrosDisponibles(){
		List<Libro> librosDisponibles = new ArrayList<>();
		for (Libro libro : libros) {
			if(libro.isDisponible()) {
				librosDisponibles.add(libro);
			}
		}
		return librosDisponibles;
	}
	
	//Consultar todos los libros disponibles (Método)
	public List<Libro> obtenerLibrosPrestados(){
		List<Libro> librosPrestados = new ArrayList<>();
		for (Libro libro : libros) {
			if (!libro.isDisponible()) {
				librosPrestados.add(libro);
			}
		}
		return librosPrestados;
		}
}





