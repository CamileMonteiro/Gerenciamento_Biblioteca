/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gerenciamentobiblioteca.DAO;

/**
 *
 * @author camil
 */
import com.mycompany.gerenciamentobiblioteca.QueroLer;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class QueroLerDAO {

   private final String ARQUIVO = "queroler.txt";

    public void salvar(QueroLer queroLer) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO, true))) {
            bw.write(queroLer.toCSV());
            bw.newLine();
        }
    }

    public List<QueroLer> listar() throws IOException {
        List<QueroLer> lista = new ArrayList<>();
        File file = new File(ARQUIVO);
        if (!file.exists()) file.createNewFile();

        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length >= 5) {
                    QueroLer a = new QueroLer();
                    a.setId(Integer.parseInt(partes[0]));
                    a.setTitulo(partes[1]);
                    a.setAutor(partes[2]);
                    a.setGenero(partes[3]);
                    a.setPrioridade(partes[4]);
                    lista.add(a);
                }
            }
        }
        return lista;
    }

    public void excluir(int id) throws IOException {
        List<QueroLer> lista = listar();
        lista.removeIf(a -> a.getId() == id);
        reescreverArquivo(lista);
    }

    public void atualizar(QueroLer Editado) throws IOException {
        List<QueroLer> lista = listar();
        for (QueroLer a : lista) {
            if (a.getId() == Editado.getId()) {
                a.setTitulo(Editado.getTitulo());
                a.setAutor(Editado.getAutor());
                a.setGenero(Editado.getGenero());
                a.setPrioridade(Editado.getPrioridade());
                break;
            }
        }
        reescreverArquivo(lista);
    }

    private void reescreverArquivo(List<QueroLer> lista) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO, false))) {
            for (QueroLer a : lista) {
                bw.write(a.toCSV());
                bw.newLine();
            }
        }
    }

}
