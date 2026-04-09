package com.aluguel.carros;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class AluguelCarroService {

    private static final int ANO_MINIMO = 1886;
    private static final int ANO_MAXIMO = 2026;

    private final List<Carro> carros = new ArrayList<>();

    public Carro cadastrarCarro(String placa, String modelo, int ano) {

        if (placa == null || placa.trim().isEmpty()) {
            throw new IllegalArgumentException("A placa do carro não pode ser vazia.");
        }

        if (modelo == null || modelo.trim().isEmpty()) {
            throw new IllegalArgumentException("O modelo do carro não pode ser vazio.");
        }

        if (ano < ANO_MINIMO || ano > ANO_MAXIMO) {
            throw new IllegalArgumentException(
                    "Ano inválido. Deve estar entre " + ANO_MINIMO + " e " + ANO_MAXIMO + ".");
        }

        Carro carro = new Carro(placa.trim(), modelo.trim(), ano);
        carros.add(carro);
        return carro;
    }


    public void alugarCarro(String placa) {
        Carro carro = buscarPorPlaca(placa);

        if (carro.isAlugado()) {
            throw new IllegalStateException(
                    "O carro com placa '" + placa + "' já está alugado.");
        }

        carro.setAlugado(true);
    }



    public void devolverCarro(String placa) {
        Carro carro = buscarPorPlaca(placa);

        if (!carro.isAlugado()) {
            throw new IllegalStateException(
                    "O carro com placa '" + placa + "' não está alugado.");
        }

        carro.setAlugado(false);
    }


    public List<Carro> listarCarrosDisponiveis() {
        return carros.stream()
                .filter(c -> !c.isAlugado())
                .collect(Collectors.toList());
    }


    private Carro buscarPorPlaca(String placa) {
        return carros.stream()
                .filter(c -> c.getPlaca().equalsIgnoreCase(placa))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Carro com placa '" + placa + "' não encontrado."));
    }

    /** Expõe a lista completa (util para inspeção nos testes). */
    public List<Carro> listarTodos() {
        return new ArrayList<>(carros);
    }
}
