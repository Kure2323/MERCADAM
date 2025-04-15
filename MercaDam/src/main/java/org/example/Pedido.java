package org.example;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Pedido {

    private HashMap<Producto, Integer> pedido;
    private double importe_total;


    public Pedido(HashMap<Producto, Integer> pedido, double importe_total) {
        this.pedido = pedido;
        this.importe_total = importe_total;
    }

    public Pedido() {
        pedido = new LinkedHashMap<>();
    }

    public void aplicarPromo3x2() {

        for (Producto ped : getPedido().keySet()) {

            if (getPedido().get(ped) % 3 == 0) {

                setImporte_total(getImporte_total()
                        - ((getPedido().get(ped)/3)*ped.getPrecio()));

            }

        }
    }

    public void aplicarPromo10() {

        setImporte_total(getImporte_total() - getImporte_total()*0.10);

    }


    public HashMap<Producto, Integer> getPedido() {
        return pedido;
    }

    public void setPedido(HashMap<Producto, Integer> pedido) {
        this.pedido = pedido;
    }

    public double getImporte_total() {
        return importe_total;
    }

    public void setImporte_total(double importe_total) {
        this.importe_total = importe_total;
    }
}
