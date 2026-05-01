package br.com.carstore.dto.input

import groovy.transform.CompileStatic
import io.micronaut.serde.annotation.Serdeable

@Serdeable
@CompileStatic
class InputSalesDTO {
    String client
    Integer vehicle
    BigDecimal value
    Integer installmentsQty
}
