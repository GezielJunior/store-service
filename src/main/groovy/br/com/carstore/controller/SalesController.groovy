package br.com.carstore.controller

import br.com.carstore.dto.input.InputSalesDTO
import br.com.carstore.service.SalesService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post

@Controller("/sales")
class SalesController {

    private final SalesService salesService

    SalesController(SalesService salesService){
        this.salesService = salesService
    }

    @Post("/")
    void makeSales(@Body InputSalesDTO inputSalesDTO){
            salesService.makeSales(inputSalesDTO)
    }
}
