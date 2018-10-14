package co.colombiandentist.www.sos_prototype.Logica.Pojos;

/**
 * Created by Usuario on 27/11/2017.
 */

public class pojoCalificacion {
    private String Usuario;
    private String Comentario;
    private short Calificacion;
    private String Fecha;

    public pojoCalificacion(String usuario, String comentario, short calificacion, String fecha) {
        Usuario = usuario;
        Comentario = comentario;
        Calificacion = calificacion;
        Fecha = fecha;
    }

    public pojoCalificacion() {
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getComentario() {
        return Comentario;
    }

    public void setComentario(String comentario) {
        Comentario = comentario;
    }

    public short getCalificacion() {
        return Calificacion;
    }

    public void setCalificacion(short calificacion) {
        Calificacion = calificacion;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }
}
