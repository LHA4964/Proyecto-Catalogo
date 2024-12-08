package dominio;

import java.io.*;
import java.util.ArrayList;
import java.io.Serializable;

public class Catalogo implements Serializable{
    private ArrayList<Ordenador> catalogo;

    public Catalogo() {
        this.catalogo = new ArrayList<>();
    }

    public void annadirOrdenador(Ordenador orde) throws ErrorOrdenadorDuplicado{
        int i=0;
        for (Ordenador ord : catalogo){
            if (orde.getId() == ord.getId()){
               i = 1;
            }
        }
        if(i == 0) {
            catalogo.add(orde);
        }else{
            throw new ErrorOrdenadorDuplicado(orde);
        }
    }

    public void removeOrdenador(int id) throws ErrorOrdenadorNoEncontrado{
        int i=0;
        for (Ordenador ord : catalogo){
            if (ord.getId() == id){
                catalogo.remove(ord);
                i=1;
                break;
            }
        }
        if (i==0){
            throw new ErrorOrdenadorNoEncontrado(id);
        }
    }

    public void modificarOrdenador(int id, String atributo, String valor) throws ErrorOrdenadorNoEncontrado {
        Ordenador encontrado = null;
        for (Ordenador ord : catalogo){
            if (ord.getId() == id){
                encontrado = ord;
            }
        }
        if (encontrado != null) {
            switch (atributo.toLowerCase()) {
                case "marca":
                    encontrado.setMarca(valor);
                    break;
                case "cpu":
                    encontrado.setCpu(valor);
                    break;
                case "gpu":
                    encontrado.setGpu(valor);
                    break;
                case "ram":
                    encontrado.setRam(Integer.parseInt(valor));
                    break;
                case "precio":
                    encontrado.setPrecio(Integer.parseInt(valor));
                    break;
                default:
                    System.out.println("Atributo no reconocido.");
                    break;
            }
        }else {
            throw new ErrorOrdenadorNoEncontrado(id);
        }
    }

    public String list(){
        StringBuilder sb = new StringBuilder();
        sb.append("Ordenadores:\n");
        for (Ordenador ord : catalogo) {
            sb.append(ord.list());
        }
        return sb.toString();
    }
    public String info(int id){
        for (Ordenador ord : catalogo){
            if (ord.getId() == id){
                return ord.info();
            }
        }
        return "Ordenador " + id + " no encontrado";
    }

    public void grabar(){
        try{
            ObjectOutputStream fo=new ObjectOutputStream (new FileOutputStream("catalogo.ser"));
            fo.writeObject(this);
            fo.close();
        } catch (Exception e){
            System.out.println("error de escritura");
        }
    }
    public static Catalogo leer() {
        try {
            ObjectInputStream fi = new ObjectInputStream(new FileInputStream("catalogo.ser"));
            return (Catalogo) fi.readObject();
        } catch (Exception e){
            return new Catalogo();
        }
    }
}