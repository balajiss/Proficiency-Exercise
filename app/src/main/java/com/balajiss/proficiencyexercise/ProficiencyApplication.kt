package com.balajiss.proficiencyexercise

import com.balajiss.proficiencyexercise.di.DaggerProficiencyAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class ProficiencyApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val component = DaggerProficiencyAppComponent.builder().application(this).build()
        component.inject(this)
        return component
    }
}