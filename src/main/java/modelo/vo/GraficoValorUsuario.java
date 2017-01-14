package modelo.vo;


import modelo.Usuario;

import java.io.Serializable;
import java.math.BigDecimal;

public class GraficoValorUsuario implements Serializable {

    private static final long serialVersionUID = 1L;

    private Usuario usuario;
    private BigDecimal valor;

    public GraficoValorUsuario(Usuario usuario, BigDecimal valor) {
        this.usuario = usuario;
        this.valor = valor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public BigDecimal getValor() {
        return valor;
    }

}

