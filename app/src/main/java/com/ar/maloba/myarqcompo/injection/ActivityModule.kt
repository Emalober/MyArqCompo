package com.ar.maloba.myarqcompo.injection

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    internal abstract fun contributeMainActivity(): MainActivity

//    @ContributesAndroidInjector
//    internal abstract fun contributePasswordActivity(): PasswordActivity

    // Add bindings for other sub-components here
}