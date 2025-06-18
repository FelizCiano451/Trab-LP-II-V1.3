package controller;

import model.Colecao;
import model.Livro;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class ColecaoZipTest {

    @Test
    public void deveGerarArquivoZipParaColecao() throws Exception {
        GerenciadorColecoes gerenciador = new GerenciadorColecoes();
        gerenciador.criarColecao("Programação");

        Livro livro = new Livro("Effective Java", "Joshua Bloch", 2018, "Addison-Wesley");
        gerenciador.adicionarEntradaNaColecao("Programação", livro);

        Colecao colecao = gerenciador.obterColecao("Programação");
        String zipPath = "colecao_programacao_test.zip";
        colecao.gerarZip(zipPath);

        File zip = new File(zipPath);
        assertTrue(zip.exists());
        assertTrue(zip.length() > 0);

        // Limpeza
        zip.delete();
    }
}
