package no.zredna.rxgithub.presenter;

public interface GitHubPresenter {
    void shouldGetGitHubInformation(String username);
    void onDestroy();
}
