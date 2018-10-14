package co.colombiandentist.www.sos_prototype.Logica.Models;

import co.colombiandentist.www.sos_prototype.Logica.Interfaces.Card;

/**
 * Created by Usuario on 28/11/2017.
 */

public class PersonModel implements Card {
    public static final int TYPE = 2;

    private String Id;
    private String Nombre;
    private String Apellido;
    private String Correo;
    private String Foto;

    public PersonModel(String id, String nombre, String apellido, String correo, String foto) {
        Id = id;
        Nombre = nombre;
        Apellido = apellido;
        Correo = correo;
        Foto = foto;
    }

    public void setId(String id) {
        Id = id;
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

    public String getFoto() {
        return Foto;
    }

    public void setFoto(String foto) {
        Foto = foto;
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
        PersonModel model = (PersonModel)card;
        Nombre = model.Nombre;
        Apellido = model.Apellido;
        Correo = model.Correo;
        Foto = model.Foto;

    }
}
