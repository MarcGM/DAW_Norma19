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
    public EscriureFitxerN19 fitxerN19;
    
    public String liniaCapceleraDePresentador;
    public String liniaCapceleraDeOrdenante;
    public ArrayList<String> arrayLiniesFitxerIO;
    public String liniaTotalOrdenante;
    public String liniaTotalGeneral;
    
    public String nifCodigoOrdenanteCompartit;
    public String sufijoCodigoOrdenanteCompartit;
    public String nifCodigoPresentadorCompartit;
    public String sufijoCodigoPresentadorCompartit;
    
    public int quantIntTotalCOimporte;
    
    public int numDomiciliacionesCO = 0;
    
    public String sumaTotalImportes;
    
    public int numeroTotalRegistrosSoporte = 0;
    
    
    public CreacioFitxerNorma19(int idClientePresentador,int idClienteOrdenante)
    {
        this.idClientePresentador = idClientePresentador;
        this.idClienteOrdenante = idClienteOrdenante;
        this.quantIntTotalCOimporte = 0;
    }
    
    public void start()
    {   
        this.connexio = new Connexio("localhost","daw_m4_uf6_pt1","usuari","contrasenya");
        this.connexio.connectarBD();
        this.crearCapceleraPresentador();
        this.crearCapceleraOrdenante();
        this.crearIndividualObligatorio();
        this.crearTotalOrdenante();
        this.crearTotalGeneral();
        
        this.fitxerN19 = new EscriureFitxerN19(this.liniaCapceleraDePresentador, this.liniaCapceleraDeOrdenante, this. arrayLiniesFitxerIO, this.liniaTotalOrdenante, this.liniaTotalGeneral);
        this.fitxerN19.start();
    }
    
    public void crearCapceleraPresentador()
    {
        ArrayList<String> consulta_AL_cPresentador = this.connexio.retornarRegistres("SELECT nom, cognoms, NIF, sufijo, entidad, oficina, telefon, email FROM clients WHERE idClient = '"+this.idClientePresentador+"';");
        
        String codigoDeRegistro = "51";
        String codigoDeDato = "80";
        String NIF_codigoPresentador = consulta_AL_cPresentador.get(2);
        String sufijo_codigoPresentador = consulta_AL_cPresentador.get(3);
        
        this.nifCodigoPresentadorCompartit = NIF_codigoPresentador;
        this.sufijoCodigoPresentadorCompartit = sufijo_codigoPresentador;
        
        Calendar calendari = Calendar.getInstance();
        
        this.calendariGlobal = calendari;
        
        String diaActual = String.valueOf(calendari.DAY_OF_MONTH);
        String mesActual = String.valueOf(calendari.MONTH);
        String anyActual = String.valueOf(calendari.YEAR);
        String fechaConfeccionSoporte = diaActual + mesActual + anyActual;
        
        String nombreDelClientePresentador = consulta_AL_cPresentador.get(0);
        int llargada_nombreDelClientePresentador = nombreDelClientePresentador.length();
        int numEspais_nombreDelClientePresentador = 40 - llargada_nombreDelClientePresentador;
        nombreDelClientePresentador = nombreDelClientePresentador + espaisEnBlanc(numEspais_nombreDelClientePresentador);
        
        String entidadReceptora = consulta_AL_cPresentador.get(4);
        String oficina = consulta_AL_cPresentador.get(5);
        
        
        this.liniaCapceleraDePresentador = codigoDeRegistro + codigoDeDato + NIF_codigoPresentador + sufijo_codigoPresentador +
                fechaConfeccionSoporte + espaisEnBlanc(6) + nombreDelClientePresentador + espaisEnBlanc(20) + entidadReceptora + oficina +
                espaisEnBlanc(12) + espaisEnBlanc(40) + espaisEnBlanc(14);
        
        this.numeroTotalRegistrosSoporte++;
    }
    
    public void crearCapceleraOrdenante()
    {
        ArrayList<String> consulta_AL_cOrdenante = this.connexio.retornarRegistres("SELECT nom, cognoms, NIF, sufijo, entidad, oficina, DC, numeroDeCuenta, telefon, email FROM clients WHERE idClient = '"+this.idClienteOrdenante+"';");
        
        String codigoDeRegistro = "53";
        String codigoDeDato = "80";
        String NIF_codigoOrdenante = consulta_AL_cOrdenante.get(2);
        this.nifCodigoOrdenanteCompartit = NIF_codigoOrdenante;
        String sufijo_codigoOrdenante = consulta_AL_cOrdenante.get(3);
        this.sufijoCodigoOrdenanteCompartit = sufijo_codigoOrdenante;
        
        Calendar calendari = Calendar.getInstance();
        String diaActual = String.valueOf(calendari.DAY_OF_MONTH);
        String mesActual = String.valueOf(calendari.MONTH);
        String anyActual = String.valueOf(calendari.YEAR);
        String fechaConfeccionSoporte = diaActual + mesActual + anyActual;
        
        String fechaDeCargo = fechaConfeccionSoporte;
        
        String nombreDelClienteOrdenante = consulta_AL_cOrdenante.get(1);
        int llargada_nombreDelClienteOrdenante = nombreDelClienteOrdenante.length();
        int numEspais_nombreDelClienteOrdenante = 40 - llargada_nombreDelClienteOrdenante;
        nombreDelClienteOrdenante = nombreDelClienteOrdenante + espaisEnBlanc(numEspais_nombreDelClienteOrdenante);
        
        String entidad_CCCClienteOrdenante = consulta_AL_cOrdenante.get(5);
        String oficina_CCCClienteOrdenante = consulta_AL_cOrdenante.get(6);
        String DC_CCCClienteOrdenante = consulta_AL_cOrdenante.get(7);
        String numeroCuenta_CCCClienteOrdenante = consulta_AL_cOrdenante.get(8);
        String procedimiento = "01";
        
        
        this.liniaCapceleraDeOrdenante = codigoDeRegistro + codigoDeDato + NIF_codigoOrdenante + sufijo_codigoOrdenante +
                fechaConfeccionSoporte + fechaDeCargo + nombreDelClienteOrdenante + entidad_CCCClienteOrdenante + 
                oficina_CCCClienteOrdenante + DC_CCCClienteOrdenante + numeroCuenta_CCCClienteOrdenante + espaisEnBlanc(8) + procedimiento +
                espaisEnBlanc(10) + espaisEnBlanc(40) + espaisEnBlanc(14);
        
        this.numeroTotalRegistrosSoporte++;
    }
    
    public void crearIndividualObligatorio()
    {
        arrayLiniesFitxerIO = new ArrayList<String>();
        java.util.Date utilDate = new java.util.Date();
        //La variable "sqlDate", és la dat ctual però en format SQL.
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        try{
            Statement stmt = null;
            ResultSet rs = null;
            //Connexio connexio_2 = new Connexio("localhost","daw_m4_uf6_pt1","usuari","contrasenya");
            //connexio_2.connectarBD();
            ResultSet consultaRegistresIO = this.connexio.retornarRegistresResultset("SELECT * FROM rebuts WHERE idClienteOrdenante = "+this.idClienteOrdenante+" AND fechaRecibo >= '"+sqlDate+"';");
            
            while(consultaRegistresIO.next())
            {
                //Xivato pera saber quin rebut està fent actualment
                int idRebutConsulta = consultaRegistresIO.getInt(1);
                System.out.println(idRebutConsulta);
                ArrayList<String> consultaIO_CO = this.connexio.retornarRegistres("SELECT * FROM clients WHERE idClient = '"+consultaRegistresIO.getInt(2)+"';"); 
                
                String codigoDeRegistro = "56";
                String codigoDeDato = "80";
                String NIF_codigoOrdenante = consultaIO_CO.get(3);
                String sufijo_codigoOrdenante = consultaIO_CO.get(4);
                String codigoDeReferencia = consultaRegistresIO.getString(4);
                
                String nombreTitularDeLaDomiciliacion = consultaRegistresIO.getString(5);
                int llargada_nombreTitularDeLaDomiciliacion = nombreTitularDeLaDomiciliacion.length();
                int numEspais_nombreTitularDeLaDomiciliacion = 40 - llargada_nombreTitularDeLaDomiciliacion;
                nombreTitularDeLaDomiciliacion = nombreTitularDeLaDomiciliacion + espaisEnBlanc(numEspais_nombreTitularDeLaDomiciliacion);
                
                String entidad_CCCAdeudo = consultaRegistresIO.getString(6);
                String oficina_CCCAdeudo = consultaRegistresIO.getString(7);
                String DC_CCCAdeudo = consultaRegistresIO.getString(8);
                String numeroCuenta_CCCAdeudo = consultaRegistresIO.getString(9);
                
                String importe = consultaRegistresIO.getString(10);
                int quantIntimporte = Integer.parseInt(importe);
                this.quantIntTotalCOimporte = quantIntTotalCOimporte + quantIntimporte;
                
                String primerCampoConcepto = consultaRegistresIO.getString(11);
                int llargada_primerCampoConcepto = primerCampoConcepto.length();
                int numEspais_primerCampoConcepto = 40 - llargada_primerCampoConcepto;
                primerCampoConcepto = primerCampoConcepto + espaisEnBlanc(numEspais_primerCampoConcepto);

                String liniaIndividualObligatorio = codigoDeRegistro + codigoDeDato + NIF_codigoOrdenante + sufijo_codigoOrdenante +
                        codigoDeReferencia + nombreTitularDeLaDomiciliacion + entidad_CCCAdeudo + oficina_CCCAdeudo + DC_CCCAdeudo +
                        numeroCuenta_CCCAdeudo + importe + espaisEnBlanc(16) + primerCampoConcepto + espaisEnBlanc(8);
                
                arrayLiniesFitxerIO.add(liniaIndividualObligatorio);
                
                this.numDomiciliacionesCO++;
                this.numeroTotalRegistrosSoporte++;
            }
        }catch(SQLException e){
            printSQLException(e);
        }
        
        
    }
    
    public void crearTotalOrdenante()
    {
        String codigoDeRegistro = "58";
        String codigoDeDato = "80";
        String NIF_codigoOrdenante = this.nifCodigoOrdenanteCompartit;
        String sufijo_codigoOrdenante = this.sufijoCodigoOrdenanteCompartit;
        
        String sumaImportesOrdenante = String.valueOf(this.quantIntTotalCOimporte);
        int numEspaisBlancsSIO = 10 - sumaImportesOrdenante.length();
        sumaImportesOrdenante = espaisEnBlanc(numEspaisBlancsSIO)+ sumaImportesOrdenante;
        this.sumaTotalImportes = sumaImportesOrdenante;
        
        String numeroDomiciliacionesOrdenante = String.valueOf(this.numDomiciliacionesCO);
        int numEspaisBlancsNDO = 10 - numeroDomiciliacionesOrdenante.length();
        numeroDomiciliacionesOrdenante = espaisEnBlanc(numEspaisBlancsNDO)+numeroDomiciliacionesOrdenante;
        
        String numeroTotalRegistroOrdenante = numeroDomiciliacionesOrdenante;
        
        
        this.liniaTotalOrdenante = codigoDeRegistro + codigoDeDato + NIF_codigoOrdenante + sufijo_codigoOrdenante +
                        espaisEnBlanc(12) + espaisEnBlanc(40) + espaisEnBlanc(20) + sumaImportesOrdenante + espaisEnBlanc(6) + 
                        numeroDomiciliacionesOrdenante + numeroTotalRegistroOrdenante + espaisEnBlanc(20) + espaisEnBlanc(18);
        
        this.numeroTotalRegistrosSoporte++;
    }
    
    public void crearTotalGeneral()
    {
        this.numeroTotalRegistrosSoporte++;
        
        String codigoDeRegistro = "59";
        String codigoDeDato = "80";
        String NIF_codigoPresentador = this.nifCodigoPresentadorCompartit;
        String sufijo_codigoPresentador = this.sufijoCodigoPresentadorCompartit;
        String numeroDeOrdenantes = "0001";
        String totalImportes = this.sumaTotalImportes;
        
        String numeroTotalDeDomiciliaciones = String.valueOf(this.numDomiciliacionesCO);
        int numEspaisBlancsTG = 10 - numeroTotalDeDomiciliaciones.length();
        numeroTotalDeDomiciliaciones = espaisEnBlanc(numEspaisBlancsTG) + numeroTotalDeDomiciliaciones;
        
        String numeroRegistrosSoporte = String.valueOf(this.numeroTotalRegistrosSoporte);
        int numEspaisBlancsTGS = 10 - numeroRegistrosSoporte.length();
        numeroRegistrosSoporte = espaisEnBlanc(numEspaisBlancsTGS) + numeroRegistrosSoporte;
        
        this.liniaTotalGeneral = codigoDeRegistro + codigoDeDato + NIF_codigoPresentador + sufijo_codigoPresentador + espaisEnBlanc(12) +
                    espaisEnBlanc(40) + numeroDeOrdenantes + espaisEnBlanc(16) + totalImportes + espaisEnBlanc(6) + numeroTotalDeDomiciliaciones +
                    numeroRegistrosSoporte + espaisEnBlanc(20) + espaisEnBlanc(18);
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
