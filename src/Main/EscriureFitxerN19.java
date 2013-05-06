/*
 * @author Marc
 */

package Main;

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class EscriureFitxerN19
{
   public String liniaCapceleraDePresentador;
   public String liniaCapceleraDeOrdenante;
   public ArrayList<String> arrayLiniesFitxerIO;
   public String liniaTotalOrdenante;
   public String liniaTotalGeneral;
   
   public File fitxerDomiciliacions;
    
    public EscriureFitxerN19(String liniaCapceleraDePresentador, String liniaCapceleraDeOrdenante, ArrayList<String> arrayLiniesFitxerIO, String liniaTotalOrdenante, String liniaTotalGeneral)
    {
        this.liniaCapceleraDePresentador = liniaCapceleraDePresentador;
        this.liniaCapceleraDeOrdenante = liniaCapceleraDeOrdenante;
        this.arrayLiniesFitxerIO = arrayLiniesFitxerIO;
        this.liniaTotalOrdenante = liniaTotalOrdenante;
        this.liniaTotalGeneral = liniaTotalGeneral;
    }
    
    public void start()
    {
        //Proves: >>>
        System.out.println(this.liniaCapceleraDePresentador);
        System.out.println(this.liniaCapceleraDeOrdenante);
        
        System.out.println(this.liniaTotalOrdenante);
        System.out.println(this.liniaTotalGeneral);
        
        escriureFitxerFinal();
        
    }
    
    public void escriureFitxerFinal()
    {
            try{
                File fitxerDomiciliacions = new File("domiciliacions.txt");
                if(fitxerDomiciliacions.exists()==false){
                    fitxerDomiciliacions.createNewFile();
                }
                FileWriter fitxerWrite = new FileWriter(fitxerDomiciliacions);
                fitxerWrite.write(liniaCapceleraDePresentador+"\n");
                fitxerWrite.write(liniaCapceleraDeOrdenante+"\n");
                for(int i=0; i<arrayLiniesFitxerIO.size(); i++){
                    fitxerWrite.write(arrayLiniesFitxerIO.get(i)+"\n");
                }
                fitxerWrite.write(liniaTotalOrdenante+"\n");
                fitxerWrite.write(liniaTotalGeneral+"\n");
                
                fitxerWrite.close();
            }catch(IOException e){
                e.printStackTrace();
            }
    }
    
    
}
