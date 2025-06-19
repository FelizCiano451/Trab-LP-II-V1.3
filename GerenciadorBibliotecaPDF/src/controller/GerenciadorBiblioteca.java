package controller;

import model.Entrada;
import exceptions.EntradaJaExisteException;
import exceptions.EntradaNaoEncontradaException;

import java.util.ArrayList;
import java.util.List;
import util.Mensagens;

public class GerenciadorBiblioteca implements IGerenciadorBiblioteca {
    private Set<Entrada> entradas;

public GerenciadorBiblioteca() {
    this.entradas = new HashSet<>();
}

    public void adicionarEntrada(Entrada entrada) throws EntradaJaExisteException {
    if (!entradas.add(entrada)) {
        throw new EntradaJaExisteException(Mensagens.get("erro.entrada.duplicada"));
    }
    }

    @Override
public void removerEntrada(String titulo) throws EntradaNaoEncontradaException {
    if (titulo == null || titulo.isBlank()) {
        throw new IllegalArgumentException(Mensagens.get("erro.titulo.vazio"));
    }

    public void substituirEntradas(List<Entrada> novasEntradas) {
    entradas.clear();
    entradas.addAll(novasEntradas);
}
    
    Entrada entradaParaRemover = entradas.stream()
        .filter(e -> e.getTitulo().equalsIgnoreCase(titulo))
        .findFirst()
        .orElseThrow(() -> new EntradaNaoEncontradaException(Mensagens.get("erro.entrada.nao.encontrada")));

    entradas.remove(entradaParaRemover);
}
    
    public List<Entrada> listarEntradas() {
    return new ArrayList<>(entradas);
    }

    public Entrada buscarPorTitulo(String titulo) throws EntradaNaoEncontradaException {
        return entradas.stream()
                .filter(e -> e.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .orElseThrow(() -> new EntradaNaoEncontradaException("Entrada não encontrada: " + titulo));
    }
}

    public String getPathBiblioteca() {
        return pathBiblioteca;
    }
}
