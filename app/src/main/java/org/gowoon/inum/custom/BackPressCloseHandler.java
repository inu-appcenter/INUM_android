package org.gowoon.inum.custom;

import android.app.Activity;
import android.widget.Toast;

import static android.widget.Toast.makeText;

public class BackPressCloseHandler {
    private long backKeyPressedTime = 0;
    private Activity activity;

    private Toast toast;

    public BackPressCloseHandler(Activity activity) {
        this.activity = activity;
    }
        public void onBackPressed() {
            long FINISH_INTERVAL_TIME = 2000;
            if (System.currentTimeMillis() > backKeyPressedTime + FINISH_INTERVAL_TIME) {
            backKeyPressedTime = System.currentTimeMillis();
            showToast();
            return;
            }

        if (System.currentTimeMillis() <= backKeyPressedTime + FINISH_INTERVAL_TIME) {
            toast.cancel();
            activity.finish();
        }
    }

    private void showToast() {
        toast = makeText(activity,"뒤로가기를 한번 더 누르면 종료됩니다.",Toast.LENGTH_SHORT);
        toast.show();
    }

}
