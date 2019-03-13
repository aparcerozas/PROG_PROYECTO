/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda;

import excepciones.Excepcion_Definida;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import javax.swing.JOptionPane;
import metodos.MetodosComunes;
import utilities.IO;

/**
 *
 * @author Fran
 */
public class Tienda  implements MetodosComunes {

    //Constructor que lee los datos de un fichero y nos lo guarda en el ArrayList de catálogo y nos muestra el catálogo.
    public Tienda() {
        lerObxectos();
    }


    /*Declaraciones*/
    public static ArrayList<Productos> catalogo = new ArrayList<>();
    Scanner sc;

    /*Métodos*/
    public ArrayList<Productos> getCatalogo() {
        return catalogo;
    }

    //Muestra nuestro ArrayList Catalogo
    @Override
    public void mostrar() {

        System.out.println("\n************Catalogo:**********\n");
        System.out.printf("%15s %30s %20s %20s", "Producto", "Precio Unidad", "Tipo", "NºUnidades\n");
        Object Datos[] = new Object[4];
        for (int i = 0; i < Tienda.catalogo.size(); i++) {
            System.out.printf("%20s", Datos[0] ="\033[31m"+ Tienda.catalogo.get(i).getNome());
            System.out.printf("%35s", Datos[1] ="\033[32m"+Tienda.catalogo.get(i).getPrecio() + "€");
            System.out.printf("%25s", Datos[2] = "\033[34m"+Tienda.catalogo.get(i).getTipo());
            System.out.printf("%25s", Datos[3] = "\033[36m"+Tienda.catalogo.get(i).getNumUnid()+"\u001B[0m");
            System.out.println("");
        }

    }

    //Añade producto a nuestro ArrayList "catalogo"
    @Override
    public void engadirProducto() {

        try {
            String nome = IO.introducirString(IO.VENTANA, "Introduzca el nombre del producto para añadirlo al catálogo.");
            excepcionIgualdadNombreProducto(nome);
            float precio = IO.introducirfloat(IO.VENTANA, "Introduzca precio de producto:");
            String tipo = selectTipo();
            excepcionPrecioNegativo(precio);

            int numUnid = IO.introducirInt(IO.VENTANA, "Número de unidades:");
            excepcionNumUnidadesNegativo(numUnid);
            Productos p = new Productos(nome, precio, tipo, numUnid);
            catalogo.add(p);

        } catch (Excepcion_Definida ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) { //Para que cuando metamos un String en cantidad o precio no rompa el programa.
            JOptionPane.showMessageDialog(null, "Introduzca un número.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Elimina el producto de nuestro Array y del fichero
    @Override
    public void eliminarProducto() {
        String nome = JOptionPane.showInputDialog("Introduzca nombre de producto:");
        for (Productos p : catalogo) {
            if (p.getNome() == nome) {
                catalogo.remove(p);
            }
        }
    }

    //Añadimos más unidades de un producto al ArrayList catálogo.
    @Override
    public void engadirUnidades() {

        try {

            String nome = IO.introducirString(IO.VENTANA, "Nombre del producto:");
            int numUnid = IO.introducirInt(IO.VENTANA, "Número de unidades:");
            excepcionNumUnidadesNegativo(numUnid);
            for (Productos productos : catalogo) {
                if (productos.getNome().equals(nome)) {
                    productos.setNumUnid(productos.getNumUnid() + numUnid);
                    break;
                }
            }
        } catch (Excepcion_Definida ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) { //Para que cuando metamos un String en cantidad o precio no rompa el programa.
            JOptionPane.showMessageDialog(null, "Introduzca un número.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Quitamos unidades de un producto al ArrayList catálogo.
    @Override
    public void quitarUnidades() {
        try {
            String nome = IO.introducirString(IO.VENTANA, "Nombre do producto:");
            int numUnid = IO.introducirInt(IO.VENTANA, "Número de unidades:");
            excepcionNumUnidadesNegativo(numUnid);
            Iterator it = catalogo.iterator();
            Productos product = new Productos();
            while (it.hasNext()) {
                product = (Productos) it.next();
                if (product.getNome().equals(nome)) {
                    product.setNumUnid(product.getNumUnid() - numUnid);
                    if (product.getNumUnid() == 0) {
                        Tienda.catalogo.remove(product);
                        break;
                    }
                }
            }

        } catch (Excepcion_Definida ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) { //Para que cuando metamos un String en cantidad o precio no rompa el programa.
            JOptionPane.showMessageDialog(null, "Introduzca un número.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Método static que busca el producto por el nombre y nos lo devuelve.
    public static Productos buscarProducto(String nome) {
        Iterator it = catalogo.iterator();
        Productos product = new Productos();
        while (it.hasNext()) {
            product = (Productos) it.next();
            if (product.getNome().equals(nome)) {
                break;
            } else {
                product = null;
            }
        }
        return product;
    }

    public String selectTipo() {
        String opcion = "";
        String[] Tipo = {"Ropa", "Alimento", "Electronica", "Higiene", "Deporte", "Informatica"};
        int opt = JOptionPane.showOptionDialog(null, "Seleccione una opción:", "Seleccionar tipo", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, Tipo, Tipo[0]);
        switch (opt) {
            case 0:
                opcion = "Ropa";
                break;
            case 1:
                opcion = "Alimento";
                break;
            case 2:
                opcion = "Electronica";
                break;
            case 3:
                opcion = "Higiene";
                break;
            case 4:
                opcion = "Deporte";
                break;
            case 5:
                opcion = "Informática";
                break;
        }
        return opcion;
    }

    public void clearConsole() {
        for (int i = 0; i < 30; i++) {
            System.out.println("");
        }
    }

    /**
     * ******EXCEPCIONES*************
     */
    //Trata la excepcion de que se repita algún nombre de producto.
    public void excepcionIgualdadNombreProducto(String nome) throws Excepcion_Definida {
        Iterator it = catalogo.iterator();
        Productos product = new Productos();
        while (it.hasNext()) {
            product = (Productos) it.next();
            if (product.getNome().equals(nome)) {
                throw new Excepcion_Definida("Ya existe un produto con el mismo nombre en el catálogo.");
            }
        }
    }

    //Trata la excepción de que el numero de unidades no sea negativo
    public void excepcionNumUnidadesNegativo(int numUnid) throws Excepcion_Definida {

        {
            if (numUnid < 0) {
                throw new Excepcion_Definida("El número de unidades no puede ser negativo");
            }
        }
    }

    //Trata la excepción de que el precio no sea negativo
    public void excepcionPrecioNegativo(float precio) throws Excepcion_Definida {

        {
            if (precio < 0) {
                throw new Excepcion_Definida("El precio no puede ser negativo");
            }
        }

    }

    /**
     * ****FICHEROS*********
     */
    //Lee los objetos del fichero catalogo y los añade a nuestro ArrayList catalogo
    public ArrayList<Productos> lerObxectos() {

        try {
            File fichero = new File("Catalogo.txt");
            sc = new Scanner(fichero);
            while (sc.hasNextLine()) {
                String[] productos = sc.nextLine().split(",");
                Productos p1 = new Productos(productos[0], Float.parseFloat(productos[1]), productos[2], Integer.parseInt(productos[3]));
                catalogo.add(p1);
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } finally {
            sc.close();
        }
        return catalogo;
    }
}
