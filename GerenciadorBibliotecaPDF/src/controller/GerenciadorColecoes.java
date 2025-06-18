package controller;

import model.Colecao;
import model.Entrada;
import exceptions.ColecaoNaoEncontradaException;
import exceptions.EntradaNaoEncontradaException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GerenciadorColecoes {
    private Map<String, Colecao> colecoes;

    public GerenciadorColecoes() {
        this.colecoes = new HashMap<>();
    }

    public void criarColecao(String nome) {
        colecoes.putIfAbsent(nome, new Colecao(nome));
    }

    public void adicionarEntradaNaColecao(String nomeColecao, Entrada entrada) throws ColecaoNaoEncontradaException {
        Colecao colecao = colecoes.get(nomeColecao);
        if (colecao == null) {
            throw new ColecaoNaoEncontradaException("Coleção não encontrada: " + nomeColecao);
        }
        colecao.adicionarEntrada(entrada);
    }

    public void removerEntradaDaColecao(String nomeColecao, Entrada entrada) throws ColecaoNaoEncontradaException, EntradaNaoEncontradaException {
        Colecao colecao = colecoes.get(nomeColecao);
        if (colecao == null) {
            throw new ColecaoNaoEncontradaException("Coleção não encontrada: " + nomeColecao);
        }
        if (!colecao.getEntradas().contains(entrada)) {
            throw new EntradaNaoEncontradaException("Entrada não encontrada na coleção.");
        }
        colecao.removerEntrada(entrada);
    }

    public List<Colecao> listarColecoes() {
        return new ArrayList<>(colecoes.values());
    }

    public Colecao obterColecao(String nomeColecao) throws ColecaoNaoEncontradaException {
        Colecao colecao = colecoes.get(nomeColecao);
        if (colecao == null) {
            throw new ColecaoNaoEncontradaException("Coleção não encontrada: " + nomeColecao);
        }
        return colecao;
    }
}


    public boolean estaVazia() {
        return entradas.isEmpty();
    }
} 
