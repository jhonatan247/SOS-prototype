package co.colombiandentist.www.sos_prototype.Logica.Models;

import co.colombiandentist.www.sos_prototype.Logica.Interfaces.Card;

/**
 * Created by Usuario on 28/11/2017.
 */

public class DocenteModel implements Card {
    public static final int TYPE = 3;

    private String Id;
    private String Nombre;
    private String Departamento;

    public DocenteModel(String id, String nombre, String departamento) {
        Id = id;
        Nombre = nombre;
        Departamento = departamento;
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

    public String getDepartamento() {
        return Departamento;
    }

    public void setDepartamento(String departamento) {
        Departamento = departamento;
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
        DocenteModel model = (DocenteModel)card;
        Nombre = model.Nombre;
        Departamento = model.Departamento;
    }
}
