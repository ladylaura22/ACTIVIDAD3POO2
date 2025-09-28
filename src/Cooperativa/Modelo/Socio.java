package Cooperativa.Modelo;
import java.util.ArrayList;
import java.util.List;

public class Socio {

    private String nombre;
    private String cedula;
    private String telefono;
    private String direccion;
    private List<Cuenta> cuentas;

    public Socio(String nombre, String cedula) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.cuentas = new ArrayList<>();
    }

    //Metodo para agregar una cuenta a la lista de cuentas del socio
    public void abrirCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

    //Cerrar cuenta
    public void cerrarCuenta(Cuenta cuenta) {
        cuentas.remove(cuenta);
    }

    public String getNombre() {
        return nombre;
    }
    public String getCedula() {
        return cedula;
    }
    public List<Cuenta> getCuentas() {
        return cuentas;

    }
    public String getTelefono() {
        return telefono;
    }

}
