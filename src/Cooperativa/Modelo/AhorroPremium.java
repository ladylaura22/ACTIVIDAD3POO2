
package Cooperativa.Modelo;
import java.time.LocalDate;

public class AhorroPremium extends CuentaAhorro {
    public AhorroPremium(String numeroCuenta, Socio propietario, LocalDate fechaApertura) {
        super(numeroCuenta, 0.05, propietario, "premiun", fechaApertura);
    }

    @Override
    public void aplicarInteres() {
        double interesGanado = getSaldo() * 0.05;
        depositarDinero(interesGanado);
        System.out.println("Interés aplicado (premium): " + interesGanado);
    }
}
