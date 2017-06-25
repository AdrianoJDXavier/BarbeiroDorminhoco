package barbeirodorminhoco;

import java.util.concurrent.*;

public class Barbearia extends Thread {

  /* PRE REQUISITOS */


  /* Criamos os semáforos. Primeiro, não há clientes e
   O barbeiro está dormindo, então chamamos o construtor com o parâmetro
   0 criando assim semáforos com zero permissões iniciais.
   Semaphore (1) constrói um semáforo binário, conforme desejado. */  
    
    public static Semaphore clientes = new Semaphore(0);
    public static Semaphore barbeiro = new Semaphore(0); //Quantidade de barbeiro
    public static Semaphore accessoCadeiras = new Semaphore(1);

  /* Definimos que o número de cadeiras nesta barbearia é de 5. */

    public static final int CADEIRAS = 5; //Cadeiras

  /* Criamos o número inteiro de FFreeSeats para que os clientes
   possam sentar-se num assento livre ou sair da barbearia se lá
   não houver lugares disponíveis */

   public static int cadeirasLivres = CADEIRAS;

  
     
  
  /*  metodo main */

  public static void main(String args[]) {
    
    Barbearia barbearia = new Barbearia();  //Cria a barbearia
    barbearia.start();  //Começo da simulação
  }

  public void run(){  

   /* Este método criará novos clientes por um tempo */
    Barbeiro barbeiro = new Barbeiro();  // Instancia o barbeiro
   barbeiro.start();  //Iniciar o trampo
   for (int i=1; i<16; i++) { //Quantidade de clientes
     Cliente cliente = new Cliente(i);
     cliente.start();
     try {
       sleep(2000); //tempo de soneca
     } catch(InterruptedException ex) {};
   }
   
   

  } 
}