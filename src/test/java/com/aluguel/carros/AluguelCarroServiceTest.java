package com.aluguel.carros;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class AluguelCarroServiceTest {

    private AluguelCarroService service;

    @BeforeEach
    void setUp() {
        service = new AluguelCarroService();
    }


    @Test
    @DisplayName("RN-01 | Deve lançar exceção ao cadastrar carro com placa vazia")
    void deveLancarExcecaoQuandoPlacaVazia() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> service.cadastrarCarro("", "Fusca", 2000)
        );
        assertTrue(ex.getMessage().contains("placa"),
                "Mensagem deve mencionar 'placa'");
    }


    @Test
    @DisplayName("RN-02 | Deve lançar exceção ao cadastrar carro com modelo vazio")
    void deveLancarExcecaoQuandoModeloVazio() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> service.cadastrarCarro("ABC-1234", "", 2020)
        );
        assertTrue(ex.getMessage().contains("modelo"),
                "Mensagem deve mencionar 'modelo'");
    }

    @Test
    @DisplayName("RN-03 | Deve lançar exceção ao cadastrar carro com ano inválido (abaixo de 1886)")
    void deveLancarExcecaoQuandoAnoAbaixoDoMinimo() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> service.cadastrarCarro("ABC-1234", "Fusca", 1800)
        );
        assertTrue(ex.getMessage().contains("Ano"),
                "Mensagem deve mencionar 'Ano'");
    }

    @Test
    @DisplayName("RN-03 | Deve lançar exceção ao cadastrar carro com ano inválido (acima de 2026)")
    void deveLancarExcecaoQuandoAnoAcimaDoMaximo() {
        assertThrows(
                IllegalArgumentException.class,
                () -> service.cadastrarCarro("XYZ-9999", "Carro Futuro", 2100)
        );
    }

    @Test
    @DisplayName("RN-04 | Deve lançar exceção ao tentar alugar carro que já está alugado")
    void deveLancarExcecaoAoAlugarCarroJaAlugado() {
        service.cadastrarCarro("ABC-1234", "Fusca", 2000);
        service.alugarCarro("ABC-1234");           // primeiro aluguel → OK

        IllegalStateException ex = assertThrows(
                IllegalStateException.class,
                () -> service.alugarCarro("ABC-1234")   // segundo aluguel → ERRO
        );
        assertTrue(ex.getMessage().contains("já está alugado"),
                "Mensagem deve indicar que o carro já está alugado");
    }

    @Test
    @DisplayName("RN-05 | Deve lançar exceção ao devolver carro que não está alugado")
    void deveLancarExcecaoAoDevolverCarroNaoAlugado() {
        service.cadastrarCarro("ABC-1234", "Gol", 2015);

        IllegalStateException ex = assertThrows(
                IllegalStateException.class,
                () -> service.devolverCarro("ABC-1234")
        );
        assertTrue(ex.getMessage().contains("não está alugado"),
                "Mensagem deve indicar que o carro não está alugado");
    }

    @Test
    @DisplayName("RN-06 | Deve listar apenas carros disponíveis (não alugados)")
    void deveListarApenasCarrosDisponiveis() {
        service.cadastrarCarro("AAA-0001", "Civic",  2022);
        service.cadastrarCarro("BBB-0002", "Corolla", 2021);
        service.cadastrarCarro("CCC-0003", "HB20",   2020);

        service.alugarCarro("AAA-0001");   // este NÃO deve aparecer

        List<Carro> disponiveis = service.listarCarrosDisponiveis();

        assertEquals(2, disponiveis.size(),
                "Deve haver exatamente 2 carros disponíveis");
        assertTrue(disponiveis.stream()
                        .noneMatch(Carro::isAlugado),
                "Nenhum carro listado pode estar alugado");
    }

    @Test
    @DisplayName("Fluxo completo | Deve alugar e devolver carro corretamente")
    void deveAlugarEDevolverCarroCorretamente() {
        service.cadastrarCarro("DEF-5678", "Onix", 2023);

        // Antes do aluguel
        assertEquals(1, service.listarCarrosDisponiveis().size(),
                "Antes do aluguel: 1 carro disponível");

        // Aluga
        service.alugarCarro("DEF-5678");
        assertTrue(service.listarTodos().get(0).isAlugado(),
                "Após alugar: carro deve estar marcado como alugado");
        assertEquals(0, service.listarCarrosDisponiveis().size(),
                "Após alugar: nenhum carro disponível");

        // Devolve
        service.devolverCarro("DEF-5678");
        assertFalse(service.listarTodos().get(0).isAlugado(),
                "Após devolução: carro deve estar disponível");
        assertEquals(1, service.listarCarrosDisponiveis().size(),
                "Após devolução: 1 carro disponível novamente");
    }
}
