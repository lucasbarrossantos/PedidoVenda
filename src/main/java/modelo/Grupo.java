package modelo;

import javax.persistence.*;

@Entity
@Table(name = "grupo")
public class Grupo extends EntidadeBase{

    @Column(nullable = false, length = 90)
    private String nome;
    @Column(nullable = false)
    private String descricao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
