public class tictac extends Thread {



	public static void main (String[] args){
			ThreadTic tic = new ThreadTic();
			tic.start();
			try{
				sleep(10);
			}catch(InterruptedException e){}
			ThreadTac tac = new ThreadTac();
			tac.start();
	
	}//fin main
}//fin clase
