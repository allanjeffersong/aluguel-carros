package com.aluguel.carros;


public class Carro {

    private String placa;
    private String modelo;
    private int ano;
    private boolean alugado;

    public Carro(String placa, String modelo, int ano) {
        this.placa   = placa;
        this.modelo  = modelo;
        this.ano     = ano;
        this.alugado = false;
    }

    // ── Getters ────────────────────────────────────────────

    public String getPlaca()  { return placa;   }
    public String getModelo() { return modelo;  }
    public int    getAno()    { return ano;      }
    public boolean isAlugado() { return alugado; }

    // ── Setters necessários ao serviço ─────────────────────

    public void setAlugado(boolean alugado) { this.alugado = alugado; }

    @Override
    public String toString() {
        return String.format("Carro{placa='%s', modelo='%s', ano=%d, alugado=%b}",
                placa, modelo, ano, alugado);
    }
}
