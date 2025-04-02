package com.universidad.repository;

import com.universidad.model.Estudiante;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class EstudianteRepository {
    private final Map<Long, Estudiante> estudiantes = new ConcurrentHashMap<>();
    private final AtomicLong idContador = new AtomicLong(1);

    public List<Estudiante> findAll() {
        return new ArrayList<>(estudiantes.values());
    }

    public Optional<Estudiante> findById(Long id) {
        return Optional.ofNullable(estudiantes.get(id));
    }

    public Estudiante save(Estudiante estudiante) {
        if (estudiante.getId() == null) {
            estudiante.setId(idContador.getAndIncrement());
        }
        estudiantes.put(estudiante.getId(), estudiante);
        return estudiante;
    }

    public boolean existsById(Long id) {
        return estudiantes.containsKey(id);
    }

    public void deleteById(Long id) {
        estudiantes.remove(id);
    }

    public void init() {
        save(Estudiante.builder()
                .nombre("Juan")
                .apellido("Pérez")
                .email("juan.perez@example.com")
                .fechaNacimiento(LocalDate.of(2000, 5, 15))
                .numeroInscripcion("S001")
                .build());

        save(Estudiante.builder()
                .nombre("María")
                .apellido("González")
                .email("maria.gonzalez@example.com")
                .fechaNacimiento(LocalDate.of(2001, 8, 22))
                .numeroInscripcion("S002")
                .build());
    }
}