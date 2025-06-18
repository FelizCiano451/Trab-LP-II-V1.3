ppackage model;

public class Slide implements Entrada {
    private static final long serialVersionUID = 1L;

    private String titulo;
    private String autor;
    private int ano;
    private String evento;

   public Slide(String titulo, String autor, int ano, int numeroDeSlides) {
    if (titulo == null || titulo.isBlank()) {
        throw new IllegalArgumentException("O título não pode ser vazio.");
    }
    if (autor == null || autor.isBlank()) {
        throw new IllegalArgumentException("O autor não pode ser vazio.");
    }
    if (ano <= 0) {
        throw new IllegalArgumentException("O ano deve ser positivo.");
    }
    if (numeroDeSlides <= 0) {
        throw new IllegalArgumentException("O número de slides deve ser positivo.");
    }

    this.titulo = titulo.trim();
    this.autor = autor.trim();
    this.ano = ano;
    this.numeroDeSlides = numeroDeSlides;
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

    @Override
    public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Slide)) return false;
    Slide slide = (Slide) o;
    return titulo.equalsIgnoreCase(slide.titulo) &&
           autor.equalsIgnoreCase(slide.autor);
}

    @Override
    public int hashCode() {
    return (titulo.toLowerCase() + autor.toLowerCase()).hashCode();
}
}
