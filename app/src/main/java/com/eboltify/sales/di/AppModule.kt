package com.eboltify.sales.di

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule constructor(val mApplication: Application) {

    @Provides
    @Singleton
    fun providesAppModule() : Application {
        return mApplication
    }

}