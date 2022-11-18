package br.edu.ifsp.ads.pdm.listviewfirebase;

public class Contato {

    private String nome;
    private String email;
    private String chave;


    public Contato(String chave, String nome, String email) {

        this.chave = chave;
        this.nome = nome;
        this.email = email;
    }


    public Contato() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

}
