package Main;

import java.util.Calendar;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;


public class CreacioFitxerNorma19
{
    public int idClientePresentador;
    public int idClienteOrdenante;
    public Connexio connexio;
    public Calendar calendariGlobal;
    
    
    public CreacioFitxerNorma19(int idClientePresentador,int idClienteOrdenante)
    {
        this.idClientePresentador = idClientePresentador;
        this.idClienteOrdenante = idClienteOrdenante;
    }
    
    public void start()
    {   
        this.connexio = new Connexio("localhost","daw_m4_uf6_pt1","usuari","contrasenya");
        this.crearIndividualObligatorio();
    }
    
    public void crearCapceleraPresentador()
    {
        ArrayList<String> consulta_AL_cPresentador = this.connexio.retornarRegistres("SELECT nom, cognoms, NIF, sufijo, entidad, oficina, telefon, email FROM clients WHERE idClient = '"+this.idClienteOrdenante+"';");
        
        String codigoDeRegistro = "51";
        String codigoDeDato = "80";
        String NIF_codigoPresentador = consulta_AL_cPresentador.get(2);
        String sufijo_codigoPresentador = consulta_AL_cPresentador.get(3);
        
        Calendar calendari = Calendar.getInstance();
        
        this.calendariGlobal = calendari;
        
        String diaActual = String.valueOf(calendari.DAY_OF_MONTH);
        String mesActual = String.valueOf(calendari.MONTH);
        String anyActual = String.valueOf(calendari.YEAR);
        String fechaConfeccionSoporte = diaActual + mesActual + anyActual;
        
        String nombreDelClientePresentador = consulta_AL_cPresentador.get(0); //OMPLIR i possar els "'espais blancs a la dreta' que facin falta". //Consulta BD Taula Clients.
        String entidadReceptora = consulta_AL_cPresentador.get(4);
        String oficina = consulta_AL_cPresentador.get(5);
        
        
        String liniaCapceleraDePresentador = codigoDeRegistro + codigoDeDato + NIF_codigoPresentador + sufijo_codigoPresentador +
                fechaConfeccionSoporte + espaisEnBlanc(6) + nombreDelClientePresentador + espaisEnBlanc(20) + entidadReceptora + oficina +
                espaisEnBlanc(12) + espaisEnBlanc(40) + espaisEnBlanc(14);
    }
    
    public void crearCapceleraOrdenante()
    {
        ArrayList<String> consulta_AL_cOrdenante = this.connexio.retornarRegistres("SELECT nom, cognoms, NIF, sufijo, entidad, oficina, DC, numeroDeCuenta, telefon, email FROM clients WHERE idClient = '"+this.idClientePresentador+"';");
        
        String codigoDeRegistro = "53";
        String codigoDeDato = "80";
        String NIF_codigoOrdenante = consulta_AL_cOrdenante.get(2);
        String sufijo_codigoOrdenante = consulta_AL_cOrdenante.get(3);
        
        Calendar calendari = Calendar.getInstance();
        String diaActual = String.valueOf(calendari.DAY_OF_MONTH);
        String mesActual = String.valueOf(calendari.MONTH);
        String anyActual = String.valueOf(calendari.YEAR);
        String fechaConfeccionSoporte = diaActual + mesActual + anyActual;
        
        String fechaDeCargo = fechaConfeccionSoporte;
        String nombreDelClienteOrdenante = consulta_AL_cOrdenante.get(1); //OMPLIR i possar els "'espais blancs a la dreta' que facin falta". //Consulta BD Taula Clients.
        String entidad_CCCClienteOrdenante = consulta_AL_cOrdenante.get(5);
        String oficina_CCCClienteOrdenante = consulta_AL_cOrdenante.get(6);
        String DC_CCCClienteOrdenante = consulta_AL_cOrdenante.get(7);
        String numeroCuenta_CCCClienteOrdenante = consulta_AL_cOrdenante.get(8);
        String procedimiento = "01";
        
        
        String liniaCapceleraDeOrdenante = codigoDeRegistro + codigoDeDato + NIF_codigoOrdenante + sufijo_codigoOrdenante +
                fechaConfeccionSoporte + fechaDeCargo + nombreDelClienteOrdenante + entidad_CCCClienteOrdenante + 
                oficina_CCCClienteOrdenante + DC_CCCClienteOrdenante + numeroCuenta_CCCClienteOrdenante + espaisEnBlanc(8) + procedimiento +
                espaisEnBlanc(10) + espaisEnBlanc(40) + espaisEnBlanc(14);
    }
    
    public void crearIndividualObligatorio()
    {
        java.util.Date utilDate = new java.util.Date();
        //La variable "sqlDate", és la dat ctual però en format SQL.
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        try{
            Statement stmt = null;
            ResultSet rs = null;
            Connexio connexio_2 = new Connexio("localhost","daw_m4_uf6_pt1","usuari","contrasenya");
            ResultSet consultaRegistresIO = connexio_2.retornarRegistresResultset("SELECT * FROM rebuts WHERE idClienteOrdenante = "+this.idClienteOrdenante+" AND fechaRecibo >= '"+sqlDate+"';");
            
            while(consultaRegistresIO.next()){
                int idClientConsulta = consultaRegistresIO.getInt(1);
                System.out.println(idClientConsulta);
            }
            
        }catch(SQLException e){
            printSQLException(e);
        }
        
        String codigoDeRegistro = "56";
        String codigoDeDato = "80";
        String NIF_codigoOrdenante = ""; //OMPLIR //Consulta BD Taula Clients.
        String sufijo_codigoOrdenante = ""; //OMPLIR
        String codigoDeReferencia = ""; //OMPLIR
        String nombreTitularDeLaDomiciliacion = ""; //OMPLIR //Consulta BD Taula Clients.
        String entidad_CCCAdeudo = ""; //OMPLIR //Consulta BD Taula Clients.
        String oficina_CCCAdeudo = ""; //OMPLIR //Consulta BD Taula Clients.
        String DC_CCCAdeudo = ""; //OMPLIR //Consulta BD Taula Clients.
        String numeroCuenta_CCCAdeudo = ""; //OMPLIR //Consulta BD Taula Clients.
        String importe = ""; //OMPLIR (potser s'ha de possar "decimal" o "int").
        String primerCampoConcepto = ""; //OMPLIR
        
        
        String liniaIndividualObligatorio = codigoDeRegistro + codigoDeDato + NIF_codigoOrdenante + sufijo_codigoOrdenante +
                codigoDeReferencia + nombreTitularDeLaDomiciliacion + entidad_CCCAdeudo + oficina_CCCAdeudo + DC_CCCAdeudo +
                numeroCuenta_CCCAdeudo + importe + espaisEnBlanc(16) + primerCampoConcepto + espaisEnBlanc(8);
    }
    
    public void crearTotalOrdenante()
    {
        String codigoDeRegistro = "58";
        String codigoDeDato = "80";
        String NIF_codigoOrdenante = ""; //OMPLIR //Consulta BD Taula Clients.
        String sufijo_codigoOrdenante = ""; //OMPLIR
        String sumaImportesOrdenante = ""; //OMPLIR (potser s'ha de possar "decimal" o "int").
        String numeroDomiciliacionesOrdenante = ""; //OMPLIR (potser s'ha de possar "decimal" o "int").
        String numeroTotalRegistroOrdenante = ""; //OMPLIR (potser s'ha de possar "decimal" o "int").
        
        
        //String liniaTotalOrdenante = 
    }
    
    
    public String espaisEnBlanc(int numeroEspais)
    {
        String cadenaEspais = "";
        
        for(int i=0; i<numeroEspais; i++){
            cadenaEspais += " ";
        }
        
        return cadenaEspais;
    }

    private void printSQLException(SQLException e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
