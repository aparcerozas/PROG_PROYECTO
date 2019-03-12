package proyecto_carro;

import carrito.Carrito;
import javax.swing.JOptionPane;
import tienda.Tienda;

/**
 *
 * @author Fran
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Tienda tienda = new Tienda();
        Carrito carro = new Carrito();
        String opcion = "";
        do {
            tienda.clearConsole();
            carro.mostrar();
            tienda.mostrar();
            opcion = JOptionPane.showInputDialog("1.Añadir productos al catálogo de tienda."//Añadir artículos a la tienda, ArrayList y al fichero
                    //Mostramos el catálogo de la tienda, desde el ArrayList importando los datos del fichero catalogo
                    + "\n2.Agregar o Quitar unidades de productos a la tienda."
                    //Guardamos los productos seleccionados, preguntando el nombre y número de unidades(restaremos este número de unidades 
                    //al ArrayList de catálogo y si llega a 0 eliminamos el producto) en un ArrayList carrito, 
                    //y mostramos lo que hay en el carrito y e precio total.
                    + "\n3.Añadir productos al carrito."
                    //Eliminamos el producto del ArrayList de carrito,sumarle las unidadesal arrayListCatalogo
                    //Restamos el número de unidades determinado al ArrayList de Productos, actualizamos fichero catalogo
                    + "\n4.Eliminar producto del carrito."
                    //
                    + "\n5.Agregar o quitar productos al carrito"
                    /*Guardamos los productos del carrito en un fichero, 
                    * mostramos productos, precio total
                    * Actualizamos el fichero de la tienda 
                     */
                    + "\n6.Confirmar pedido.");
            switch (opcion) {

                case "1":
                    tienda.engadirProducto();

                    break;

                case "2":
                    //tienda.lerObxectos();
                    String[] option = {"Quitar", "Agregar"};
                    int op1 = JOptionPane.showOptionDialog(null, "Seleccione una opcion:", "Agregar o quitar unidades.", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[0]);
                    switch (op1) {
                        case 0:
                            tienda.quitarUnidades();

                            break;
                        case 1:

                            tienda.engadirUnidades();

                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Error.");
                    }
                    break;
                case "3":

                    carro.engadirProducto();
                    ;
                    //tienda.escribirProductos();
                    break;
                case "4":
                    carro.eliminarProducto();
                    //Actualizar JTable
                    carro.mostrar();
                    tienda.mostrar();
                    break;
                case "5":
                    String[] option2 = {"Quitar", "Agregar"};
                    int op2 = JOptionPane.showOptionDialog(null, "Seleccione una opcion:", "Agregar o quitar unidades.", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, option2, option2[0]);
                    switch (op2) {
                        case 0:
                            carro.quitarUnidades();

                            break;
                        case 1:
                            carro.engadirUnidades();

                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Error.");
                    }
                    break;
                case "6":
                    int op3 = JOptionPane.showConfirmDialog(null, "¿Desea confirmar el pedido?");
                    switch (op3) {
                        case 0:
                            carro.escribirProductos();
                            System.exit(6);
                            break;
                        case 1:
                            break;
                        case 2:
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Error.");
                    }

                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Error.");
            }
        } while (opcion != "10");

    }

}
