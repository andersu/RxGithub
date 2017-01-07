package no.zredna.rxgithub.interactor;

import org.junit.Before;
import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import no.zredna.rxgithub.model.github.GitHubInformation;
import no.zredna.rxgithub.service.GitHubServiceProvider;

import static org.junit.Assert.*;

public class GitHubInteractorIntegrationTest {
    private GitHubInteractor gitHubInteractor;

    @Before
    public void setUp() {
        gitHubInteractor = new GitHubInteractorImpl(new GitHubServiceProvider(),
                AndroidSchedulers.mainThread());
    }
    @Test
    public void getGitHubInformation() throws Exception {
        String username = "andersu";
        String expectedName = "Anders Ullnæss";

        Observable<GitHubInformation> observable = gitHubInteractor.getGitHubInformation(username);

        GitHubInformation gitHubInformation = observable.blockingFirst();

        int publicRepos = gitHubInformation.getUser().getPublicRepos();
        assertEquals(username, gitHubInformation.getUser().getLogin());
        assertEquals(expectedName, gitHubInformation.getUser().getName());
        assertTrue(publicRepos > 0);
        assertEquals(publicRepos, gitHubInformation.getRepos().size());
        assertNotNull(gitHubInformation.getRepos().get(0).getName());
        assertNotNull(gitHubInformation.getRepos().get(0).getCreatedAt());
    }
}