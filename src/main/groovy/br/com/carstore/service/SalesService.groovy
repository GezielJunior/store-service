package br.com.carstore.service

import br.com.carstore.client.VehicleClient
import br.com.carstore.dto.input.InputSalesDTO
import br.com.carstore.dto.output.InstallmentDTO
import br.com.carstore.dto.output.SalesDTO
import br.com.carstore.dto.output.VehicleDTO
import groovy.util.logging.Slf4j
import jakarta.inject.Singleton

@Slf4j
@Singleton
class SalesService {

    private final VehicleClient vehicleClient
    private final TokenService tokenService
    private final InstallmentService installmentService

    SalesService(VehicleClient vehicleClient, TokenService tokenService, InstallmentService installmentService) {
        this.vehicleClient = vehicleClient
        this.tokenService = tokenService
        this.installmentService = installmentService
    }

    SalesDTO makeSales(InputSalesDTO inputSalesDTO) {
        String token = "Bearer ${tokenService.getToken()}"
        VehicleDTO vehicle = vehicleClient.findById(inputSalesDTO.vehicle, token)
        log.info("Vehicle found - ID: {}, Model: {}, Brand: {}, License Plate: {}", vehicle.id, vehicle.model, vehicle.brand, vehicle.licensePlate)

        List<InstallmentDTO> installments = installmentService.generateInstallments(inputSalesDTO.value, inputSalesDTO.installmentsQty)

        SalesDTO sales = new SalesDTO(
                client: inputSalesDTO.client,
                vehicle: vehicle,
                value: inputSalesDTO.value,
                installments: installments
        )
        log.info("Realized Sales - Client: {}, Vehicle: {}, Value: {}, Installments: {}", sales.client, sales.vehicle.model, sales.value, sales.installments.size())
        return sales
    }
}
