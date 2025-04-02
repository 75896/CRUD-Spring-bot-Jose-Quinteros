package com.universidad.exeption;

public class EstudianteNotFoundException extends RuntimeException {
	public EstudianteNotFoundException(Long id) {
        super("Estudiante con ID " + id + " no encontrado");
    }
}
