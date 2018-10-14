package co.colombiandentist.www.sos_prototype.Logica.Pojos;

/**
 * Created by Usuario on 28/11/2017.
 */

public class pojoNotification {
    private String User;
    private String Title;
    private String Noticia;
    private String Fecha;

    public pojoNotification() {
    }

    public pojoNotification(String user, String title, String noticia, String fecha) {
        User = user;
        Title = title;
        Noticia = noticia;
        Fecha = fecha;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getNoticia() {
        return Noticia;
    }

    public void setNoticia(String noticia) {
        Noticia = noticia;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }
}
