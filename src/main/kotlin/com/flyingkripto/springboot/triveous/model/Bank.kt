package com.flyingkripto.springboot.triveous.model

import org.apache.tomcat.util.descriptor.web.ContextTransaction

data class Bank(val accountNumber: String, val trust: Double, val transactionFee: Int)