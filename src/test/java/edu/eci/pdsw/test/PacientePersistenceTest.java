package edu.eci.pdsw.test;

/*
 * Copyright (C) 2016 hcadavid
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

import edu.eci.pdsw.samples.entities.Comentario;
import edu.eci.pdsw.samples.entities.Usuario;
import edu.eci.pdsw.samples.persistence.DaoFactory;
import edu.eci.pdsw.samples.persistence.DaoUsuario;
import edu.eci.pdsw.samples.persistence.PersistenceException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hcadavid
 */
public class PacientePersistenceTest {
    
    public PacientePersistenceTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void databaseConnectionTest() throws IOException, PersistenceException{
        InputStream input = null;
        input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
        Properties properties=new Properties();
        properties.load(input);
        
        DaoFactory daof=DaoFactory.getInstance(properties);
        
        daof.beginSession();
                
        //IMPLEMENTACION DE LAS PRUEBAS
        fail("Pruebas no implementadas");


        daof.commitTransaction();
        daof.endSession();        
    }
    
    @Test
    public void usuarioNuevo() throws IOException, PersistenceException{
           DaoFactory daof = null;
        try {
            //Usuario NUevo
            InputStream input = null;
            input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
            Properties properties = new Properties();
            properties.load(input);
            daof = DaoFactory.getInstance(properties);
            daof.beginSession();
            DaoUsuario dao = daof.getDaoUsuario();
            Usuario us = new Usuario();
            dao.save(us);
            Usuario usua= dao.load("");// ???
            daof.commitTransaction();
            daof.endSession();
            Assert.assertEquals("No se guardo correctamente el  nuevo con mas de una consulta", "Nombre", usua.getNombre());
        } catch (PersistenceException | IOException ex) {
            try {
                if (daof != null) {
                    daof.rollbackTransaction();
                    daof.endSession();
                    System.out.println(ex.getMessage());
                }
            } catch (PersistenceException ex1) {
                Logger.getLogger(PacientePersistenceTest.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
      }   
    }
      