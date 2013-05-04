/*
 * @author Marc
 */
package Main;


import java.util.ArrayList;
import java.sql.*;

public class Norma19
{ 
    public ArrayList<ClientsC> coleccioClients;
    public Connexio conn;
    public ResultSet rs;
    public Connection con;
    
    public void main() throws SQLException
    {
        crearObjectesClientBD();
    }
    
    public void crearObjecteClientNou(String ccc,String nif,String nom,String cognoms,String telefon,String email,boolean clientNou)
    {
        clientNou = true;
        ClientsC nouClient = new ClientsC(ccc, nif, nom, cognoms, telefon, email, clientNou);
        this.coleccioClients.add(nouClient);
    }
    public void crearObjectesClientBD() throws SQLException
    {
        conn = new Connexio("localhost","DAW_M4_UF6_Pt1","usuari","contrasenya");
        conn.connectarBD();
        seleccionarDades(this.con);
        crearObjectes();
    }
    public void seleccionarDades(Connection con) throws SQLException
    {
        String Consulta = "SELECT * FROM "+conn.baseDades+".clients";
        Statement stmt = null;
        try{
            stmt = conn.con.createStatement();
            this.rs = stmt.executeQuery(Consulta);
        }catch (SQLException e){
            conn.printSQLException(e);
        }
    }
    public void crearObjectes()
    {
        try{
            while(rs.next()){
                ClientsC nouClient = new ClientsC(rs.getString(5),rs.getString(4),rs.getString(2),rs.getString(3),rs.getString(6),rs.getString(7),false);
                coleccioClients.add(nouClient);
            }
        }catch (SQLException e){
            conn.printSQLException(e);
        }
    }
    
}