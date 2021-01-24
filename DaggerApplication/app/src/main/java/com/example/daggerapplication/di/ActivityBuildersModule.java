package com.example.daggerapplication.di;

import com.example.daggerapplication.di.auth.AuthModule;
import com.example.daggerapplication.di.auth.AuthViewModelsModule;
import com.example.daggerapplication.ui.auth.AuthActivity;
import com.example.daggerapplication.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
//this contains declatations of activities
@Module
public abstract class ActivityBuildersModule {


//stating that the AuthActivity is a possible client to the AppComponent
    @ContributesAndroidInjector(
            modules = {
                    AuthViewModelsModule.class,
                    AuthModule.class,
            }
    )
    abstract AuthActivity contributeAuthActivity();


    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();
}
