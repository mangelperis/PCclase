import javax.swing.*;
public class Validar extends JFrame {
	public Validar() {
	}

	public static void main(String[] args) {
		HolaClase marco = new HolaClase();
		int ancho = 350;
		int alto = 350;
		
		marco.setSize(ancho, alto);
		marco.setLocation(300, 300);
		marco.setTitle("Validació");
		
		marco.setDefaultCloseOperation(marco.EXIT_ON_CLOSE);
		
		
		JPanel panel = new JPanel();
		marco.getContentPane().add(panel);
		addComponentes(panel);
		
		marco.setVisible(true);
	}
	
	private static void addComponentes(JPanel panel){
		panel.setLayout(null);
		JLabel labelUser = new JLabel ("Usuario");
		labelUser.setBounds(10, 45, 80, 15);
		panel.add(labelUser);
		
		JLabel labelPwd = new JLabel ("Password");
		labelPwd.setBounds(10, 100, 100, 25);
		panel.add(labelPwd);
		
		JTextField textUser = new JTextField(20);
		textUser.setBounds(110,40,180,25);
		panel.add(textUser);
		
		JPasswordField textPwd = new JPasswordField(20);
		textPwd.setBounds(110, 100, 180, 25);
		panel.add(textPwd);
		
		JButton botonLogin = new JButton("Login");
		botonLogin.setBounds(25, 165, 80, 25);
		panel.add(botonLogin);
		
		JButton botonRegistrar = new JButton("Registrar");
		botonRegistrar.setBounds(125, 165, 120,25);
		panel.add(botonRegistrar);
		
		
	}

}
