package lt.markmerkk.mortar_flow;

import android.os.Parcelable;
import android.support.annotation.NonNull;

import flow.KeyParceler;

/**
 * @author mariusmerkevicius
 * @since 2016-11-24
 */

public class BasicParceler implements KeyParceler {

    @NonNull
    @Override public Parcelable toParcelable(@NonNull Object key) {
        return (Parcelable) key;
    }

    @NonNull @Override public Object toKey(@NonNull Parcelable parcelable) {
        return parcelable;
    }
}
