import java.util.Random;
import java.util.Scanner;

public class SistemaDeVentas {
    private Localidad[] localidades;
    private Comprador compradorActual;

    public SistemaDeVentas() {
        localidades = new Localidad[3];
        // Estan las 3 localidades con sus precios y capacidad fija de 20 boletos cada una
        localidades[0] = new Localidad("Localidad 1", 100, 20);
        localidades[1] = new Localidad("Localidad 5", 500, 20);
        localidades[2] = new Localidad("Localidad 10", 1000, 20);
        compradorActual = null;
    }

    public static void main(String[] args) {
        SistemaDeVentas sistema = new SistemaDeVentas();
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        // EL BUCLE WHILE AHORA ESTÁ DENTRO DEL MAIN COMO DEBE SER
        while (!salir) {
            System.out.println("\n--- SISTEMA DE VENTAS DE BOLETOS ---");
            System.out.println("1. Nuevo comprador (Solicitar compra)");
            System.out.println("2. Consultar disponibilidad total");
            System.out.println("3. Consultar disponibilidad por localidad");
            System.out.println("4. Reporte de caja (Recaudación)");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    sistema.registrarComprador(scanner);
                    sistema.procesarSolicitud();
                    break;
                case 2:
                    sistema.mostrarDisponibilidadTotal();
                    break;
                case 3:
                    sistema.mostrarDisponibilidadPorLocalidad(scanner);
                    break;
                case 4:
                    sistema.mostrarReporteCaja();
                    break;
                case 5:
                    salir = true;
                    System.out.println("¡Gracias por usar el sistema!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
        scanner.close();
    } 

    // Método para pedir datos del comprador por consola
    private void registrarComprador(Scanner scanner) {
        System.out.println("\n--- Datos del Comprador ---");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Cantidad de boletos deseados: ");
        int boletos = scanner.nextInt();
        System.out.print("Presupuesto máximo ($): ");
        float presupuesto = scanner.nextFloat();
        this.compradorActual = new Comprador(nombre, email, boletos, presupuesto);
    }

    public int generarTicketAleatorio() {
        Random random = new Random();
        return random.nextInt(15000) + 1;
    }

    
    public boolean aptoParaCompra(int ticket) {
        Random random = new Random();
        int a = random.nextInt(15000) + 1;
        int b = random.nextInt(15000) + 1;

        int min = Math.min(a, b);
        int max = Math.max(a, b);

        return (ticket >= min && ticket <= max);
    }

    
    public void procesarSolicitud() {
        if (compradorActual == null) {
            System.out.println("No hay un comprador registrado.");
            return;
        }

        int ticket = generarTicketAleatorio();
        System.out.println("\nSe generó el ticket número: " + ticket);

        if (!aptoParaCompra(ticket)) {
            System.out.println(" Lo sentimos, su ticket no fue apto para realizar la compra.");
            return;
        }

        System.out.println(" ¡Felicidades! Su ticket es apto para comprar.");

    
        Random random = new Random();
        int indiceLocalidad = random.nextInt(3);
        Localidad localidadAsignada = localidades[indiceLocalidad];

        System.out.println("Se le ha asignado aleatoriamente la: " + localidadAsignada.getNombre());

        // Validar disponibilidad
        int boletosDeseados = compradorActual.getBoletosDeseados();
        if (localidadAsignada.getBoletosDisponibles() < boletosDeseados) {
            System.out.println(" No hay suficientes boletos disponibles en esta localidad.");
            return;
        }

        // Validar presupuesto
        float costoTotal = boletosDeseados * localidadAsignada.getPrecio();
        if (compradorActual.getPresupuesto() < costoTotal) {
            System.out.println(" Presupuesto insuficiente. El costo total es $" + costoTotal + 
                               " y su presupuesto es $" + compradorActual.getPresupuesto());
            return;
        }

        // Realizar la venta
        localidadAsignada.venderBoletos(boletosDeseados);
        System.out.println(" ¡Venta realizada con éxito!");
        System.out.println("Comprador: " + compradorActual.getNombre());
        System.out.println("Boletos comprados: " + boletosDeseados);
        System.out.println("Total pagado: $" + costoTotal);
    }

    private void mostrarDisponibilidadTotal() {
        int totalDisponibles = 0;
        for (Localidad loc : localidades) {
            totalDisponibles += loc.getBoletosDisponibles();
        }
        System.out.println("\nTotal de boletos disponibles en el sistema: " + totalDisponibles);
    }

    private void mostrarDisponibilidadPorLocalidad(Scanner scanner) {
        System.out.println("\nSeleccione la localidad:");
        for (int i = 0; i < localidades.length; i++) {
            System.out.println((i + 1) + ". " + localidades[i].getNombre());
        }
        System.out.print("Opción: ");
        int sel = scanner.nextInt() - 1;

        if (sel >= 0 && sel < localidades.length) {
            System.out.println("Boletos disponibles en " + localidades[sel].getNombre() + 
                               ": " + localidades[sel].getBoletosDisponibles());
        } else {
            System.out.println("Localidad inválida.");
        }
    }

    private void mostrarReporteCaja() {
        int totalCaja = 0;
        System.out.println("\n--- REPORTE DE CAJA ---");
        for (Localidad loc : localidades) {
            int recaudado = loc.getRecaudacion();
            System.out.println(loc.getNombre() + ": $" + recaudado);
            totalCaja += recaudado;
        }
        System.out.println("Recaudación Total: $" + totalCaja);
    }
}











    

