package dominio;
import java.io.Serializable;

public class Ordenador implements Serializable{
    private String marca;
    private String cpu;
    private String gpu;
    private int ram;
    private int precio;
    private int id;

    public Ordenador(String marca, String cpu, String gpu, int ram, int precio, int id) {
        this.marca = marca;
        this.cpu = cpu;
        this.gpu = gpu;
        this.ram = ram;
        this.precio = precio;
        this.id = id;
    }

    public Ordenador(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca_) {
        marca = marca_;
    }
    public String getCpu() {
        return cpu;
    }
    public void setCpu(String cpu_) {
        cpu = cpu_;
    }
    public String getGpu() {
        return gpu;
    }
    public void setGpu(String gpu_) {
        gpu = gpu_;
    }
    public int getRam() {
        return ram;
    }
    public void setRam(int ram_) {
        ram = ram_;
    }
    public int getPrecio() {return precio;}
    public void setPrecio(int precio_) {precio = precio_;}
    public int getId() {return id;}
    public void setId(int id_) {id = id_;}

    public String list() {
        return "id = " + id + ", marca = "+ marca +", precio = " + precio + "€ \n";
    }
    public String info() {
        return "id = " + id + ", marca = " + marca + ", CPU = " + cpu + ", GPU = " + gpu + ", RAM = " + ram + "GB, precio = " + precio + "€ \n";
    }
}