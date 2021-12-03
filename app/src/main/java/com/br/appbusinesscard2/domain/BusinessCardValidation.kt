package com.br.appbusinesscard2.domain

import com.br.appbusinesscard2.exception.InvalidNameException
import java.lang.Exception

class BusinessCardValidation {

    companion object {

        const val NAME_TOO_SHORT_EXCEPTION = "O nome precisa ter no mínimo 3 caracteres"

        fun validate(businessCard: BusinessCard) : Either<BusinessCard?, Exception?> {
            return when (businessCard.name.length) {
                0, 1, 2 -> Either.BusinessCardEither(
                    null, InvalidNameException(NAME_TOO_SHORT_EXCEPTION)
                )
                else -> Either.BusinessCardEither(businessCard, null)
            }
        }
    }
}