package no.zredna.rxgithub.model.github;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused") // Fields are set by Gson when deserializing
public class User {
    private String login;
    private String name;

    @SerializedName("public_repos")
    private int publicRepos;

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public int getPublicRepos() {
        return publicRepos;
    }

}
