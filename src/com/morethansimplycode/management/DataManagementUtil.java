/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.morethansimplycode.management;

import com.morethansimplycode.data.Data;
import java.util.ArrayList;

/**
 *
 * @author Oscar
 */
public class DataManagementUtil {
    
    private static DataManagement dataManagment;
    // TODO Usar este listener para llamar a todo el Array de Listener
    private static DataListener dataListener;
    
    public static DataManagement getDataManagment() {
        return dataManagment;
    }
    
    public static void setDataManagment(DataManagement dataManagment) {
        DataManagementUtil.dataManagment = dataManagment;
    }    
    
    public static ArrayList<Data> recuperarDatos(String where) {
        
        return dataManagment.recuperarDatos(where);
    }
    
    public static void recoveryDataAsync(DataProcessor p, String where) {
        
        dataManagment.recoveryDataAsync(dataListener, p, where);
    }
    
    public static void recoveryDataAsync(DataProcessor p, String where, boolean cached) {
        
        dataManagment.recoveryDataAsync(dataListener, p, where, where);
    }
    
    public void recoveryDataAsync(DataProcessor p, String where, String key) {
        
        dataManagment.recoveryDataAsync(dataListener, p, where, true);

    }
    
    
    
    
    
    
    
    // Pasar esto a otra clase, o lo anterior
//    public static boolean ejecutarSentenciaUpdate(String textoSentencia) {
//
//        Statement sentenciaLocal;
//        ResultSet dev = null;
//        try {
//            
//            sentenciaLocal = DataManagementUtil.conexion.createStatement();
//            System.out.println(textoSentencia);
//            int result = sentenciaLocal.executeUpdate(textoSentencia);
//
//            return result == 1;
//        } catch(SQLException ex) {
//            Logger.getLogger(DataManagementUtil.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return false;
//    }
//
//    /**
//     * @param textoSentencia
//     * @return Devuelve un ResultSet con los datos de la consulta o null si hay una excepción
//     */
//    public synchronized static ResultSet ejecutarSentenciaQuery(String textoSentencia) {
//
//        Statement sentenciaLocal;
//        ResultSet dev = null;
//        try {
//            
//            sentenciaLocal = DataManagementUtil.conexion.createStatement();
//            System.out.println(textoSentencia);
//            dev = sentenciaLocal.executeQuery(textoSentencia);
//
//        } catch(SQLException ex) {
//            Logger.getLogger(DataManagementUtil.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return dev;
//    }
//
//    public static void cerrarConexion() {
//
//        try {
//
//            DataManagementUtil.conexion.commit();
//            DataManagementUtil.conexion.close();
//        } catch(SQLException ex) {
//            Logger.getLogger(DataManagementUtil.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public static boolean insertarDato(Data d) {
//
//        String[] claves = devuelveOrdenDeColumnas(d.getClass());
//
//        if(!comprobarExiste(d)) {
//
//            StringBuilder textoSentencia = construyeSentenciaInsert(d, claves);
//            DataManagementUtil.ejecutarSentenciaUpdate(textoSentencia.toString());
//            return comprobarExiste(d);
//        }
//        return false;
//    }
//
//    public static boolean comprobarExiste(Data d) {
//        String primaryKey = devuelveClave(d.getClass());
//        Statement sentenciaLocal;
//        try {
//            sentenciaLocal = DataManagementUtil.conexion.createStatement();
//            if(!primaryKey.contains(" ")) {
//
//                ResultSet rs = sentenciaLocal.executeQuery("Select " + primaryKey + " from " + devuelveNombreTablaDato(d.getClass())
//                        + " where " + primaryKey + " = "
//                        + ((d.get(primaryKey) instanceof String) ? "'" + d.get(primaryKey) + "'" : d.get(primaryKey)));
//
//                return rs.next();
//
//            } else {
//                String[] key = primaryKey.split(" ");
//                ResultSet rs = sentenciaLocal.executeQuery("Select " + key[0] + ", " + key[1] + " from " + devuelveNombreTablaDato(d.getClass())
//                        + " where " + key[0] + " = "
//                        + ((d.get(key[0]) instanceof String) ? "'" + d.get(key[0]) + "'" : d.get(key[0]))
//                        + " AND " + key[1] + " = "
//                        + ((d.get(key[1]) instanceof String) ? "'" + d.get(key[1]) + "'" : d.get(key[1]))
//                );
//
//                return rs.next();
//            }
//        } catch(SQLException ex) {
//            Logger.getLogger(DataManagementUtil.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return false;
//    }
//
//    private static StringBuilder construyeSentenciaInsert(Data d, String[] claves) {
//
//        StringBuilder textoSentencia = new StringBuilder("insert into ");
//        textoSentencia.append(devuelveNombreTablaDato(d.getClass()));
//        textoSentencia.append("(");
//        for(String clave :claves) {
//
//            textoSentencia.append(clave).append(",");
//        }
//        textoSentencia.replace(textoSentencia.length() - 1, textoSentencia.length(), ")");
//        textoSentencia.append(" VALUES(");
//        for(String clave :claves) {
//
//            Object rec = d.get(clave);
//            if(rec instanceof String || rec instanceof LocalDate) {
//
//                textoSentencia.append("'");
//                textoSentencia.append(rec.toString());
//                textoSentencia.append("'");
//            } else if(rec instanceof Integer) {
//
//                textoSentencia.append(rec);
//            } else if(rec instanceof Float) {
//
//                textoSentencia.append(rec);
//            } else {
//
//                textoSentencia.append(rec);
//            }
//            textoSentencia.append(" ,");
//        }
//        textoSentencia.replace(textoSentencia.length() - 2, textoSentencia.length(), ");");
//
//        return textoSentencia;
//    }
//
//    public static boolean updateDato(Data d) {
//
//        if(comprobarExiste(d)) {
//
//            return ejecutarSentenciaUpdate(construyeSentenciaUpdate(d).toString());
//        }
//
//        return false;
//    }
//
//    public static StringBuilder construyeSentenciaUpdate(Data d) {
//
//        String[] claves = devuelveOrdenDeColumnas(d.getClass());
//        StringBuilder textoSentencia = new StringBuilder("update ");
//        textoSentencia.append(devuelveNombreTablaDato(d.getClass()));
//        textoSentencia.append(" set ");
//        for(int i = 1;i < claves.length;i++) {
//
//            Object rec = d.get(claves[i]);
//            textoSentencia.append(claves[i]).append("=");
//
//            if(rec instanceof String || rec instanceof LocalDate) {
//
//                textoSentencia.append("'");
//                textoSentencia.append(rec.toString());
//                textoSentencia.append("'");
//            } else if(rec instanceof Integer) {
//
//                textoSentencia.append(rec);
//            } else if(rec instanceof Float) {
//
//                textoSentencia.append(rec);
//            } else {
//
//                textoSentencia.append(rec);
//            }
//            textoSentencia.append(" ,");
//        }
//
//        textoSentencia.replace(textoSentencia.length() - 2, textoSentencia.length(), " where ");
//        textoSentencia.append(claves[0]).append(" = ").append(d.get(claves[0]));
//        return textoSentencia;
//    }
//
//    public static String construyeSentenciaSelect(String[] claves, String nombreTabla) {
//
//        StringBuilder dev = new StringBuilder("Select ");
//
//        for(String clave :claves) {
//
//            dev.append(clave).append(",");
//        }
//
//        dev.replace(dev.length() - 1, dev.length(), " from " + nombreTabla);
//
//        return dev.toString();
//    }
//
//    public static String construyeSentenciaSelect(String[] claves, String nombreTabla, String where) {
//
//        StringBuilder dev = new StringBuilder("Select ");
//
//        for(String clave :claves) {
//
//            dev.append(clave).append(",");
//        }
//
//        dev.replace(dev.length() - 1, dev.length(), " from ");
//        dev.append(nombreTabla).append(" ").append(where);
//
//        return dev.toString();
//    }
//
//    private static String[] devuelveOrdenDeColumnas(Class<? extends Data> d) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    private static String devuelveNombreTablaDato(Class<? extends Data> d) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    private static String devuelveClave(Class<? extends Data> d) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//}
}