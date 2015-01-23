package serpis.ad;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateCategoria {
	
	private static EntityManagerFactory entityManagerFactory;
	
	public static void main (String [] args){
		
		entityManagerFactory = Persistence.createEntityManagerFactory("serpis.ad.jpa.mysql");
		
		//MOSTRAR
		System.out.println("\nVoy a mostrar primero");
		showCategorias();
		//AÑADIR
		System.out.println("\nAñado categorias nuevas");		
		persistNuevasCategorias();
		
		showCategorias();
		//BORRAR
		System.out.println("\nIntroduce el id de la categoria a BORRAR");
		Scanner tcl = new Scanner(System.in);
				
		Long id = tcl.nextLong();
		removeCategoria(id);
		//MOSTRAR
		System.out.println("\nMuestro otra vez");
		showCategorias();
		
		//EDITAR
		System.out.println("\nIntroduce el id de la categoria a editar");
		id = tcl.nextLong();
		tcl.nextLine();
		
		
		System.out.println("\nIntroduce el nombre");
		String nombre = tcl.nextLine();
		
		editCategoria(id, nombre);
		
		
		//FIN
		System.out.println("\n\n...SALIENDO DEL PROGRAMA...");
		entityManagerFactory.close();
	}
	
	//INSERT
	
	public static void persistNuevasCategorias(){
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		Categoria categoria = new Categoria();
		categoria.setNombre("Hibernate " + new SimpleDateFormat("yyyy-MM-dd:mm:ss").format(new Date()));
		
		entityManager.persist(categoria);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
	}
	
	//LISTAR
	
	public static void showCategorias(){
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		List<Categoria> categorias = 
				entityManager.createQuery("from Categoria", Categoria.class).getResultList();
		
		for (Categoria categoria: categorias){
			System.out.printf("id=%d nombre=%s\n", categoria.getId(), categoria.getNombre());
		}
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
	}
	
	//ENCONTRAR Y BORRAR CATEGORIA
	
	public static Categoria findCategoria(Long id){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Categoria categoria = entityManager.find(Categoria.class, id);
		return categoria;
	}
	
	public static void removeCategoria(Long id){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		entityManager.remove(entityManager.find(Categoria.class, id));
		
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	//EDITAR 
	public static void editCategoria(Long id, String nombre){
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		Categoria categoria = entityManager.find(Categoria.class, id);
		categoria.setNombre(nombre);
		entityManager.merge(categoria);
		
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
	}
	
	
}
