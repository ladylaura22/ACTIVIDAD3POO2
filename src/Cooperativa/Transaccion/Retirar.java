package Cooperativa.Transaccion;
import Cooperativa.Modelo.Cuenta;

public class Retirar implements Transaccion{
    private Cuenta cuenta;
    private double monto;

    public Retirar(Cuenta cuenta, double monto) {
        this.cuenta = cuenta;
        this.monto = monto;
    }

    @Override
    public void ejecutar() {
        cuenta.retirarDinero(monto);
    }

    @Override
    public double getMonto() {
        return monto;
    }
}





