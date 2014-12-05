package dos;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;



@SuppressWarnings("serial")
public class HiloApplet extends Applet implements Runnable {
		
	private Thread hilo_applet = null;
	private UsarApplet h1 = null;
	private UsarApplet h2 = null;
	private UsarApplet h3 = null;
	int c1_applet = 1;
	int c2_applet = 5;
	private Font fuente;
	 
	//método init
		public void init(){
			fuente=new Font("Verdana",Font.BOLD,26);
			setBackground(Color.yellow);//color de fondo
			
		}
		
	public void start(){
		hilo_applet = new Thread(this);
		hilo_applet.start();
		
		h1= new UsarApplet(0,5);
		h2= new UsarApplet(1,2);
		h3= new UsarApplet(3,9);
	}
	

	@Override
	public void run() {
		// TODO Auto-generated method stub		
		while (true){
				
			try {
				Thread.sleep(1000);
			
			}catch (InterruptedException e){}
			repaint();
			
		}
		
	}
	
	public void paint(Graphics g){
		g.setFont(fuente);
		g.drawString("C1:: "+Integer.toString((int)h1.getContador1()), 40, 100); //escribe contador
		g.drawString("C2:: "+Integer.toString((int)h1.getContador2()), 40, 170);
		
	}	
	
	
		
		

}