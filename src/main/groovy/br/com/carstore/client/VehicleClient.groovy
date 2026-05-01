package br.com.carstore.client

import br.com.carstore.dto.output.VehicleDTO
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.client.annotation.Client

@Client("\${external.service.vehicle.url}")
interface VehicleClient {

    @Get("/vehicles/{id}")
    VehicleDTO findById(@PathVariable Long id)
}