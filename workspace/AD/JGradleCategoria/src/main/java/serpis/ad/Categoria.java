package serpis.ad;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;


@Entity


public class Categoria {
	
	 private Long id;
	 private String nombre;
	 
	 public Categoria() {
			// this form used by Hibernate
		}

		public Categoria(Long id, String nombre) {
			// for application use, to create new events
			this.id = id;
			this.nombre = nombre;
		}

		@Id
		@GeneratedValue(generator="increment")
		@GenericGenerator(name="increment", strategy = "increment")
	    public Long getId() {
			return id;	  
			}
	    private void setId(Long id) {
			this.id = id;
	    }
	    public String getNombre() {
			return nombre;
	    }
	    public void setNombre(String nombre) {
			this.nombre = nombre;
	    }
}
