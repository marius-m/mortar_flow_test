package lt.markmerkk.mortar_flow;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import javax.inject.Inject;

import flow.Flow;

/**
 * @author mariusmerkevicius
 * @since 2016-11-17
 */

public class MainView extends LinearLayout {

    @Inject
    MainPresenterDagger.Presenter presenter;
    private Button textView;

    public MainView(Context context) {
        super(context);
        init(context);
    }

    public MainView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MainView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MainView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    //region Init

    private void init(Context context) {
        DaggerService.<MainPresenterDagger.Component>getDaggerComponent(context).inject(this);
    }

    @Override protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        MainScreen screen = Flow.getKey(this);
        textView = (Button) findViewById(R.id.main_button);
        textView.setText(screen.getTitle());
        textView.setOnClickListener(new OnClickListener() {
            @Override public void onClick(View v) {
                presenter.incrementSerial();
            }
        });
        
        presenter.takeView(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        presenter.dropView(this);
        super.onDetachedFromWindow();
    }

    //endregion

    public void show(String message) {
        textView.setText(message);
    }

}
