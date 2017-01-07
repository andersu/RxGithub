package no.zredna.rxgithub.interactor;

import android.util.Log;

import java.util.Date;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import no.zredna.rxgithub.model.github.GitHubInformation;
import no.zredna.rxgithub.service.GitHubService;
import no.zredna.rxgithub.service.GitHubServiceProvider;

public class GitHubInteractorImpl implements GitHubInteractor {
    private static final String TAG = "GithubInteractorImpl";

    private GitHubService gitHubService;

    private long onCompleteMillis;

    public GitHubInteractorImpl(GitHubServiceProvider gitHubServiceProvider) {
        gitHubService = gitHubServiceProvider.provideGithubService();
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
                            onCompleteMillis = new Date().getTime();
                        }),
                gitHubService.listRepos(username)
                        .doOnComplete(() -> {
                            onCompleteMillis = new Date().getTime();
                        }),
                (user, repos) -> {
                    long waitedMillis = new Date().getTime() - onCompleteMillis;
                    return new GitHubInformation(user, repos, waitedMillis);
                })
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(Schedulers.io());
    }
}
