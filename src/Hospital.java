public class Hospital {
    //[] Es decir, esta variable va a almacenar una lista de varios pacientes.
    private Paciente[] pacientes;
    private int contadorPacientes;
    private Jaula[] jaulas;

    public Hospital() {
        pacientes = new Paciente[10];
        contadorPacientes = 0;
        jaulas = new Jaula[6];
        cargarDatosIniciales();
    }

    private void cargarDatosIniciales() {
        jaulas[0] = new Jaula(1, "S", "A");
        jaulas[1] = new Jaula(2, "M", "A");
        jaulas[2] = new Jaula(3, "M", "A");
        jaulas[3] = new Jaula(4, "L", "A");
        jaulas[4] = new Jaula(5, "S", "B");
        jaulas[5] = new Jaula(6, "M", "B");

        pacientes[0] = new Perro("Rocky", 30.0, 4, "labrador", true);
        pacientes[1] = new Gato("Misifu", 4.0, 3);
        pacientes[2] = new AveExotica("Kiwi", 0.8, 2, 70.0);
        contadorPacientes = 3;
    }

    public Paciente buscarPaciente(String nombre) {
        for (int i = 0; i < contadorPacientes; i++) {
            if (pacientes[i].getNombre().equalsIgnoreCase(nombre)) {
                return pacientes[i];
            }
        }
        return null;
    }

    public String ingresarPerro(String nombre, double peso, int edad, String raza, String vacunaStr) {
        if (peso <= 0 || peso > 80 || edad < 0 || edad > 30 || nombre == null || nombre.trim().isEmpty()) {
            return "ERROR: datos invalidos";
        }
        if (buscarPaciente(nombre) != null) {
            return "ERROR: nombre duplicado";
        }
        if (contadorPacientes >= 10) {
            return "ERROR: hospital lleno";
        }
        boolean vacunado = vacunaStr.equalsIgnoreCase("si");
        Perro p = new Perro(nombre, peso, edad, raza, vacunado);
        pacientes[contadorPacientes++] = p;

        if (!vacunado) {
            return "OK: " + nombre + " ingresado en CUARENTENA";
        }
        return "OK: " + nombre + " ingresado";
    }

    public String ingresarGato(String nombre, double peso, int edad) {
        if (peso <= 0 || peso > 15 || edad < 0 || edad > 30 || nombre == null || nombre.trim().isEmpty()) {
            return "ERROR: datos invalidos";
        }
        if (buscarPaciente(nombre) != null) {
            return "ERROR: nombre duplicado";
        }
        if (contadorPacientes >= 10) {
            return "ERROR: hospital lleno";
        }
        Gato g = new Gato(nombre, peso, edad);
        pacientes[contadorPacientes++] = g;
        return "OK: " + nombre + " ingresado";
    }

    public String ingresarAve(String nombre, double peso, int edad, double envergadura) {
        if (peso <= 0 || peso > 15 || edad < 0 || edad > 30 || envergadura <= 0 || nombre == null || nombre.trim().isEmpty()) {
            return "ERROR: datos invalidos";
        }
        if (buscarPaciente(nombre) != null) {
            return "ERROR: nombre duplicado";
        }
        if (contadorPacientes >= 10) {
            return "ERROR: hospital lleno";
        }
        AveExotica a = new AveExotica(nombre, peso, edad, envergadura);
        pacientes[contadorPacientes++] = a;
        return "OK: " + nombre + " ingresado";
    }

    public String vacunar(String nombre) {
        Paciente p = buscarPaciente(nombre);
        if (p == null) {
            return "ERROR: paciente no encontrado";
        }
        if (!(p instanceof Perro)) {
            return "ERROR: solo aplica a perros";
        }
        Perro perro = (Perro) p;
        if (perro.isVacunado()) {
            return "ERROR: ya vacunado";
        }
        perro.setVacunado(true);
        perro.setEstado("INGRESADO");
        return "OK: " + nombre + " vacunado (pasa a INGRESADO)";
    }

    public String asignar(String nombre) {
        Paciente p = buscarPaciente(nombre);
        if (p == null) {
            return "ERROR: paciente no encontrado";
        }
        if (p.getEstado().equals("CUARENTENA")) {
            return "ERROR: paciente en cuarentena";
        }
        if (p.getJaulaAsignada() != null) {
            return "ERROR: paciente ya tiene jaula";
        }

        String zonaReq = (p instanceof AveExotica) ? "B" : "A";
        int tamReqValor = obtenerValorTamanoRequerido(p);

        Jaula jaulaSeleccionada = null;
        for (int i = 0; i < jaulas.length; i++) {
            Jaula j = jaulas[i];
            if (j.estaLibre() && j.getZona().equals(zonaReq)) {
                int tamJaulaValor = convertirTamanoAValue(j.getTamano());
                if (tamJaulaValor >= tamReqValor) {
                    jaulaSeleccionada = j;
                    break;
                }
            }
        }

        if (jaulaSeleccionada == null) {
            return "ERROR: no hay jaula compatible";
        }

        p.setJaulaAsignada(jaulaSeleccionada);
        jaulaSeleccionada.setOcupante(p);
        return "OK: " + nombre + " asignado a jaula " + jaulaSeleccionada.getNumero();
    }

    private int obtenerValorTamanoRequerido(Paciente p) {
        double peso = p.getPeso();
        String tam = "S";
        if (peso >= 5 && peso <= 25) {
            tam = "M";
        } else if (peso > 25) {
            tam = "L";
        }
        if (p instanceof AveExotica) {
            AveExotica ave = (AveExotica) p;
            if (ave.getEnvergadura() > 60 && tam.equals("S")) {
                tam = "M";
            }
        }
        return convertirTamanoAValue(tam);
    }

    private int convertirTamanoAValue(String tam) {
        if (tam.equals("S")) return 1;
        if (tam.equals("M")) return 2;
        if (tam.equals("L")) return 3;
        return 1;
    }

    public String medicar(String nombre, double mg) {
        Paciente p = buscarPaciente(nombre);
        if (p == null) {
            return "ERROR: paciente no encontrado";
        }
        if (mg <= 0) {
            return "ERROR: datos invalidos";
        }
        if (p.getEstado().equals("CUARENTENA")) {
            return "ERROR: paciente en cuarentena";
        }
        if (p.getJaulaAsignada() == null) {
            return "ERROR: paciente sin jaula";
        }
        if (p instanceof Gato) {
            Gato g = (Gato) p;
            if (g.getNivelEstres() >= 8) {
                return "ERROR: gato con estres alto";
            }
        }

        double dosisMax = p.getPeso() * p.getFactorDosis();
        if (mg > dosisMax) {
            return "ERROR: dosis excede el maximo";
        }

        double topeAcumulado = 3 * dosisMax;
        if (p.getDosisAcumulada() + mg > topeAcumulado) {
            return "ERROR: tope acumulado superado";
        }

        p.setDosisAcumulada(p.getDosisAcumulada() + mg);
        p.setEstado("EN_TRATAMIENTO");

        if (p instanceof Gato) {
            Gato g = (Gato) p;
            g.setNivelEstres(Math.min(10, g.getNivelEstres() + 2));
        }

        return "OK: " + nombre + " medicado con " + (int)mg + " mg";
    }

    public String alta(String nombre) {
        Paciente p = buscarPaciente(nombre);
        if (p == null) {
            return "ERROR: paciente no encontrado";
        }
        if (!p.getEstado().equals("EN_TRATAMIENTO")) {
            return "ERROR: paciente no apto para alta";
        }

        Jaula j = p.getJaulaAsignada();
        if (j != null) {
            j.setOcupante(null);
        }

        int index = -1;
        for (int i = 0; i < contadorPacientes; i++) {
            if (pacientes[i] == p) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            for (int i = index; i < contadorPacientes - 1; i++) {
                pacientes[i] = pacientes[i + 1];
            }
            pacientes[contadorPacientes - 1] = null;
            contadorPacientes--;
        }

        return "OK: " + nombre + " dado de alta";
    }

    public String generarReporte() {
        StringBuilder sb = new StringBuilder();
        sb.append("REPORTE\n");
        sb.append("Pacientes: ").append(contadorPacientes).append("/10\n");
        for (int i = 0; i < contadorPacientes; i++) {
            Paciente p = pacientes[i];
            String jaulaStr = (p.getJaulaAsignada() == null) ? "-" : String.valueOf(p.getJaulaAsignada().getNumero());
            sb.append(p.getNombre()).append(" | ")
                    .append(p.getTipo()).append(" | ")
                    .append(String.format("%.1f", p.getPeso())).append(" kg | ")
                    .append(p.getEstado()).append(" | ")
                    .append("jaula: ").append(jaulaStr).append("\n");
        }
        int jaulasLibres = 0;
        for (int i = 0; i < jaulas.length; i++) {
            if (jaulas[i].estaLibre()) {
                jaulasLibres++;
            }
        }
        sb.append("Jaulas libres: ").append(jaulasLibres);
        return sb.toString();
    }


}
