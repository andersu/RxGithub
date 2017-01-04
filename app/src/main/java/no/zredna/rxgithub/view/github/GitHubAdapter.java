package no.zredna.rxgithub.view.github;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
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
                View headerView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_header, parent, false);
                return new ViewHolderHeader(headerView);
            case VIEW_TYPE_USER:
                View userView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_user, parent, false);
                return new ViewHolderUser(userView);
            default: // VIEW_TYPE_REPO
                View repoView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_repo, parent, false);

                return new ViewHolderRepo(repoView);
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
            holder.textViewTitle.setText(USER_TITLE);
        } else {
            holder.textViewTitle.setText(REPOS_TITLE);
        }
    }

    private void bindViewHolderRepo(ViewHolderRepo holder, int position) {
        Repo repo = gitHubInformation.getRepos().get(position - 1); // - 1 because position 0 is the user item

        holder.textViewRepoName.setText(repo.getName());
        holder.textViewRepoCreated.setText(repo.getCreatedFormattedString());
    }

    private void bindViewHolderUser(ViewHolderUser holder) {
        User user = gitHubInformation.getUser();

        holder.textViewUsername.setText(user.getLogin());
        holder.textViewName.setText(user.getName());
        holder.textViewPublicRepos.setText(String.valueOf(user.getPublicRepos()));
        holder.textViewWaitingTime.setText(gitHubInformation.getWaitedMillisText());
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
        @BindView(R.id.textViewTitle)
        public TextView textViewTitle;

        public ViewHolderHeader(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolderUser extends RecyclerView.ViewHolder {
        @BindView(R.id.textViewUsername)
        TextView textViewUsername;

        @BindView(R.id.textViewName)
        TextView textViewName;

        @BindView(R.id.textViewPublicRepos)
        TextView textViewPublicRepos;

        @BindView(R.id.textViewWaitingTime)
        TextView textViewWaitingTime;

        public ViewHolderUser(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolderRepo extends RecyclerView.ViewHolder {
        @BindView(R.id.textViewRepoName)
        public TextView textViewRepoName;

        @BindView(R.id.textViewRepoCreated)
        public TextView textViewRepoCreated;

        public ViewHolderRepo(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
