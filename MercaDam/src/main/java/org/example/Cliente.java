package org.example;

public class Cliente {

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
    }

    public void crearPedido() {



    }

    public void insertarProducto() {

    }

    public double importePedido() {
        return pedido.importe_total;
    }


}
