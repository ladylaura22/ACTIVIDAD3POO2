package Cooperativa.Modelo;

import java.util.HashMap;
import java.util.Map;

public class Cooperativa {
    private Map<String, Socio> socios = new HashMap<>();

    public boolean agregarSocio(Socio socio) {
        if (socios.containsKey(socio.getCedula())) {
            return false;
        }
        socios.put(socio.getCedula(), socio);
        return true;
    }

    public Socio buscarSocio(String cedula) {
        return socios.get(cedula);
    }

    public Cuenta buscarCuentaDeSocio(String cedula, String numeroCuenta) {
        Socio socio = buscarSocio(cedula);
        if (socio == null) {
            return null;
        }
        return socio.getCuentaPorNumero(numeroCuenta);
    }
}
