//La palabra abstract en la declaración impide usar new sobre esa clase.
// Se usa cuando el concepto es una generalización que no tiene existencia concreta: no hay un "Producto genérico", hay productos electrónicos, alimenticios, etc.

public abstract class Paciente {

    //Estados de datos validos, static: Significa que las variables pertenecen a la clase en sí y no a una instancia (objeto) en particular.
    public static final String INGRESADO = "INGRESADO";
    public static final String CUARENTENA = "CUARENTENA";
    public static final String EN_TRATAMIENTO = "EN_TRATAMIENTO";

    //Atributos
    private String nombre;
    private double peso;
    private int edad;
    private String estado = INGRESADO;
    //no cambia, cada especie tiene su dosis fija
    private final double factorDosis;
    private double dosisAcumulada;
    private Jaula jaulaAsignada;

    //constructor

    protected Paciente (String nombre, double peso, int edad, String estado, double factorDosis){
        this.nombre=nombre;
        this.peso=peso;
        this.edad=edad;
        this.estado=estado;
        this.factorDosis=factorDosis;
        this.dosisAcumulada=0.0;
        this.jaulaAsignada=null;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPeso() {
        return peso;
    }

    public int getEdad() {
        return edad;
    }

    public String getEstado() {
        return estado;
    }

    public double getFactorDosis() {
        return factorDosis;
    }

    public double getDosisAcumulada() {
        return dosisAcumulada;
    }

    public Jaula getJaulaAsignada() {
        return jaulaAsignada;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setEdad(int edad) {
        if (edad >= 0 && edad <= 30) {
            this.edad = edad;
        }
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setDosisAcumulada(double dosisAcumulada) {
        this.dosisAcumulada = dosisAcumulada;
    }

    public void setJaulaAsignada(Jaula jaulaAsignada) {
        this.jaulaAsignada = jaulaAsignada;
    }

    public String getTipo(){
        return "Perro";
    };
}
