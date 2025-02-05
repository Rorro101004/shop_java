/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author roroc
 */
public class Client extends Person{
    int num_socio;
    Amount saldo = new Amount(50);

    public Client(int num_socio, String nombre) {
        super(nombre);
        this.num_socio = num_socio;
    }

    public int getNum_socio() {
        return num_socio;
    }

    public Amount getSaldo() {
        return saldo;
    }

    public void setSaldo(Amount saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "Client{" + "num_socio=" + num_socio + ", saldo=" + saldo + '}';
    }
    
    
}
