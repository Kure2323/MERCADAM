package org.example;

import java.util.HashMap;

public class Pedido {

    HashMap<Producto, Integer> pedido;
    double importe_total;


    public Pedido(HashMap<Producto, Integer> pedido, double importe_total) {
        this.pedido = pedido;
        this.importe_total = importe_total;
    }

    public void aplicarPromo3x2() {

    }

    public void aplicarPromo10() {

    }



}
