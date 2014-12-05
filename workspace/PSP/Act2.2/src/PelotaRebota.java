import java.awt.*;
import java.awt.event.*;
import java.applet.*;


public class PelotaRebota extends Applet implements Runnable, ActionListener {

	//Propiedades
	private Thread h;
	
	private Font fuente;
	
	private boolean parar1;
	private Button b1; //botones del Applet
	int ancho,alto;
	int x=0;
	boolean rebote;
	
	public void start(){
		h = new Thread(this);
		h.start();
							
	}	
		
		
	//método init
	public void init(){
		 ancho = Integer.parseInt(this.getParameter("WIDTH")); 
		 alto = Integer.parseInt(this.getParameter("HEIGHT")); 
		 
		setBackground(Color.white);//color de fondo
		setSize(250, 200);
		
		//añado botón 1 y su listener
		add(b1=new Button("Parar Hilo"));
		b1.addActionListener(this);
						
		
		fuente=new Font("Verdana",Font.BOLD,26); //tipo de letra
	}
	

	public void run() {
		//inicializo parar a falso
		parar1=false;
		
		//recojo hiloActual
		Thread hiloActual=Thread.currentThread();
		
		while (h==hiloActual ){
			if(!parar1){
			try{
				Thread.sleep(10);
			}catch (InterruptedException e){e.printStackTrace();}
			repaint();
			//TODO REBOTAR DE LA PELOTA
			
		 if (x==(ancho+30)){
			 rebote = true;
			 
			 
		 } 
		 
		 if(x==(0)) {
			 rebote = false;
		 }
		
		if(rebote){
			x--;
		}else{
			x++;
		}
		 
			}	 
		 
		}//fin while
		
		
					
	}//fin run
	
	
	public void paint(Graphics g){
		g.setFont(fuente);
		g.fillArc(x, alto/2,25, 25, 0, 360);
		
		
	}	
	
	//parar controlar pulsación botones	
	public void actionPerformed(ActionEvent e) {
				
		 if (e.getSource()==b1 && b1.getLabel().equals("Parar Hilo")){
		
			b1.setLabel("Seguir...");
			parar1=true;
			
		}else {//boton 1
			b1.setLabel("Parar Hilo");
			parar1=false;
			
		}
		
		
	}//actionperformed
	
	public void stop(){
		
		h=null;
		
	}

}