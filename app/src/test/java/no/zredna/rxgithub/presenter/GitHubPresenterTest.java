package no.zredna.rxgithub.presenter;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import io.reactivex.Observable;
import no.zredna.rxgithub.RxGitHubTest;
import no.zredna.rxgithub.interactor.GitHubInteractor;
import no.zredna.rxgithub.model.github.GitHubInformation;
import no.zredna.rxgithub.view.github.GitHubView;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GitHubPresenterTest extends RxGitHubTest {
    @InjectMocks
    GitHubPresenterImpl gitHubPresenter;

    @Mock
    GitHubView gitHubView;

    @Mock
    GitHubInteractor gitHubInteractor;

    @Mock
    GitHubInformation gitHubInformation;

    private String username = "username";

    @Test
    public void onCreate_callsSetInformation_withGitHubInformationFromObservable() throws Exception {
        when(gitHubInteractor.getGitHubInformation(username)).thenReturn(Observable.fromArray(gitHubInformation));

        gitHubPresenter.onCreate(username);

        verify(gitHubView).setInformation(gitHubInformation);
    }

    @Test
    public void onCreate_callsFailedToGetInformation_withErrorFromObservable() throws Exception {
        when(gitHubInteractor.getGitHubInformation(username)).thenReturn(Observable.error(new Error()));

        gitHubPresenter.onCreate(username);

        verify(gitHubView).failedToGetInformation();
    }
}