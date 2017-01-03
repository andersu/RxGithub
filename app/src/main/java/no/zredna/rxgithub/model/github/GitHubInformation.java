package no.zredna.rxgithub.model.github;

import java.util.List;

public class GitHubInformation {
    private User user;
    private List<Repo> repos;

    public GitHubInformation(User user, List<Repo> repos) {
        this.user = user;
        this.repos = repos;
    }

    public User getUser() {
        return user;
    }

    public List<Repo> getRepos() {
        return repos;
    }
}
