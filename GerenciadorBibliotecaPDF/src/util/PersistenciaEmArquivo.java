package util;

import model.Entrada;

import java.io.*;
import java.util.List;

public class PersistenciaEmArquivo {
    public static void salvar(List<Entrada> entradas, String caminho) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminho))) {
            oos.writeObject(entradas);
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Entrada> carregar(String caminho) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminho))) {
            return (List<Entrada>) ois.readObject();
        }
    }
}
