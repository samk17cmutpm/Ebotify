package com.eboltify.sales.data.mapper

interface EntityMapper<in M, out E> {

    fun mapFromRemote(type: M) : E

}