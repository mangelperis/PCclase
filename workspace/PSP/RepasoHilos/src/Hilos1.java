
public class Hilos1 implements Runnable{

	private Thread hilo=null;
	private int c1,c2,i1,i2;
	private int valor1, valor2;
	Hilos1 (int c1,int c2,int i1,int i2){
		this.c1=c1;
		this.c2=c2;
		this.i1=i1;
		this.i2=i2;
	}
	
	public void start(){
		if (hilo==null){
			valor1 = c1;
			valor2 = c2;
			hilo=new Thread(this);	//creo el hilo
			hilo.start(); //lanzo hilo
		}
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while (true){
			System.out.println("Contador1:: "+valor1);
			System.out.println("Contador2:: "+valor2);
			System.out.println();
			try {
				Thread.sleep(1000);
			}catch (InterruptedException e){}
			
			valor1 += i1;
			valor2 += i2;
		}
	}
	
	

}
