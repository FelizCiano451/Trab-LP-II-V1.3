package model;

public interface Entrada {
    String getTitulo();
    String getAutor();
    int getAno();
    String gerarBibTeX();
}
