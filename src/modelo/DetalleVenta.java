package modelo;

public class DetalleVenta {
    private int idDetalledeVenta;
    private int idCabeceraVenta;
    private int idProducto;
    private int cantidad;
    private double preciounitario;
    private double subtotal;
    private double descuento;
    private double iva;
    private double totalpagar;
    private int estado;
    
    public DetalleVenta() {
        this.idDetalledeVenta = 0;
        this.idCabeceraVenta = 0;
        this.idProducto = 0;
        this.cantidad = 0;
        this.preciounitario = 0.0;
        this.subtotal = 0.0;
        this.descuento = 0.0;
        this.iva = 0.0;
        this.totalpagar = 0.0;
        this.estado = 1;
    }
    
    // Getters y Setters
    public int getIdDetalledeVenta() { return idDetalledeVenta; }
    public void setIdDetalledeVenta(int idDetalledeVenta) { this.idDetalledeVenta = idDetalledeVenta; }
    
    public int getIdCabeceraVenta() { return idCabeceraVenta; }
    public void setIdCabeceraVenta(int idCabeceraVenta) { this.idCabeceraVenta = idCabeceraVenta; }
    
    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }
    
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    
    public double getPreciounitario() { return preciounitario; }
    public void setPreciounitario(double preciounitario) { this.preciounitario = preciounitario; }
    
    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
    
    public double getDescuento() { return descuento; }
    public void setDescuento(double descuento) { this.descuento = descuento; }
    
    public double getIva() { return iva; }
    public void setIva(double iva) { this.iva = iva; }
    
    public double getTotalpagar() { return totalpagar; }
    public void setTotalpagar(double totalpagar) { this.totalpagar = totalpagar; }
    
    public int getEstado() { return estado; }
    public void setEstado(int estado) { this.estado = estado; }
}
