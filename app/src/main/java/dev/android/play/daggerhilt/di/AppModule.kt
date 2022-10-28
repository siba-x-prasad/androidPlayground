package dev.android.play.daggerhilt.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.components.SingletonComponent

// @InstallIn(ActivityComponent::class) module can only be scoped with @ActivityScoped.
// @InstallIn(FragmentComponent::class) module can only be scoped with @FragmentScoped.

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

}