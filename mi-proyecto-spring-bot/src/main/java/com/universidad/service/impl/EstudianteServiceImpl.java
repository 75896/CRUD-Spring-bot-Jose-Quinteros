package com.universidad.service.impl;

import com.universidad.dto.EstudianteDTO;
import com.universidad.exception.EstudianteNotFoundException;
import com.universidad.model.Estudiante;
import com.universidad.repository.EstudianteRepository;
import com.universidad.service.IEstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstudianteServiceImpl implements IEstudianteService {

    private final EstudianteRepository estudianteRepository;

    @Autowired
    public EstudianteServiceImpl(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    @Override
    public List<EstudianteDTO> obtenerTodosLosEstudiantes() {
        return estudianteRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EstudianteDTO obtenerEstudiantePorId(Long id) {
        return estudianteRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new EstudianteNotFoundException(id));
    }

    @Override
    public EstudianteDTO crearEstudiante(EstudianteDTO estudianteDTO) {
        Estudiante estudiante = convertToEntity(estudianteDTO);
        estudiante = estudianteRepository.save(estudiante);
        return convertToDTO(estudiante);
    }

    @Override
    public EstudianteDTO actualizarEstudiante(Long id, EstudianteDTO estudianteDTO) {
        return estudianteRepository.findById(id)
                .map(estudianteExistente -> {
                    actualizarDatosEstudiante(estudianteExistente, estudianteDTO);
                    estudianteRepository.save(estudianteExistente);
                    return convertToDTO(estudianteExistente);
                })
                .orElseThrow(() -> new EstudianteNotFoundException(id));
    }

    @Override
    public void eliminarEstudiante(Long id) {
        if (!estudianteRepository.existsById(id)) {
            throw new EstudianteNotFoundException(id);
        }
        estudianteRepository.deleteById(id);
    }

    private EstudianteDTO convertToDTO(Estudiante estudiante) {
        return EstudianteDTO.builder()
                .id(estudiante.getId())
                .nombre(estudiante.getNombre())
                .apellido(estudiante.getApellido())
                .email(estudiante.getEmail())
                .fechaNacimiento(estudiante.getFechaNacimiento())
                .numeroInscripcion(estudiante.getNumeroInscripcion())
                .build();
    }

    private Estudiante convertToEntity(EstudianteDTO estudianteDTO) {
        return Estudiante.builder()
                .id(estudianteDTO.getId())
                .nombre(estudianteDTO.getNombre())
                .apellido(estudianteDTO.getApellido())
                .email(estudianteDTO.getEmail())
                .fechaNacimiento(estudianteDTO.getFechaNacimiento())
                .numeroInscripcion(estudianteDTO.getNumeroInscripcion())
                .build();
    }

    private void actualizarDatosEstudiante(Estudiante estudiante, EstudianteDTO estudianteDTO) {
        estudiante.setNombre(estudianteDTO.getNombre());
        estudiante.setApellido(estudianteDTO.getApellido());
        estudiante.setEmail(estudianteDTO.getEmail());
        estudiante.setFechaNacimiento(estudianteDTO.getFechaNacimiento());
        estudiante.setNumeroInscripcion(estudianteDTO.getNumeroInscripcion());
    }
}