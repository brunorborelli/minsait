package com.minsait.teste.Algoritimos;

import java.io.*;
import java.nio.file.*;
import java.util.stream.Stream;
public class ArquivoFormat {

    public static void removerLinhasEmBranco(String inputPath, String outputPath, Boolean removerEspacos) throws IOException {
        Path entrada = Paths.get(inputPath);
        Path saida = Paths.get(outputPath);

        try (Stream<String> linhas = Files.lines(entrada);
             BufferedWriter writer = Files.newBufferedWriter(saida)) {

            linhas
                    .map(String::trim)
                    .filter(linha -> !linha.isEmpty())
                    .map(linha -> removerEspacos ? linha.replaceAll("\\s+", "") : linha)
                    .forEach(linha -> {
                        try {
                            writer.write(linha);
                            writer.newLine();
                        } catch (IOException e) {
                            throw new UncheckedIOException(e);
                        }
                    });
        }
    }
}
