package lt.markmerkk.mortar_flow;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author mariusmerkevicius
 * @since 2016-11-24
 */

public class NextView extends LinearLayout {
    public NextView(Context context) {
        super(context);
    }

    public NextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public NextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override protected void onFinishInflate() {
        super.onFinishInflate();
        TextView nameView = new TextView(getContext());
        addView(nameView);
        nameView.setText("Test test");
    }

}
