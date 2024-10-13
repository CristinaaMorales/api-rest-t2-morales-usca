package com.cibertec.edu.controllers;
import com.cibertec.edu.rest.entities.PagoProcesado;
import com.cibertec.edu.rest.entities.PagoRequest;
import com.cibertec.edu.service.PagoService;
import com.cibertec.edu.soap.entities.CreacionPagoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @GetMapping("/procesados")
    public ResponseEntity<List<PagoProcesado>> obtenerPagosProcesados(
            @RequestParam(required = false) String fechaInicio,
            @RequestParam(required = false) String fechaFin) {

        List<PagoProcesado> pagosProcesados = pagoService.obtenerPagosProcesados(fechaInicio, fechaFin);

        if (pagosProcesados.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(pagosProcesados, HttpStatus.OK);
    }


    @PostMapping("/crear")
    public ResponseEntity<CreacionPagoResponse> crearNuevoPago(@RequestBody PagoRequest pagoRequest) {
        CreacionPagoResponse response = pagoService.crearNuevoPago(
                pagoRequest.getFechaPago(),
                pagoRequest.getMontoTotal(),
                pagoRequest.getMoneda()
        );

        // Devuelve 201 si el pago fue creado correctamente, independientemente del estado en la respuesta
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


}
