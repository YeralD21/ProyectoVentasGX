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
import java.util.Collection;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import pe.edu.upeu.app.dao.conx.Conn;
import pe.edu.upeu.app.modelo.ProductoTO;
import pe.edu.upeu.app.util.ErrorLogger;

/**
 *
 * @author ACER ASPIRE
 */
public class ProductoDAO implements ProductoDaoI {

    Statement stmt = null;
    Vector columnNames;
    Vector visitdata;
    Connection connection = Conn.connectSQLite();
    static PreparedStatement ps;
    static ErrorLogger log = new ErrorLogger(ProductoDAO.class.getName());
    ResultSet rs = null;

    public ProductoDAO() {
        columnNames = new Vector();
        visitdata = new Vector();
    }

    @Override
    public int create(ProductoTO d) {
        int rsId = 0;
        String[] returns = {"dniruc"};
        String sql = "INSERT INTO producto(idProducto, nombre, id_categoria, id_marca, pu, utilidad, stock) "
                + "VALUES(?,?,?,?,?,?,?,)";
        int i = 0;
        try {
            ps = connection.prepareStatement(sql, returns);
            ps.setString(++i, d.getidProducto());
            ps.setString(++i, d.getnombre());
            ps.setString(++i, d.getid_categoria());
            ps.setString(++i, d.getid_marca());

            ps.setDouble(++i, d.getpu());
            ps.setDouble(++i, d.getutilidad());

            ps.setInt(++i, d.getstock());

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

    //@Override
    public int update(ProductoTO d) {
        System.out.println("actualizar d.getNombre: " + d.getnombre());
        int comit = 0;
        String sql = "UPDATE cliente SET "
                + "WHERE idProducto=?"
                + "nombre=?"
                + "id_categoria=? "
                + "id_marca=? "
                + "getpu=? "
                + "utilidad=? "
                + "stock=? ";

        int i = 0;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(++i, d.getidProducto());
            ps.setString(++i, d.getnombre());
            ps.setString(++i, d.getid_categoria());
            ps.setString(++i, d.getid_marca());

            ps.setDouble(++i, d.getpu());
            ps.setDouble(++i, d.getutilidad());

            ps.setInt(++i, d.getstock());
            comit = ps.executeUpdate();
        } catch (SQLException ex) {
            log.log(Level.SEVERE, "update", ex);
        }
        return comit;
    }

    @Override
    public int delete(String id) throws Exception {
        int comit = 0;
        String sql = "DELETE FROM producto WHERE nombre =?";
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
    public List<ProductoTO> listCmb(String filter) {
        List<ProductoTO> ls = new ArrayList();
        ls.add(new ProductoTO());
        ls.addAll(listarProducto());
        return ls;
    }

    //@Override
    public List<ProductoTO> listarproducto() {
        List<ProductoTO> listarProducto = new ArrayList();
        String sql = "SELECT * FROM cliente";
        try {
            connection = new Conn().connectSQLite();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ProductoTO cli = new ProductoTO();
                cli.setidProducto(rs.getInt("Producto"));
                cli.setnombre(rs.getString("nombre"));
                cli.setid_categoria(rs.getInt("id_Categoria"));
                cli.setpu(rs.getDouble("Precio_Unitario"));
                cli.setutilidad(rs.getDouble("utilidad"));
                cli.setstock(rs.getInt("STOCK"));

                listarProducto.add(cli);

            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return listarProducto;
    }

    @Override
    public ProductoDaoI buscarProducto(String nombre) {
        ProductoTO producto = new ProductoTO();
        String sql = "SELECT * FROM cliente WHERE dniruc = ?";
        try {
            connection = new Conn().connectSQLite();
            ps = connection.prepareStatement(sql);
            String idProducto = null;
            ps.setString(1, idProducto);
            rs = ps.executeQuery();
            if (rs.next()) {
                producto.setidProducto(rs.getInt("Producto"));
                 producto.setnombre(rs.getString("nombre"));
                 producto.setid_categoria(rs.getInt("id_Categoria"));
                 producto.setpu(rs.getDouble("Precio_Unitario"));
                 producto.setutilidad(rs.getDouble("utilidad"));
                 producto.setstock(rs.getInt("STOCK"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return (ProductoDaoI) producto;
    }

    @Override
    public void reportarProducto() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int create(ProductoDaoI d) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Collection<? extends ProductoTO> listarProducto() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   

}
