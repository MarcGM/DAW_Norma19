/*
 * @author Marc
 */

package Main;

import java.sql.*;


public class ClientsC
{
    private int idClient;
    private String ccc;
    private String nif;
    private String nom;
    private String cognoms;
    private String telefon;
    private String email;
    public Connection con;
    public String baseDades;
    public String usuariBD;
    public String contrasenyaBD;
    public boolean clientNou;
    
    public ClientsC(String ccc,String nif,String nom,String cognoms,String telefon,String email,boolean clientNou)
    {
        this.ccc = ccc;
        this.nif = nif;
        this.nom = nom;
        this.cognoms = cognoms;
        this.telefon = telefon;
        this.email = email;
        this.clientNou = clientNou;
        
        /* Aquest condicional, lo que fà es que si el objecte s'acaba de crear, o sigui que és "true", s'insereix a la base de dades.
         * Si no s'acaba de crear (és "false"), no s'insereix (ja que hi ha hi és).
         */
        if(this.clientNou == true){
            try{
                this.connectarBD();
                this.inserirClient(this.con);
            }catch (SQLException e){
                printSQLException(e);
            }
        }
    }
    
    public void donarAlta(Connection con) throws SQLException
    {
        Statement stmt = null;
        String query;
        
        try{
            stmt = con.createStatement();
            query = "UPDATE "+this.baseDades+".clients SET 'donatAlta' = '1' WHERE 'clients'.'idClient' = "+this.getIdClient();
        }catch (SQLException e){
            printSQLException(e);
        }finally{
            stmt.close();
        }
    }
    public void donarBaixa(Connection con) throws SQLException
    {
        Statement stmt = null;
        String query;
        
        try{
            stmt = con.createStatement();
            query = "UPDATE "+this.baseDades+".clients SET 'donatAlta' = '0' WHERE 'clients'.'idClient' = "+this.getIdClient();
        }catch (SQLException e){
            printSQLException(e);
        }finally{
            stmt.close();
        }
    }
    public void comprovarExisteix()
    {
        
    }
    //Aquest mètode serveix per a fer la connexió a la base de dades.
    public void connectarBD()
    {
        this.baseDades = "DAW_M4_UF6_Pt1";
        this.usuariBD = "usuari";
        this.contrasenyaBD = "contrasenya";
        try{
            this.con = DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/"+this.baseDades,this.usuariBD,this.contrasenyaBD);
            
            System.out.println("Connexió2 amb exit");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    //Aquest mètode dóna de alta un client.
    public void inserirClient(Connection con) throws SQLException
    {
        Statement stmt = null;
        String query;
        
        try{
            stmt = con.createStatement();
            query = "INSERT INTO `clients` (`nom`, `cognom`, `NIF`, `CCC`, `telefon`, `email`, `donatAlta`) "
             + "VALUES (`"+this.nom+"`, `"+this.cognoms+"`, `"+this.nif+"`, `"+this.ccc+"`, `"+this.telefon+"`, `"+this.email+"`, `1`);"; //El "0" significa "false".
            stmt.executeUpdate(query);
        }catch (SQLException e){
            printSQLException(e);
        }finally{
            stmt.close();
        }
        possarIdClient(con);
    }
    //Aquest mètode serveix per a assignar al paràmetre "idClient" d'aquest objecte, el idClient assignat per MySQL al inserir-lo a la base de dades.
    public void possarIdClient(Connection con) throws SQLException
    {
        Statement stmt = null;
        //En aquesta consulta SQL, fem servir "max" per a agafar el idClient mes alt (ja que serà el client que acabem, de inserir (perquè és auto-increment).
        String query = "SELECT MAX(idclient) FROM CLIENTS;";
        
        try{
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            this.idClient = rs.getInt(1);
        }catch (SQLException e){
            printSQLException(e);
        }finally{
            stmt.close();
        }
    }
    //Aquest mètode s'haurà de cridar quan s'hagin fet els setters pertinents.
    public void updateDadesClient(Connection con) throws SQLException
    {
        Statement stmt = null;
        String query = "UPDATE "+this.baseDades+".clients SET 'CCC' = '"+this.getCcc()+"', 'telefon' = '"+this.getTelefon()+"', 'email' = '"+this.getEmail()+";";
        
        try{
            stmt = con.createStatement();
            stmt.executeUpdate(query);
            
        }catch (SQLException e){
            printSQLException(e);
        }finally{
            stmt.close();
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

   /*
    * GETTERS: >>>
    */    
    public int getIdClient() {
        return idClient;
    }
    public String getCcc() {
        return ccc;
    }
    public String getNif() {
        return nif;
    }
    public String getNom() {
        return nom;
    }
    public String getCognoms() {
        return cognoms;
    }
    public String getTelefon() {
        return telefon;
    }
    public String getEmail() {
        return email;
    }
    
    /*
     * SETTERS: >>>
     */
    public void setCcc(String ccc) {
        this.ccc = ccc;
        try{
            updateDadesClient(this.con);
        }catch (SQLException e){
            printSQLException(e);
        }
    }
    public void setTelefon(String telefon) {
        this.telefon = telefon;
        try{
            updateDadesClient(this.con);
        }catch (SQLException e){
            printSQLException(e);
        }
    }
    public void setEmail(String email) {
        this.email = email;
        try{
            updateDadesClient(this.con);
        }catch (SQLException e){
            printSQLException(e);
        }
    } 
    
    
}
