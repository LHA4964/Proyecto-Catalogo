package dominio;

public class ErrorOrdenadorNoEncontrado extends Exception {
    private int oerror;
    public ErrorOrdenadorNoEncontrado(int o) {
        oerror = o;
    }
    public int getOrdenador() {
        return oerror;
    }
}
