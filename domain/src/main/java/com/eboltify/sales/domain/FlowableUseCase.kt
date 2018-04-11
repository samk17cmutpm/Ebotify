package com.eboltify.sales.domain

import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber

/**
 * Created by sam_nguyen on 1/22/18.
 */
abstract class FlowableUseCase<in Q : FlowableUseCase.RequestValues, P> {

    private val disposable: CompositeDisposable = CompositeDisposable()

    interface RequestValues

    abstract fun buildUseCaseObservable(requestValues: Q) : Flowable<P>

    fun run(consumer: DisposableSubscriber<P>, requestValues: Q) {
        val observable: Flowable<P> = this.buildUseCaseObservable(requestValues)
        disposable.add(observable.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribeWith(consumer))
    }

    open fun dispose() {
        disposable.clear()
    }
}