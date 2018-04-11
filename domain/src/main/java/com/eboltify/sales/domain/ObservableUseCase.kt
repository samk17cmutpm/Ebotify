package com.eboltify.sales.domain

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

/**
 * Created by sam_nguyen on 1/11/18.
 */
abstract class ObservableUseCase<in Q : ObservableUseCase.RequestValues, P> {

    private val disposable: CompositeDisposable = CompositeDisposable()

    interface RequestValues

    abstract fun buildUseCaseObservable(requestValues: Q) : Observable<P>

    fun run(consumer: DisposableObserver<P>, requestValues: Q) {
        val observable: Observable<P> = this.buildUseCaseObservable(requestValues)
        disposable.add(observable.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribeWith(consumer))
    }

    open fun dispose() {
        disposable.clear()
    }
}