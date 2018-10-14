package co.colombiandentist.www.sos_prototype.Logica.Models;

import co.colombiandentist.www.sos_prototype.Logica.Interfaces.Card;

/**
 * Created by Usuario on 25/10/2017.
 */

public class NewsModel implements Card {
    public static final int TYPE = 0;

    private String Id;
    private String usuario;
    private String imagen;
    private String texto;
    private String fecha;

    public NewsModel(String id, String usuario, String imagen, String texto, String fecha) {
        Id = id;
        this.usuario = usuario;
        this.imagen = imagen;
        this.texto = texto;
        this.fecha = fecha;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String getId() {
        return Id;
    }


    @Override
    public int getCardType() {
        return TYPE;
    }

    @Override
    public void changeValues(Card card) {
        try {
            NewsModel values = (NewsModel) card;
            imagen = values.imagen;
            texto = values.texto;
            fecha = values.fecha;
            usuario = values.usuario;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}