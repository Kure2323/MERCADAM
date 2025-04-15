package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Mercadam {

    static Random rdm = new Random();

    private static List<Cliente> clientes;
    static String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public Mercadam() {
        clientes = new ArrayList<>();
    }


    /**
     * Genera un cliente con un usuario y contraseña totalmente aleatorio
     * y lo añade a la lista de clientes
     */
    public static void generarClientes() {

        String usuario = "";
        String contra = "";

        for (int i = 0; i < 8; i++) {
            int num = rdm.nextInt(caracteres.length()-1);
            usuario = usuario + caracteres.substring(num,num+1);
        }
        for (int i = 0; i < 8; i++) {
            int num = rdm.nextInt(caracteres.length()-1);
            contra = contra + caracteres.substring(num,num+1);
        }


         clientes.add(new Cliente(usuario,contra));

    }


    public static List<Cliente> getClientes() {
        return Collections.unmodifiableList(clientes);
    }


}
