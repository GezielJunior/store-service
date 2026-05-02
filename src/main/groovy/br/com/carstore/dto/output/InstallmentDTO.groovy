package br.com.carstore.dto.output

import groovy.transform.CompileStatic
import io.micronaut.serde.annotation.Serdeable

import java.time.LocalDate

@Serdeable
@CompileStatic
class InstallmentDTO {
    BigDecimal value
    LocalDate dueDate
}
