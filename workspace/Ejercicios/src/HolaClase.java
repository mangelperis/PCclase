
import javax.swing.*;
public class HolaClase extends JFrame{

	public static void main(String[] args) {
		
		HolaClase marco = new HolaClase();
		int ancho = 200;
		int alto = 200;
		
		marco.setSize(ancho, alto);
		marco.setLocation(300, 300);
		marco.setTitle("Hola Clase");
		
		marco.setDefaultCloseOperation(marco.EXIT_ON_CLOSE);
		marco.setVisible(true);
		
		
	}

}
