package com.eboltify.sales.mapper

interface MapperForApp<in M, out E> {
    fun map(type: M) : E
}