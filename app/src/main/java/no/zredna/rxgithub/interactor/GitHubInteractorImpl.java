package no.zredna.rxgithub.interactor;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import no.zredna.rxgithub.model.github.GitHubInformation;
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
                GitHubInformation::new)
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread());
    }
}
