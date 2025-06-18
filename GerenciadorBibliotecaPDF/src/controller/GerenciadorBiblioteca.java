package controller;

import model.Entrada;
import exceptions.EntradaJaExisteException;
import exceptions.EntradaNaoEncontradaException;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorBiblioteca implements IGerenciadorBiblioteca {
    private Set<Entrada> entradas;

public GerenciadorBiblioteca() {
    this.entradas = new HashSet<>();
}

    public void adicionarEntrada(Entrada entrada) throws EntradaJaExisteException {
    if (!entradas.add(entrada)) {
        throw new EntradaJaExisteException("Entrada já existe na biblioteca.");
    }
    }

    public void removerEntrada(String titulo) throws EntradaNaoEncontradaException {
    Entrada entradaParaRemover = entradas.stream()
        .filter(e -> e.getTitulo().equalsIgnoreCase(titulo))
        .findFirst()
        .orElseThrow(() -> new EntradaNaoEncontradaException("Entrada não encontrada."));
    
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
