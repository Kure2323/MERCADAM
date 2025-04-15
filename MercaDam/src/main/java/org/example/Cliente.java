package org.example;

import jdk.jshell.spi.SPIResolutionException;

import java.util.*;

public class Cliente {

    static Scanner in = new Scanner(System.in);

    private String usuario;
    private String contrasenya;
    private String direccion;
    private Pedido pedido;
    private boolean promociones;


    public Cliente(String usuario, String contrasenya,
                   String direccion, Pedido pedido,
                   boolean promociones) {
        this.usuario = usuario;
        this.contrasenya = contrasenya;
        this.direccion = direccion;
        this.pedido = pedido;
        this.promociones = promociones;
    }

    public Cliente(String usuario, String contrasenya) {
        this.usuario=usuario;
        this.contrasenya=contrasenya;
        this.direccion="Calle falsa, 123";
    }

    public void crearPedido() {

        pedido = new Pedido();

    }

    public void insertarProducto() {

        System.out.println("=============================================");
        System.out.println();
        System.out.println("Elige un producto:");
        String prod = in.next();
        System.out.println();
        System.out.println("=============================================");

        for (Producto p : Arrays.stream(Producto.values()).toList()) {
            if (p.name().equalsIgnoreCase(prod)) {
                pedido.setImporte_total(pedido.getImporte_total() + p.getPrecio());

                opciones(p);
                return;
            }
        }
        System.out.println();
        System.out.println("El producto no existe! Elige otro.");
        System.out.println();
        in.nextLine();
        AppZonaClientes.imprimirProductos();

    }

    private void opciones(Producto p) {

        pedido.getPedido().put(p,pedido.getPedido().getOrDefault(p, 0) + 1);

        System.out.println("Has añadido " + p.name() + " con un precio de " + p.getPrecio() + "€. Importe" +
                "total del carrito: " + pedido.getImporte_total() + ". " +
                "¿Quieres añadir más productos a tu carrito de la compra? [S/N]:");
        if (in.next().equalsIgnoreCase("s")) {
            System.out.println();
            AppZonaClientes.imprimirProductos();
        } else {

            mostrarListaProd();

            boolean e = true;

            do {

                System.out.println(
                        "\n========================================\n\n" +
                                "¿QUÉ DESEA HACER?\n\n" +
                                "    [1]. Aplicar promo.\n" +
                                "    [2]. Mostrar resumen ordenado por uds.\n" +
                                "    [3]. Terminar pedido.\n\n" +
                                "========================================\n\n" +
                                "    Elige una opción:\n\n" +
                                "========================================\n"
                );

                switch (in.next()) {
                    case "1":

                        aplicarPromociones();
                        mostrarListaProd();
                        break;

                    case "2":

                        List<Map.Entry<Producto, Integer>> lista = new ArrayList<>(pedido.getPedido().entrySet());
                        lista.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

                        System.out.println();
                        System.out.println("========================================");
                        System.out.println();
                        System.out.println("RESUMEN DE TU CARRITO DE LA COMPRA:");
                        System.out.println();
                        System.out.println("Productos:");
                        System.out.println();

                        for (Map.Entry<Producto, Integer> entry : lista) {
                            System.out.println(entry.getValue() + " " + entry.getKey() + " " + entry.getKey().getPrecio() + "€");
                            System.out.println();
                        }

                        break;
                    default:
                        AppZonaClientes.imprimirDespedida();
                        e = false;
                        break;
                }
                in.nextLine();

            } while (e);


        }
    }

    private void aplicarPromociones() {
        if (!promociones) {

            pedido.aplicarPromo3x2();
            pedido.aplicarPromo10();

            promociones = true;

            System.out.println("\n PROMO 3X2 y 10% APLICADAS. \n");


        } else {
            System.out.println("YA HAS APLICADO TUS PROMOS.");
        }
    }

    public void mostrarListaProd() {

        System.out.println();
        System.out.println("========================================");
        System.out.println();
        System.out.println("RESUMEN DE TU CARRITO DE LA COMPRA:");
        System.out.println();
        System.out.println("Productos:");
        System.out.println();

        for (Producto p : pedido.getPedido().keySet()) {
            System.out.println(pedido.getPedido().get(p) + " " + p + " " + p.getPrecio());
            System.out.println();
        }
        System.out.println("IMPORTE TOTAL: " + pedido.getImporte_total() + "€");
    }

    public double importePedido() {
        return pedido.getImporte_total();
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public String getDireccion() {
        return direccion;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public boolean isPromociones() {
        return promociones;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "usuario='" + usuario + '\'' +
                ", contrasenya='" + contrasenya + '\'' +
                ", direccion='" + direccion + '\'' +
                ", pedido=" + pedido +
                ", promociones=" + promociones +
                '}';
    }
}
