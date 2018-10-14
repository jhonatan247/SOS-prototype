package co.colombiandentist.www.sos_prototype.Logica.Pojos;

/**
 * Created by Usuario on 27/11/2017.
 */

public class pojoAsignatura {
    private String Nombre;
    private String Departamento;

    public pojoAsignatura(String nombre, String departamento) {
        Nombre = nombre;
        Departamento = departamento;
    }

    public pojoAsignatura() {
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDepartamento() {
        return Departamento;
    }

    public void setDepartamento(String departamento) {
        Departamento = departamento;
    }
}
