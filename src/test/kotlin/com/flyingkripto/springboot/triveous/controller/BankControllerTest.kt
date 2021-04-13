package com.flyingkripto.springboot.triveous.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.flyingkripto.springboot.triveous.model.Bank
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
internal class BankControllerTest @Autowired constructor(
        val mockMvc: MockMvc,
        val objectMapper: ObjectMapper
){


    @Test
    fun `should return all banks`() {
       mockMvc.get("/api/banks")
               .andDo { print() }
               .andExpect { status { isOk() }
                   content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$[0].accountNumber") {value("1")}
               }
    }

    @Test
    fun `should return the bank with given account number` () {
        val accountNumber = 2
        mockMvc.get("/api/banks/$accountNumber")
                .andDo { print() }
                .andExpect { status { isOk() }
                 content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.trust") {value("4.0")}
                }
    }

    @Test
    fun `should add a new bank` () {
        val bank = Bank(accountNumber = "21",transactionFee = 300, trust = 55.0)

        mockMvc.post("/api/banks") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(bank)
        }
                .andDo { print() }
                .andExpect {
                    status { isCreated() }
                }
    }

    @Test
    fun `should return BAD request if bank with given account number already exists` () {
        val invalidBank = Bank(accountNumber = "1",trust = 4.0,transactionFee = 100)

       val performPost=  mockMvc.post("/api/banks"){
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(invalidBank)
        }

        performPost
                .andDo { print() }
                .andExpect { status { isBadRequest() } }
    }


}