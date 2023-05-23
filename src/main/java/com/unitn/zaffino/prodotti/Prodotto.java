package com.unitn.zaffino.prodotti;

public class Prodotto {



    private String nome;
    private int id;
    private int fornitore;
    private int prezzo;

    public Prodotto(String nome, int fornitore, int prezzo) {
        this.nome = nome;
        this.fornitore = fornitore;
        this.prezzo = prezzo;
    }

    public Prodotto(String nome, int id, int fornitore, int prezzo) {
        this.nome = nome;
        this.id = id;
        this.fornitore = fornitore;
        this.prezzo = prezzo;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }


    public int getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(int prezzo) {
        this.prezzo = prezzo;
    }

    public int getFornitore() {
        return fornitore;
    }

    public void setFornitore(int fornitore) {
        this.fornitore = fornitore;
    }

    @Override
    public String toString() {
        return  "id - " + id +
                "\tnome - " + nome +
                "\tfornitore - " + fornitore +
                "\tprezzo - " + prezzo;
    }



}
