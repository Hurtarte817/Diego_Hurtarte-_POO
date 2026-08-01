public class Comprador {
    private String nombre;
    private String email;
    private int boletosDeseados;
    private float presupuesto;


        /*
        Contrusctor: Es el método especial dentro de una clase que sirve para crear un objeto 
        e instanciarlo con valores iniciales.

        this.nombre se refiere al atributo de la clase (la variable global del objeto).

        nombre se refiere al parámetro recibido entre paréntesis en el constructor.
        */


    public Comprador(String nombre, String email, int boletosDeseados, float presupuesto) {
        this.nombre = nombre;
        this.email = email;
        this.boletosDeseados = boletosDeseados;
        this.presupuesto = presupuesto;
    }       

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public int getBoletosDeseados() {
        return boletosDeseados;
    }

    public float getPresupuesto() {
        return presupuesto;
    }
}