package Cooperativa.Transaccion;
import Cooperativa.Modelo.Cuenta;

public class Depositar implements Transaccion{
    private Cuenta cuenta;
    private double monto;

    public Depositar(Cuenta cuenta, double monto) {
        this.cuenta = cuenta;
        this.monto = monto;
    }

    @Override
    public void ejecutar() {
        cuenta.depositarDinero(monto);
    }

    @Override
    public double getMonto() {
        return monto;
    }

}
