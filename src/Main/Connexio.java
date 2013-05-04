/*
 * @author Marc
 */

package Main;

import static Main.ClientsC.ignoreSQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;


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
            
            System.out.println("Connexió amb la base de dades feta amb éxit");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public ArrayList getIdClientsClientsArray() throws SQLException
    {
        String consulta = "SELECT idClient FROM clients";
        Statement stmt = null;
        ArrayList arrayIdClients = new ArrayList<String>();
        
        try{
            stmt = this.con.createStatement();
            ResultSet rs = stmt.executeQuery(consulta);
            
            while(rs.next()){
                arrayIdClients.add(rs.getInt(1));
            }
        }catch (SQLException e){
            printSQLException(e);
        }finally{
            stmt.close();
        }
        
        return arrayIdClients;
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
