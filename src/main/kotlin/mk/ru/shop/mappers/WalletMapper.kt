package mk.ru.shop.mappers

import mk.ru.shop.persistence.entities.Wallet
import mk.ru.shop.web.responses.PaymentWalletInfoResponse
import mk.ru.shop.web.responses.TransactionWalletInfoResponse
import mk.ru.shop.web.responses.WalletInfoResponse
import org.springframework.stereotype.Component

@Component
class WalletMapper(
    private val appUserMapper: AppUserMapper
) {
    fun toInfoResponse(wallet: Wallet): WalletInfoResponse = WalletInfoResponse(
        id = wallet.id!!,
        balance = wallet.balance,
        lastModifiedDate = wallet.lastModifiedDate,
        owner = appUserMapper.toWalletInfoResponse(wallet.owner!!)
    )

    fun toTransactionInfoResponse(wallet: Wallet): TransactionWalletInfoResponse = TransactionWalletInfoResponse(
        id = wallet.id!!,
        balance = wallet.balance,
        lastModifiedDate = wallet.lastModifiedDate,
    )

    fun toPaymentInfoResponse(wallet: Wallet): PaymentWalletInfoResponse = PaymentWalletInfoResponse(
        id = wallet.id!!,
        ownerLogin = wallet.owner!!.login!!
    )
}