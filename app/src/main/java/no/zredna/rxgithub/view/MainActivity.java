package no.zredna.rxgithub.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import no.zredna.rxgithub.R;
import no.zredna.rxgithub.interactor.GitHubInteractor;
import no.zredna.rxgithub.interactor.GitHubInteractorImpl;
import no.zredna.rxgithub.model.github.GitHubInformation;
import no.zredna.rxgithub.service.GitHubServiceProvider;

public class MainActivity extends AppCompatActivity implements MainView {
    private static final String TAG = "MainActivity";

    @BindView(R.id.editTextUsername)
    EditText editTextUsername;

    private GitHubInteractor gitHubInteractor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        gitHubInteractor = new GitHubInteractorImpl(new GitHubServiceProvider());
    }

    @OnClick(R.id.buttonGithubInformation)
    public void getGithubInformation() {
        String username = editTextUsername.getText().toString();

        gitHubInteractor.getGitHubInformation(username).subscribe(
                this::handleSuccess,
                this::handleError);
    }

    private void handleError(Throwable throwable) {
        Log.d(TAG, "Error :(");
    }

    private void handleSuccess(GitHubInformation gitHubInformation) {
        Log.d(TAG, "Success :)");
    }
}
