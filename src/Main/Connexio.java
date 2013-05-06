/*
 * @author Marc
 */

package Main;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class Connexio
{
    public Connection con;
    public String servidor;
    public String baseDades;
    public String usuariBD;
    public String contrasenyaBD;
    
    public Connexio(String servidor, String baseDades, String usuariBD, String contrasenya)
    {
        this.servidor = servidor;
        this.baseDades = baseDades;
        this.usuariBD = usuariBD;
        this.contrasenyaBD = contrasenya;
    }
    
    public void connectarBD()
    {
        try{
            this.con = DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/"+this.baseDades,this.usuariBD,this.contrasenyaBD);
            
            System.out.println("Connexió amb la base de dades feta amb éxit!");
        }
        catch(SQLException e){
            printSQLException(e);
        }
    }
    
    public Vector getIdClientsClientsArray()
    {
        String consulta = "SELECT idClient FROM clients;";
        Statement stmt;
        Vector vectorIdClients = new Vector();
        
        try{
            stmt = this.con.createStatement();
            ResultSet rs = stmt.executeQuery(consulta);
            
            while(rs.next()){
                vectorIdClients.add(rs.getInt(1));
            }
            stmt.close();
        }catch (SQLException e){
            printSQLException(e);
        }finally{
            return vectorIdClients;
        }
    }
    
    public ArrayList<String> retornarRegistres(String consultaSQL)
    {
        Statement stmt=null;
        ResultSet rs;
        ArrayList<String> conjuntRegistres = new ArrayList<>();
        
        try{
            stmt = this.con.createStatement();
            rs = stmt.executeQuery(consultaSQL);
            
            while(rs.next()){
                for(int i=1; i<=12; i++){
                    conjuntRegistres.add(rs.getString(i));
                }
            }
            stmt.close();
        }catch (SQLException e){
            printSQLException(e);
        }finally{
            return conjuntRegistres;
        }  
    }
    
    public ResultSet retornarRegistresResultset(String consultaSQL)
    {
        Statement stmnt = null;
        ResultSet rst = null;
        Connection connn;
        
        try{
            connn = DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/"+this.baseDades,this.usuariBD,this.contrasenyaBD);
            stmnt = connn.createStatement();
            rst = stmnt.executeQuery(consultaSQL);
        }catch (SQLException e){
            printSQLException(e);
        }finally{
            return rst;
        }
    }
    
    public void actualitzarEstatRebut(int idRebut)
    {
        Statement stmt = null;
        ResultSet rs;
        try{
            stmt = this.con.createStatement();
            stmt.executeUpdate("UPDATE rebuts SET rebutGenerat = 1 WHERE idRebut = "+idRebut+";");
            stmt.close();
        }catch(SQLException e){
            printSQLException(e);
        }
    }
    
    
    public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                if (ignoreSQLException(((SQLException)e).getSQLState()) == false) {
                    e.printStackTrace(System.err);
                    System.err.println("SQLState: " + ((SQLException)e).getSQLState());
                    System.err.println("Error Code: " + ((SQLException)e).getErrorCode());
                    System.err.println("Message: " + e.getMessage());
                    Throwable t = ex.getCause();
                    while(t != null) {
                        System.out.println("Cause: " + t);
                        t = t.getCause();
                    }
                }
            }
        }
    }
    public static boolean ignoreSQLException(String sqlState) {
        if (sqlState == null) {
            System.out.println("The SQL state is not defined!");
            return false;
        }
        // X0Y32: Jar file already exists in schema
        if (sqlState.equalsIgnoreCase("X0Y32")){
            return true;
        }
        // 42Y55: Table already exists in schema
        if (sqlState.equalsIgnoreCase("42Y55")){
            return true;
        }
        return false;
    }
    
}
