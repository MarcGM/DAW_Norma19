/*
 * @author Marc
 */

package Main;

import java.util.ArrayList;


public class EscriureFitxerN19
{
   public String liniaCapceleraDePresentador;
   public String liniaCapceleraDeOrdenante;
   public ArrayList<String> arrayLiniesFitxerIO;
   public String liniaTotalOrdenante;
   public String liniaTotalGeneral;
    
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
        System.out.println(this.liniaCapceleraDePresentador);
        System.out.println(this.liniaCapceleraDeOrdenante);
        
        System.out.println(this.liniaTotalOrdenante);
        System.out.println(this.liniaTotalGeneral);
    }
    
    
}
