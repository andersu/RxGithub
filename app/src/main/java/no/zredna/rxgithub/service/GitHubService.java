package no.zredna.rxgithub.service;

import java.util.List;

import io.reactivex.Observable;
import no.zredna.rxgithub.model.github.Repo;
import no.zredna.rxgithub.model.github.User;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubService {

    @GET("users/{username}")
    Observable<User> getUser(@Path("username") String username);

    @GET("users/{username}/repos")
    Observable<List<Repo>> listRepos(@Path("username") String username);
}
