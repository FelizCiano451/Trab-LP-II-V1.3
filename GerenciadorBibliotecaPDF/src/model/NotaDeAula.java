package model;

public class NotaDeAula implements Entrada {
    private String titulo;
    private String autor;
    private int ano;
    private String curso;

    public NotaDeAula(String titulo, String autor, int ano, String curso) {
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.curso = curso;
    }

    @Override
    public String getTitulo() { return titulo; }

    @Override
    public String getAutor() { return autor; }

    @Override
    public int getAno() { return ano; }

    public String getCurso() { return curso; }

    @Override
    public String gerarBibTeX() {
        return String.format("@misc{%s,\n  author = {%s},\n  title = {%s},\n  year = {%d},\n  note = {Nota de aula do curso: %s}\n}",
            titulo.replaceAll("\\s", ""), autor, titulo, ano, curso);
    }
}
