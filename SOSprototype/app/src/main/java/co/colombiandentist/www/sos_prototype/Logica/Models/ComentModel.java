package co.colombiandentist.www.sos_prototype.Logica.Models;

import co.colombiandentist.www.sos_prototype.Logica.Interfaces.Card;

/**
 * Created by Usuario on 28/11/2017.
 */

public class ComentModel implements Card {
    public static final int TYPE = 5;

    private String Id;
    private String user;
    private String fecha;
    private String comentario;

    public ComentModel(String id, String user, String fecha, String comentario) {
        Id = id;
        this.user = user;
        this.fecha = fecha;
        this.comentario = comentario;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
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
        ComentModel model = (ComentModel)card;
        user = model.user;
        fecha = model.fecha;
        comentario = model.comentario;
    }
}
