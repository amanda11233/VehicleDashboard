package com.example.retrofitapplication;

import com.example.retrofitapplication.models.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {


    @GET("employees")
    Call<List<Employee>> getEmployees();

}
