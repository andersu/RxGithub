package no.zredna.rxgithub.presenter;

public interface GitHubPresenter {
    void shouldGetGithubInformation(String username);
    void onDestroy();
}
