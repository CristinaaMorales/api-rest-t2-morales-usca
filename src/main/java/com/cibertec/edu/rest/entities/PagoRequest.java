package com.cibertec.edu.rest.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagoRequest {
    private String fechaPago;
    private double montoTotal;
    private String moneda;

}
