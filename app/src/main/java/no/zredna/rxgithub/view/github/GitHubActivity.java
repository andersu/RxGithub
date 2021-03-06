package no.zredna.rxgithub.view.github;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import no.zredna.rxgithub.R;
import no.zredna.rxgithub.interactor.GitHubInteractorImpl;
import no.zredna.rxgithub.model.github.GitHubInformation;
import no.zredna.rxgithub.presenter.GitHubPresenter;
import no.zredna.rxgithub.presenter.GitHubPresenterImpl;
import no.zredna.rxgithub.router.RouterImpl;
import no.zredna.rxgithub.service.GitHubServiceProvider;
import no.zredna.rxgithub.view.github.list.GitHubAdapter;
import no.zredna.rxgithub.view.loading.LoadingView;

public class GitHubActivity extends AppCompatActivity implements GitHubView {
    private GitHubPresenter presenter;
    private GitHubAdapter gitHubAdapter;

    @BindView(R.id.layoutRoot)
    View layoutRoot;

    @BindView(R.id.recyclerViewGithub)
    RecyclerView recyclerViewGithub;

    @BindView(R.id.loadingView)
    LoadingView loadingView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github_information);
        ButterKnife.bind(this);

        GitHubInteractorImpl gitHubInteractor = new GitHubInteractorImpl(new GitHubServiceProvider(),
                AndroidSchedulers.mainThread());
        presenter = new GitHubPresenterImpl(this, gitHubInteractor);

        addUpArrowToActionBar();
        setPageTitle();

        getGitHubInformation();

        initRepoRecyclerView();
    }

    private void setPageTitle() {
        String username = getUsernameFromIntent();
        if (username != null) {
            ActionBar supportActionBar = getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setTitle(username);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    private void addUpArrowToActionBar() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void getGitHubInformation() {
        String username = getUsernameFromIntent();
        if (username != null) {
            presenter.shouldGetGitHubInformation(username);
        }
    }

    @Nullable
    private String getUsernameFromIntent() {
        String username = null;

        Intent intent = getIntent();

        if (intent != null) {
            username = intent.getStringExtra(RouterImpl.EXTRA_USERNAME);
        }

        return username;
    }

    private void initRepoRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewGithub.setLayoutManager(layoutManager);
        gitHubAdapter = new GitHubAdapter();
        recyclerViewGithub.setAdapter(gitHubAdapter);
    }

    @Override
    public void showLoadingScreen() {
        loadingView.startAnimation();
        loadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingScreen() {
        loadingView.setVisibility(View.GONE);
    }

    @Override
    public void setInformation(GitHubInformation gitHubInformation) {
        gitHubAdapter.setGitHubInformation(gitHubInformation);
    }

    @Override
    public void failedToGetInformation() {
        Snackbar.make(layoutRoot, R.string.failed_to_get_github_information, Snackbar.LENGTH_LONG)
                .setAction(R.string.retry, view -> getGitHubInformation())
                .show();
    }

    @Override
    public void userNotFound() {
        Snackbar.make(layoutRoot, R.string.user_not_found, Snackbar.LENGTH_LONG)
                .setAction(R.string.go_back, view -> finish())
                .show();
    }
}
