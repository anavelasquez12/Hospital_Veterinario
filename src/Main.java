import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hospital hospital = new Hospital();

        while (true) {
            String linea = scanner.nextLine().trim();
            if (linea.equalsIgnoreCase("salir")) {
                break;
            }

            String[] partes = linea.split("\\s+");
            if (partes.length == 0) continue;

            String comando = partes[0].toLowerCase();

            if (comando.equals("ingresar")) {
                if (partes.length < 2) {
                    System.out.println("Comando desconocido: ERROR: comando desconocido.");
                    continue;
                }
                String tipo = partes[1].toLowerCase();
                if (tipo.equals("perro")) {
                    if (partes.length < 7) {
                        System.out.println("ERROR: datos invalidos");
                    } else {
                        String nombre = partes[2];
                        try {
                            double peso = Double.parseDouble(partes[3]);
                            int edad = Integer.parseInt(partes[4]);
                            String raza = partes[5];
                            String vacuna = partes[6];
                            System.out.println(hospital.ingresarPerro(nombre, peso, edad, raza, vacuna));
                        } catch (NumberFormatException e) {
                            System.out.println("ERROR: datos invalidos");
                        }
                    }
                } else if (tipo.equals("gato")) {
                    if (partes.length < 5) {
                        System.out.println("ERROR: datos invalidos");
                    } else {
                        String nombre = partes[2];
                        try {
                            double peso = Double.parseDouble(partes[3]);
                            int edad = Integer.parseInt(partes[4]);
                            System.out.println(hospital.ingresarGato(nombre, peso, edad));
                        } catch (NumberFormatException e) {
                            System.out.println("ERROR: datos invalidos");
                        }
                    }
                } else if (tipo.equals("ave")) {
                    if (partes.length < 6) {
                        System.out.println("ERROR: datos invalidos");
                    } else {
                        String nombre = partes[2];
                        try {
                            double peso = Double.parseDouble(partes[3]);
                            int edad = Integer.parseInt(partes[4]);
                            double envergadura = Double.parseDouble(partes[5]);
                            System.out.println(hospital.ingresarAve(nombre, peso, edad, envergadura));
                        } catch (NumberFormatException e) {
                            System.out.println("ERROR: datos invalidos");
                        }
                    }
                } else {
                    System.out.println("Comando desconocido: ERROR: comando desconocido.");
                }
            } else if (comando.equals("vacunar")) {
                if (partes.length < 2) {
                    System.out.println("ERROR: paciente no encontrado");
                } else {
                    System.out.println(hospital.vacunar(partes[1]));
                }
            } else if (comando.equals("asignar")) {
                if (partes.length < 2) {
                    System.out.println("ERROR: paciente no encontrado");
                } else {
                    System.out.println(hospital.asignar(partes[1]));
                }
            } else if (comando.equals("medicar")) {
                if (partes.length < 3) {
                    System.out.println("ERROR: datos invalidos");
                } else {
                    String nombre = partes[1];
                    try {
                        double mg = Double.parseDouble(partes[2]);
                        System.out.println(hospital.medicar(nombre, mg));
                    } catch (NumberFormatException e) {
                        System.out.println("ERROR: datos invalidos");
                    }
                }
            } else if (comando.equals("alta")) {
                if (partes.length < 2) {
                    System.out.println("ERROR: paciente no encontrado");
                } else {
                    System.out.println(hospital.alta(partes[1]));
                }
            } else if (comando.equals("reporte")) {
                System.out.println(hospital.generarReporte());
            } else {
                System.out.println("Comando desconocido: ERROR: comando desconocido.");
            }
        }
        scanner.close();
    }
}
