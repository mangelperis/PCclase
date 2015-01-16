/* JArticulo
 * 	0 - Salir
 *  1 - Nuevo
 *  2 - Editar
 *  3 - Eliminar
 *  4 - Visualizar
 *  
 * */

package JArticuloSimple;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Scanner;


public class JArticulo {
	static Scanner tcl = new Scanner(System.in);
	
	public static void Visualizar(){
		try {
		 Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost/dbprueba?user=root&password=sistemas");
		
		
		PreparedStatement preparedStatement = connection.prepareStatement(
				"SELECT * FROM articulo");
		
		
		//preparedStatement.setObject(1,"%vo%");
		//preparedStatement.setObject(1,1);
		ResultSet resultSet = preparedStatement.executeQuery();
		System.out.println("\n .:: SQL RESULT ::.");
		while (resultSet.next()) 
			System.out.printf("id=%1s nombre=%s categoria=%s precio=%s\n", 
				resultSet.getObject("id"), resultSet.getObject("nombre")
				, resultSet.getObject("categoria"), resultSet.getObject("precio")); 
		
		
		resultSet.close();
		preparedStatement.close();
		connection.close();
		Thread.sleep(1500);
		} catch ( Exception e){
			e.printStackTrace();			
		}
		
		
	}
	public static void Eliminar(){
		try {
			 Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/dbprueba?user=root&password=sistemas");
			
			 int resp;
			 System.out.print("Introduce el ID a borrar :: ");
			 resp = tcl.nextInt();
			
			
			String sql ="DELETE FROM articulo WHERE id ='"+resp+"'";
			Statement stmt = connection.createStatement();
						
			stmt.execute(sql);
			System.out.println("\n .:: REGISTRO BORRADO ::.");
						
			stmt.close();
			connection.close();
			Thread.sleep(1500);
			} catch ( Exception e){
				e.printStackTrace();			
			}
		
	}
	public static void Nuevo(){
		try {
			 Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/dbprueba?user=root&password=sistemas");
			
			 String nombre;
			 int categoria;
			 Double precio;
			 
			 System.out.println("\nIntroduce los nuevos valores ::> ");
			 tcl.nextLine();
			 System.out.print("Nombre :: ");
			 nombre = tcl.nextLine();
			// tcl.nextLine();
			 System.out.print("\nCategoria :: ");
			 categoria = tcl.nextInt();
			 tcl.nextLine();
			 System.out.print("Precio :: ");
			 precio = tcl.nextDouble();
			 tcl.nextLine();
						
			String sql ="INSERT INTO articulo (nombre,categoria,precio) VALUES ('"+nombre+"','"+categoria+"','"+precio+"')";
			Statement stmt = connection.createStatement();
			
			stmt.execute(sql);
			System.out.println("\n .:: REGISTRO AÑADIDO ::.");
				
			
			stmt.close();
			connection.close();
			Thread.sleep(1500);
			} catch ( Exception e){
				e.printStackTrace();			
			}
		
		
	}
	public static void Editar(){
		System.out.println("TODO");
		
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		 		
		
		int resp=-1;
		
		
		while(resp != 0){
			System.out.println("\n************* MENU *************\n");
			System.out.println("****    0 - Salir          *****");
			System.out.println("****    1 - Nuevo          *****");
			System.out.println("****    2 - Editar         *****");
			System.out.println("****    3 - Eliminar       *****");
			System.out.println("****    4 - Visualizar     *****\n\n");
			
			System.out.print("Tu opcion :::  ");
			resp = tcl.nextInt();
			
				switch (resp){
				
					case 0:
						System.out.println("\n Saliendo ...");
						break;
					
					case 1:
						Nuevo();
						break;
					case 2:
						Editar();
						break;
					case 3:
						Eliminar();
						break;
					case 4: 
						Visualizar();
						break;
					default: 
						System.out.println("\nERROR en la selección del menú");
						break;
				
				
				}
		
		}
	}
}
