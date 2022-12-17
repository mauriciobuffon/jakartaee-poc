package br.dev.webit.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

public class ValorObjetoTest {

    @ParameterizedTest
    @NullSource
    public void dadoNulo(String dado) {
        assertThrows(NullPointerException.class, () -> new ValorObjeto(dado));
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = { " ", "\t", "\n", "\r", "\f" })
    public void dadoInvalido(String dado) {
        assertThrows(IllegalArgumentException.class, () -> new ValorObjeto(dado));
    }

    @ParameterizedTest
    @ValueSource(strings = { " Valor Objeto   ", """
            Mais um teste de valor objeto! Não deveria ser um problema.
            """ })
    public void dadoValido(String dado) {
        assertDoesNotThrow(() -> new ValorObjeto(dado));
    }
}
