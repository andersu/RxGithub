package no.zredna.rxgithub.interactor;

import android.util.Log;

import java.util.Date;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import no.zredna.rxgithub.model.github.GitHubInformation;
import no.zredna.rxgithub.service.GitHubService;
import no.zredna.rxgithub.service.GitHubServiceProvider;

public class GitHubInteractorImpl implements GitHubInteractor {
    private static final String TAG = "GithubInteractorImpl";

    private GitHubService gitHubService;
    private Scheduler observeOn;

    private long onCompleteMillis;

    public GitHubInteractorImpl(GitHubServiceProvider gitHubServiceProvider, Scheduler observeOn) {
        gitHubService = gitHubServiceProvider.provideGithubService();
        this.observeOn = observeOn;
    }

    @Override
    /*
     * doOnComplete will only be called for the first of getUser and listRepos to finish.
     * See the documentation of Observable.zip for more details.
     */
    public Observable<GitHubInformation> getGitHubInformation(String username) {
        return Observable.zip(
                gitHubService.getUser(username)
                        .doOnComplete(() -> {
                            onCompleteMillis = System.currentTimeMillis();
                        }),
                gitHubService.listRepos(username)
                        .doOnComplete(() -> {
                            onCompleteMillis = System.currentTimeMillis();
                        }),
                (user, repos) -> {
                    long waitedMillis = System.currentTimeMillis() - onCompleteMillis;
                    return new GitHubInformation(user, repos, waitedMillis);
                })
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the injected thread
                .observeOn(observeOn);
    }
}
