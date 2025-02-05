/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author roroc
 */
public class Amount {
    private double value;
    private final String currency;

    public Amount(double value) {
        this.value = value;
        this.currency = "$";
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return "Amount{" + "value=" + value + ", currency=" + currency + '}';
    }
    
}
