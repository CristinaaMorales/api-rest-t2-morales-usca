package com.cibertec.edu.clients;

import com.cibertec.edu.soap.entities.CreacionPagoRequest;
import com.cibertec.edu.soap.entities.CreacionPagoResponse;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.cibertec.edu.soap.entities.ListaPagosRequest;
import com.cibertec.edu.soap.entities.ListaPagosResponse;

@Component
public class PagoSoapClient {

    @Autowired
    private WebServiceTemplate webServiceTemplate;

    public ListaPagosResponse obtenerListaPagos(ListaPagosRequest request) {
        try {
            return (ListaPagosResponse) webServiceTemplate.marshalSendAndReceive(request);
        } catch (Exception e) {
            System.err.println("Error al consumir el servicio SOAP: " + e.getMessage());
            return null;
        }
    }


    public CreacionPagoResponse crearPago(CreacionPagoRequest request) {
        try {
            // Consumir el servicio SOAP para crear el pago
            return (CreacionPagoResponse) webServiceTemplate.marshalSendAndReceive(request);
        } catch (Exception e) {
            System.err.println("Error al consumir el servicio SOAP: " + e.getMessage());
            return null;
        }
    }
}
