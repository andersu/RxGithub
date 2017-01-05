package no.zredna.rxgithub.view.github.list;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import no.zredna.rxgithub.R;
import no.zredna.rxgithub.model.github.Repo;

public class RepoItemView extends LinearLayout {

    @BindView(R.id.textViewRepoName)
    public TextView textViewRepoName;

    @BindView(R.id.textViewRepoCreated)
    public TextView textViewRepoCreated;

    public RepoItemView(Context context) {
        super(context);
    }

    public RepoItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RepoItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public void bindTo(Repo repo) {
        textViewRepoName.setText(repo.getName());
        textViewRepoCreated.setText(repo.getCreatedFormattedString());
    }
}
