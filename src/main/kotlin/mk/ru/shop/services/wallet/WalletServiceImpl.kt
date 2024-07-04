package mk.ru.shop.services.wallet

import jakarta.transaction.Transactional
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID
import mk.ru.shop.exceptions.ContentNotFoundException
import mk.ru.shop.exceptions.SoftDeletionException
import mk.ru.shop.exceptions.ValidationException
import mk.ru.shop.mappers.WalletMapper
import mk.ru.shop.persistence.entities.AppUser
import mk.ru.shop.persistence.entities.Transaction
import mk.ru.shop.persistence.entities.Wallet
import mk.ru.shop.persistence.repositories.WalletRepo
import mk.ru.shop.utils.AppUserInfo
import mk.ru.shop.web.responses.WalletInfoResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class WalletServiceImpl(
    private val walletRepo: WalletRepo,
    private val walletMapper: WalletMapper,
) : WalletService {
    private val log: Logger = LoggerFactory.getLogger(this.javaClass.name)

    override fun create(owner: AppUser): Wallet {
        val savedWallet: Wallet = walletRepo.save(Wallet(owner = owner))
        log.info("Created wallet with id - ${savedWallet.id} for user - ${owner.login}")
        return savedWallet
    }

    @Transactional
    override fun getFromBalance(transaction: Transaction, wallet: Wallet): WalletInfoResponse {
        AppUserInfo.checkAccessAllowed(wallet.owner?.login!!)
        val amount: BigDecimal = transaction.amount!! + transaction.feeAmount!!

        if (wallet.balance.minus(amount) <= BigDecimal.ZERO)
            throw ValidationException("Can not write off due to lack of funds")

        wallet.balance = wallet.balance.minus(amount)
        wallet.lastModifiedDate = LocalDateTime.now()
        wallet.transactionsSender = wallet.transactionsSender!!.plus(transaction)
        walletRepo.save(wallet)
        log.info("Wrote off $amount from wallet - ${wallet.id}")

        return walletMapper.toInfoResponse(wallet)
    }

    @Transactional
    override fun addToBalance(transaction: Transaction, wallet: Wallet): WalletInfoResponse {
        val amount: BigDecimal = transaction.amount!!

        wallet.balance = wallet.balance.plus(amount)
        wallet.lastModifiedDate = LocalDateTime.now()
        wallet.transactionsRecipient = wallet.transactionsRecipient!!.plus(transaction)
        walletRepo.save(wallet)
        log.info("Replenished $amount to wallet - ${wallet.id}")

        return walletMapper.toInfoResponse(wallet)
    }

    override fun findEntityById(id: UUID): Wallet {
        val wallet: Wallet =
            walletRepo.findById(id).orElseThrow { ContentNotFoundException("Wallet with id - $id not found") }
        if (wallet.owner!!.blocked)
            throw SoftDeletionException("Wallet with id - $id not found")
        return wallet
    }
}