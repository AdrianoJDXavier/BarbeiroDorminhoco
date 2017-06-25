package barbeirodorminhoco;


import static barbeirodorminhoco.Barbearia.accessoCadeiras;
import static barbeirodorminhoco.Barbearia.barbeiro;
import static barbeirodorminhoco.Barbearia.clientes;
import static barbeirodorminhoco.Barbearia.cadeirasLivres;
import static java.lang.Thread.sleep;

/**
 *
 * @author alunoces
 */
public class Barbeiro extends Thread {

      public Barbeiro() {}
    
  public void run() {
    while(true) {  // abrindo um loop infinito
      try {
      clientes.acquire(); // Tenta atender um cliente - se nenhum estiver disponível ele vai dormir
      accessoCadeiras.release(); // Neste momento, ele despertou -> quer modificar o número de lugares disponíveis
      cadeirasLivres++; // Uma cadeira fica livre
      barbeiro.release();  // O barbeiro está pronto para cortar
      accessoCadeiras.release(); // Não precisamos mais do bloqueio nas cadeiras
      this.cutHair();  //cortando...
      
    } catch (InterruptedException ex) {}
    }
  }

    /* Este método irá simular o corte de cabelo */
   
  public void cutHair(){
           
    System.out.println("O barbeiro está cortando o cabelo" );
    try {
      sleep(5000);
    } catch (InterruptedException ex){ }
  }
}
