public class Gato extends Paciente{
    private int nivelEstres; //el nivel de estres va de 0 a 10

    public Gato(String nombre, double peso, int edad) {
        super(nombre, peso, edad, "INGRESADO" , 6.0);
        this.nivelEstres = 0;
    }

    public int getNivelEstres() {
        return nivelEstres;
    }

    public void setNivelEstres(int nivelEstres) {
        if (nivelEstres >= 0 && nivelEstres <= 10) {
            this.nivelEstres = nivelEstres;
        }
    }

    @Override
    public String getTipo() {
        return "Gato";
    }
}
