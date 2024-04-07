package com.miapp.biblioteca;

import java.util.ArrayList;
import java.util.Objects;
import java.util.List;

public class Usuario {
	//Atributos:
	private String nombre;
	private String apellido;
	private long id;
	private ArrayList<Libro> librosPrestados;
	
	
	//Constructor:
	public Usuario(String nombre, String apellido, long id) {
	this.nombre = nombre;
	this.apellido = apellido;
	this.id = id;
	this.librosPrestados = new ArrayList<>();
	}
	
	//Constructor vacio:
	public Usuario() {}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ArrayList<Libro> getLibrosPrestados() {
		return librosPrestados;
	}

	public void setLibrosPrestados(ArrayList<Libro> librosPrestados) {
		this.librosPrestados = librosPrestados;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(apellido, id, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(apellido, other.apellido) && id == other.id && Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", apellido=" + apellido + ", id=" + id + "]";
	}
	
	//Agregar libro a la lista de prestados
	public void agregarLibroPrestado(Libro libro) {
		librosPrestados.add(libro);
	}
	
	//Eliminar libro de la lista de prestados del usuario
	public void eliminarLibroPrestado(Libro libro) {
		librosPrestados.remove(libro);
	}
	
	//Obtener libros prestados del usuario
	public List<Libro> obtenerLibrosPrestados() {
		return librosPrestados;
	}
}
