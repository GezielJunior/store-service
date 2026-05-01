package br.com.carstore.service

import br.com.carstore.client.VehicleClient
import br.com.carstore.dto.input.InputSalesDTO
import br.com.carstore.dto.output.VehicleDTO
import groovy.util.logging.Log4j
import groovy.util.logging.Slf4j
import jakarta.inject.Singleton

@Slf4j
@Singleton
class SalesService {

    private final VehicleClient vehicleClient

    SalesService(VehicleClient vehicleClient){
        this.vehicleClient = vehicleClient
    }

    void makeSales(InputSalesDTO inputSalesDTO){
        VehicleDTO vehicle = vehicleClient.findById(inputSalesDTO.vehicle)
        log.info(vehicle as String)
    }
}
