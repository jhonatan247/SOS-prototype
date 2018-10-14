package co.colombiandentist.www.sos_prototype.Logica.Models;

import co.colombiandentist.www.sos_prototype.Logica.Interfaces.Card;

/**
 * Created by Usuario on 28/11/2017.
 */

public class NotificationModel implements Card {
    public static final int TYPE = 4;

    private String Id;
    private String Usuario;
    private String Titulo;
    private String Noticia;
    private String Fecha;

    public NotificationModel(String id, String usuario, String titulo, String noticia, String fecha) {
        Id = id;
        Usuario = usuario;
        Titulo = titulo;
        Noticia = noticia;
        Fecha = fecha;
    }

    public static int getTYPE() {
        return TYPE;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
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

    @Override
    public int getCardType() {
        return TYPE;
    }

    @Override
    public String getId() {
        return Id;
    }

    @Override
    public void changeValues(Card card) {
        NotificationModel model = (NotificationModel)card;
        Usuario = model.Usuario;
        Titulo = model.Titulo;
        Noticia = model.Noticia;
        Fecha = model.Fecha;

    }
}
