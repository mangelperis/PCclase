public class Cuenta {
	private int saldo;
	private int max;
	
	Cuenta (int s, int m){
		saldo=s;
		max = m;
	}
	
	public int getSaldo(){
		return saldo;
	}
	public void sumar(int cantidad){
		saldo = saldo + cantidad;
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
			System.out.println(nom +" :: No puede RETIRAR dinero( "+cant+" ) no hay saldo. Saldo Actual: "+ getSaldo() +"");
			
		}
		
		
	}
	public synchronized void IngresarDinero(int cant, String nom){
		if((getSaldo() + cant) < max){
			System.out.println("Se va a ingresar dinero saldo (actual es: "+ getSaldo() +")");
			try{
				Thread.sleep(500);
			} catch (InterruptedException ex){}
			sumar(cant);
			System.out.println(nom +" ingresa => : "+cant+". Saldo Actual: "+ getSaldo() +"\n");
			
		} else {
			System.out.println(nom +" :: No puede INGRESAR dinero ("+cant+") porque supera el maximo. Saldo Actual: "+ getSaldo() +"");
			
		}
		
	}
}