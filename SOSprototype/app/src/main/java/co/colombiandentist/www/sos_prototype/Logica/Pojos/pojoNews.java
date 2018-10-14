package co.colombiandentist.www.sos_prototype.Logica.Pojos;

/**
 * Created by Usuario on 25/10/2017.
 */

public class pojoNews {
    private String Usuario;
    private String Imagen;
    private String Legend;
    private String Type;
    private String Fecha;

    public pojoNews() {
    }

    public pojoNews(String usuario, String imagen, String legend, String type, String fecha) {
        Usuario = usuario;
        Imagen = imagen;
        Legend = legend;
        Type = type;
        Fecha = fecha;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String imagen) {
        Imagen = imagen;
    }

    public String getLegend() {
        return Legend;
    }

    public void setLegend(String legend) {
        Legend = legend;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }
}
