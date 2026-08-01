public class Localidad {
    private String nombre;
    private int precio;
    private int boletosVendidos;
    private int capacidadTotal;


    public Localidad(String nombre, int precio, int capacidadTotal) {
        this.nombre = nombre;
        this.precio = precio;
        this.capacidadTotal = capacidadTotal;
        this.boletosVendidos = 0;

    }

    public int getBoletosDisponibles() {
        return capacidadTotal - boletosVendidos;
    }

    public int getRecaudacion(){
        return boletosVendidos*precio;
    }

    public boolean venderBoletos(int cantidad) {
    if (cantidad > 0 && cantidad <= getBoletosDisponibles()) {
        this.boletosVendidos += cantidad;
        return true; // Venta exitosa
    }
    return false; // No hay suficientes boletos disponibles o la cantidad es inválida
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public int getBoletosVendidos() {
        return boletosVendidos;
    }



}
