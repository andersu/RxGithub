package no.zredna.rxgithub.presenter;

import android.support.annotation.NonNull;

import no.zredna.rxgithub.view.main.MainView;

public class MainPresenterImpl implements MainPresenter {

    private MainView mainView;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void onUsernameTextChanged(@NonNull String usernameText) {
        if (usernameText.isEmpty()) {
            mainView.disableButton();
        } else {
            mainView.enableButton();
        }
    }
}
