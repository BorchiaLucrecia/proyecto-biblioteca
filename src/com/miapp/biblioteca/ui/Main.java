package com.miapp.biblioteca.ui;

import com.miapp.biblioteca.Libro;
import com.miapp.biblioteca.Usuario;
import com.miapp.biblioteca.service.LibroServicio;
import com.miapp.biblioteca.service.UsuarioServicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
	private Scanner scanner;
	private LibroServicio libroServicio;
	private UsuarioServicio usuarioServicio;
	
	private List<Libro> libros = new ArrayList<>();
	private List<Usuario> usuarios = new ArrayList<>();
	
	public Main() {
		this.scanner = new Scanner(System.in);
		this.libroServicio = new LibroServicio();
		this.usuarioServicio = new UsuarioServicio();
		inicializarDatos();
	
	//*************************************************//
		
	
	}
	
	//Registros de prueba
	public void inicializarDatos() {
		
		Libro l00001 = new Libro("Cien años de soledad","Gabriel Garcia Márquez","9788498394462","Realismo mágico", true);
		Libro l00002 = new Libro("El código Da Vinci","Dan Brown","9788408025276","Suspenso, misterio",true);
		Libro l00003 = new Libro("La sombra del viento","Carlos Ruis Zafón","9780307279238","Misterio, Drama",true);
		Libro l00004 = new Libro("Harry Potter y la piedra filosofal","J.K. Rowling","9788497912742","Fantasía, Aventura",true);
		Libro l00005 = new Libro("El alquimista","Paulo Coelho","9780056221578","Ficción, Inspiracional",true);
	
		libroServicio.agregarLibro(l00001);
		libroServicio.agregarLibro(l00002);
		libroServicio.agregarLibro(l00003);
		libroServicio.agregarLibro(l00004);
		libroServicio.agregarLibro(l00005);
		
		Usuario u00001 = new Usuario("Ana","García",12345678);
		Usuario u00002 = new Usuario("Juan","Martines",876654321);
		Usuario u00003 = new Usuario("Laura","Rodrigues",45678912);
		Usuario u00004 = new Usuario("Carlos","López",98765432);
		
		usuarioServicio.agregarUsuario(u00001);
		usuarioServicio.agregarUsuario(u00002);
		usuarioServicio.agregarUsuario(u00003);
		usuarioServicio.agregarUsuario(u00004);
		
		usuarioServicio.prestarLibro(u00004,l00002);
		usuarioServicio.prestarLibro(u00004,l00005);
		
		usuarioServicio.prestarLibro(u00002,l00003);
	}
	
	// Método que muestra el menú y opciones 
	public void menu() {
		int opcion;
		do {
			System.out.println("*** Menú ***");
			System.out.println("1. Buscar libros");
			System.out.println("2. Buscar usuarios");
			System.out.println("3. Administrar listado de libro");
			System.out.println("4. Administrar usuarios ");
			System.out.println("5. Salir");
			System.out.println("Ingresar el número de la opción que desea:");
			opcion = scanner.nextInt();
			scanner.nextLine();
			
			switch (opcion) {
				case 1:
					buscarLibros();
					break;
				case 2:
					buscarUsuario();
					break;
				case 3:
					gestionLibros();
					break;
				case 4:
					gestionUsuarios();
					break;
				case 5:
					System.out.println("¡Gracias por usar nuestra biblioteca!");
					return;
				default:
					System.out.println("Opción no válida. Intente de nuevo.");
			}
		} while (opcion != 5);
	}
	
	//Método para buscar libros
	private void buscarLibros() {
		System.out.println("*** Libros ***");
		System.out.println("1. Buscar libro por título");
		System.out.println("2. Buscar libro por ISBN");
		System.out.println("3. Gestión de libros");
		System.out.println("4. Salir");
		int opcionBusqueda = scanner.nextInt();
		scanner.nextLine();
		
		switch (opcionBusqueda) {
			case 1: 
				System.out.println("Ingresar el título del libro que desea buscar: ");
				String titulo = scanner.nextLine();
				List<Libro> librosEncontrados = libroServicio.consultarLibrosPorTitulo(titulo);
				
				if(!librosEncontrados.isEmpty()) {
					if(librosEncontrados.size()==1) {
						Libro libroEncontrado = librosEncontrados.get(0);
						System.out.println("Libro encontrado: ");
						System.out.println(libroEncontrado.toString());
						System.out.println("¿Qué desea hacer? ");
						System.out.println("1. Prestar");
						System.out.println("2. Devolver");
						System.out.println("3. Salir");
						int opcionGestionLibro = scanner.nextInt();
						scanner.nextLine();
					
					switch (opcionGestionLibro) {
						case 1: 
							prestarLibro(libroEncontrado);
							break;
						case 2:
							devolverLibro(libroEncontrado);
							break;
						case 3:
							System.out.println("Volviendo al menú...");
							break;
						default:
							System.out.println("Opción no válida.");
							break;
					}
					} else {
						 System.out.println("Libros encontrados: ");
						 for(int i=0; i<librosEncontrados.size(); i++) {
							    System.out.println((i+1) + ". " + librosEncontrados.get(i).toString());
				          }
						 
						 System.out.println("Ingrese el número del libro que desea seleccionar: ");
						 int opcionLibro = scanner.nextInt();
						 scanner.nextLine();
						 
						 if(opcionLibro>= 1 && opcionLibro <= librosEncontrados.size()) {
							 Libro libroSeleccionado = librosEncontrados.get(opcionLibro -1);
							 System.out.println("Libro seleccionado: ");
			                    System.out.println(libroSeleccionado.toString());
			                    
			                    System.out.println("¿Qué desea hacer con el libro?");
			                    System.out.println("1. Prestar");
			                    System.out.println("2. Devolver");
			                    System.out.println("3. Salir");
			                    int opcionGestionLibro = scanner.nextInt();
			                    scanner.nextLine();
			                    
			                    switch (opcionGestionLibro) {
		                        case 1: 
		                            prestarLibro(libroSeleccionado);
		                            break;
		                        case 2:
		                            devolverLibro(libroSeleccionado);
		                            break;
		                        case 3:
		                            System.out.println("Volviendo al menú...");
		                            break;
		                        default:
		                            System.out.println("Opción no válida.");
		                            break;
						 }
					}else {
						System.out.println("Número de libro no válido.");
					}
				}
			} else {
					System.out.println("No se encontraron libros con el titulo: " + titulo);
				}
				break;
				
			case 2:
				System.out.println("Ingresar ISBN del libro que desea buscar: ");
				String isbn = scanner.nextLine();
				Libro libroEncontrado = libroServicio.consultarLibroPorISBN(isbn);
				if(libroEncontrado != null) {
					System.out.println("Libro encontrado: ");
					System.out.println(libroEncontrado.toString());
					
					System.out.println("¿Qué desea hacer con: ?" + libroEncontrado);
					System.out.println("1. Prestar");
					System.out.println("2. Devolver");
					System.out.println("3. Salir");
					
					int opcionGestionLibro = scanner.nextInt();
					scanner.nextLine();
					switch (opcionGestionLibro) {
						case 1:
							prestarLibro(libroEncontrado);							
							break;
						case 2:
							devolverLibro(libroEncontrado);	
							break;
						case 3:
							System.out.println("Volviendo al menú...");	
							break;
						default:
							System.out.println("Opción no válida.");
							break;
					}
				} else {
					System.out.println("No se encontró libro con el ISBN: " + isbn);
				}
				break;
				
			case 3: 
				gestionLibros();
				break;
				
			case 4:
				System.out.println("Volviendo al menú principal...");
				return;
				
			default:
				System.out.println("Opción no válida.");
		}
	}
	
	//Método para buscar usuarios
	private void buscarUsuario() {
		System.out.println("*** Menú usuario ***");
		System.out.println("1. Buscar por ID");
		System.out.println("2. Buscar por nombre y apellido");
		System.out.println("3. Gestion usuarios");
		System.out.println("4. Salir");
		int opcionUsuario = scanner.nextInt();
		scanner.nextLine();
		
		switch (opcionUsuario) {
			case 1:
				System.out.println("Ingresar el ID del usuario que desea buscar: ");
				long idUsuario = scanner.nextLong();
				Usuario usuarioId = usuarioServicio.obtenerUsuarioPorId(idUsuario);
				if (usuarioId != null) {
					System.out.println("Datos del usuario: ");
					System.out.println(usuarioId.toString());
					menuUsuario(usuarioId);
				}else {
					System.out.println("No se encontro usuario con el ID: " + idUsuario);
				}
				break;
			
			case 2:
				System.out.println("Ingresar nombre del usuario: ");
				String nombre = scanner.nextLine();
				System.out.println("Ingresar apellido del usuario: ");
				String apellido = scanner.nextLine();
				Usuario usuarioPorNombreApellido = usuarioServicio.obtenerUsuarioPorNombreApellido(nombre, apellido);
				if (usuarioPorNombreApellido != null) {
					System.out.println("Datos del usuario: ");
					System.out.println(usuarioPorNombreApellido.toString());
					menuUsuario(usuarioPorNombreApellido);
				} else {
					System.out.println("No se encontró un usuario con el nombre y apellido: " + nombre + apellido);
				}
				break;
				
			case 3:
				gestionUsuarios();
				break;
				
			case 4:
				System.out.println("Volviendo al menú principal...");
				break;
			
			default:
				System.out.println("Opción no válida.");
				
		}
	}

	//Método para gestionar libros 
	private void gestionLibros() {
		int opcionGestionLibros;
		do {
			System.out.println("*** Gestión de libros ***");
			System.out.println("1. Agregar un nuevo libro");
			System.out.println("2. Modificar/actualizar libro");
			System.out.println("3. Listado de libros");
			System.out.println("4. Libros disponibles");
			System.out.println("5. Libros prestados");
			System.out.println("6. Salir");
			System.out.println("Ingresar el número de la opción que desea: ");
			opcionGestionLibros = scanner.nextInt();
			scanner.nextLine();
			
			switch (opcionGestionLibros) {
			case 1:
				agregarNuevoLibro();
				break;
			case 2:
				System.out.println("Ingresar ISBN del libro que desea modificar: ");
				String ISBN = scanner.nextLine();
				
				if(!Pattern.matches("\\d+$", ISBN)) {
					System.out.println("El ISBN debe contener solo números.");
					return;
				} else {
					Libro libroExistente = libroServicio.consultarLibroPorISBN(ISBN);
					if (libroExistente != null) {
						modificarLibro(libroExistente);
					} else {
			            System.out.println("No se encontró ningún libro con el ISBN: " + ISBN);
					}
				}
				
				break;
			case 3:
				mostrarListadoLibros();
				break;
			case 4:
				mostrarLibrosDisponibles();
				break;
			case 5:
				mostrarLibrosPrestados();
				break;
			case 6:
				System.out.println("Volviendo al menú principal..");
				menu();
				break;
			default:
				System.out.println("Opción no válida");
				return;
			}
		} while (opcionGestionLibros != 6);
		
	}
	
	//Método para gestionar usuarios
	private void gestionUsuarios() {
		int opcionGestionUsuario;
		do {
			System.out.println("*** Gestión de usuarios ***");
			System.out.println("1. Registrar usuario");
			System.out.println("2. Modificar datos");
			System.out.println("3. Dar de baja");
			System.out.println("4. Listado de usuarios");
			System.out.println("5. Salir");
			System.out.println("Ingresar el número de la opcion que desea: ");
			opcionGestionUsuario = scanner.nextInt();
			scanner.nextLine();
			
			switch (opcionGestionUsuario) {
				case 1:
					registrarUsuario();
					break;
				case 2:
					modificarUsuario();
					break;
				case 3:
					darDeBaja();
					break;
				case 4:
					listarUsuarios();
					break;
				case 5:
					System.out.println("Volviendo al menú principal...");
					break;
				default:
					System.out.println("Opción no válida");
			}
			
		}while (opcionGestionUsuario != 5);
	}
	
	//Método para agregar un libro nuevo
	private void agregarNuevoLibro() {
		System.out.println("Ingrese el ISBN del libro: ");
		String ISBN =scanner.nextLine().trim();
		if(!ISBN.matches("\\d+")) {
			System.out.println("El ISBN debe contener solo números.");
			return;
		}
		
		System.out.println("Ingresar título: ");
		String titulo = scanner.nextLine();
		if(!Pattern.matches("^[a-zA-Z]+$", titulo)) {
			System.out.println("El título debe contener solo letras.");
			return;
		}
		
		System.out.println("Ingresar autor: ");
		String autor = scanner.nextLine();
		if(!Pattern.matches("^[a-zA-Z]+$", autor)) {
			System.out.println("El autor debe contener solo letras.");
			return;
		}
		
		System.out.println("Ingresar género: ");
		String genero = scanner.nextLine();
		if(!Pattern.matches("^[a-zA-Z]+$", genero)) {
			System.out.println("El género debe contener solo letras.");
			return;
		}
		
		Libro nuevoLibro = new Libro(titulo,autor,ISBN,genero,true);
		libroServicio.agregarLibro(nuevoLibro);
		System.out.println("Libro agregado correctamente:  " + nuevoLibro.toString());
		
		menu();
	}
	
	//Método para modificar libro
	private void modificarLibro( Libro libroExistente) {
		System.out.println("*** Gestion libros: Actualizar datos. ***");
		
		if(libroExistente != null) {
			System.out.println("Ingresar titulo: ");
			String nuevoTitulo = scanner.nextLine();
			
			System.out.println("Ingresar autor: ");
			String nuevoAutor = scanner.nextLine();
			
			System.out.println("Ingresar ISBN: ");
			String nuevoISBN = scanner.nextLine();
			
			System.out.println("Ingresar género: ");
			String nuevoGenero = scanner.nextLine();
			
			System.out.println("Indique si el libro está disponible (true/false):  ");
			boolean nuevoDisponibilidad = scanner.nextBoolean();
			scanner.nextLine();
			
			Libro libroModificado = new Libro(nuevoISBN, nuevoTitulo, nuevoAutor, nuevoGenero, nuevoDisponibilidad);
            libroServicio.modificarLibro(nuevoISBN, libroModificado);
            System.out.println("Libro actualizado/modificado correctamente: " + libroModificado.toString());
            menu();
        } else {
			System.out.println("No se encontró ningún libro.");
			gestionLibros();
		}
	}
	
	//Método para mostrar listado de libros
	private void mostrarListadoLibros() {
		List<Libro> listaLibros = libroServicio.obtenerListaLibros();
		System.out.println("*** Listado de Libros ***");
		for (int i = 0; i < listaLibros.size(); i++) {
			System.out.println((i + 1) + "." + listaLibros.get(i));
		}
		
	    System.out.println("Ingrese el número del libro que desea seleccionar (o 0 para volver atrás): ");
	    int opcionLibro = scanner.nextInt();
	    scanner.nextLine();
	    
	    if(opcionLibro >= 1 && opcionLibro <= listaLibros.size()) {
	    	Libro libroSeleccionado = listaLibros.get(opcionLibro - 1);
	    	System.out.println("Libro seleccionado: ");
	        System.out.println(libroSeleccionado);
	        
	        System.out.println("¿Qué desea hacer con el libro?");
	        System.out.println("1. Prestar");
	        System.out.println("2. Devolver");
	        System.out.println("3. Modificar");
	        System.out.println("4. Cancelar");
	        int opcionAccion = scanner.nextInt();
	        scanner.nextLine();
	        
	        switch (opcionAccion) {
		        case 1: 
		        	prestarLibro(libroSeleccionado);
		        	break;
		        case 2:
		        	devolverLibro(libroSeleccionado);
		        	break;
		        case 3:
		        	System.out.println("Ingresar ISBN del libro que desea modificar: ");
					String ISBN = scanner.nextLine();
					
					if(!Pattern.matches("\\d+$", ISBN)) {
						System.out.println("El ISBN debe contener solo números.");
						return;
					} else {
						Libro libroExistente = libroServicio.consultarLibroPorISBN(ISBN);
						if (libroExistente != null) {
							modificarLibro(libroExistente);
						} else {
				            System.out.println("No se encontró ningún libro con el ISBN: " + ISBN);
						}
					}
		        	break;
		        case 4:
		        	System.out.println("Operación cancelada.");
		        	gestionLibros();
		        	break;
		        default:
		        	System.out.println("Opción no válida.");
		        	gestionLibros();
		        	break;
	        }
	    } else if (opcionLibro == 0) {
	    	System.out.println("Volviendo atrás...");
	    	gestionLibros();
	    } else {
	        System.out.println("Número de libro no válido.");
	        gestionLibros();
	    }
	}
	
	//Método para mostrar listado de libros disponibles para prestamos
	private void mostrarLibrosDisponibles() {
		List<Libro> librosDisponibles = libroServicio.obtenerLibrosDisponibles();
		System.out.println("*** Libros disponibles ***");
		for(int i = 0; i < librosDisponibles.size(); i++) {
			System.out.println((i + 1) + ". " + librosDisponibles.get(i));
		}
		
		System.out.println("Ingrese el número del libro que desea seleccionar (o 0 para volver atrás): ");
	    int opcionLibro = scanner.nextInt();
	    scanner.nextLine();
	    
	    if (opcionLibro >= 1 && opcionLibro <= librosDisponibles.size()) {
	    	Libro libroSeleccionado = librosDisponibles.get(opcionLibro -1);
	    	System.out.println("Libro seleccionado: ");
	        System.out.println(libroSeleccionado);
	        
	        System.out.println("¿Qué desea hacer con el libro?");
	        System.out.println("1. Prestar");
	        System.out.println("2. Modificar");
	        System.out.println("3. Cancelar");
	        int opcionAccion = scanner.nextInt();
	        scanner.nextLine();
	        
	        switch (opcionAccion) {
	            case 1:
	                prestarLibro(libroSeleccionado);
	                break;
	            case 2:
	            	modificarLibro(libroSeleccionado);
	                break;
	            case 3:
	                System.out.println("Operación cancelada.");
	                gestionLibros();
	                break;
	            default:
	                System.out.println("Opción no válida.");
	                gestionLibros();
	                break;
	        }    
	    } else if (opcionLibro == 0) {
	    	System.out.println("Volviendo atrás...");
	    	gestionLibros();
	    } else {
	    	System.out.println("Número de libro no válido.");
	    	gestionLibros();
	    }
	}
	
	//Método para mostrar listado de libros prestados
	private void mostrarLibrosPrestados() {
		List<Libro> librosPrestados = libroServicio.obtenerLibrosPrestados();
		System.out.println("*** Libros prestados ***");
		
		if (!librosPrestados.isEmpty()) {
			for (int i = 0; i < librosPrestados.size(); i++) {
				System.out.println((i + 1) + ". " + librosPrestados.get(i));
			}
			
			System.out.println("Ingrese el número del libro que desea seleccionar (o 0 para volver atrás): ");
	        int opcionLibro = scanner.nextInt();
	        scanner.nextLine();
	        
	        if (opcionLibro >= 1 && opcionLibro <= librosPrestados.size()) {
	            Libro libroSeleccionado = librosPrestados.get(opcionLibro - 1);
	            System.out.println("Libro seleccionado: ");
	            System.out.println(libroSeleccionado);
	            
	            System.out.println("¿Qué desea hacer con el libro?");
	            System.out.println("1. Devolver");
	            System.out.println("2. Cancelar");
	            int opcionAccion = scanner.nextInt();
	            scanner.nextLine();
	            
	            switch (opcionAccion) {
	                case 1:
	                    devolverLibro(libroSeleccionado);
	                    break;
	                case 2:
	                    System.out.println("Operación cancelada.");
	                    gestionLibros();
	                    break;
	                default:
	                    System.out.println("Opción no válida.");
	                    gestionLibros();
	                    break;
	            }
	        } else if (opcionLibro == 0) {
	        	System.out.println("Volviendo atrás...");
	        	gestionLibros();
	        } else {
	        	System.out.println("Número de libro no válido.");
	        	gestionLibros();
	        }
       }else {
			System.out.println("No hay libros prestados en este momento.");
			gestionLibros();
       }
	}
	
	//Método para prestar libros
	private void prestarLibro(Libro libro) {
		System.out.println("Ingresar ID(Identificación) del usuario: ");
		long idUsuario = scanner.nextLong();
		scanner.nextLine();
		
		Usuario usuario = usuarioServicio.obtenerUsuarioPorId(idUsuario);
		if(usuario != null) {
			if(libro.isDisponible()) {
				usuarioServicio.prestarLibro(usuario, libro);
				libro.setDisponible(false);
				System.out.println("¡Libro prestado con éxito!");
			} else {
				System.out.println("El libro seleccionado no está disponible para realizar el prestamo.");
			}
		} else {
			System.out.println("No se encontró ningún usuario con el ID: " + idUsuario);
		}
		menu();
	}
	
	//método para devolver libros
	private void devolverLibro(Libro libro) {
		System.out.println("Ingresar el ID(Identificación) del usuario: ");
		long idUsuario = scanner.nextLong();
		scanner.nextLine();
		
		Usuario usuario = usuarioServicio.obtenerUsuarioPorId(idUsuario);
		if(usuario != null) {
			usuarioServicio.devolverLibro(usuario, libro);
			libro.setDisponible(true);
			System.out.println("¡Libro devuelto con éxito!");
		} else {
			System.out.println("No se encontró ningún usuario con el ID: " + idUsuario);
		}
		menu();
	}
	
	//Método que muestra menú del usuario para realizar operaciones de prestamo/devolución
	private void menuUsuario(Usuario usuario) {
		System.out.println("*** Menú usuario ***");
		System.out.println("1. Prestar libro");
		System.out.println("2. Devolver libro");
		System.out.println("3. Listado de libros prestados");
		System.out.println("4. Salir");
		int opcionMenuUsuario = scanner.nextInt();
		scanner.nextLine();
		
		switch (opcionMenuUsuario) {
			case 1:
				System.out.println("Ingresar titulo del libro que desea prestar: ");
				String tituloPrestamo = scanner.nextLine();
				List<Libro> librosEncontrados = libroServicio.consultarLibrosPorTitulo(tituloPrestamo);
				
				if (!librosEncontrados.isEmpty()) {
					System.out.println("Libros encontrados: ");
					for (int i=0; i< librosEncontrados.size(); i++) {
						Libro libro = librosEncontrados.get(i);
						System.out.println((i+1) + "." + libro.getTitulo() + "- ISBN: " + libro.getISBN());
					}
					System.out.println("Ingrese el número del libro que desea prestar: ");
					int opcionLibro = scanner.nextInt();
					scanner.nextLine();
					
					if(opcionLibro >= 1 && opcionLibro <= librosEncontrados.size()) {
						Libro libroPrestamo = librosEncontrados.get(opcionLibro - 1);
						if(libroPrestamo.isDisponible()) {
							usuarioServicio.prestarLibro(usuario, libroPrestamo);
							System.out.println("¡Libro prestado con éxito!");
						} else {
							System.out.println("El libro seleccionado no está disponible.");
						}
					}else {
						System.out.println("Número de libro no válido.");
					}
				} else {
					System.out.println("No se encontró ningún libro con el titulo: " + tituloPrestamo);
				}
				break;
				
			case 2:
				List<Libro> librosPrestados = usuarioServicio.obtenerLibrosPrestados(usuario);
				if (!librosPrestados.isEmpty()) {
					System.out.println("Libros prestados: ");
					for (Libro libro : librosPrestados) {
						System.out.println(libro.toString());
					}
					System.out.println("Ingrese el titulo del libro que desea devolver: ");
					String tituloDevolucion = scanner.nextLine();
					Libro libroDevolucion = librosPrestados.stream()
							.filter(libro -> libro.getTitulo().equalsIgnoreCase(tituloDevolucion))
							.findFirst().orElse(null);
						if(libroDevolucion != null) {
							usuarioServicio.devolverLibro(usuario, libroDevolucion);
							System.out.println("¡Libro devuelto con éxito!");
						} else {
							System.out.println("No se encontrólibro prestado con el titulo: " + tituloDevolucion);
						}
				}else {
					System.out.println("No tiene libros prestados.");
				}
				break;
							
			case 3:
				List<Libro> librosPrestadosList = usuarioServicio.obtenerLibrosPrestados(usuario);
				if (!librosPrestadosList.isEmpty()) {
					System.out.println("Libros prestados: ");
					for (Libro libro : librosPrestadosList) {
						System.out.println(libro.toString());
					}
				} else {
					System.out.println("No tiene libros prestados.");
				}
				break;
				
			case 4:
				System.out.println("Volviendo al menú anterior...");
				return;
				
			default: 
				System.out.println("Opción no válida.");
		}
	}

	//Método para registrar un nuevo usuario
	private void registrarUsuario() {
		System.out.println("*** Registro de usuario ***");
		long id;
		while (true) {
			System.out.println("Ingrese el ID (Identificación): ");
			if(scanner.hasNextLong()) {
				id = scanner.nextLong();
				scanner.nextLine();
				break;
			} else {
				System.out.println("ERROR: El ID debe ser un número entero.");
				scanner.nextLine();
			}
		}
		
		if (usuarioServicio.obtenerUsuarioPorId(id) != null) {
			System.out.println("ERROR: El usuario ya está registrado.");
		} else {
			
			String nombre;
			while (true) {
				System.out.println("Ingrese el nombre del usuario: ");
				if(scanner.hasNext("[a-zA-Z]")) {
					nombre = scanner.nextLine();
					break;
				} else {
					System.out.println("ERROR: El nombre solo puede contener letras.");
					scanner.nextLine();
				}
			}
			
			String apellido;
			while(true) {
				System.out.println("Ingresar el apellido del usuario: ");
				if(scanner.hasNext("[a-zA-Z]")) {
					apellido = scanner.nextLine();
					break;
				} else {
					System.out.println("ERROR: El apellido solo puede contener letras.");
					scanner.nextLine();
				}
			}
			
			Usuario nuevoUsuario = new Usuario(nombre, apellido, id);
			usuarioServicio.agregarUsuario(nuevoUsuario);
			System.out.println("¡Usuario registrado con éxito!");
		}
		
	}
	
	//Método para modificar datos del usuario
	private void modificarUsuario() {
		System.out.println("Ingresar el ID del usuario que desea modificar: ");
		long idUsuario = scanner.nextLong();
		scanner.nextLine();
		
		Usuario usuario = usuarioServicio.obtenerUsuarioPorId(idUsuario);
		if (usuario != null) {
			System.out.println("Datos del usuario: ");
			System.out.println(usuario);
			
			System.out.println("Ingresar el número del dato que desea modificar: ");
			System.out.println("1. Nombre");
			System.out.println("2. Apellido");
			System.out.println("3. ID (Identificación)");
			System.out.println("4. Cancelar");
			int opcion = scanner.nextInt();
			scanner.nextLine();
			
			switch (opcion) {
			case 1:
				System.out.println("Ingrese el nuevo nombre: ");
				String nuevoNombre = scanner.nextLine();
				usuario.setNombre(nuevoNombre);
				break;
				
			case 2:
				System.out.println("Ingrese el nuevo apellido: ");
				String nuevoApellido = scanner.nextLine();
				usuario.setApellido(nuevoApellido);
				break;
				
			case 3:
				System.out.println("Ingrese el nuevo ID(identificación): ");
				long nuevoId = scanner.nextLong();
				usuario.setId(nuevoId);
				break;
			case 4:
				return;
			default:
				System.out.println("Opción no válida.");
				return;
			}
			
			System.out.println("Usuario actualizado: ");
			System.out.println(usuario);
		}else {
			System.out.println("No se encontró ningún usuario con el ID: " + idUsuario);
		}
	}
	
	//Método para eliminar un usuario
	private void darDeBaja() {
		System.out.println("Ingresar ID del usuario al que desea dar de baja: ");
		long idUsuario = scanner.nextLong();
		scanner.nextLine();
		
		Usuario usuario = usuarioServicio.obtenerUsuarioPorId(idUsuario);
		if(usuario != null) {
			System.out.println("Datos del usuario: ");
			System.out.println(usuario);
			
			List <Libro> librosPrestados = usuarioServicio.obtenerLibrosPrestados(usuario);
			if (!librosPrestados.isEmpty()) {
				System.out.println("Libros Prestados: ");
				for ( Libro libro : librosPrestados) {
					System.out.println("libro");
				}
			}
			
			System.out.println("¿Está seguro de eliminar este usuario? (s/n) : ");
			System.out.println(usuario);
			String confirmacion = scanner.nextLine();
			if (confirmacion.equalsIgnoreCase("s")) {
				usuarioServicio.eliminarUsuario(idUsuario);
				System.out.println("Usuario eliminado con éxito. ");
			}else {
				System.out.println("Operación cancelada");
			}
		} else {
			System.out.println("No se encontró ningún usuario con el ID: " + idUsuario);
		}
	}

	//Método para ver todos los usuarios
	private void listarUsuarios() {
		List<Usuario> usuarios = usuarioServicio.obtenerListaUsuarios();
		if ( !usuarios.isEmpty()) {
			System.out.println("*** Listado de Usuarios ***");
			for (Usuario usuario : usuarios) {
				System.out.println(usuario);
			}
		}else {
			System.out.println("No hay usuarios registrados en la biblioteca.");
		}
	}
	
	//*****************************************//
	public static void main(String[] args) {
		Main ui = new Main();
		ui.menu();
	}
}



