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

    /**
     * Constructor por defecto de Cliente donde, pedido es null y promociones es false.
     * @param usuario
     * @param contrasenya
     */
    public Cliente(String usuario, String contrasenya) {
        this.usuario=usuario;
        this.contrasenya=contrasenya;
        this.direccion="Calle falsa, 123";
    }

    /**
     * Crea una instancia de pedido para que el usuario pueda meter productos a la
     * cesta y realizar propiamente el pedido
     */
    public void crearPedido() {

        pedido = new Pedido();

    }

    /**
     * Pide por teclado el producto que se quiere meter
     * en la cesta y coteja que efectivamente existe en el enum.
     */
    public void insertarProducto() {

        System.out.println("=============================================");
        System.out.println();
        System.out.println("Elige un producto:");
        String prod = in.next();
        System.out.println();
        System.out.println("=============================================");

        for (Producto p : Arrays.stream(Producto.values()).toList()) {
            //En caso de coincidir se actualiza el importe total hasta ahora, se añade y sale del método
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

    /**
     * Añade el producto a la cesta, en caso de ya existir, se le suma 1 a su valor.
     * También se pregunta cada vez que se introduzca un producto si se quiere añadir otro.
     * @param p
     */
    private void opciones(Producto p) {

        //Añade el producto al Map y lo actualiza si ya existe
        pedido.getPedido().put(p,pedido.getPedido().getOrDefault(p, 0) + 1);

        System.out.println("Has añadido " + p.name() + " con un precio de " + p.getPrecio() + "€. Importe" +
                "total del carrito: " + pedido.getImporte_total() + ". " +
                "¿Quieres añadir más productos a tu carrito de la compra? [S/N]:");

        //En caso de seguir queriendo comprar te lleva a imprimirProductos, donde vuelve a empezar
        if (in.next().equalsIgnoreCase("s")) {
            System.out.println();
            AppZonaClientes.imprimirProductos();
        } else {

            //Muestra los productos que están en la cesta junto a su precio total
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
                    //Aplica las promociones de 3x2 y 10%
                    case "1":

                        aplicarPromociones();
                        mostrarListaProd();
                        break;

                    //Muestra una lista de los productos de la cesta ordenada de mayor a menor por su cantidad
                    case "2":

                        ordenarLista();

                        break;

                    //Muestra el mensaje de despedida y termina el programma ya que pone 'e'
                    // en false y sale del do-while
                    default:
                        AppZonaClientes.imprimirDespedida();
                        e = false;
                        break;
                }
                //Limpia el buffer, ahora ya empiezo a entender mejor el nextLine() jeje
                in.nextLine();

            } while (e);


        }
    }

    /**
     * Método que ordena la cesta de la compra por valor del Map, es decir, por la cantidad de cada uno de ellos
     * de mayor a menor.
     */
    private void ordenarLista() {
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
    }

    /**
     * Aplica ambas promociones, no sin antes verificar que no han sido aplicadas con anterioridad
     */
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

    /**
     * Muestra por pantalla la lista de productos de la cesta
     */
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
