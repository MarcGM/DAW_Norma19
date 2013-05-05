package Main;

import java.util.Calendar;


public class FitxerNorma19
{
    public int idClientePresentador;
    public int idClienteOrdenante;
    
    public FitxerNorma19(int idClientePresentador,int idClienteOrdenante)
    {
        this.idClientePresentador = idClientePresentador;
        this.idClienteOrdenante = idClienteOrdenante;
    }
    
    public void start()
    {
        //Proves:>>>
        System.out.println("Xivvvvvvvv_1: "+this.idClientePresentador);
        System.out.println("Xivvvvvvvv_2: "+this.idClienteOrdenante);
        
        
    }
    
    public void crearCapceleraPresentador()
    {
        String codigoDeRegistro = "51";
        String codigoDeDato = "80";
        String NIF_codigoPresentador = ""; //OMPLIR //Consulta BD Taula Clients.
        String sufijo_codigoPresentador = ""; //OMPLIR
        
        Calendar calendari = Calendar.getInstance();
        String diaActual = String.valueOf(calendari.DAY_OF_MONTH);
        String mesActual = String.valueOf(calendari.MONTH);
        String anyActual = String.valueOf(calendari.YEAR);
        String fechaConfeccionSoporte = diaActual + mesActual + anyActual;
        
        String nombreDelClientePresentador = ""; //OMPLIR i possar els "'espais blancs a la dreta' que facin falta". //Consulta BD Taula Clients.
        String entidadReceptora = ""; //OMPLIR
        String oficina = ""; //OMPLIR
        
        
        String liniaCapceleraDePresentador = codigoDeRegistro + codigoDeDato + NIF_codigoPresentador + sufijo_codigoPresentador +
                fechaConfeccionSoporte + espaisEnBlanc(6) + nombreDelClientePresentador + espaisEnBlanc(20) + entidadReceptora + oficina +
                espaisEnBlanc(12) + espaisEnBlanc(40) + espaisEnBlanc(14);
    }
    
    public void crearCapceleraOrdenante()
    {
        String codigoDeRegistro = "53";
        String codigoDeDato = "80";
        String NIF_codigoOrdenante = ""; //OMPLIR //Consulta BD Taula Clients.
        String sufijo_codigoOrdenante = ""; //OMPLIR
        
        Calendar calendari = Calendar.getInstance();
        String diaActual = String.valueOf(calendari.DAY_OF_MONTH);
        String mesActual = String.valueOf(calendari.MONTH);
        String anyActual = String.valueOf(calendari.YEAR);
        String fechaConfeccionSoporte = diaActual + mesActual + anyActual;
        
        String fechaDeCargo = ""; //OMPLIR
        String nombreDelClienteOrdenante = ""; //OMPLIR i possar els "'espais blancs a la dreta' que facin falta". //Consulta BD Taula Clients.
        String entidad_CCCClienteOrdenante = ""; //OMPLIR //Consulta BD Taula Clients.
        String oficina_CCCClienteOrdenante = ""; //OMPLIR //Consulta BD Taula Clients.
        String DC_CCCClienteOrdenante = ""; //OMPLIR //Consulta BD Taula Clients.
        String numeroCuenta_CCCClienteOrdenante = ""; //Consulta BD Taula Clients.
        String procedimiento = ""; //OMPLIR
        
        
        String liniaCapceleraDeOrdenante = codigoDeRegistro + codigoDeDato + NIF_codigoOrdenante + sufijo_codigoOrdenante +
                fechaConfeccionSoporte + fechaDeCargo + nombreDelClienteOrdenante + entidad_CCCClienteOrdenante + 
                oficina_CCCClienteOrdenante + DC_CCCClienteOrdenante + numeroCuenta_CCCClienteOrdenante + espaisEnBlanc(8) + procedimiento +
                espaisEnBlanc(10) + espaisEnBlanc(40) + espaisEnBlanc(14);
    }
    
    public void crearIndividualObligatorio()
    {
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
    
}
