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

public class materiadto {
	private Long id;
	private String nombremat;
	private String codunico;
	private Integer creditos;
}
