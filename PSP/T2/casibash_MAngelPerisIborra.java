//Miguel Angel Peris Iborra
import java.util.*;
//import java.io.*;
public class casibash_MAngelPerisIborra{
public static void main(String[] args) {
	Runtime r=Runtime.getRuntime();
	String comando;
	Process p;
	Scanner sc = new Scanner(System.in);
	System.out.println("\nIntroduce el comando:: ");
	comando = sc.next();
	
	try{
		p=r.exec(comando);
		} catch(Exception e) {
			System.out.println ("\nError en << "+comando+" >>");
			e.printStackTrace();
			}
	
	}

	
}
