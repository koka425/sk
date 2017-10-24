package com.example.javogx.sk.Datos;



public class Compra {
    private String name;
    private long id_compra;
    private int id_producto;
    private int precio;
    private int cantidad;
    private int total;

    public Compra(int id_compra, int id_producto, String name, int precio, int cantidad, int total) {
        this.id_compra = id_compra;
        this.id_producto = id_producto;
        this.name = name;
        this.precio = precio;
        this.cantidad = cantidad;
        this.total = total;
    }

    public int getIdProducto() {
        return id_producto;
    }

    public void setIdProducto(int id) {
        this.id_producto = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public long getIdCompra() {
        return id_compra;
    }

    public void setIdCompra(long id_compra) {
        this.id_compra = id_compra;
    }
}
