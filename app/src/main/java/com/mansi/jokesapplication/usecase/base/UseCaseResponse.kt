package com.mansi.jokesapplication.usecase.base

import com.mansi.jokesapplication.domain.model.ApiError


interface UseCaseResponse<Type> {

    fun onSuccess(result: Type)

    fun onError(apiError: ApiError?)
}

