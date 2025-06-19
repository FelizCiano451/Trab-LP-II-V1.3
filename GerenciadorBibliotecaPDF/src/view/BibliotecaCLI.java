package view;

import controller.*;
import model.*;
import util.*;

import java.util.Scanner;

public class BibliotecaCLI {

    private static final Scanner scanner = new Scanner(System.in);
    private static final IGerenciadorBiblioteca biblioteca = new GerenciadorBiblioteca();
    private static final IGerenciadorColecoes colecoes = new GerenciadorColecoes();
    private static final Persistencia<IGerenciadorBiblioteca> persistencia = new PersistenciaEmArquivo<>();

    public static void main(String[] args) {
        String comando;
        System.out.println("Bem-vindo ao Gerenciador de Biblioteca PDF!");
        do {
            mostrarMenu();
            comando = scanner.nextLine().trim().toLowerCase();

            try {
                switch (comando) {
                    case "1" -> adicionarLivro();
                    case "2" -> listarEntradas();
                    case "3" -> removerEntrada();
                    case "4" -> exportarBibTeX();
                    case "5" -> salvar();
                    case "6" -> carregar();
                    case "0" -> System.out.println("Encerrando...");
                    default -> System.out.println("Comando inválido.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }

        } while (!comando.equals("0"));
    }

    private static void mostrarMenu() {
        System.out.println("\nEscolha uma opção:");
        System.out.println("1 - Adicionar Livro");
        System.out.println("2 - Listar Entradas");
        System.out.println("3 - Remover Entrada");
        System.out.println("4 - Exportar para BibTeX");
        System.out.println("5 - Salvar Biblioteca");
        System.out.println("6 - Carregar Biblioteca");
        System.out.println("0 - Sair");
        System.out.print(">>> ");
    }

    private static void adicionarLivro() throws Exception {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Ano: ");
        int ano = Integer.parseInt(scanner.nextLine());
        System.out.print("Editora: ");
        String editora = scanner.nextLine();

        Livro livro = new Livro(titulo, autor, ano, editora);
        biblioteca.adicionarEntrada(livro);
        System.out.println("Livro adicionado com sucesso.");
    }

    private static void listarEntradas() {
        var entradas = biblioteca.listarEntradas();
        if (entradas.isEmpty()) {
            System.out.println("Nenhuma entrada na biblioteca.");
        } else {
            entradas.forEach(e -> System.out.printf("- %s (%s, %d)%n", e.getTitulo(), e.getAutor(), e.getAno()));
        }
    }

    private static void removerEntrada() throws Exception {
        System.out.print("Título da entrada a remover: ");
        String titulo = scanner.nextLine();
        biblioteca.removerEntrada(titulo);
        System.out.println("Entrada removida.");
    }

    private static void exportarBibTeX() throws Exception {
        System.out.print("Caminho do arquivo BibTeX de saída: ");
        String caminho = scanner.nextLine();
        ExportadorBibTeX.exportar(biblioteca.listarEntradas(), caminho);
        System.out.println("Exportação concluída.");
    }

    private static void salvar() throws Exception {
        persistencia.salvar(biblioteca, "biblioteca.dat");
        System.out.println("Biblioteca salva.");
    }

    private static void carregar() throws Exception {
        IGerenciadorBiblioteca b = persistencia.carregar("biblioteca.dat");
        biblioteca.substituirEntradas(b.listarEntradas());
        System.out.println("Biblioteca carregada.");
    }
}
