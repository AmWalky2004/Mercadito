package modelo;

import java.sql.Timestamp;

public class Historial {
    // Atributos que coinciden con los campos de la base de datos
    private int id_historial;
    private String accion;
    private String modulo;
    private Timestamp fecha_hora;

    // Constructor vacío (necesario para muchas operaciones de base de datos)
    public Historial() {
    }

    // Constructor con parámetros (útil para crear registros rápidamente)
    public Historial(int id_historial, String accion, String modulo, Timestamp fecha_hora) {
        this.id_historial = id_historial;
        this.accion = accion;
        this.modulo = modulo;
        this.fecha_hora = fecha_hora;
    }

    // Métodos Getter y Setter para acceder y modificar las propiedades
    public int getId_historial() {
        return id_historial;
    }

    public void setId_historial(int id_historial) {
        this.id_historial = id_historial;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public Timestamp getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(Timestamp fecha_hora) {
        this.fecha_hora = fecha_hora;
    }
}