package mk.ru.shop.mappers

import mk.ru.shop.persistence.entities.Transaction
import mk.ru.shop.web.responses.PaymentTransactionInfoResponse
import mk.ru.shop.web.responses.TransactionInfoResponse
import org.springframework.stereotype.Component

@Component
class TransactionMapper(
    private val productMapper: ProductMapper,
    private val walletMapper: WalletMapper
) {
    fun toInfoResponse(transaction: Transaction): TransactionInfoResponse = TransactionInfoResponse(
        id = transaction.id!!,
        amount = transaction.amount!!,
        date = transaction.date!!,
        product = productMapper.toTransactionInfoResponse(transaction.product!!),
        sender = walletMapper.toTransactionInfoResponse(transaction.sender!!),
        recipient = walletMapper.toTransactionInfoResponse(transaction.recipient!!),
    )

    fun toPaymentInfoResponse(transaction: Transaction): PaymentTransactionInfoResponse = PaymentTransactionInfoResponse(
        id = transaction.id!!,
        amount = transaction.amount!!,
        feePercent = transaction.feePercent!!,
        feeAmount = transaction.feeAmount!!
    )
}