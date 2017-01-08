package no.zredna.rxgithub.view.loading;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import no.zredna.rxgithub.R;

public class LoadingView extends FrameLayout {

    @BindView(R.id.imageViewGitHubLogo)
    ImageView imageViewGitHubLogo;

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public LoadingView(@NonNull Context context) {
        super(context);
    }

    public LoadingView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadingView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void startAnimation() {
        imageViewGitHubLogo.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.rotate));
    }
}
