import java.awt.*;
import java.awt.event.*;
import java.applet.*;


public class HiloContador extends Applet implements Runnable, ActionListener {

	//Propiedades
	private Thread h;
	private Thread h2;
	private Font fuente;
	long CONTADOR=0;
	long CONTADOR2=5;
	private boolean parar1,parar2;
	private Button b1,b2; //botones del Applet
	
	
	public void start(){
		h = new Thread(this);
		h.start();
		
		h2 = new Thread(this);
		h2.start();
						
	}	
	
	
	
	
	//método init
	public void init(){
		setBackground(Color.yellow);//color de fondo
		setSize(250, 200);
		
		//añado botón 1 y su listener
		add(b1=new Button("Parar Hilo 1"));
		b1.addActionListener(this);
				
		//añado botón 2 y su listener
		add(b2=new Button("Parar Hilo 2"));
		b2.addActionListener(this);
		
		fuente=new Font("Verdana",Font.BOLD,26); //tipo de letra
	}
	

	public void run() {
		//inicializo parar a falso
		parar1=false;parar2=false;
		
		//recojo hiloActual
		Thread hiloActual=Thread.currentThread();
		
		while (h==hiloActual && !parar1 ){
			try{
				Thread.sleep(600);
			}catch (InterruptedException e){e.printStackTrace();}
			repaint();
			CONTADOR++;			
		}//fin while
		
		while (h2==hiloActual && !parar2 ){
			try{
				Thread.sleep(600);
			}catch (InterruptedException e){e.printStackTrace();}
			repaint();
			CONTADOR2++;						
		}//fin while
					
	}//fin run
	
	
	public void paint(Graphics g){
		g.setFont(fuente);
		g.drawString("H1:: "+Long.toString((long)CONTADOR), 40, 100); //escribe contador
		g.drawString("H2:: "+Long.toString((long)CONTADOR2), 40, 170);
		
	}	
	
	//parar controlar pulsación botones	
	public void actionPerformed(ActionEvent e) {
				
		if (e.getSource()==b1){//boton 1
			b1.setLabel("H1 Finalizado");
			parar1=true;
			
		} else if (e.getSource()==b2){ //boton 2
			b2.setLabel("H2 Finalizado");
			parar2=true;
		}
		
		
	}//actionperformed
	
	public void stop(){
		
		h=null;
		h2=null;
	}

}