/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodos;

import excepciones.Excepcion_Definida;

/**
 *
 * @author Fran
 */
public interface MetodosComunes {
    public void mostrar();

    public void engadirProducto();

    public void eliminarProducto();

    public void engadirUnidades();

    public void quitarUnidades();

    public void excepcionIgualdadNombreProducto(String nome) throws Excepcion_Definida;
}
