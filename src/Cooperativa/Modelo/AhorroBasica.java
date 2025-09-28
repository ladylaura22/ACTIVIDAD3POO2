package Cooperativa.Modelo;
import java.time.LocalDate;

public class AhorroBasica extends CuentaAhorro {

    public AhorroBasica(String numeroCuenta, Socio propietario, LocalDate fechaApertura) {
        super(numeroCuenta, 0.015, propietario, "basica", fechaApertura);
    }

    @Override
    public void aplicarInteres() {
        double interesGanado = getSaldo() * 0.015;
        depositarDinero(interesGanado);
        System.out.println("Interés aplicado (básica): " + interesGanado);
    }


}

