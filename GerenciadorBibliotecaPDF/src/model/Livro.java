package model;

public class Livro implements Entrada {
    private String titulo;
    private String autor;
    private int ano;
    private String editora;

    public Livro(String titulo, String autor, int ano, String editora) {
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.editora = editora;
    }

    @Override
    public String getTitulo() { return titulo; }

    @Override
    public String getAutor() { return autor; }

    @Override
    public int getAno() { return ano; }

    public String getEditora() { return editora; }

    @Override
    public String gerarBibTeX() {
        return String.format("@book{%s,\n  author = {%s},\n  title = {%s},\n  year = {%d},\n  publisher = {%s}\n}",
            titulo.replaceAll("\\s", ""), autor, titulo, ano, editora);
    }
}
