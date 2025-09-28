package Cooperativa.Modelo;
import java.time.LocalDate;

public abstract class CuentaAhorro extends Cuenta
{
    private double interes;
    private double saldoMinimo;
    private boolean activa;
    private LocalDate fechaApertura;

    // Constructor
    public CuentaAhorro(String numeroCuenta, double interes, Socio propietario, String tipo, LocalDate fechaApertura) {
        super(numeroCuenta, tipo, propietario);
        this.interes = interes;
        this.saldoMinimo = 50.0; // Ejemplo de saldo mínimo
        this.activa = true; // La cuenta está activa al momento de la creación
        this.fechaApertura = fechaApertura;
    }

    // RETORNAR FECHA DE APERTURA
    public LocalDate getFechaApertura() {
        return fechaApertura;
    }

    // Hay 2 tipo de cuenta de ahorro: basica y premiun
    // Método para aplicar interés al saldo
    public abstract void aplicarInteres();

    //METODO

    // Método para verificar si la cuenta está activa
    public boolean estaActiva() {
        if (getSaldo() >= saldoMinimo) {
            return activa;
        } else {
            activa = false;
            System.out.println("La cuenta está inactiva por insuficiencia de saldo.");

            getPropietario().cerrarCuenta(this);
            return activa;
        }
    }
}
