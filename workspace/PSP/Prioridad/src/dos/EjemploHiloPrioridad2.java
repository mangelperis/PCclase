package dos;
class EjemploHiloPrioridad2 {

	public static void main (String args[]) {
		
		//Creo objetos hilos
			HiloPrioridad2 h1=new HiloPrioridad2("Uno");
			HiloPrioridad2 h2=new HiloPrioridad2("Dos");
			HiloPrioridad2 h3=new HiloPrioridad2("Tres");
			HiloPrioridad2 h4=new HiloPrioridad2("Cuatro");
			HiloPrioridad2 h5=new HiloPrioridad2("Cinco");
	
			 //Establezco prioridades
			   h4.setPriority(Thread.MIN_PRIORITY);
h1.setPriority(3);
			        h5.setPriority(Thread.NORM_PRIORITY);
h3.setPriority(8);
			      h2.setPriority(Thread.MAX_PRIORITY);

//Ejecuto hilos
h4.start();
h2.start();
 h5.start();	
h1.start();
						  h3.start();
	}
}
