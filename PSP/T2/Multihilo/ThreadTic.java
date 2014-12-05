public class ThreadTic extends Thread {
//Metodo run
	public void run(){
		while (true){

		System.out.print (" TIC ");
		try{		
			sleep(20);

		}catch (InterruptedException e){}
		}
	}

	
}//fin hilo
