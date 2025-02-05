/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Scanner;
import main.Logable;

/**
 *
 * @author roroc
 */
public class Employee extends Person implements Logable{

    final private int num_empleado = 123;
    final private String password = "test";

    public Employee(String nombre) {
        super(nombre);
    }

    public int getNum_empleado() {
        return num_empleado;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Employee{" + "num_empleado=" + num_empleado + ", password=" + password + '}';
    }

    /**
     * 
     * @param user
     * @param password
     * @return 
     */
    @Override
    public boolean login(int user, String password) {
     
        if (user == getNum_empleado() && password.equals(getPassword())) {
            return true;
        }
        else {
            System.out.println("Empleado no válido");
            return false;
        }
    }
    
}
