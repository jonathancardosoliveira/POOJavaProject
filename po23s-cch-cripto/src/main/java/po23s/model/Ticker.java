/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package po23s.model;

/**
 *
 * @author jonat
 */
public class Ticker {
    private String nome;
    private double buy;
    private double sell;

    public Ticker(String nome, double buy, double sell) {
        this.nome = nome;
        this.buy = buy;
        this.sell = sell;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getBuy() {
        return buy;
    }

    public void setBuy(double buy) {
        this.buy = buy;
    }

    public double getSell() {
        return sell;
    }

    public void setSell(double sell) {
        this.sell = sell;
    }

    @Override
    public String toString() {
        return String.format("%s - Buy: %.2f, Sell: %.2f", nome, buy, sell);
    }
}
