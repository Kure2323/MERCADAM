package org.example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class AppZonaClientes {

    static Scanner in = new Scanner(System.in);

    private static Cliente cliente;
    String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static void main(String[] args) {


        Mercadam merca = new Mercadam();
        Mercadam.generarClientes();
        Mercadam.generarClientes();

        System.out.println(Mercadam.getClientes());

        autenticacion(Mercadam.getClientes());





    }

    public static void autenticacion(List<Cliente> clientes) {

        System.out.println("*** COMPRA ONLINE DE MERCADAM ***");
        String user;
        String pass;

        patatita:
        for (int i = 0; i<3 ; i++) {

            System.out.println();
            System.out.println("Usuario:");
            user = in.next();
            System.out.println("Contraseña:");
            pass = in.next();

            for (Cliente c : clientes) {
                if (c.getUsuario().equals(user) && c.getContrasenya().equals(pass)) {
                    System.out.println();
                    System.out.println("BIENVENID@ " + user +"!");
                    System.out.println();
                    cliente = c;
                    iniciarCompra();
                    break patatita;
                }
            }
            if (i != 2) {
                System.out.println("Algo no coincide o no existe! Vuelve a intentarlo...");
                System.out.println();
                in.nextLine();
            } else {
                System.out.println();
                System.out.println("ERROR DE AUTENTICACIÓN.");
                System.out.println();
                return;
            }

        }

    }

    public static void iniciarCompra() {

        cliente.crearPedido();
        imprimirProductos();

    }

    public static void imprimirProductos() {



        System.out.println("Añade productos a tu lista de la compra...");
        for (Producto p : Arrays.stream(Producto.values()).toList()) {
            System.out.println();
            System.out.println("    " + p.name() + " precio (" + p.getPrecio() + "€),");
        }
        System.out.println();


        cliente.insertarProducto();



    }

    public static void imprimirDespedida() {
        System.out.println("GRACIAS POR SU PEDIDO. Se lo mandaremos a la dirección " + cliente.getDireccion() + ".");

    }

}
