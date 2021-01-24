package com.example.daggerapplication.di;

import android.app.Application;

import com.example.daggerapplication.BaseApplication;
import com.example.daggerapplication.SessionManager;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;



@Singleton
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                ActivityBuildersModule.class,
                AppModule.class,
                ViewModelFactoryModule.class,

        }
)
public interface AppComponent extends AndroidInjector<BaseApplication> {

    SessionManager sessionManager();

    @Component.Builder
    //overriding the builder interface
    interface Builder{

        //Binding the Application to the AppComponent
        @BindsInstance
        Builder application(Application application);


        AppComponent build();

    }

}
