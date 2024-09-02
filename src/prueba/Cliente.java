package prueba;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import modelo.Conexion;

public class Cliente extends Persona{
    private int id;
    private String nit;
    
    Conexion cn;
    
    public Cliente(){}

    public Cliente(int id,String nit, String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento) {
        super(nombres, apellidos, direccion, telefono, fecha_nacimiento);
        this.nit = nit;
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }
    
    public DefaultTableModel leer(){
        DefaultTableModel tabla = new DefaultTableModel();
    
        try {
            cn = new Conexion();
            cn.abrirConexion();
            String query;
            query = "Select id_clientes as id,nit,nombres,apellidos,direccion,telefono,fecha_nacimiento from clientes;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
        
            String encabezado[] = {"id","Nit","Nombres","Apellidos","Direccion","Telefono","Nacimiento"};
            tabla.setColumnIdentifiers(encabezado);
            
            String datos[] = new String[7];
            
            while(consulta.next()){
                datos[0] = consulta.getString("id");
                datos[1] = consulta.getString("nit");
                datos[2] = consulta.getString("nombres");
                datos[3] = consulta.getString("apellidos");
                datos[4] = consulta.getString("direccion");
                datos[5] = consulta.getString("telefono");
                datos[6] = consulta.getString("fecha_nacimiento");
                tabla.addRow(datos);
            }
            cn.cerrarConexion();
            
        }catch(SQLException ex){
            cn.cerrarConexion();
            System.out.println("Error" + ex.getMessage());
        }
        return tabla;
    }
    
    @Override
    public void agregar(){
        try {
          PreparedStatement parametro;
          cn = new Conexion();
          cn.abrirConexion();
          String query;
          query = "insert into clientes(nit,nombres,apellidos,direccion,telefono,fecha_nacimiento) "+
                 "values(?,?,?,?,?,?);";
          parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
          parametro.setString(1, getNit());
          parametro.setString(2, getNombres());
          parametro.setString(3, getApellidos());
          parametro.setString(4, getDireccion());
          parametro.setString(5, getTelefono());
          parametro.setString(6, getFecha_nacimiento());
          
          int executar = parametro.executeUpdate();
          cn.cerrarConexion();
          JOptionPane.showMessageDialog(null, Integer.toString(executar) + " Registro Ingresado",
                  "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        }catch(HeadlessException | SQLException ex){
            System.out.println("Error"+ex.getMessage());
        }   
    }
    
    @Override
    public void actualizar(){
        try {
            PreparedStatement parametro;
          cn = new Conexion();
          cn.abrirConexion();
          String query;
          query = "update clientes SET nit = ?, nombres = ?, apellidos = ?, direccion = ?, telefono = ?, fecha_nacimiento = ?"+
                 "where id_clientes = ?";
          parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
          parametro.setString(1, getNit());
          parametro.setString(2, getNombres());
          parametro.setString(3, getApellidos());
          parametro.setString(4, getDireccion());
          parametro.setString(5, getTelefono());
          parametro.setString(6, getFecha_nacimiento());
          parametro.setInt(7, getId());
          
          int executar = parametro.executeUpdate();
          cn.cerrarConexion();
          JOptionPane.showMessageDialog(null, Integer.toString(executar) + " Registro Actualizado",
                  "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        }catch(HeadlessException | SQLException ex){
            System.out.println("Error"+ex.getMessage());
        }   
    }
    
    @Override
    public void eliminar(){
        
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            cn.abrirConexion();
            String query;
            query = "delete from clientes where id_clientes = ?";
            
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getId());
            
            int executar = parametro.executeUpdate();
            cn.cerrarConexion();
            JOptionPane.showMessageDialog(null,Integer.toString(executar) + " Registro Eliminado",
                "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } catch(HeadlessException | SQLException ex){
            System.out.println("Error"+ex.getMessage());
        }
    }

}   
