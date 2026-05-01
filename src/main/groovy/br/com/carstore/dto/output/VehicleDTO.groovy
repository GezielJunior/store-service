package br.com.carstore.dto.output

import groovy.transform.CompileStatic
import io.micronaut.serde.annotation.Serdeable

@Serdeable
@CompileStatic
class VehicleDTO {
    Long id
    String model
    String brand
    String licensePlate
}
