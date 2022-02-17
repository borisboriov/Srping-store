package com.soskin.store.springstore.controllers;


import com.soskin.store.springstore.services.ProductsService;
import com.soskin.store.springstore.soapproducts.GetAllSoapProductsRequest;
import com.soskin.store.springstore.soapproducts.GetAllSoapProductsResponse;
import com.soskin.store.springstore.soapproducts.GetSoapProductByIdRequest;
import com.soskin.store.springstore.soapproducts.GetSoapProductByIdResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class SoapProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.soskin.com/store/springstore/SoapProducts";
    private final ProductsService productsService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetSoapProductByIdRequest")
    @ResponsePayload
    public GetSoapProductByIdResponse getSoapProductById(@RequestPayload GetSoapProductByIdRequest request) {
        GetSoapProductByIdResponse response = new GetSoapProductByIdResponse();
        response.setSoapProduct(productsService.getById(request.getId()));
        return response;
    }

    /*

---------------------------------Мой запрос----------------------------------------

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
        xmlns:f="http://www.soskin.com/store/springstore/SoapProducts">
            <soapenv:Header/>
            <soapenv:Body>
                <f:GetAllSoapProductsRequest/>
            </soapenv:Body>
        </soapenv:Envelope>
     */

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetAllSoapProductsRequest")
    @ResponsePayload
    public GetAllSoapProductsResponse getAllSoapProducts(@RequestPayload GetAllSoapProductsRequest request) {
        GetAllSoapProductsResponse response = new GetAllSoapProductsResponse();
        productsService.getAllProducts().forEach(response.getSoapProduct()::add);
        return response;
    }
}
