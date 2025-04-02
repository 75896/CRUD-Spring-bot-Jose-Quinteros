package com.universidad.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

public class docentedto {
	private Long id;
	private String nombre;
	private String apellido;
	private String email;
	private LocalDate fecnac;
	private String nroempleado;
	private String departamento;
}
