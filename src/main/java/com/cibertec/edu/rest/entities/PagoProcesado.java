package com.cibertec.edu.rest.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PagoProcesado {
    private Monedas monedas;
    private double montoOriginal;
    private String monedaOriginal;
    private double impuestos;
    private double valorNeto;
    private String fechaVenta;
}
