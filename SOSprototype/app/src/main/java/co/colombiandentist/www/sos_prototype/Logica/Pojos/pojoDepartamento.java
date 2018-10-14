package co.colombiandentist.www.sos_prototype.Logica.Pojos;

/**
 * Created by Usuario on 27/11/2017.
 */

public class pojoDepartamento {
    private String Nombre;

    public pojoDepartamento() {
    }

    public pojoDepartamento(String nombre) {
        Nombre = nombre;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }
}
