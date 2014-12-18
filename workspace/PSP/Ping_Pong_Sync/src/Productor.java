class Productor extends Thread{
	private Cola cola;
	private String s;

	public Productor (Cola c,String s){
		cola=c;
		this.s=s;
	}
	
	public void run(){
		for (int i=0;i<7;i++){
			cola.put("PING"); //pone el string
			System.out.println("->Productor produce: PING" );
 			try {
 				
				sleep(50);
			}catch (InterruptedException e){}
 			
 			
 			cola.put("PONG"); //pone el string
 			System.out.println("->Productor produce: PONG");
 			try {
 				
				sleep(50);
			}catch (InterruptedException e){}
		}
	}
}
