package modelo;
import java.sql.Date;

public class Venta {
    private int idCabeceraVenta;
    private int idCliente;
    private double valorpagar;
    private Date fechaventa;
    private int estado;  // 1=Activa, 0=Anulada
    
    public Venta() {
        this.idCabeceraVenta = 0;
        this.idCliente = 0;
        this.valorpagar = 0.0;
        this.fechaventa = null;
        this.estado = 1;
    }
    
    // Getters y Setters
    public int getIdCabeceraVenta() { return idCabeceraVenta; }
    public void setIdCabeceraVenta(int idCabeceraVenta) { this.idCabeceraVenta = idCabeceraVenta; }
    
    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
    
    public double getValorpagar() { return valorpagar; }
    public void setValorpagar(double valorpagar) { this.valorpagar = valorpagar; }
    
    public Date getFechaventa() { return fechaventa; }
    public void setFechaventa(Date fechaventa) { this.fechaventa = fechaventa; }
    
    public int getEstado() { return estado; }
    public void setEstado(int estado) { this.estado = estado; }
    
    
}