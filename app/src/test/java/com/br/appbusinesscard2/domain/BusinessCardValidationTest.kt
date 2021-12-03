package com.br.appbusinesscard2.domain

import org.junit.Test
import kotlin.test.assertTrue
import com.br.appbusinesscard2.exception.InvalidNameException

class BusinessCardValidationTest {

    val mInvalidBusinessCard = BusinessCard(
        name = "Teste1",
        phone = "99999999",
        email = "test@test.com",
        company = "Test",
        customBackground = "#FF000000"
    )

    val mValidBusinessCard = BusinessCard(
        name = "Teste2",
        phone = "99999999",
        email = "test@test.com",
        company = "Test",
        customBackground = "#FFFFFFFF"
    )

    @Test
    fun mustReturnBusinessCardEitherWithValidData() {

        val either = BusinessCardValidation.validate(mValidBusinessCard)

        assertTrue(either.t is BusinessCard)
        assertTrue(either.u == null)

    }

    @Test
    fun mustReturnBusinessCardExceptionWithInvalidData() {

        val either = BusinessCardValidation.validate(mInvalidBusinessCard)

        assertTrue(either.t == null)
        assertTrue(either.u is InvalidNameException)
        assertTrue(either.u?.message == "O nome precisa ter ao menos 3 caracteres")

    }
}