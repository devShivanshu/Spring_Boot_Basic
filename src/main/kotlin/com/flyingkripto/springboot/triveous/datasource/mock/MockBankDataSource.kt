package com.flyingkripto.springboot.triveous.datasource.mock

import com.flyingkripto.springboot.triveous.datasource.BankDataSource
import com.flyingkripto.springboot.triveous.model.Bank
import org.springframework.stereotype.Repository
import java.lang.IllegalArgumentException

@Repository
class MockBankDataSource : BankDataSource {
    val banks = mutableListOf<Bank>(Bank(accountNumber = "1", trust = 4.0, transactionFee = 500),
            Bank(accountNumber = "2", trust = 4.0, transactionFee = 100),
            Bank(accountNumber = "3", trust = 4.0, transactionFee = 200),
            Bank(accountNumber = "4", trust = 4.0, transactionFee = 600),
            Bank(accountNumber = "5", trust = 4.0, transactionFee = 800))
    override fun retrieveBanks(): Collection<Bank> {
        return banks
    }

    override fun retrieveBank(accountNumber: String): Bank = banks.first { it.accountNumber == accountNumber }
    override fun createBank(bank: Bank): Bank {
        if(banks.any{it.accountNumber == bank.accountNumber}){
            throw  IllegalArgumentException("Bank already exists with account number ${bank.accountNumber}")
        }
        banks.add(bank)
        return  bank
    }

}