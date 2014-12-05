public class ThreadTac extends Thread {
//Metodo run
	public void run(){
		while (true){

		System.out.print (" TAC ");
		try{		
			sleep(20);

		}catch (InterruptedException e){}
		}
	}

	
}//fin hilo
