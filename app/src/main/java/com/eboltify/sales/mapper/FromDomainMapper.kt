package com.eboltify.sales.mapper

interface FromDomainMapper<in M, out E> {
    fun mapFromDomain(type: M) : E
}