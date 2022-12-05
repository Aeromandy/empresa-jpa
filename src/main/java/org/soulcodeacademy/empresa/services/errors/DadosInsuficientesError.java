package org.soulcodeacademy.empresa.services.errors;

public class DadosInsuficientesError  extends RuntimeException {
    public DadosInsuficientesError(String message) {
        super(message);
    }
}
