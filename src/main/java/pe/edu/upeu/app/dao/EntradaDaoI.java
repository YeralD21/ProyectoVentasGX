/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.upeu.app.dao;

import java.util.List;
import pe.edu.upeu.app.modelo.EntradaTO;

/**
 *
 * @author ACER ASPIRE
 */
public interface EntradaDaoI {

    public int create(EntradaTO d);

    public int update(EntradaTO d);

    public int delete(String id) throws Exception;

    public List<EntradaTO> listCmb(String filter);

    public List<EntradaTO> listarEntrada();

    public EntradaTO buscarEntrada(String id_entrada);

    public void reportarEntrada();
}
