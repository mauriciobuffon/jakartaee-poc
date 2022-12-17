package br.dev.webit.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EntidadeTest {

    private Agregador agregador;

    @BeforeEach
    public void inicializaAgregador() {
        agregador = new Agregador();
    }

    @Test
    public void agregadorNulo() {
        assertThrows(NullPointerException.class, () -> new Entidade(null, null, null));
    }

    @Test
    public void valorObjetoNulo() {
        assertThrows(NullPointerException.class, () -> new Entidade(null, agregador, null));
    }

    @Test
    public void entidadeValida() {
        ValorObjeto valorObjeto = assertDoesNotThrow(() -> new ValorObjeto("Objeto da entidade"));
        assertDoesNotThrow(() -> new Entidade(null, agregador, valorObjeto));
        assertDoesNotThrow(() -> agregador.addEntidade(valorObjeto));
    }
}
