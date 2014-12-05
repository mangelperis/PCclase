
public class Figura
{
    protected Color color;
    protected String tipo;

    /** Constructor por defecto: crea una figura por defecto de color negro (Color.black) y tipo "indefinido" */
    public Figura() {
        color = Color.black;
        tipo = "indefinido";
    }
    
    public Figura(Color color, String tipo) {
        this.color = color;
        this.tipo = tipo;
    }
    
    public String getTipo() { 
        return tipo;
    }
    
    public Color getColor() { 
        return color;
    }
    
    public void setColor(Color color){
        this.color = color;
    }
    
    public String toString(){
        return "Figura de tipo: " + tipo + " y color " + color;
    }
        
    /** Devuelve el true si la figura actual es igual a la que recibe como parámetro.
     *  Se considera que dos figuras son iguales si tienen el mismo tipo y color */
    public boolean equals(Object x) {
        Figura f = (Figura) x;
        return (color.equals(f.color) && tipo.equals(f.tipo));
    }
}