

public class Cuenta {
	private int saldo;
	
	Cuenta (int s){
		saldo=s;
	}
	
	public int getSaldo(){
		return saldo;
	}
	
	public void restar(int cantidad){
		saldo = saldo - cantidad;
	}
	
	public synchronized void RetirarDinero(int cant, String nom){
		
		if(getSaldo()>=cant){
			System.out.println("Se va a retirar saldo (actual es: "+ getSaldo() +")");
			try{
				Thread.sleep(500);
			} catch (InterruptedException ex){}
			restar(cant);
			System.out.println(nom +" retira => : "+cant+". Saldo Actual: "+ getSaldo() +"\n");
			
		} else {
			System.out.println(nom +"No puede retirar dinero no hay saldo. Saldo Actual: "+ getSaldo() +"");
		}
		
		
	}
}
