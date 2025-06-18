package view;

import controller.GerenciadorBiblioteca;
import controller.GerenciadorColecoes;
import exceptions.ColecaoNaoEncontradaException;
import exceptions.EntradaJaExisteException;
import exceptions.EntradaNaoEncontradaException;
import model.Entrada;
import model.Livro;
import model.NotaDeAula;
import model.Slide;
import util.CompactadorZip;
import util.ExportadorBibTeX;
import util.PersistenciaEmArquivo;

import java.io.IOException;
import java.util.List;

public class GerenciadorBibliotecaPDF {

    public static void main(String[] args) {
        IGerenciadorBiblioteca biblioteca = new GerenciadorBiblioteca();
        IGerenciadorColecoes colecoes = new GerenciadorColecoes();

        try {
            // Criar entradas
            Livro livro = new Livro("Programação Orientada a Objetos", "Ana Silva", 2020, "TechBooks");
            NotaDeAula nota = new NotaDeAula("Estruturas de Dados", "Carlos Lima", 2019, "BCC101");
            Slide slide = new Slide("Apresentação de Java", "Beatriz Costa", 2021, "Semana da Computação");

            // Adicionar à biblioteca
            biblioteca.adicionarEntrada(livro);
            biblioteca.adicionarEntrada(nota);
            biblioteca.adicionarEntrada(slide);

            // Criar coleção e adicionar entradas
            colecoes.criarColecao("Computação");
            colecoes.adicionarEntradaNaColecao("Computação", livro);
            colecoes.adicionarEntradaNaColecao("Computação", slide);

            // Exportar para BibTeX
            List<Entrada> entradasParaExportar = colecoes.obterColecao("Computação").getEntradas();
            String caminhoBib = "colecao_computacao.bib";
            ExportadorBibTeX.exportarParaArquivo(entradasParaExportar, caminhoBib);
            System.out.println("BibTeX exportado para " + caminhoBib);

            // Compactar o arquivo BibTeX
            String caminhoZip = "colecao_computacao.zip";
            CompactadorZip.compactarArquivo(caminhoBib, caminhoZip);
            System.out.println("Arquivo compactado para " + caminhoZip);

            // Persistir dados da biblioteca
            PersistenciaEmArquivo.salvar(biblioteca.listarEntradas(), "biblioteca.dat");
            System.out.println("Biblioteca salva com sucesso.");

            // Carregar dados da biblioteca
            List<Entrada> entradasCarregadas = PersistenciaEmArquivo.carregar("biblioteca.dat");
            System.out.println("Entradas carregadas da biblioteca:");
            for (Entrada e : entradasCarregadas) {
                System.out.printf("- %s (%d) por %s\n", e.getTitulo(), e.getAno(), e.getAutor());
            }

        } catch (EntradaJaExisteException | EntradaNaoEncontradaException | ColecaoNaoEncontradaException e) {
            System.err.println("Erro de dados: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erro de E/S: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
        }
    }
}
