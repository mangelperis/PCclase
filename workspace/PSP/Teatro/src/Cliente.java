
import java.io.*;
import java.net.*;
import java.util.Scanner;

class Cliente {
	//nombre  máquina y puerto
	//static final String HOST = "localhost";
	static final int PUERTO=5000;
	
	
	public Cliente(String HOST ) {
		System.out.println("Conectando al servidor...");

		try{
			
			//se crea el socket			
			Socket skCliente = new Socket(HOST, PUERTO);
			System.out.println("Conectado.");
			
			
			OutputStream auxo = skCliente.getOutputStream();
			//asocio flujo de datos flujo de tipo DataOutputStream 
			DataOutputStream flujou= new DataOutputStream( auxo );
			
			//recojo flujo de datos del socket
			InputStream aux = skCliente.getInputStream();

			//asocio flujo de datos flujo de tipo DataInputStream
			DataInputStream flujo = new DataInputStream( aux );
			
			
			//1er mensaje		
			System.out.println("Hola");
			flujou.writeUTF("Hola");
			System.out.println("Servidor:> " +flujo.readUTF() );
			
			
			Scanner tcl = new Scanner(System.in);
				
						
			
				System.out.println( "Cerrando conexion...");
				
				//Cierro el socket
				skCliente.close();
				
			
			
		}catch(Exception e) {
		System.out.println( e.getMessage() );
	}
	}

public static void main(String[] arg) {
	
	new Cliente("localhost");
}
}
