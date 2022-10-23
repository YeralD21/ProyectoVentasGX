/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.upeu.app.dao;

import java.util.List;

/**
 *
 * @author ACER ASPIRE
 */
public interface ProductoDaoI {

   

  

        public int create(ProductoDaoI d);

        public int update(ProductoDaoI d);

        public int delete(String id) throws Exception;

        public List<ProductoDaoI> listCmb(String filter);

        public List<ProductoDaoI> listarProducto();

        public ProductoDaoI buscarProducto(String nombre);

        public void reportarProducto();

    public String getidProducto();
    
}
