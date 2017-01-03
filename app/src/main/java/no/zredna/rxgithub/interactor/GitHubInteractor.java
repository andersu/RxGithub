package no.zredna.rxgithub.interactor;

import io.reactivex.Observable;
import no.zredna.rxgithub.model.github.GitHubInformation;

public interface GitHubInteractor {
    Observable<GitHubInformation> getGitHubInformation(String username);
}
