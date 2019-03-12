/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Fran
 */
public class IO {
    /**
     * Constante de tipo entero.
     */
    public static final int CONSOLA = 0;

    /**
     * Constante de tipo entero.
     */
    public static final int VENTANA = 1;

    /**
     * Método que guarda un int en una variable, que da la opción de
     * introducirla por consola o ventana.
     *
     * @param opcion CONSOLA, para utilizar la consola o VENTANA, para utilizar
     * la ventana.
     * @param mensaje Descripción que quiera poner.
     * @return Retorna un dato de tipo int.
     */
    public static int introducirInt(int opcion, String mensaje) {
        int dato = 0;
        switch (opcion) {
            case CONSOLA:
                System.out.println(mensaje);
                Scanner teclado = new Scanner(System.in);
                dato = Integer.parseInt(teclado.nextLine());
                break;
            case VENTANA:
                dato = Integer.parseInt(JOptionPane.showInputDialog(mensaje));
                break;
        }
        return dato;
    }

    /**
     * Método que devuelve un valor de tipo double introducido por consola o
     * ventana.
     *
     * @param opcion CONSOLA, para utilizar la consola o VENTANA, para utilizar
     * la ventana.
     * @param mensaje Descripción que quiera poner.
     * @return Retorna un dato de tipo double.
     */
    public static double introducirDouble(int opcion, String mensaje) {
        double dato = 0.0;
        switch (opcion) {
            case CONSOLA:
                System.out.println(mensaje);
                Scanner teclado = new Scanner(System.in);
                dato = Double.parseDouble(teclado.nextLine());
                break;
            case VENTANA:
                dato = Double.parseDouble(JOptionPane.showInputDialog(mensaje));
                break;
        }
        return dato;
    }

    /**
     * Método que devuelve un valor de tipo String introducido por consola o
     * ventana.
     *
     * @param opcion CONSOLA, para utilizar la consola o VENTANA, para utilizar
     * la ventana.
     * @param mensaje Descripción que quiera poner.
     * @return Retorna un dato de tipo String.
     */
    public static String introducirString(int opcion, String mensaje) {
        String msj = "";
        switch (opcion) {
            case CONSOLA:
                System.out.println(mensaje);
                Scanner teclado = new Scanner(System.in);
                msj = (teclado.nextLine());
                break;
            case VENTANA:
                msj = JOptionPane.showInputDialog(mensaje);
                break;
        }
        return msj;
    }

    /**
     * Método que devuelve un valor de tipo float introducido por consola o
     * ventana.
     *
     * @param opcion CONSOLA, para utilizar la consola o VENTANA, para utilizar
     * la ventana.
     * @param mensaje Descripción que quiera poner.
     * @return
     */
    public static float introducirfloat(int opcion, String mensaje) {
        float dato = 0;
        switch (opcion) {
            case CONSOLA:
                System.out.println(mensaje);
                Scanner teclado = new Scanner(System.in);
                dato = Float.parseFloat(teclado.nextLine());
                break;
            case VENTANA:
                dato = Float.parseFloat(JOptionPane.showInputDialog(mensaje));
                break;
        }
        return dato;
    }

    /**
     * Método para mostrar por pantalla o consola un dato de tipo int.
     *
     * @param opcion CONSOLA, para utilizar la consola o VENTANA, para utilizar
     * la ventana.
     * @param dato Dato a introducir.
     */
    public static void devolver(int opcion, int dato) {
        switch (opcion) {
            case CONSOLA:
                System.out.println(dato);
                break;
            case VENTANA:
                JOptionPane.showMessageDialog(null, dato);
                break;
        }
    }

    /**
     * Método para mostrar por pantalla o consola un dato de tipo String.
     *
     * @param opcion CONSOLA, para utilizar la consola o VENTANA, para utilizar
     * la ventana.
     * @param mensaje Dato a introducir.
     */
    public static void devolver(int opcion, String mensaje) {
        switch (opcion) {
            case CONSOLA:
                System.out.println(mensaje);
                break;
            case VENTANA:
                JOptionPane.showMessageDialog(null, mensaje);
                break;
        }
    }

    /**
     * Método para mostrar por pantalla o consola un dato de tipo float.
     *
     * @param opcion CONSOLA, para utilizar la consola o VENTANA, para utilizar
     * la ventana.
     * @param dato Dato a introducir.
     */
    public static void devolver(int opcion, float dato) {
        switch (opcion) {
            case CONSOLA:
                System.out.println(dato);
                break;
            case VENTANA:
                JOptionPane.showMessageDialog(null, dato);
                break;
        }
    }

    /**
     * Método para mostrar por pantalla o consola un dato de tipo double.
     *
     * @param opcion CONSOLA, para utilizar la consola o VENTANA, para utilizar
     * la ventana.
     * @param dato Dato a introducir.
     */
    public static void devolver(int opcion, double dato) {
        switch (opcion) {
            case CONSOLA:
                System.out.println(dato);
                break;
            case VENTANA:
                JOptionPane.showMessageDialog(null, dato);
                break;
        }
    }
    
}
