package com.miapp.biblioteca.service;

import com.miapp.biblioteca.Usuario;
import com.miapp.biblioteca.Libro;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioServicio {
	private static final Logger LOGGER = Logger.getLogger(UsuarioServicio.class.getName());
	private List<Usuario> usuarios;
	
	public UsuarioServicio() {
		this.usuarios = new ArrayList<>();
	}
	
	
	//CRUD: Create/Agregar usuarios(Método)
	public void agregarUsuario(Usuario nuevoUsuario) {
		//Comprobar si existe el usuario
		for (Usuario usuario : usuarios) {
			if (usuario.getId() == nuevoUsuario.getId()) {
				LOGGER.log(Level.WARNING, "No se pudo crear el usuario.");
				return;
			}
		}
		usuarios.add(nuevoUsuario);
		LOGGER.log(Level.INFO, "Usuario creado correctamente: " + nuevoUsuario.toString());
	}
	
	
	//CRUD: Read/Consultar todos los usuarios(Método)
	public List<Usuario> obtenerListaUsuarios(){
		return usuarios;
	}
	
	//CRUD: Read/Consultar usuarios por ID(Método)
	public Usuario obtenerUsuarioPorId(long id) {
		for (Usuario usuario : usuarios) {
			if (usuario.getId() == id) {
				LOGGER.log(Level.INFO, "Usuario encontrado! " + usuario.toString());
				return usuario;
			}
		}
		LOGGER.log(Level.WARNING, "No se pudo encontrar el usuario con ID: " + id);
		//Si NO existe/encuentra el usuarioPorId devuelve null
		return null;
	}
	
	//CRUD: Read/Consultar usuarios por Nombre y Apellido (Método)
	public Usuario obtenerUsuarioPorNombreApellido(String nombre, String apellido) {
		for (Usuario usuario : usuarios) {
			if(usuario.getNombre().equals(nombre) && usuario.getApellido().equals(apellido)) {
				LOGGER.log(Level.INFO, "¡Usuario encontrado!" + usuario.toString());
				return usuario;
			}
		}
		LOGGER.log(Level.WARNING, "No se pudo encontrar el usuario con nombre: "+ nombre + "apellido: " + apellido);
		return null;
	}
	
	
	//CRUD: Update/Actualizar(modificar) usuarios(Método)
	public void modificarUsuario(Usuario usuarioModificado) {
		for (int i = 0; i < usuarios.size(); i++) {
			Usuario usuario = usuarios.get(i);
			if (usuario.getId() == usuarioModificado.getId()) {
				usuarios.set(i, usuarioModificado);
				LOGGER.log(Level.INFO, "Usuario actualizado/modificado correctamente: " + usuarioModificado.toString());
				return;
			}
		}
		
		//Si NO encuentra el usuario que se quiere actualizar/modificar
		LOGGER.log(Level.WARNING, "No se pudo encontrar el usuario para actualizar/modificar con ID: " + usuarioModificado.getId());
	}
	
	//CRUD: Delete/Eliminar usuario(Método)
	public void eliminarUsuario(long id) {
		for (int i = 0; i< usuarios.size(); i++) {
			Usuario usuario = usuarios.get(i);
			if (usuario.getId() == id) {
				usuarios.remove(i);
				LOGGER.log(Level.INFO, "Usuario eliminado correctamente! Eliminaste: " + usuario.toString());
				return;
			}
		}
		LOGGER.log(Level.WARNING, "No se pudo encontrar el usuario para eliinar con ID: " + id);
		}
	
	//Agregar un libro a la lista de libros prestados del usuario
	public void prestarLibro( Usuario usuario, Libro libro) {
		if (libro.isDisponible()) {
			usuario.agregarLibroPrestado(libro);
			libro.setDisponible(false);
			   LOGGER.log(Level.INFO, "Libro prestado correctamente: " + libro.getTitulo());
        } else {
            LOGGER.log(Level.WARNING, "El libro no está disponible para préstamo: " + libro.getTitulo());
        }
	}
	
	//Eliminar un libro de la lista de libros prestados del usuario
	public void devolverLibro (Usuario usuario, Libro libro) {
		if (usuario.getLibrosPrestados().contains(libro)) {
            usuario.eliminarLibroPrestado(libro);
            libro.setDisponible(true);
            LOGGER.log(Level.INFO, "Libro devuelto correctamente: " + libro.getTitulo());
        } else {
            LOGGER.log(Level.WARNING, "El usuario no tiene prestado el libro: " + libro.getTitulo());
        }
	}
	
	
	//Obtener lista de libros prestados al usuario
	public List<Libro> obtenerLibrosPrestados(Usuario usuario) {
		for (Usuario u : usuarios) {
			if (u.getId() == usuario.getId()) {
				return u.getLibrosPrestados();
			}
		}
	  LOGGER.log(Level.WARNING, "No se pudo encontrar al usuario para obtener los libros prestados.");
	     return new ArrayList<>();
	}
}








