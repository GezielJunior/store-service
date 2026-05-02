package br.com.carstore.controller

import br.com.carstore.dto.input.InputSalesDTO
import br.com.carstore.dto.output.SalesDTO
import br.com.carstore.service.SalesService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn

@ExecuteOn(TaskExecutors.BLOCKING)
@Controller("/sales")
class SalesController {

    private final SalesService salesService

    SalesController(SalesService salesService) {
        this.salesService = salesService
    }

    @Post("/")
    HttpResponse<SalesDTO> makeSales(@Body InputSalesDTO inputSalesDTO) {
        SalesDTO sales = salesService.makeSales(inputSalesDTO)
        return HttpResponse.created(sales)
    }
}
