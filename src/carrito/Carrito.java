/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carrito;

import excepciones.Excepcion_Definida;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import metodos.MetodosComunes;
import tienda.Productos;
import tienda.Tienda;
import utilities.IO;

/**
 *
 * @author Fran
 */
public class Carrito  implements MetodosComunes{
    
    //Instancia de ArrayList de Productos
    ArrayList<Productos> carrito = new ArrayList<>();
    //Atributo para guardar el precio de los artículos del carrito.
    private float precio;

    //Mètodo que nos muestra los productos del ArrayList carrito formateados.
    @Override
    public void mostrar() {
        System.out.println("\n************Carrito:************\n");
        System.out.printf("%15s %30s %20s %20s", "Producto", "Precio Unidad", "Tipo", "NºUnidades\n");
        Object Datos[] = new Object[4];
        for (int i = 0; i < carrito.size(); i++) {
            System.out.printf("%20s", Datos[0] = "\033[31m"+carrito.get(i).getNome());
            System.out.printf("%35s", Datos[1] ="\033[32m"+ carrito.get(i).getPrecio() + "€");
            System.out.printf("%25s", Datos[2] ="\033[34m"+ carrito.get(i).getTipo());
            System.out.printf("%25s", Datos[3] = "\033[36m"+carrito.get(i).getNumUnid());
            System.out.println("");
        }
        System.out.println("\nPrecio Total: " + calcularPrecio());
    }

    //Método que añade un producto al carrito, restando la cantidad de unidades seleccionadas 
    //al catálogo de la tienda, tratando las excepciones.
    @Override
    public void engadirProducto() {

        try {
            String nome = IO.introducirString(IO.VENTANA, "¿Qué producto desea añadir?");
            excepcionIgualdadNombreProducto(nome);
            int numUnid = IO.introducirInt(IO.VENTANA, "Número de unidades:");
            excepcionAñadirNumUnidades(nome, numUnid);
            for (Productos p : Tienda.catalogo) {
                if (p.getNome().equals(nome)) {
                    Productos nuevo = new Productos(p.getNome(), p.getPrecio(), p.getTipo(), numUnid);
                    carrito.add(nuevo);
                    p.setNumUnid(p.getNumUnid() - numUnid);
                    if (p.getNumUnid() == 0) {
                        Tienda.catalogo.remove(p);

                    }
                    break;
                }
            }

        } catch (Excepcion_Definida ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Introduzca un número.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Método que aelimina un producto del carrito, restando la cantidad de unidades seleccionadas 
    //al catálogo de la tienda, tratando las excepciones.
    @Override
    public void eliminarProducto() {
        String nome = IO.introducirString(IO.VENTANA, "¿Qué producto desea eliminar?");
        Iterator it = carrito.iterator();
        Productos product = new Productos();
        while (it.hasNext()) {
            product = (Productos) it.next();
            if (product.getNome().equals(nome)) {
                Productos p = new Productos();
                p = Tienda.buscarProducto(nome);
                if (p == null) {
                    Productos nuevo = new Productos(product.getNome(), product.getPrecio(), product.getTipo(), product.getNumUnid());
                    Tienda.catalogo.add(nuevo);
                } else {
                    p.setNumUnid(p.getNumUnid() + product.getNumUnid());
                }
                carrito.remove(product);
                            break;//Para que si ya lo encontró no siga iterando
            }
        }
    }

    //Método que añade unidades de un producto al carrito, restando la cantidad de unidades seleccionadas 
    //al catálogo de la tienda, tratando las excepciones.
    @Override
    public void engadirUnidades() {
        try {
            String nome = IO.introducirString(IO.VENTANA, "Nombre del producto:");
            int numUnid = IO.introducirInt(IO.VENTANA, "Número de unidades:");
            excepcionAñadirNumUnidades(nome, numUnid);
            for (Productos productos : carrito) {
                if (productos.getNome().equals(nome)) {
                    productos.setNumUnid(productos.getNumUnid() + numUnid);
                    Productos p = new Productos();
                    p = Tienda.buscarProducto(nome);
                    p.setNumUnid(p.getNumUnid() - numUnid);
                    if (p.getNumUnid() == 0) {
                        Tienda.catalogo.remove(p);

                    }
                    break;
                }
            }
        } catch (Excepcion_Definida ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Introduzca un número.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
//Calcular precio actual de los productos del carrito

    public float calcularPrecio() {
        float precio = 0f;
        Iterator it = carrito.iterator();
        Productos product = new Productos();
        while (it.hasNext()) {
            product = (Productos) it.next();
            precio = precio + product.getPrecio() * product.getNumUnid();
        }
        return precio;
    }

    //Método que quita unidades de un producto al carrito, sumando la cantidad de unidades seleccionadas 
    //al catálogo de la tienda, tratando las excepciones.
    @Override
    public void quitarUnidades() {
        try {
            String nome = IO.introducirString(IO.VENTANA, "Nombre del producto:");
            int numUnid = IO.introducirInt(IO.VENTANA, "Número de unidades:");
            excepcionQuitarNumUnidades(nome, numUnid);
            for (Productos product : carrito) {
                if (product.getNome().equals(nome)) {
                    product.setNumUnid(product.getNumUnid() - numUnid);
                    Productos p = new Productos();
                    p = Tienda.buscarProducto(nome);
                    if (p == null) {
                        Productos nuevo = new Productos(product.getNome(), product.getPrecio(), product.getTipo(), numUnid);
                        Tienda.catalogo.add(nuevo);
                    } else {
                        p.setNumUnid(p.getNumUnid() + numUnid);
                    }
                    if (product.getNumUnid() == 0) {
                        carrito.remove(product);
                    }
                }
                break;
            }
        } catch (Excepcion_Definida ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) { //Para que cuando metamos un String en cantidad o precio no rompa el programa.
            JOptionPane.showMessageDialog(null, "Introduce un número.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * *****EXCEPCIONES*****
     */
    //Trata que no se repita el producto, podremos añadir unidades solamente
    @Override
    public void excepcionIgualdadNombreProducto(String nome) throws Excepcion_Definida {
        Iterator it = carrito.iterator();
        Productos product = new Productos();
        while (it.hasNext()) {
            product = (Productos) it.next();
            if (product.getNome().equals(nome)) {
                throw new Excepcion_Definida("Ya existe un produto en el carrito con el mismo nombre.");
            }
        }
    }

    //Trata que el número de unidades no pueda ser negativo y que elnúmero de unidades que queramos añadir al carro no pueda ser mayor de las unidades que hay en el catalogo.
    public void excepcionAñadirNumUnidades(String nome, int numUnid) throws Excepcion_Definida {

        Iterator it = Tienda.catalogo.iterator();
        Productos product = new Productos();
        while (it.hasNext()) {
            product = (Productos) it.next();
            if (product.getNome().equals(nome)) {
                if (numUnid < 0) {
                    throw new Excepcion_Definida("El número de unidades no puede ser negativo");
                } else if (product.getNumUnid() < numUnid) {
                    throw new Excepcion_Definida("El número de unidades en el catálogo es menor del que pides.");
                }
            }
        }

    }

    //Trata que el número de unidades que queremos quitar no sea mayor del que hay y que este no sea un número negativo.
    public void excepcionQuitarNumUnidades(String nome, int numUnid) throws Excepcion_Definida {
        Iterator it = carrito.iterator();
        Productos product = new Productos();
        while (it.hasNext()) {
            product = (Productos) it.next();
            if (product.getNome().equals(nome)) {
                if (numUnid < 0) {
                    throw new Excepcion_Definida("El número de unidades no puede ser negativo");
                } else if (product.getNumUnid() < numUnid) {
                    throw new Excepcion_Definida("El número de unidades en el carrito es menor del que quieres eliminar.");
                }
            }
        }
    }

    /**
     * ****FICHEROS*********
     */
    //Escribimos los productos del ArrayList en el fichero catalogo
    public File escribirProductos() {
        File fichero = null;
        PrintWriter p = null;
        try {
            fichero = new File("Carrito.txt");
            p = new PrintWriter(fichero);
            for (Productos lista : carrito) {
                p.println(lista.getNome() + "," + lista.getPrecio() + "," + lista.getTipo() + "," + lista.getNumUnid());
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "erro" + ex.getMessage());
        } finally {
            p.close();
        }
        return fichero;

    }
}
