package Cooperativa.Modelo;

public class Cuenta {
    // Atributos
    private String numeroCuenta;
    private double saldo;
    private String tipo;
    private Socio propietario;
    // Constructor
    public Cuenta (String numeroCuenta, String tipo, Socio propietario) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = 0.0;
        this.tipo = tipo;
        this.propietario = propietario;
    }

    // Métodos
    public void depositarDinero(double monto) {
        if (monto > 0) {
            this.saldo += monto;
        } else {
            System.out.println("El monto debe ser mayor a 0.");
        }

    }

    public void retirarDinero (double monto){
        if (monto > 0 && monto <= this.saldo) {
            this.saldo -= monto;
        } else {
            System.out.println("El monto debe ser mayor a 0 y menor o igual al saldo disponible.");
        }
    }
    // get para ver saldo
    public double getSaldo() {
        return saldo;
    }

    public String getTipo() {
        return tipo;
    }

    //numero de cuenta de acuerdo al socio
    public String getNumeroCuenta() {
        return numeroCuenta;
    }
    public Socio getPropietario() {
        return propietario;
    }

}
