package lt.markmerkk.mortar_flow;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import flow.Dispatcher;
import flow.Flow;
import flow.Traversal;
import flow.TraversalCallback;

/**
 * @author mariusmerkevicius
 * @since 2016-11-24
 */

public class BasicDispatcher implements Dispatcher {

    private final Activity activity;

    BasicDispatcher(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void dispatch(
            @NonNull Traversal traversal,
            @NonNull TraversalCallback callback
    ) {
        Log.d("BasicDispatcher", "dispatching " + traversal);
        Object destKey = traversal.destination.top();

        ViewGroup frame = (ViewGroup) activity.findViewById(R.id.activity_main);

        // We're already showing something, clean it up.
        if (frame.getChildCount() > 0) {
            final View currentView = frame.getChildAt(0);

            // Save the outgoing view state.
            if (traversal.origin != null) {
                traversal.getState(traversal.origin.top()).save(currentView);
            }

            // Short circuit if we would just be showing the same view again.
            final Object currentKey = Flow.getKey(currentView);
            if (destKey.equals(currentKey)) {
                callback.onTraversalCompleted();
                return;
            }

            frame.removeAllViews();
        }

        @LayoutRes final int layout;
        if (destKey instanceof MainScreen) {
            layout = R.layout.main_screen;
        } else if(destKey instanceof NextScreen) {
            layout = R.layout.next_screen;
        } else {
            throw new AssertionError("Unrecognized screen " + destKey);
        }

        View incomingView = LayoutInflater.from(traversal.createContext(destKey, activity)) //
                .inflate(layout, frame, false);

        frame.addView(incomingView);
        traversal.getState(traversal.destination.top()).restore(incomingView);

        callback.onTraversalCompleted();
    }
}
