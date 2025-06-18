package biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Colecao<T extends IEntrada> {
    private String nome;
    private String autor;
    private Class<T> tipo;
    private int maxEntradas;
    private List<T> entradas;

    public Colecao(String nome, String autor, Class<T> tipo, int maxEntradas, List<T> entradasIniciais) {
        this.nome = nome;
        this.autor = autor;
        this.tipo = tipo;
        this.maxEntradas = maxEntradas;
        this.entradas = new ArrayList<>();
        adicionarEntradas(entradasIniciais);
    }

    public String getNome() {
        return nome;
    }

    public String getAutor() {
        return autor;
    }

    public Class<T> getTipo() {
        return tipo;
    }

    public List<T> getEntradas() {
        return entradas;
    }

    public void adicionarEntradas(List<T> novasEntradas) {
        for (T entrada : novasEntradas) {
            if (entradas.size() < maxEntradas) {
                entradas.add(entrada);
            } else {
                System.out.println("A coleção '" + nome + "' atingiu o número máximo de entradas.");
                break;
            }
        }
    }

    public void adicionarEntrada(T entrada) {
        if (entradas.size() < maxEntradas) {
            entradas.add(entrada);
        } else {
            System.out.println("A coleção '" + nome + "' já está cheia.");
        }
    }

    public boolean removerEntrada(T entrada) {
        boolean removido = entradas.remove(entrada);
        if (removido && entradas.isEmpty()) {
            System.out.println("Coleção '" + nome + "' agora está vazia e será removida.");
        }
        return removido;
    }
}
