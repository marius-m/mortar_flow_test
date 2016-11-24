package lt.markmerkk.mortar_flow;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author mariusmerkevicius
 * @since 2016-11-24
 */

public class NextScreen implements Parcelable {
    @Override public void writeToParcel(Parcel dest, int flags) {
    }

    @Override public int describeContents() {
        return 0;
    }

    public static final Creator<NextScreen> CREATOR = new Creator<NextScreen>() {
        @Override public NextScreen createFromParcel(Parcel in) {
            return new NextScreen();
        }

        @Override public NextScreen[] newArray(int size) {
            return new NextScreen[size];
        }
    };

    @Override public boolean equals(Object o) {
        return o != null && o instanceof NextScreen;
    }
}
