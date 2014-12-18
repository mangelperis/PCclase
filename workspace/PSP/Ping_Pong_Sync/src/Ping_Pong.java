public class Ping_Pong{
	public static void main(String[] args) {
		Cola cola=new Cola();
		Productor p=new Productor(cola,"PING");
		Consumidor c=new Consumidor(cola,"PING");
		p.start();
		c.start();	
		
	}
	


}
