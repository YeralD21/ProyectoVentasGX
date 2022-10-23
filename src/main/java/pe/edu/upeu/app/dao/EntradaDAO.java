/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.upeu.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import pe.edu.upeu.app.dao.conx.Conn;
import pe.edu.upeu.app.modelo.EntradaTO;
import pe.edu.upeu.app.util.ErrorLogger;

/**
 *
 * @author ACER ASPIRE
 */
public class EntradaDAO implements EntradaDaoI {

    Statement stmt = null;
    Vector columnNames;
    Vector visitdata;
    Connection connection = Conn.connectSQLite();
    static PreparedStatement ps;
    static ErrorLogger log = new ErrorLogger(EntradaDAO.class.getName());
    ResultSet rs = null;

    public EntradaDAO() {
        columnNames = new Vector();
        visitdata = new Vector();
    }

    @Override
    public int create(EntradaTO d) {
        int rsId = 0;
        String[] returns = {"precio1"};
        String sql = "INSERT INTO entrada(precio1, precio2, precio3) "
                + "VALUES(?,?,?)";
        int i = 0;
        try {
            ps = connection.prepareStatement(sql, returns);
            ps.setDouble(++i, d.getprecio1());
            ps.setDouble(++i, d.getprecio2());
            ps.setDouble(++i, d.getprecio3());
            rsId = ps.executeUpdate();// 0 no o 1 si commit
            try ( ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    rsId = rs.getInt(1);
                }
                rs.close();
            }
        } catch (SQLException ex) {
//System.err.println("create:" + ex.toString());
            log.log(Level.SEVERE, "create", ex);
        }
        return rsId;
    }

    @Override
    public int update(EntradaTO d) {
        System.out.println("actualizar d.getprecio1: " + d.getprecio1());
        int comit = 0;
        String sql = "UPDATE entrada SET "
                + "precio1=?, "
                + "precio2=? "
                + "WHERE precio3=? ";
        //+ "WHERE dniruc=?";
        int i = 0;
        try {
            ps = connection.prepareStatement(sql);
            ps.setDouble(++i, d.getprecio1());
            ps.setDouble(++i, d.getprecio2());
            ps.setDouble(++i, d.getprecio3());
            comit = ps.executeUpdate();
        } catch (SQLException ex) {
            log.log(Level.SEVERE, "update", ex);
        }
        return comit;

    }

    @Override
    public int delete(String id) throws Exception {
        int comit = 0;
        String sql = "DELETE FROM entrada WHERE precio3 = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            comit = ps.executeUpdate();
        } catch (SQLException ex) {
            log.log(Level.SEVERE, "delete", ex);
// System.err.println("NO del " + ex.toString());
            throw new Exception("Detalle:" + ex.getMessage());
        }
        return comit;
    }

    @Override
    public List<EntradaTO> listCmb(String filter) {
        List<EntradaTO> ls = new ArrayList();
        ls.add(new EntradaTO());
        ls.addAll(listarEntrada());
        return ls;
    }

    @Override
    public List<EntradaTO> listarEntrada() {
        List<EntradaTO> listarEntrada = new ArrayList();
        String sql = "SELECT * FROM cliente";
        try {
            connection = new Conn().connectSQLite();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                EntradaTO cli = new EntradaTO();
                cli.setprecio1(rs.getString("Solo Maquinas"));
                cli.setprecio2(rs.getString("Corredoras20MIN y Maquinas"));
                cli.setprecio3(rs.getString("Corredoras40MIN y Maquinas"));
                listarEntrada.add(cli);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return listarEntrada;
    }

    @Override
    public EntradaTO buscarEntrada(String id_entrada) {
        EntradaTO entrada = new EntradaTO();
        String sql = "SELECT * FROM cliente WHERE dniruc = ?";
        try {
            connection = new Conn().connectSQLite();
            ps = connection.prepareStatement(sql);
            ps.setString(1, id_entrada);
            rs = ps.executeQuery();
            if (rs.next()) {
                entrada.setprecio1(rs.getString("Solo Maquinas"));
                entrada.setprecio2(rs.getString("Corredoras20MIN y Maquinas"));
                entrada.setprecio3(rs.getString("Corredoras40MIN y Maquinas"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return entrada;
    }

    @Override
    public void reportarEntrada() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
