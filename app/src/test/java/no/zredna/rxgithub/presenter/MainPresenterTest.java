package no.zredna.rxgithub.presenter;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import no.zredna.rxgithub.RxGitHubTest;
import no.zredna.rxgithub.view.main.MainView;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

public class MainPresenterTest extends RxGitHubTest {
    @InjectMocks
    private MainPresenterImpl mainPresenter;

    @Mock
    private MainView mainView;

    @Test
    public void onUsernameTextChanged_withSomeUsernameText_callsEnableButton() throws Exception {
        mainPresenter.onUsernameTextChanged("username");

        verify(mainView).enableButton();
    }

    @Test
    public void onUsernameTextChanged_withEmptyUsernameText_callsDisableButton() throws Exception {
        mainPresenter.onUsernameTextChanged("");

        verify(mainView).disableButton();
    }
}