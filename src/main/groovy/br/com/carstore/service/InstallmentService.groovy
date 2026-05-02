package br.com.carstore.service

import br.com.carstore.dto.output.InstallmentDTO
import jakarta.inject.Singleton

import java.math.RoundingMode
import java.time.LocalDate

@Singleton
class InstallmentService {

    List<InstallmentDTO> generateInstallments(BigDecimal totalValue, Integer installmentsQty) {
        BigDecimal installmentValue = totalValue.divide(installmentsQty as BigDecimal, 2, RoundingMode.HALF_UP)
        LocalDate dueDate = LocalDate.now().plusMonths(1)

        List<InstallmentDTO> installments = new ArrayList<>()
        for (int i = 0; i < installmentsQty; i++) {
            def installment = new InstallmentDTO()
            installment.value = installmentValue
            installment.dueDate = dueDate.plusMonths(i)
            installments.add(installment)
        }
        return installments
    }
}
