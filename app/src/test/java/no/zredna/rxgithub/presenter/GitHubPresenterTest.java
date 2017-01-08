package no.zredna.rxgithub.presenter;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.net.HttpURLConnection;

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

    @Mock
    HttpException notFoundException;

    private String username = "username";

    @Test
    public void shouldGetGitHubInformationcallsSetInformation_withGitHubInformationFromObservable() throws Exception {
        when(gitHubInteractor.getGitHubInformation(username)).thenReturn(Observable.fromArray(gitHubInformation));

        gitHubPresenter.shouldGetGitHubInformation(username);

        verify(gitHubView).setInformation(gitHubInformation);
    }

    @Test
    public void shouldGetGitHubInformationcallsUserNotFound_whenNotFoundFromObservable() throws Exception {
        when(notFoundException.code()).thenReturn(HttpURLConnection.HTTP_NOT_FOUND);
        when(gitHubInteractor.getGitHubInformation(username)).thenReturn(Observable.error(notFoundException));

        gitHubPresenter.shouldGetGitHubInformation(username);

        verify(gitHubView).failedToGetInformation();
    }

    @Test
    public void shouldGetGitHubInformationcallsFailedToGetInformation_whenOtherErrorFromObservable() throws Exception {
        when(gitHubInteractor.getGitHubInformation(username)).thenReturn(Observable.error(new Error()));

        gitHubPresenter.shouldGetGitHubInformation(username);

        verify(gitHubView).failedToGetInformation();
    }
}