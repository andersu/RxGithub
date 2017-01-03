package no.zredna.rxgithub.model.github;

import com.google.gson.annotations.SerializedName;

public class User {
    private String login;
    private String name;

    @SerializedName("public_repos")
    private int publicRepos;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPublicRepos() {
        return publicRepos;
    }

    public void setPublicRepos(int publicRepos) {
        this.publicRepos = publicRepos;
    }
}
