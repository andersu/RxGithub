package no.zredna.rxgithub.view.github;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import no.zredna.rxgithub.R;
import no.zredna.rxgithub.interactor.GitHubInteractorImpl;
import no.zredna.rxgithub.model.github.GitHubInformation;
import no.zredna.rxgithub.presenter.GitHubPresenter;
import no.zredna.rxgithub.presenter.GitHubPresenterImpl;
import no.zredna.rxgithub.router.RouterImpl;
import no.zredna.rxgithub.service.GitHubServiceProvider;
import no.zredna.rxgithub.view.github.list.GitHubAdapter;

public class GitHubActivity extends AppCompatActivity implements GitHubView {
    private static final String TAG = "GitHubActivity";

    private GitHubPresenter presenter;
    private GitHubAdapter gitHubAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @BindView(R.id.recyclerViewGithub)
    RecyclerView recyclerViewGithub;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github_information);
        ButterKnife.bind(this);

        GitHubInteractorImpl gitHubInteractor = new GitHubInteractorImpl(new GitHubServiceProvider());
        presenter = new GitHubPresenterImpl(this, gitHubInteractor);

        addUpArrowToActionBar();

        handleIntent();

        initRepoRecyclerView();
    }

    private void addUpArrowToActionBar() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void handleIntent() {
        Intent intent = getIntent();

        if (intent != null) {
            String username = intent.getStringExtra(RouterImpl.EXTRA_USERNAME);
            if (username != null) {
                presenter.onCreate(username);
            }
        }
    }

    private void initRepoRecyclerView() {
        layoutManager = new LinearLayoutManager(this);
        recyclerViewGithub.setLayoutManager(layoutManager);
        gitHubAdapter = new GitHubAdapter();
        recyclerViewGithub.setAdapter(gitHubAdapter);
    }

    @Override
    public void setInformation(GitHubInformation gitHubInformation) {
        gitHubAdapter.setGitHubInformation(gitHubInformation);
    }
}
