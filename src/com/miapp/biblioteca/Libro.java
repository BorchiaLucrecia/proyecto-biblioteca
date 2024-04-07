package com.miapp.biblioteca;

import java.util.Objects;

public class Libro {

	//Atributos:
	private String titulo;
	private String autor;
	private String ISBN;
	private String genero;
	private boolean disponible;
	
	//Constructor:
	public Libro(String titulo, String autor, String ISBN, String genero,boolean disponible ) {
		this.titulo = titulo;
		this.autor = autor;
		this.ISBN = ISBN;
		this.genero = genero;
		this.disponible = true;
	}
	
	//Constructor vacio:
	public Libro() {}

	//Getters and Setters
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	
	

	@Override
	public int hashCode() {
		return Objects.hash(ISBN, autor, titulo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		return Objects.equals(ISBN, other.ISBN) && Objects.equals(autor, other.autor)
				&& Objects.equals(titulo, other.titulo);
	}

	@Override
	public String toString() {
		return "Libro [titulo=" + titulo + ", autor=" + autor + ", ISBN=" + ISBN + ", genero=" + genero
				+ ", disponible=" + disponible + "]";
	}
	
	
}
