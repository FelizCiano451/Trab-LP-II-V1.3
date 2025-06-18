package model;

public class Slide implements Entrada {
    private String titulo;
    private String autor;
    private int ano;
    private String evento;

    public Slide(String titulo, String autor, int ano, String evento) {
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.evento = evento;
    }

    @Override
    public String getTitulo() { return titulo; }

    @Override
    public String getAutor() { return autor; }

    @Override
    public int getAno() { return ano; }

    public String getEvento() { return evento; }

    @Override
    public String gerarBibTeX() {
        return String.format("@misc{%s,\n  author = {%s},\n  title = {%s},\n  year = {%d},\n  howpublished = {Apresentado em: %s}\n}",
            titulo.replaceAll("\\s", ""), autor, titulo, ano, evento);
    }
}
