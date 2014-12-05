import java.io.*;
public class act_sumandos2_MAngelPeris{
	public static void main (String[] args){
	InputStreamReader in=new InputStreamReader(System.in);
	BufferedReader br = new BufferedReader (in);
	String texto;
	
	try{
		System.out.println("Introduce un numero...");
		int num1=Integer.parseInt(br.readLine());
		System.out.println("Introduce otro numero");
		int num2=Integer.parseInt(br.readLine());
		in.close();
		int suma = num1 + num2;
		System.out.println("La suma es: "+suma);
		
		}catch (Exception e) {e.printStackTrace();}

	}//fin main
}//fin ejemplo lectura
