package controller;

import model.Entrada;
import exceptions.EntradaJaExisteException;
import exceptions.EntradaNaoEncontradaException;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorBiblioteca implements IGerenciadorBiblioteca {
    private List<Entrada> entradas;

    public GerenciadorBiblioteca() {
        this.entradas = new ArrayList<>();
    }

    public void adicionarEntrada(Entrada entrada) throws EntradaJaExisteException {
        for (Entrada e : entradas) {
            if (e.getTitulo().equalsIgnoreCase(entrada.getTitulo()) &&
                e.getAutor().equalsIgnoreCase(entrada.getAutor())) {
                throw new EntradaJaExisteException("Entrada já existente: " + entrada.getTitulo());
            }
        }
        entradas.add(entrada);
    }

    public void removerEntrada(String titulo) throws EntradaNaoEncontradaException {
        Entrada encontrada = null;
        for (Entrada e : entradas) {
            if (e.getTitulo().equalsIgnoreCase(titulo)) {
                encontrada = e;
                break;
            }
        }
        if (encontrada == null) {
            throw new EntradaNaoEncontradaException("Entrada não encontrada: " + titulo);
        }
        entradas.remove(encontrada);
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
