package dos;

public class UsarApplet extends Thread{
	
		 int c1,c2;
		 int valor,valor2;
		
		UsarApplet(int c1,int c2){
			this.c1=c1;
			this.c2=c2;
			valor = c1;
			valor2 = c2;
			
		}
		

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			while (true){

			
				try {
					Thread.sleep(1000);
					valor += 1;
					valor2 += 2;
				}catch (InterruptedException e){}
				
				
			}
			
		}
		public int getContador1 (){
			return valor;
		}
		
		public int getContador2 (){
			return valor2;
		}

	}

