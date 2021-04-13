package com.flyingkripto.springboot.triveous.service

import com.flyingkripto.springboot.triveous.datasource.BankDataSource
import com.flyingkripto.springboot.triveous.model.Bank
import org.springframework.stereotype.Service

@Service
class BankService(private  val dataSource: BankDataSource) {
  fun getBanks() : Collection<Bank> = dataSource.retrieveBanks()
  fun getBank(accountNumber: String) : Bank = dataSource.retrieveBank(accountNumber)
  fun addBank(bank: Bank): Bank = dataSource.createBank(bank)

}