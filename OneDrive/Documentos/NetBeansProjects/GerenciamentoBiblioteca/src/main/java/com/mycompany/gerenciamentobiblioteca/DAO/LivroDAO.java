/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gerenciamentobiblioteca.DAO;

import com.mycompany.gerenciamentobiblioteca.Livro;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    private final String ARQUIVO = "livro.txt";

    // SALVAR (Adicionar no fim do arquivo)
    public void salvar(Livro livro) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO, true))) {
            bw.write(livro.toCSV());
            bw.newLine();
        }
    }

    // LISTAR (Ler do arquivo e devolver uma lista)
    public List<Livro> listar() throws IOException {
        List<Livro> lista = new ArrayList<>();
        File file = new File(ARQUIVO);

        if (!file.exists()) {
            file.createNewFile(); // Cria o arquivo se não existir
        }
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";"); // Quebra nos pontos e vírgula
                if (partes.length >= 6) {
                    Livro l = new Livro();
                    l.setId(Integer.parseInt(partes[0]));
                    l.setTitulo(partes[1]);
                    l.setAutor(partes[2]);
                    l.setAnoLeitura(Integer.parseInt(partes[3]));
                    l.setNota(Integer.parseInt(partes[4]));
                    l.setResenha(partes[5]);
                    lista.add(l);
                }
            }
        }
        return lista;
    }

    // EXCLUIR (Lê tudo, remove )
    public void excluir(int id) throws IOException {
        List<Livro> lista = listar();
        lista.removeIf(l -> l.getId() == id); // Remove se o ID for igual
        reescreverArquivo(lista);
    }

    // ATUALIZAR
    public void atualizar(Livro livroEditado) throws IOException {
        List<Livro> lista = listar();
        for (Livro l : lista) {
            if (l.getId() == livroEditado.getId()) {
                l.setTitulo(livroEditado.getTitulo());
                l.setAutor(livroEditado.getAutor());
                l.setAnoLeitura(livroEditado.getAnoLeitura());
                l.setNota(livroEditado.getNota());
                l.setResenha(livroEditado.getResenha());
                break;
            }
        }
        reescreverArquivo(lista);
    }

    //para reescrever o arquivo do zero
    private void reescreverArquivo(List<Livro> lista) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO, false))) {
            for (Livro l : lista) {
                bw.write(l.toCSV());
                bw.newLine();
            }
        }
    }
}
