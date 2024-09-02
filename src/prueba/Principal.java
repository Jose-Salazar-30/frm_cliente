/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prueba;
import modelo.Conexion;

/**
 *
 * @author josej
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
/*        Conexion cn = new Conexion();
        cn.abrirConexion();
        cn.cerrarConexion();*/
        frm_estudiante frm = new frm_estudiante();
        frm.show();
    }
    
}
;