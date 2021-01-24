package com.example.daggerapplication.ui.auth;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.daggerapplication.SessionManager;
import com.example.daggerapplication.models.User;
import com.example.daggerapplication.network.auth.AuthApi;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";
   private final AuthApi authApi;
   private SessionManager sessionManager;


    @Inject
    public AuthViewModel(AuthApi authApi, SessionManager sessionManager)
    {
        this.sessionManager = sessionManager;
        this.authApi = authApi;
    }



    public void authenticateWithid(int userid){

        Log.d(TAG, "authenticateWithid: attempting to login");

        sessionManager.autheticateWithId(queryUserId(userid));

    }

    private LiveData<AuthResource<User>> queryUserId(int userId)
    {
        return LiveDataReactiveStreams.fromPublisher(
                authApi.getUser(userId)
                        //error happends
                        .onErrorReturn(new Function<Throwable, User>() {
                            @Override
                            public User apply(Throwable throwable) throws Exception {
                                User errorUser  = new User();
                                errorUser.setId(-1);


                                return errorUser;
                            }
                        })

                        .map(new Function<User, AuthResource<User>>() {
                            @Override
                            public AuthResource<User> apply(User user) throws Exception {
                                if(user.getId() == -1){
                                    return AuthResource.error("Could not authenticate", (User)null);
                                }
                                return AuthResource.authenticated(user);
                            }
                        })
                        .subscribeOn(Schedulers.io())
        );
    }
    public LiveData<AuthResource<User>> observeAuthState(){
        return sessionManager.getAuthUser();
    }
}
