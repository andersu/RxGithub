package no.zredna.rxgithub.view.github.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import no.zredna.rxgithub.R;
import no.zredna.rxgithub.model.github.GitHubInformation;
import no.zredna.rxgithub.model.github.Repo;
import no.zredna.rxgithub.model.github.User;

public class GitHubAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int USER_HEADER_POSITION = 0;
    private static final int REPOS_HEADER_POSITION = 2;

    private static final String USER_TITLE = "User";
    private static final String REPOS_TITLE = "Repos";

    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_USER = 1;
    private static final int VIEW_TYPE_REPO = 2;

    private GitHubInformation gitHubInformation;

    public void setGitHubInformation(GitHubInformation gitHubInformation) {
        this.gitHubInformation = gitHubInformation;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_HEADER:
                HeaderItemView headerItemView = (HeaderItemView) LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_header, parent, false);
                return new ViewHolderHeader(headerItemView);
            case VIEW_TYPE_USER:
                UserItemView userItemView = (UserItemView) LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_user, parent, false);
                return new ViewHolderUser(userItemView);
            default: // VIEW_TYPE_REPO
                RepoItemView repoItemView = (RepoItemView) LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_repo, parent, false);

                return new ViewHolderRepo(repoItemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_HEADER:
                bindViewHolderHeader((ViewHolderHeader) holder, position);
                break;
            case VIEW_TYPE_USER:
                bindViewHolderUser((ViewHolderUser) holder);
                break;
            case VIEW_TYPE_REPO:
                bindViewHolderRepo((ViewHolderRepo) holder, position);
        }
    }

    private void bindViewHolderHeader(ViewHolderHeader holder, int position) {
        if (position == 0) {
            holder.headerItemView.bindTo(USER_TITLE);
        } else {
            holder.headerItemView.bindTo(REPOS_TITLE);
        }
    }

    private void bindViewHolderRepo(ViewHolderRepo holder, int position) {
        Repo repo = gitHubInformation.getRepos().get(position - 1); // - 1 because position 0 is the user item
        holder.repoItemView.bindTo(repo);
    }

    private void bindViewHolderUser(ViewHolderUser holder) {
        User user = gitHubInformation.getUser();
        holder.userItemView.bindTo(user, gitHubInformation.getWaitedMillisText());
    }

    @Override
    public int getItemCount() {
        if (gitHubInformation == null) {
            return 0;
        }

        return gitHubInformation.getRepos().size() + 1; // + 1 for user item
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
            case 2:
                return VIEW_TYPE_HEADER;
            case 1:
                return VIEW_TYPE_USER;
            default:
                return VIEW_TYPE_REPO;
        }
    }

    static class ViewHolderHeader extends RecyclerView.ViewHolder {
        private HeaderItemView headerItemView;

        public ViewHolderHeader(HeaderItemView headerItemView) {
            super(headerItemView);
            this.headerItemView = headerItemView;
        }
    }

    static class ViewHolderUser extends RecyclerView.ViewHolder {
        private UserItemView userItemView;

        public ViewHolderUser(UserItemView userItemView) {
            super(userItemView);

            this.userItemView = userItemView;
        }
    }

    static class ViewHolderRepo extends RecyclerView.ViewHolder {
        private RepoItemView repoItemView;

        public ViewHolderRepo(RepoItemView repoItemView) {
            super(repoItemView);

            this.repoItemView = repoItemView;
        }
    }
}
