package no.zredna.rxgithub.presenter;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.net.HttpURLConnection;

import io.reactivex.Observable;
import no.zredna.rxgithub.RxGitHubTest;
import no.zredna.rxgithub.interactor.GitHubInteractor;
import no.zredna.rxgithub.model.github.GitHubInformation;
import no.zredna.rxgithub.view.github.GitHubView;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.BufferedSource;
import retrofit2.Response;

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
    public void shouldGetGitHubInformationcallsSetInformation_withGitHubInformationFromObservable() throws Exception {
        when(gitHubInteractor.getGitHubInformation(username)).thenReturn(Observable.fromArray(gitHubInformation));

        gitHubPresenter.shouldGetGitHubInformation(username);

        verify(gitHubView).hideLoadingScreen();
        verify(gitHubView).setInformation(gitHubInformation);
    }

    @Test
    public void shouldGetGitHubInformationcallsUserNotFound_whenNotFoundFromObservable() throws Exception {
        // Mockito cannot mock final classes, so making our own mock
        HttpException notFoundException = new HttpException(Response.error(HttpURLConnection.HTTP_NOT_FOUND, new ResponseBody() {
            @Override
            public MediaType contentType() {
                return null;
            }

            @Override
            public long contentLength() {
                return 0;
            }

            @Override
            public BufferedSource source() {
                return null;
            }
        }));

        when(gitHubInteractor.getGitHubInformation(username)).thenReturn(Observable.error(notFoundException));

        gitHubPresenter.shouldGetGitHubInformation(username);

        verify(gitHubView).hideLoadingScreen();
        verify(gitHubView).userNotFound();
    }

    @Test
    public void shouldGetGitHubInformationcallsFailedToGetInformation_whenOtherErrorFromObservable() throws Exception {
        when(gitHubInteractor.getGitHubInformation(username)).thenReturn(Observable.error(new Error()));

        gitHubPresenter.shouldGetGitHubInformation(username);

        verify(gitHubView).hideLoadingScreen();
        verify(gitHubView).failedToGetInformation();
    }
}