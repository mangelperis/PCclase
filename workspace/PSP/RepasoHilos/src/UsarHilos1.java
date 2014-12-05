public class UsarHilos1{
	public static void main (String[] args){
		Hilos1 p=new Hilos1(1,3,1,2); //creo objeto hilo
		new Thread(p).start(); //inicio ejecución
		}


}