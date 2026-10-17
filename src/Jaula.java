public class Jaula {
        private int numero;
        private String tamano;
        private String zona;
        private Paciente ocupante;

    public Jaula(int numero, String tamano, String zona) {
        this.numero = numero;
        this.tamano = tamano;
        this.zona = zona;
        this.ocupante = null;
    }

    public int getNumero() {
        return numero;
    }

    public String getTamano() {
        return tamano;
    }

    public String getZona() {
        return zona;
    }

    public Paciente getOcupante() {
        return ocupante;
    }

    public void setOcupante(Paciente ocupante) {
        this.ocupante = ocupante;
    }

    public boolean estaLibre() {
        return ocupante == null;
    }
}
