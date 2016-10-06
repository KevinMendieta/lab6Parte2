/*
 * Copyright (C) 2015 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.pdsw.samples.persistence.jdbcimpl;

import edu.eci.pdsw.samples.entities.Usuario;
import edu.eci.pdsw.samples.persistence.PersistenceException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import edu.eci.pdsw.samples.persistence.DaoUsuario;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hcadavid
 */
public class JDBCDaoUsuario implements DaoUsuario {

    Connection con;

    public JDBCDaoUsuario(Connection con) {
        this.con = con;
    }
        

    @Override
    public Usuario load(String email) throws PersistenceException {
        String sql = "SELECT email,nombre FROM usuarios WHERE email = ?";
        Usuario usuario = null;
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet result = ps.executeQuery();
            if(result.next()){
                usuario = new Usuario(result.getNString(1),result.getNString(2));
            }
        }catch(SQLException e){
            throw new PersistenceException(e.getMessage());
        }
        return usuario;
    }

    @Override
    public void save(Usuario u) throws PersistenceException {
        PreparedStatement ps;
        String sql = "INSERT INTO Usuaios(email,Nombre) VALUES (?,?)";
        
        try {
            Usuario us=load(u.getEmail());
            if(us!=null) throw new PersistenceException("Ya existe");
            ps=con.prepareStatement(sql);
            ps.setString(1, u.getEmail());
            ps.setString(2, u.getNombre());
            ps.execute();
            con.commit();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCDaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Usuario u) throws PersistenceException {
        PreparedStatement ps;
       
        
        
        /*try {
            
        } catch (SQLException ex) {
            throw new PersistenceException("An error ocurred while updating Usuario.",ex);
        } */
        throw new RuntimeException("No se ha implementado el metodo 'update' del DAOPAcienteJDBC");
    }
    
}
