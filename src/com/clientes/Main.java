package com.clientes;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JOptionPane;

 
import com.clases.Clientes;
import com.clases.Producto;


public class Main {

    public static void main(String[] args) {
    	

    	
		ArrayList<Producto> vectorProducto = new ArrayList<Producto>();
    	ArrayList<Clientes> vectorCliente = new ArrayList<Clientes>();
    	Integer opMenu = new Integer(0);
        
    	Scanner in = new Scanner(System.in);
         
		
    	do {
    		String menu = JOptionPane.showInputDialog("Ingrese opción: \n" 
                    + "         * 1 Agregar cliente\n" 
                    + "         * 2 Editar cliente\n" 
                    + "         * 3 Eliminar cliente\n" 
                    + "         * 4 Agregar productos\n" 
                    + "         * 5 Consultar clientes con documento y tipo de documento \n" 
                    + "         * 0 salir de la aplicacion");
    		
    		
    		try {
        	
    			opMenu = new Integer(menu);
    			
	        	
				switch(opMenu) {
				
					case 1:
						Clientes cliente = new Clientes();
						Producto auxProducto = new Producto();
						ArrayList<Producto> vectorProductoCliente = new ArrayList<Producto>();
						boolean encontrado3 = false;
						String prod;
						cliente.setTipoDocumento(JOptionPane.showInputDialog("Tipo de documento del cliente"));
						cliente.setIdentificacion(JOptionPane.showInputDialog("Identificación del cliente"));
						cliente.setNombre(JOptionPane.showInputDialog("Nombre del cliente"));
						cliente.setDireccion(JOptionPane.showInputDialog("Dirección del cliente"));
						cliente.setTelefono(JOptionPane.showInputDialog("Telefono del cliente"));
						cliente.setCelular(JOptionPane.showInputDialog("Celular del cliente"));
						
						do {
							prod = JOptionPane.showInputDialog("Ingreso el Id del producto.  En caso de no querer continuar ingrese 'N'");
							encontrado3 = false;
							for(int i = 0; i < vectorProducto.size(); i++) {
								
								auxProducto = vectorProducto.get(i);
								
								if (prod.equalsIgnoreCase(auxProducto.getIdProducto())) {
									
									//rompe el ciclo mas cercano que tenga
									encontrado3 = true;
									break;
								}
		
							}
							if (encontrado3 == false) {
								System.out.println("Producto NO encontrado");
							}
							else {
								vectorProductoCliente.add(auxProducto);
							}
						} while (!prod.equals("N"));	
						
						if (vectorProductoCliente.size() > 0){
							cliente.setProductos(vectorProductoCliente);
						}
						
						vectorCliente.add(cliente);
						
						break;
					case 2:
						//
						vectorCliente = EditarCliente(vectorCliente);
						
						break;
					case 3:
						String documentoBusqueda1 = JOptionPane.showInputDialog("Ingrese el documento del cliente que desea Eliminar");
						vectorCliente = EliminarCliente(vectorCliente, documentoBusqueda1);
						break;
					case 4:
						
						Producto producto = new Producto();
						producto.setIdProducto(JOptionPane.showInputDialog("Id del producto"));
						producto.setNombre(JOptionPane.showInputDialog("Nombre del producto"));
						producto.setCarateristicas(JOptionPane.showInputDialog("Caracteristicas del producto"));
						producto.setCondiciones(JOptionPane.showInputDialog("Condiciones del producto"));
						
						vectorProducto.add(producto);
						
						
						
						break;
					case 5:
						String tipoDocBusqueda = JOptionPane.showInputDialog("Ingrese el tipo de documento del cliente");
						String documentoBusqueda2 = JOptionPane.showInputDialog("Ingrese el documento del cliente");
						ConsultarCliente(vectorCliente, tipoDocBusqueda, documentoBusqueda2);
						
						
						break;
					case 0:
	                    
						System.out.println("Muchas gracias por usar nuestra app, hasta luego");
	                    
	                    break;
	                
					default:
						JOptionPane.showMessageDialog(null,"El valor ingresado no es una opcion de menu",  
								"Información",
								JOptionPane.WARNING_MESSAGE);
						break;
				}
    		}
        	
		
			catch(InputMismatchException | NumberFormatException e){
				JOptionPane.showMessageDialog(null,"Debe ingresar un valor numerico",  
						"Error",
						JOptionPane.ERROR_MESSAGE);
			}
    	} while (!opMenu.equals(0));
        
        
        in.close();
    	

    }
    
    public static ArrayList EditarCliente (ArrayList<Clientes> vectorCliente){
    

		String documentoBusqueda = JOptionPane.showInputDialog("Ingrese el documento del cliente que desea Editar");
		boolean encontrado = false;
		for(int i = 0; i < vectorCliente.size(); i++) {
			
			Clientes auxCliente = vectorCliente.get(i);
			
			if (documentoBusqueda.equalsIgnoreCase(auxCliente.getIdentificacion())) {
				
				auxCliente.setNombre(JOptionPane.showInputDialog("Nombre del cliente"));
				auxCliente.setDireccion(JOptionPane.showInputDialog("Dirección del cliente"));
				auxCliente.setTelefono(JOptionPane.showInputDialog("Telefono del cliente"));
				auxCliente.setCelular(JOptionPane.showInputDialog("Celular del cliente"));
				 
				 
				vectorCliente.add(i, auxCliente);
											
				
				encontrado = true;
				break;
			}
	
		}
    

		if (encontrado == false) {
			System.out.println("Cliente NO encontrado");
		
		}
		return vectorCliente;

    }
    
    public static ArrayList EliminarCliente(ArrayList<Clientes> vectorCliente, String documentoBusqueda) {
    	boolean encontrado1 = false;
		for(int i = 0; i < vectorCliente.size(); i++) {
			
			Clientes auxCliente = vectorCliente.get(i);
			
			if (documentoBusqueda.equalsIgnoreCase(auxCliente.getIdentificacion())) {
				
				vectorCliente.remove(i);
				encontrado1 = true;
				System.out.println("Cliente Eliminado correctamente");
				break;
				
			}

		}
		if (encontrado1 == false) {
			System.out.println("Cliente NO encontrado");
		
		}
		return vectorCliente;
		
    }
    
    public static void ConsultarCliente(ArrayList<Clientes> vectorCliente, String tipoDocBusqueda, String documentoBusqueda) {
    	
    	boolean encontrado2 = false;
		for(int i = 0; i < vectorCliente.size(); i++) {
			
			Clientes auxCliente = vectorCliente.get(i);
			
			if (tipoDocBusqueda.equalsIgnoreCase(auxCliente.getTipoDocumento()) &&
				documentoBusqueda.equalsIgnoreCase(auxCliente.getIdentificacion())) {
				System.out.println("Cliente encontrado # " + i + ":");
				System.out.println("Tipo Documento: " + auxCliente.getTipoDocumento());
				System.out.println("Documento: " + auxCliente.getIdentificacion());
				System.out.println("Nombre: " + auxCliente.getNombre());
				System.out.println("Dirección: " + auxCliente.getDireccion());
				System.out.println("Telefono: " + auxCliente.getTelefono());
				System.out.println("Celular: " + auxCliente.getCelular());
				try {
					for(int j = 0; j < auxCliente.getProductos().size(); j++) {
						
						Producto auxProductoCliente = auxCliente.getProductos().get(j);
						
						System.out.println("Producto " + j + ":");
						System.out.println("Id del producto: " + auxProductoCliente.getIdProducto());
						System.out.println("Nombre del Producto: " + auxProductoCliente.getNombre());
						System.out.println("Condiciones del producto: " + auxProductoCliente.getCondiciones());
						System.out.println("Caracteristicas del producto: " + auxProductoCliente.getCarateristicas());
						
					}
				}
				catch (NullPointerException e) {
					System.out.println("Este cliente no tiene productos asociados");
				}
			
											
				//rompe el ciclo mas cercano que tenga
				encontrado2 = true;
				 
			}

		}
		if (encontrado2 == false) {
			System.out.println("Cliente NO encontrado");
		}
		
		
    }
    

}