
package Cooperativa.Modelo;
import java.time.LocalDate;

public class AhorroPremium extends CuentaAhorro {
    public AhorroPremium(String numeroCuenta, Socio propietario, LocalDate fechaApertura) {
        super(numeroCuenta, 0.05, propietario, "premiun", fechaApertura);
    }
    // Método para aplicar interés al saldo
    // El interés es del 5% del saldo actual
    // El interés se aplica mensualmente
    // El interés se deposita en la cuenta
    // El interés se muestra en consola
    // El interés se aplica solo si la cuenta está activa
    // El interés se aplica solo si el saldo es mayor a 0
    @Override
    public void aplicarInteres() {
        double interesGanado = getSaldo() * 0.05;
        depositarDinero(interesGanado);
        System.out.println("Interés aplicado (premium): " + interesGanado);
    }
}
