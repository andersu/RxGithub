package no.zredna.rxgithub.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import no.zredna.rxgithub.R;
import no.zredna.rxgithub.interactor.GitHubInteractorImpl;
import no.zredna.rxgithub.model.github.GitHubInformation;
import no.zredna.rxgithub.presenter.GitHubPresenter;
import no.zredna.rxgithub.presenter.GitHubPresenterImpl;
import no.zredna.rxgithub.router.RouterImpl;
import no.zredna.rxgithub.service.GitHubServiceProvider;

public class GitHubActivity extends AppCompatActivity implements GitHubView {
    private static final String TAG = "GitHubActivity";

    private GitHubPresenter presenter;

    @BindView(R.id.textViewUsername)
    TextView textViewUsername;

    @BindView(R.id.textViewName)
    TextView textViewName;

    @BindView(R.id.textViewPublicRepos)
    TextView textViewPublicRepos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github_information);
        ButterKnife.bind(this);

        GitHubInteractorImpl gitHubInteractor = new GitHubInteractorImpl(new GitHubServiceProvider());
        presenter = new GitHubPresenterImpl(this, gitHubInteractor);

        Intent intent = getIntent();

        if (intent != null) {
            String username = intent.getStringExtra(RouterImpl.EXTRA_USERNAME);
            if (username != null) {
                presenter.onCreate(username);
            }
        }
    }

    @Override
    public void setInformation(GitHubInformation gitHubInformation) {
        textViewUsername.setText(gitHubInformation.getUser().getLogin());
        textViewName.setText(gitHubInformation.getUser().getName());
        textViewPublicRepos.setText(String.valueOf(gitHubInformation.getUser().getPublicRepos()));
    }
}
