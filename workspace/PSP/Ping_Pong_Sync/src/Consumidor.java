class Consumidor extends Thread {
	private Cola cola;
	private String s;

	public Consumidor (Cola c, String s){
		cola=c;
		this.s=s;
	}
	
	public void run(){
		String valor;
		for (int i=0;i<7;i++){
			
			valor=cola.get();//recoge el string
			System.out.println("->Consumidor consume:" +valor);
			//try{
			//	sleep(10);
			
			//}catch(Exception e){}
			
			
	 			
		}
	}

}
