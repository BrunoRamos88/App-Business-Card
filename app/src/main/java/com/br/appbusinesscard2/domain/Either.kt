package com.br.appbusinesscard2.domain

import java.lang.Exception

sealed class Either<T, U>(val t: T?, val u: U?){

    class BusinessCardEither<T, U>(
        val businessCard: BusinessCard?,
        val exception: Exception?
    ) : Either<T, U>(businessCard as T, exception as U)
}