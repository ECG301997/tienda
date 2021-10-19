package Controlador;;
import Vista.Interfaz;
import Modelo.Producto;
import Vista.Inventario;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import javax.swing.table.DefaultTableModel;
public class CFactura{
        public static Interfaz tienda = new Interfaz();
        public static Inventario inv = new Inventario();  
        
        public static void iniciar(){
            inv.setVisible(false);
            tienda.setVisible(true);
            tienda.setLocationRelativeTo(null);
        }

        public static int suma(){
            int contar = tienda.factura.getRowCount();
            int suma = 0;
            for (int i = 0; i < contar; i++){
                suma=suma+Integer.parseInt(tienda.factura.getValueAt(i, 4).toString());
            }
            return suma;
        }
        
        public static void totalFactura(){
            tienda.total.setText(Integer.toString(suma()));
        }
        
        public static void borrar(){
           try {
            DefaultTableModel modelo=(DefaultTableModel) tienda.factura.getModel();
            int filas=tienda.factura.getRowCount();
            for (int i = 0;filas>i; i++) {
                modelo.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
            
        }
        public static void irInventario(){
            tienda.setVisible(false);
            inv.setVisible(true);
            inv.setLocationRelativeTo(null);
                                    
        }
       
        public static void agregar(){
            int id,pre,cant;
            String prod;
        try{
            id = Integer.parseInt(tienda.codigo.getText());
            prod = tienda.nombre.getText();
            cant = Integer.parseInt(tienda.cantidad.getText());
            pre = Integer.parseInt(tienda.precio.getText());
            tienda.Lis.add(new Producto(id,prod,cant,pre));
            Limpiar();
            verDatos();
            totalFactura();
            
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Verifica los datos");
        }
    }
        public static void verDatos(){
        String mat[][] = new String [tienda.Lis.size()][5];
        Producto aux;
        for(int i = 0;i<tienda.Lis.size();i++){
            aux = tienda.Lis.get(i);
            mat[i][0] = Integer.toString(aux.getCodigo());
            mat[i][1] = aux.getNombre();
            mat[i][2] = Integer.toString(aux.getCantidad());
            mat[i][3] = Integer.toString(aux.getPrecio());
            mat[i][4] = Integer.toString((aux.getCantidad() * aux.getPrecio())+((aux.getCantidad() * aux.getPrecio()*19/100)));
        }
                tienda.factura.setModel(new javax.swing.table.DefaultTableModel(
                mat,
            new String [] {
                "CODIGO", "PRODUCTO", "CANTIDAD", "PRECIO", "TOTAL"
            }
        ));        
    }
        public static void modificar(){
        int mod,selec,c,p;
        Producto aux;
        try {
            mod = tienda.factura.getSelectedRow();
            aux = tienda.Lis.get(mod);
            selec = Integer.parseInt(JOptionPane.showInputDialog(null,"seleccione la opción que desea modificar\n1.Cantidad\n2.Precio Unitario"));
            if(selec == 1){
                c = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese la nueva Cantidad del producto"));
                aux.setCantidad(c);
            }else if(selec == 2){
                p = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el nuevo precio del producto"));
                aux.setPrecio(p);
            }else{
                JOptionPane.showMessageDialog(null,"La elección realizada es inválida");
            }
                    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"no fue posible actualizar,\n Por favor escoja una fila");
        }
        verDatos();
        }
        
        public static void eliminar(){ 
        int c;
        int ok;
        try {
            c = tienda.factura.getSelectedRow();
            ok = JOptionPane.showConfirmDialog(null,"Desea eliminar la fila");
            if(ok == 0){
                tienda.Lis.remove(c);
                 verDatos();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Por favor Escoja una fila");
        }
        }
        
        public static void guardar(){
        File file = new File("Factura.txt");
        PrintWriter Escribe;
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (Exception e) {
            }
        }
        try {
            Producto aux;
            Escribe = new PrintWriter(file,"utf-8");
            for (int i = 0;i<tienda.Lis.size();i++){
                aux = tienda.Lis.get(i);
                aux.guardar(Escribe);
            }
            Escribe.close();
        } catch (Exception e) {
        }
    }
        
        public static void cerrar(){
         String botones[] ={"Si","No"};
        int n =JOptionPane.showOptionDialog(null,"Desea guardar la información?","Titulo",0,0,null, botones,null);
        if(n==0){
            guardar();
        }
        }
        
        public static void cargar(){
            File Archivo = new File("Factura.txt");
            FileReader Leer;
            BufferedReader Factura;
            Producto prod,aux = new Producto();
            try {
                Leer = new FileReader(Archivo);
                Factura = new BufferedReader(Leer);
                prod = aux.cargar(Factura);
                while(prod != null){
                    tienda.Lis.add(prod);
                    prod = aux.cargar(Factura);
                    }
                    Factura.close();
                    Leer.close();
            } catch (Exception e) {
            }
            
              verDatos();
            
        }
        public static void Limpiar(){
        tienda.codigo.setText("");
        tienda.nombre.setText("");
        tienda.cantidad.setText("");
        tienda.precio.setText("");
        tienda.codigo.requestFocus();
    }       
}
   


