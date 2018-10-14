package co.colombiandentist.www.sos_prototype.Logica.Pojos;

/**
 * Created by Usuario on 27/11/2017.
 */

public class pojoUser {
    private String Nombre;
    private String Apellido;
    private String Correo;
    private String Contraseña;
    private String Foto;

    public pojoUser() {
    }
    public pojoUser(String nombre, String apellido, String correo, String contraseña) {
        Nombre = nombre;
        Apellido = apellido;
        Correo = correo;
        Contraseña = contraseña;
        Foto = "";
    }
    public pojoUser(String nombre, String apellido, String correo, String contraseña, String foto) {
        Nombre = nombre;
        Apellido = apellido;
        Correo = correo;
        Contraseña = contraseña;
        Foto = foto;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }

    public String getFoto() {
        return Foto;
    }

    public void setFoto(String foto) {
        Foto = foto;
    }
}
