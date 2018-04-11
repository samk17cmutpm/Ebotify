package com.eboltify.sales.data.mapper

interface MapperForDomain<in M, out E> {

    fun map(type: M) : E

}