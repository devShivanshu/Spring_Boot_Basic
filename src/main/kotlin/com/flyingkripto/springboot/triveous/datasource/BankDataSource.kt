package com.flyingkripto.springboot.triveous.datasource

import com.flyingkripto.springboot.triveous.model.Bank

interface BankDataSource {
    fun retrieveBanks() : Collection<Bank>
    fun retrieveBank(accountNumber: String) : Bank
    fun createBank(bank: Bank): Bank
}