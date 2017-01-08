package no.zredna.rxgithub.view.github;

import no.zredna.rxgithub.model.github.GitHubInformation;

public interface GitHubView {
    void showLoadingScreen();
    void hideLoadingScreen();
    void setInformation(GitHubInformation gitHubInformation);
    void failedToGetInformation();
    void userNotFound();
}
