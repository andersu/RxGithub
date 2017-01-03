package no.zredna.rxgithub.interactor;

import android.util.Log;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;
import no.zredna.rxgithub.model.github.GitHubInformation;
import no.zredna.rxgithub.model.github.Repo;
import no.zredna.rxgithub.model.github.User;
import no.zredna.rxgithub.service.GitHubService;
import no.zredna.rxgithub.service.GitHubServiceProvider;

public class GitHubInteractorImpl implements GitHubInteractor {
    private static final String TAG = "GithubInteractorImpl";

    private GitHubService gitHubService;

    public GitHubInteractorImpl(GitHubServiceProvider gitHubServiceProvider) {
        gitHubService = gitHubServiceProvider.provideGithubService();
    }



    @Override
    public Observable<GitHubInformation> getGitHubInformation(String username) {
        return Observable.zip(gitHubService.getUser(username), gitHubService.listRepos(username),
                (user, repos) -> new GitHubInformation(user, repos))
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread());
    }

    /*private Observer<GitHubInformation> getObserver() {
        return new Observer<GitHubInformation>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(GitHubInformation gitHubInformation) {

                Log.d(TAG, " onNext");
            }

            @Override
            public void onError(Throwable e) {

                Log.d(TAG, " onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {

                Log.d(TAG, " onComplete");
            }
        };
    }*/
}
