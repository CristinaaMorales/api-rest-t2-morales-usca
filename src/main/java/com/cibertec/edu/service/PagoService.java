package com.cibertec.edu.service;

import com.cibertec.edu.clients.PagoSoapClient;
import com.cibertec.edu.rest.entities.Monedas;
import com.cibertec.edu.rest.entities.PagoProcesado;
import com.cibertec.edu.soap.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PagoService {
    private static final double TIPO_CAMBIO = 3.85;
    private static final double IGV = 0.18;

    @Autowired
    private PagoSoapClient pagoSoapClient;

    public List<PagoProcesado> obtenerPagosProcesados(String fechaInicio, String fechaFin) {
        ListaPagosRequest request = new ListaPagosRequest();
        request.setFechaInicio(fechaInicio);
        request.setFechaFin(fechaFin);

        ListaPagosResponse response = pagoSoapClient.obtenerListaPagos(request);
        List<PagoProcesado> pagosProcesados = new ArrayList<>();

        // Procesar cada pago
        for (PagoObject pago : response.getListaPagos()) {
            PagoProcesado pagoProcesado = new PagoProcesado();
            pagoProcesado.setMontoOriginal(pago.getMontoTotal());
            pagoProcesado.setMonedaOriginal(pago.getMoneda());

            // Calcular impuestos y valor neto
            double impuesto = pago.getMontoTotal() * IGV;
            double valorNeto = pago.getMontoTotal() - impuesto;

            pagoProcesado.setImpuestos(impuesto);
            pagoProcesado.setValorNeto(valorNeto);
            pagoProcesado.setFechaVenta(pago.getFechaPago());

            // Convertir monedas
            Monedas monedas = new Monedas();
            if (pago.getMoneda().equalsIgnoreCase("DOLARES")) {
                monedas.setSoles(pago.getMontoTotal() * TIPO_CAMBIO);
                monedas.setDolares(pago.getMontoTotal());
            } else {
                monedas.setSoles(pago.getMontoTotal());
                monedas.setDolares(pago.getMontoTotal() / TIPO_CAMBIO);
            }

            pagoProcesado.setMonedas(monedas);
            pagosProcesados.add(pagoProcesado);
        }

        return pagosProcesados;
    }

    // Método para crear un nuevo pago
    public CreacionPagoResponse crearNuevoPago(String fechaPago, double montoTotal, String moneda) {
        // Crea una solicitud de pago para el servicio SOAP
        CreacionPagoRequest request = new CreacionPagoRequest();
        request.setFechaPago(fechaPago);
        request.setMontoTotal(montoTotal);
        request.setMoneda(moneda);

        return pagoSoapClient.crearPago(request);
    }
}
