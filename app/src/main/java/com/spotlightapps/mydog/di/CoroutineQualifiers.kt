package com.spotlightapps.mydog.di

import javax.inject.Qualifier

/**
 * Created by Ahmad Jawid Muhammadi
 * on 24-12-2021.
 */


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IoDispatcher

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultDispatcher