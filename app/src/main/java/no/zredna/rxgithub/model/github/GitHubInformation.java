package no.zredna.rxgithub.model.github;

import java.util.List;

public class GitHubInformation {
    private User user;
    private List<Repo> repos;
    private long waitedMillis;

    public GitHubInformation(User user, List<Repo> repos, long waitedMillis) {
        this.user = user;
        this.repos = repos;
        this.waitedMillis = waitedMillis;
    }

    public User getUser() {
        return user;
    }

    public List<Repo> getRepos() {
        return repos;
    }

    public long getWaitedMillis() {
        return waitedMillis;
    }
}
