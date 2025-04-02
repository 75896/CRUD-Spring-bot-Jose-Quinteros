package com.universidad.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.*;
import java.time.LocalDate;
import com.universidad.model.Persona;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

public class docente extends Persona {
	private String nroempleado;
	private String departamento;
}
