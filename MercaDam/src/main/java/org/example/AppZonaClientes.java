package org.example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class AppZonaClientes {

    static Scanner in = new Scanner(System.in);

    //Cliente que inicia sesión
    private static Cliente cliente;
    String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";


    public static void main(String[] args) {

        Mercadam merca = new Mercadam();
        Mercadam.generarClientes();
        Mercadam.generarClientes();

        System.out.println(Mercadam.getClientes());

        autenticacion(Mercadam.getClientes());

    }

    /**
     * Método para verificar un usuario, para iniciar sesión.
     * Toma por parámetro la lista de clientes con sus contraseñas,
     * en caso de iniciar sesión le da a 'cliente' el valor del cliente de la sesión.
     * @param clientes
     */
    public static void autenticacion(List<Cliente> clientes) {

        System.out.println("*** COMPRA ONLINE DE MERCADAM ***");
        String user;
        String pass;


        //El usuario tiene 3 intentos para introducir correctamente las credenciales, de lo contrario
        //se terminará el programa
        patatita:
        for (int i = 0; i<3 ; i++) {


            //Pido las credenciales del usuario
            System.out.println();
            System.out.println("Usuario:");
            user = in.next();
            System.out.println("Contraseña:");
            pass = in.next();


            //Y las cotejo con la lista de clientes existente
            for (Cliente c : clientes) {
                if (c.getUsuario().equals(user) && c.getContrasenya().equals(pass)) {
                    System.out.println();
                    System.out.println("BIENVENID@ " + user +"!");
                    System.out.println();

                    //Otorga a cliente el objeto del cliente que ha iniciado sesión
                    cliente = c;
                    iniciarCompra();
                    break patatita;
                }
            }

            //Cuando NO coinciden las credenciales
            if (i != 2) {
                System.out.println("Algo no coincide o no existe! Vuelve a intentarlo...");
                System.out.println();
                in.nextLine();
            } else { //Cuando NO coinciden las credenciales y ya no hay más intentos
                System.out.println();
                System.out.println("ERROR DE AUTENTICACIÓN.");
                System.out.println();
                return;
            }

        }

    }

    /**
     * Inicia el programa de compra de productos
     */
    public static void iniciarCompra() {

        cliente.crearPedido();
        imprimirProductos();

    }

    /**
     * Imprime en la terminal la lista de productos a añadir en la lista de la compra con
     * sus respectivos precios.
     * LLeva a 'insertar producto'
     */
    public static void imprimirProductos() {



        System.out.println("Añade productos a tu lista de la compra...");
        for (Producto p : Arrays.stream(Producto.values()).toList()) {
            System.out.println();
            System.out.println("    " + p.name() + " precio (" + p.getPrecio() + "€),");
        }
        System.out.println();


        cliente.insertarProducto();



    }

    /**
     * Simplemente muestra por pantalla el mensaje de despedida.
     */
    public static void imprimirDespedida() {
        System.out.println("GRACIAS POR SU PEDIDO. Se lo mandaremos a la dirección " + cliente.getDireccion() + ".");

    }

}
