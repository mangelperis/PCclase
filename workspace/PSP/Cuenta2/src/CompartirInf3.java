
public class CompartirInf3 {
	public static void main (String [] args){
		Cuenta c = new Cuenta(40, 800);
		Persona h1 = new Persona ("Ana", c);
		Persona h2 = new Persona ("Juan", c);
		
		h1.start();
		h2.start();
	}
}