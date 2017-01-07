package no.zredna.rxgithub.interactor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import no.zredna.rxgithub.RxGitHubTest;
import no.zredna.rxgithub.model.github.GitHubInformation;
import no.zredna.rxgithub.model.github.Repo;
import no.zredna.rxgithub.model.github.User;
import no.zredna.rxgithub.service.GitHubService;
import no.zredna.rxgithub.service.GitHubServiceProvider;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class GitHubInteractorTest extends RxGitHubTest {
    private GitHubInteractorImpl gitHubInteractor;

    @Mock
    private GitHubServiceProvider gitHubServiceProvider;

    @Mock
    private GitHubService gitHubService;

    @Mock
    private User user;

    @Mock
    private List<Repo> repos;

    private String username = "username";

    @Before
    public void setUp() throws Exception {
        super.setUp();
        gitHubInteractor = new GitHubInteractorImpl(gitHubServiceProvider, Schedulers.trampoline());

        when(gitHubServiceProvider.provideGithubService()).thenReturn(gitHubService);
        when(gitHubService.getUser(username)).thenReturn(Observable.fromArray(user));
        when(gitHubService.listRepos(username)).thenReturn(Observable.fromArray(repos));
    }

    @Test
    public void getGitHubInformation_returnsObservable_combiningObservablesFromGetUserAndGetRepos() throws Exception {
        Observable<GitHubInformation> observable = gitHubInteractor.getGitHubInformation(username);

        GitHubInformation gitHubInformation = observable.blockingFirst();

        assertEquals(user, gitHubInformation.getUser());
        assertEquals(repos, gitHubInformation.getRepos());
    }
}