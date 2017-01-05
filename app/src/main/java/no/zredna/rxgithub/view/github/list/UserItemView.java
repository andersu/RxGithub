package no.zredna.rxgithub.view.github.list;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import no.zredna.rxgithub.R;
import no.zredna.rxgithub.model.github.User;

public class UserItemView extends ConstraintLayout {

    @BindView(R.id.textViewUsername)
    TextView textViewUsername;

    @BindView(R.id.textViewName)
    TextView textViewName;

    @BindView(R.id.textViewPublicRepos)
    TextView textViewPublicRepos;

    @BindView(R.id.textViewWaitingTime)
    TextView textViewWaitingTime;

    public UserItemView(Context context) {
        super(context);
    }

    public UserItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UserItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public void bindTo(User user, String waitedTime) {
        textViewUsername.setText(user.getLogin());
        textViewName.setText(user.getName());
        textViewPublicRepos.setText(String.valueOf(user.getPublicRepos()));
        textViewWaitingTime.setText(waitedTime);
    }
}
