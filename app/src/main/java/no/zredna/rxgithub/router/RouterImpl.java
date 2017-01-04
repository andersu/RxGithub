package no.zredna.rxgithub.router;

import android.content.Context;
import android.content.Intent;

import no.zredna.rxgithub.view.github.GitHubActivity;

public class RouterImpl implements Router {

    public static final String EXTRA_USERNAME = "username";

    @Override
    public void goToGitHubInformationActivity(Context context, String username) {
        Intent intent = new Intent(context, GitHubActivity.class);
        intent.putExtra(EXTRA_USERNAME, username);
        context.startActivity(intent);
    }
}
