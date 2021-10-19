package Modelo;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class Producto {
    private int codigo;
    private String nombre;
    private int cantidad;
    private int precio;

    public Producto() {
    }
    
    
    public Producto(int codigo, String nombre, int cantidad, int precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
       
    }



    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getTotal() {
        return (precio * cantidad)+((precio * cantidad)*(19/100));
    }
    
       
        public void guardar(PrintWriter Escribe) {
        Escribe.println(codigo);
        Escribe.println(nombre);
        Escribe.println(cantidad);
        Escribe.println(precio);
        Escribe.println(cantidad * precio*(19/100));
    }
        public Producto cargar(BufferedReader Factura){
        int code;
        String name;
        int quantity;
        int price;
        try {
            code = Integer.parseInt(Factura.readLine());
            name = Factura.readLine();
            quantity = Integer.parseInt(Factura.readLine());
            price = Integer.parseInt(Factura.readLine());
            return new Producto(code,name,quantity,price);
                    
        } catch (Exception e) {
        }
        return null;
    }

}