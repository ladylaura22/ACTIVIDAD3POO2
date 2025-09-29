package Cooperativa.APP;


import Cooperativa.Modelo.*;
import Cooperativa.Transaccion.*;
import java.time.LocalDate;
import java.util.*;

public class APP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Socio> socios = new ArrayList<>();

        while (true) {
            System.out.println("\n--- MENÚ COOPERATIVA ---");
            System.out.println("1. Ingresar nuevo socio");
            System.out.println("2. Realizar depósito");
            System.out.println("3. Realizar retiro");
            System.out.println("4. Listar nombres de todos los socios");
            System.out.println("5. Mostrar cuentas con saldo > $500,000 ");
            System.out.println("6. Calcular total de dinero en la cooperativa");
            System.out.println("7. Listar todos los socios con sus datos");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion;
            try {
                opcion = sc.nextInt();
                sc.nextLine(); // limpiar buffer
            } catch (InputMismatchException e) {
                System.out.println("Por favor ingrese un número válido.");
                sc.nextLine();
                continue;
            }

            switch (opcion) {
                case 1: // Ingresar nuevo socio
                    System.out.print("Ingrese nombre del socio: ");
                    String nombre = sc.nextLine();
                    System.out.print("Ingrese cédula: ");
                    String cedula = sc.nextLine();
                    Socio socio = new Socio(nombre, cedula);

                    System.out.println("¿Agregar cuenta de ahorro básica (1) o premium (2)?");
                    int tipoCuenta;
                    try {
                        tipoCuenta = sc.nextInt();
                        sc.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("Valor no válido. Intente nuevamente.");
                        sc.nextLine();
                        break;
                    }

                    System.out.print("Ingrese número de cuenta: ");
                    String numCuenta = sc.nextLine();

                    // Validar número de cuenta único en toda la cooperativa
                    boolean numeroExistente = socios.stream()
                            .flatMap(s -> s.getCuentas().stream())
                            .anyMatch(c -> c.getNumeroCuenta().equals(numCuenta));

                    if (numeroExistente) {
                        System.out.println("Ya existe una cuenta con ese número en la cooperativa. Intente con otro número.");
                        break;
                    }

                    Cuenta cuenta;
                    if (tipoCuenta == 1) {
                        cuenta = new AhorroBasica(numCuenta, socio, LocalDate.now());
                    } else {
                        cuenta = new AhorroPremium(numCuenta, socio, LocalDate.now());
                    }

                    try {
                        socio.abrirCuenta(cuenta);
                        socios.add(socio);
                        System.out.println("Socio y cuenta registrados exitosamente.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error al abrir la cuenta: " + e.getMessage());
                    }
                    break;

                case 2: // Realizar depósito
                    if (socios.isEmpty()) {
                        System.out.println("No hay socios registrados.");
                        break;
                    }
                    Socio socioDep = seleccionarSocio(socios, sc);
                    if (socioDep == null) break;

                    Cuenta cuentaDep = seleccionarCuenta(socioDep, sc);
                    if (cuentaDep == null) break;

                    System.out.print("Ingrese monto a depositar: ");
                    double montoDep;
                    try {
                        montoDep = sc.nextDouble();
                        sc.nextLine();
                        if (montoDep <= 0) throw new IllegalArgumentException("El monto debe ser mayor a 0.");
                        try {
                            Depositar deposito = new Depositar(cuentaDep, montoDep);
                            deposito.ejecutar();
                            System.out.println("Depósito realizado.");
                        } catch (Exception e) {
                            System.out.println("Error al realizar el depósito: " + e.getMessage());
                        }
                    } catch (InputMismatchException | IllegalArgumentException e) {
                        System.out.println("Monto inválido: " + e.getMessage());
                        sc.nextLine();
                    }
                    break;

                case 3: // Realizar retiro
                    if (socios.isEmpty()) {
                        System.out.println("No hay socios registrados.");
                        break;
                    }
                    Socio socioRet = seleccionarSocio(socios, sc);
                    if (socioRet == null) break;

                    Cuenta cuentaRet = seleccionarCuenta(socioRet, sc);
                    if (cuentaRet == null) break;

                    System.out.print("Ingrese monto a retirar: ");
                    double montoRet;
                    try {
                        montoRet = sc.nextDouble();
                        sc.nextLine();
                        if (montoRet <= 0) throw new IllegalArgumentException("El monto debe ser mayor a 0.");
                        try {
                            Retirar retiro = new Retirar(cuentaRet, montoRet);
                            retiro.ejecutar();
                            System.out.println("Retiro realizado.");
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error al realizar el retiro: " + e.getMessage());
                        }
                    } catch (InputMismatchException | IllegalArgumentException e) {
                        System.out.println("Monto inválido: " + e.getMessage());
                        sc.nextLine();
                    }
                    break;

                case 4: // Listar nombres de todos los socios (map + forEach, programación funcional)
                    System.out.println("Nombres de todos los socios:");
                    socios.stream()
                            .map(Socio::getNombre)
                            .forEach(System.out::println);
                    break;

                case 5: // Mostrar cuentas con saldo > $500,000 (filter, programación funcional)
                    System.out.println("Cuentas con saldo mayor a $500,000:");
                    socios.stream()
                            .flatMap(s -> s.getCuentas().stream())
                            .filter(c -> c.getSaldo() > 500000)
                            .forEach(c -> System.out.println(
                                    "Cuenta: " + c.getNumeroCuenta() +
                                            ", Socio: " + c.getPropietario().getNombre() +
                                            ", Saldo: " + c.getSaldo()
                            ));
                    break;

                case 6: // Calcular total de dinero en la cooperativa (reduce, programación funcional)
                    double total = socios.stream()
                            .flatMap(s -> s.getCuentas().stream())
                            .map(Cuenta::getSaldo)
                            .reduce(0.0, Double::sum);
                    System.out.println("Total en la cooperativa: $" + total);
                    break;

                case 7: // Listar todos los socios con sus datos (programación funcional)
                    System.out.println("Listado de socios:");
                    socios.stream()
                            .forEach(s -> {
                                System.out.println("Nombre: " + s.getNombre());
                                System.out.println("Cédula: " + s.getCedula());
                                System.out.println("Cuentas:");
                                s.getCuentas().stream()
                                        .forEach(c -> System.out.println(
                                                "  - Nro: " + c.getNumeroCuenta() +
                                                        " | Tipo: " + c.getTipo() +
                                                        " | Saldo: " + c.getSaldo()
                                        ));
                                System.out.println();
                            });
                    break;

                case 8:
                    System.out.println("¡Hasta pronto!");
                    sc.close();
                    return;

                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    // Métodos auxiliares para selección de socio y cuenta
    private static Socio seleccionarSocio(List<Socio> socios, Scanner sc) {
        System.out.println("Seleccione el socio:");
        for (int i = 0; i < socios.size(); i++) {
            System.out.println((i + 1) + ". " + socios.get(i).getNombre() + " - " + socios.get(i).getCedula());
        }
        System.out.print("Opción: ");
        int idx;
        try {
            idx = sc.nextInt();
            sc.nextLine();
            if (idx < 1 || idx > socios.size()) {
                System.out.println("Índice fuera de rango.");
                return null;
            }
            return socios.get(idx - 1);
        } catch (InputMismatchException e) {
            System.out.println("Por favor ingrese un número válido.");
            sc.nextLine();
            return null;
        }
    }

    private static Cuenta seleccionarCuenta(Socio socio, Scanner sc) {
        List<Cuenta> cuentas = socio.getCuentas();
        if (cuentas.isEmpty()) {
            System.out.println("El socio no tiene cuentas.");
            return null;
        }
        System.out.println("Seleccione la cuenta:");
        for (int i = 0; i < cuentas.size(); i++) {
            System.out.println((i + 1) + ". " + cuentas.get(i).getNumeroCuenta() + " (" + cuentas.get(i).getTipo() + ")");
        }
        System.out.print("Opción: ");
        int idx;
        try {
            idx = sc.nextInt();
            sc.nextLine();
            if (idx < 1 || idx > cuentas.size()) {
                System.out.println("Índice fuera de rango.");
                return null;
            }
            return cuentas.get(idx - 1);
        } catch (InputMismatchException e) {
            System.out.println("Por favor ingrese un número válido.");
            sc.nextLine();
            return null;
        }
    }
}