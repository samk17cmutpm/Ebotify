package com.eboltify.sales.remote.mapper

interface MapperForData<in M, out E> {

    fun map(type: M) : E

}