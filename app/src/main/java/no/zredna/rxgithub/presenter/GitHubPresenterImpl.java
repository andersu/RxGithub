package no.zredna.rxgithub.presenter;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.net.HttpURLConnection;

import no.zredna.rxgithub.interactor.GitHubInteractor;
import no.zredna.rxgithub.model.github.GitHubInformation;
import no.zredna.rxgithub.view.github.GitHubView;

public class GitHubPresenterImpl implements GitHubPresenter {

    private GitHubView gitHubView;
    private GitHubInteractor gitHubInteractor;

    public GitHubPresenterImpl(GitHubView gitHubView, GitHubInteractor gitHubInteractor) {
        this.gitHubView = gitHubView;
        this.gitHubInteractor = gitHubInteractor;
    }

    @Override
    public void shouldGetGitHubInformation(String username) {
        getGitHubInformation(username);
    }

    @Override
    public void onDestroy() {
        gitHubView = null;
    }

    private void getGitHubInformation(String username) {
        gitHubView.showLoadingScreen();
        gitHubInteractor.getGitHubInformation(username).subscribe(
                this::handleSuccess,
                this::handleError);
    }

    private void handleError(Throwable throwable) {
        gitHubView.hideLoadingScreen();
        if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            if (httpException.code() == HttpURLConnection.HTTP_NOT_FOUND) {
                gitHubView.userNotFound();
                return;
            }
        }
        gitHubView.failedToGetInformation();
    }

    private void handleSuccess(GitHubInformation gitHubInformation) {
        gitHubView.hideLoadingScreen();
        gitHubView.setInformation(gitHubInformation);
    }
}
