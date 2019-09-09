package com.example.barivara.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;

public interface UserClient {
	@POST("/users-list/")
	Call<List<User>> userAll();
}
