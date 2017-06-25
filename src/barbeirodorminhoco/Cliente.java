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
public class Cliente extends Thread {
      int iD;
  boolean notCut=true;
  
  /* Construtor para o Cliente */
    
  public Cliente(int i) {
    iD = i;
  }

  public void run() {   
    while (notCut) {  // Enquanto o cliente não estiver cortado 
      try {
      accessoCadeiras.acquire();  //Tenta acessar as cadeiras
      if (cadeirasLivres > 0) {  //Se houver assentos livres
        System.out.println("Cliente " + this.iD + " senta na cadeira.");
        cadeirasLivres--;  //Sentado em uma cadeira
        clientes.release();  //Notificar o barbeiro que existe um cliente
        accessoCadeiras.release();  // Não precisa mais bloquear as cadeiras  
        try {
	barbeiro.acquire();  // temos que esperar se o barbeiro estiver ocupado
        notCut = false;  // Esse cliente vai agora depois do procedimento
        this.get_haircut();  //cortando...
        } catch (InterruptedException ex) {}
      }   
      else  {  // Não há assentos livres
        System.out.println("Não há assentos livres. Cliente " + this.iD + " deixou a barbearia.");
        accessoCadeiras.release();  //Solte o bloqueio nas cadeiras
        notCut=false; // O cliente partirá porque não há pontos na fila à esquerda.
      }
     }
      catch (InterruptedException ex) {}
    }
  }

  /* Este método irá simular a obtenção de um corte de cabelo */
  
  public void get_haircut(){
    System.out.println("Cliente " + this.iD + " está tendo o cabelo cortado.");
    try {
    sleep(5050);
    } catch (InterruptedException ex) {}
  }
    
}
