package no.zredna.rxgithub.view.github.list;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import no.zredna.rxgithub.R;

public class HeaderItemView extends FrameLayout {

    @BindView(R.id.textViewTitle)
    public TextView textViewTitle;

    public HeaderItemView(@NonNull Context context) {
        super(context);
    }

    public HeaderItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HeaderItemView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public void bindTo(String title) {
        textViewTitle.setText(title);
    }
}
