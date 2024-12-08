package dominio;

public class ErrorOrdenadorDuplicado extends Exception {
    private Ordenador oerror;
    public ErrorOrdenadorDuplicado(Ordenador o) {
        oerror = o;
    }
    public Ordenador getOrdenador() {
        return oerror;
    }
}