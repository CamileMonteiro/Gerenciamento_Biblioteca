/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.gerenciamentobiblioteca;

/**
 *
 * @author camil
 */
public class Livro {

    private int id;
    private String titulo;
    private String autor;
    private int anoLeitura;
    private int nota;
    private String resenha;

    public Livro() {

    }

    public Livro(int id, String titulo, String autor, int anoLeitura, int nota, String resenha) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anoLeitura = anoLeitura;
        this.nota = nota;
        this.resenha = resenha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String Autor) {
        this.autor = Autor;
    }

    public int getAnoLeitura() {
        return anoLeitura;
    }

    public void setAnoLeitura(int anoLeitura) {
        this.anoLeitura = anoLeitura;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getResenha() {
        return resenha;
    }

    public void setResenha(String resenha) {
        this.resenha = resenha;
    }

    public String toCSV() {
        return id+";"+titulo+";"+autor+";"+anoLeitura+";"+nota+";"+resenha;
    }
}
