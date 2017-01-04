package no.zredna.rxgithub.presenter;

import android.util.Log;

import no.zredna.rxgithub.interactor.GitHubInteractor;
import no.zredna.rxgithub.model.github.GitHubInformation;
import no.zredna.rxgithub.view.github.GitHubView;

public class GitHubPresenterImpl implements GitHubPresenter {
    private static final String TAG = "GitHubPresenterImpl";

    private GitHubView gitHubView;
    private GitHubInteractor gitHubInteractor;

    public GitHubPresenterImpl(GitHubView gitHubView, GitHubInteractor gitHubInteractor) {
        this.gitHubView = gitHubView;
        this.gitHubInteractor = gitHubInteractor;
    }

    @Override
    public void onCreate(String username) {
        getGitHubInformation(username);
    }

    @Override
    public void onDestroy() {
        gitHubView = null;
    }

    private void getGitHubInformation(String username) {
        gitHubInteractor.getGitHubInformation(username).subscribe(
                this::handleSuccess,
                this::handleError);
    }

    private void handleError(Throwable throwable) {
        Log.d(TAG, "Error :(");
    }

    private void handleSuccess(GitHubInformation gitHubInformation) {
        Log.d(TAG, "Success :)");
        gitHubView.setInformation(gitHubInformation);
    }
}
