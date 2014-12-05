import java.io.*;
public class psfichero_MAngelPeris {
	public static void main(String[] args) {
		Runtime r=Runtime.getRuntime(); //objeto runtime
		String comando="ps"; //comando a ejecutar y params
		Process p=null; //inicializo vble process 
		
		//control params entrada
		if (args.length<1){
			System.out.println("Se necesita nombre de fichero destino..");
			System.exit(1);
		}//fin control parametros
		
	try {
		//fichero al que se enviará la salida del programa Unsaludo
		FileOutputStream fos=new FileOutputStream(args[0]);
		PrintWriter pw=new PrintWriter(fos);
		
		//ejecuto comando
		p=r.exec(comando);
		
		//creo buffer 
		InputStream is=p.getInputStream();
		BufferedReader br=new BufferedReader(new InputStreamReader (is));
		String linea;
		//leo linea a linea e imprimio en el fichero 
		while ((linea=br.readLine())!=null)//lee una linea
			 {
			 System.out.println("Inserto en "+args[0]+" > "+linea);
			 pw.println(linea); //la inserto en el fichero
			 }//fin bucle lineas
		br.close();
		pw.close();
		}
		catch (Exception e) {e.printStackTrace();}
	
	//comprobación de error 0 bien - 1 mal
	int exitVal;
	try {
		exitVal=p.waitFor();
		System.out.println("Valor de salida "+exitVal);
		} catch(InterruptedException e){
			e.printStackTrace();
			}
	
	}//fin main
}//fin Ejemplo3
