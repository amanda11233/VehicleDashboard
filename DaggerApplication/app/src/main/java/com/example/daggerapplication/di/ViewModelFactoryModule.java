package com.example.daggerapplication.di;


import androidx.lifecycle.ViewModelProvider;

import com.example.daggerapplication.viewmodels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;
// generating the dependencies injection for viewmodelfactory class
@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory modelProviderFactory);


}
