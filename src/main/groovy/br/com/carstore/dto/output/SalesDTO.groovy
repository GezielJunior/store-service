package br.com.carstore.dto.output

import groovy.transform.CompileStatic
import io.micronaut.serde.annotation.Serdeable

@Serdeable
@CompileStatic
class SalesDTO {
    String client
    VehicleDTO vehicle
    BigDecimal value
    List<InstallmentDTO> installments
}
