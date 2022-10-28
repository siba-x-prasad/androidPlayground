package dev.android.play.daggerhilt.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import dev.android.play.daggerhilt.components.Bar
import dev.android.play.daggerhilt.components.ScopedBinding
import dev.android.play.daggerhilt.components.UnscopedBinding

@Module
@InstallIn(FragmentComponent::class)
object FooModule {

    @Provides
    fun provideBar(): Bar {
        return Bar()
    }

    // This binding is "unscoped".
    @Provides
    fun provideUnscopedBinding() = UnscopedBinding()

    // This binding is "scoped".
    @Provides
    @FragmentScoped
    fun provideScopedBinding() = ScopedBinding()
}