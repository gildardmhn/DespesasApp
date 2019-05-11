package com.codinginflow.despesas.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "estabelecimento_table")
public class Estabelecimento {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String nome;
    private String telefone;
    private String endereco;

    public Estabelecimento(String nome, String telefone, String endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEndereco() {
        return endereco;
    }
}
