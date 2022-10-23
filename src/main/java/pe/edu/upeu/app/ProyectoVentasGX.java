/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package pe.edu.upeu.app;

import pe.edu.upeu.app.dao.ClienteDAO;
import pe.edu.upeu.app.dao.ClienteDaoI;
import pe.edu.upeu.app.dao.EntradaDAO;
import pe.edu.upeu.app.dao.EntradaDaoI;
import pe.edu.upeu.app.gui.Login;
import pe.edu.upeu.app.modelo.ClienteTO;
import pe.edu.upeu.app.modelo.EntradaTO;

/**
 *
 * @author ACER ASPIRE
 */
public class ProyectoVentasGX {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        //new MsgBox("WELCOME", JOptionPane.WARNING_MESSAGE, "CronosGym.jpg");
        new Login().setVisible(true);
        
        
        ClienteDaoI dao= new ClienteDAO();
        for (ClienteTO listarCliente : dao.listarClientes()) {
            System.out.println(""+listarCliente.getDniruc()+"\t"+listarCliente.nombresrs);
         
    }
        
        
        
    }
    public static void main(Double[] args) {
        
        //new MsgBox("WELCOME", JOptionPane.WARNING_MESSAGE, "CronosGym.jpg");
        new Login().setVisible(true);
    EntradaDaoI dao= new EntradaDAO();
        for (EntradaTO listarEntrada : dao.listarEntrada()) {
            System.out.println(""+listarEntrada.getprecio1()+"\t"+listarEntrada.precio1);
}
    }
}

