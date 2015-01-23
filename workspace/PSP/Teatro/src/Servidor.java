
import java.io.* ;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	
	
	//puerto espera conexiones
	static final int PUERTO=5000;
	String mensaje="";

		public Servidor( ) {
			System.out.println("Esperando conexion... Puerto : " + PUERTO );
			try {
				//crea socket y espera
				@SuppressWarnings("resource")
				ServerSocket skServidor = new ServerSocket( PUERTO );
		
					//Primitiva accept() crea nuevo socket skCliente
					//skCliente atiene al cliente
					Socket skCliente = skServidor.accept(); // Crea objeto
 
					System.out.println("Conectado.");
					
					
					//asocio flujo salida de datos al socket
					OutputStream aux = skCliente.getOutputStream();
					//asocio flujo de datos flujo de tipo DataOutputStream 
					DataOutputStream flujo= new DataOutputStream( aux );
					
					//recojo flujo de datos del socket
					InputStream auxi = skCliente.getInputStream();
					//asocio flujo de datos flujo de tipo DataInputStream
					DataInputStream flujoi = new DataInputStream( auxi );	
										
				//	String input ="";
					
					while(!mensaje.equals("Fin")){
							
						mensaje = flujoi.readUTF();
						System.out.println(mensaje);
						//1er mensaje
						if(mensaje.equals("Hola")){
							System.out.println("Cliente:> " +flujoi.readUTF() );
							System.out.println("Hola soy el servidor");
							flujo.writeUTF( "Hola soy el servidor " );					
							
						}		
						
						
						
						
						
						
						Thread.sleep(1000);
				}
					
					
				
					System.out.println("Hasta luego ...");
					flujo.writeUTF("Hasta luego...");
					System.out.println("Cerrando la conexion...");
										
					//cierro conexión
					skCliente.close();
			
			} catch( Exception e ) {

		System.out.println( e.getMessage() );

		}

	}

	public static void main( String[] arg ) {

	new Servidor();

	}

}
