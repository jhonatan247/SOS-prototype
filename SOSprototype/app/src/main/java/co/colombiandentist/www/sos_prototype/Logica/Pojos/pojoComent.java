package co.colombiandentist.www.sos_prototype.Logica.Pojos;

/**
 * Created by Usuario on 28/11/2017.
 */

public class pojoComent {
    private String User;
    private String Ano;
    private String Mensaje;

    public pojoComent(String user, String ano, String mensaje) {
        User = user;
        Ano = ano;
        Mensaje = mensaje;
    }

    public pojoComent() {
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getAno() {
        return Ano;
    }

    public void setAno(String ano) {
        Ano = ano;
    }

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String mensaje) {
        Mensaje = mensaje;
    }
}
