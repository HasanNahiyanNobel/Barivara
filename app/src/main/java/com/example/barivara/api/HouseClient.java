package com.example.barivara.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HouseClient {
	@GET("/houses-list/")
	Call<List<House>> houseAll();
}