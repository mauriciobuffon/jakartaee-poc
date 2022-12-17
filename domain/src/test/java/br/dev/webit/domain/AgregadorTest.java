package br.dev.webit.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

public class AgregadorTest {

    @Test
    public void agregadorValido() {
        assertDoesNotThrow(() -> new Agregador());
    }
}
