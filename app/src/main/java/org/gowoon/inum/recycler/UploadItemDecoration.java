package org.gowoon.inum.recycler;

import android.content.Context;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class UploadItemDecoration extends RecyclerView.ItemDecoration {
    private int MARGIN_WIDTH;
    private int MARGIN_HEIGHT;

    public UploadItemDecoration(Context context) {

        MARGIN_WIDTH = dpToPx(context, 9.6);
        MARGIN_HEIGHT = dpToPx(context, 11.5);
    }

    @Override
    public void getItemOffsets
            (@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int position = parent.getChildAdapterPosition(view);
        int itemCount = state.getItemCount();

        //상하 설정
        if(position == 0 || position == 1) {
            // 첫번 째 줄 아이템
            outRect.top = 0;
            outRect.bottom = MARGIN_HEIGHT;
        } else {
            outRect.bottom = MARGIN_HEIGHT;
        }

        // spanIndex = 0 -> 왼쪽
        // spanIndex = 1 -> 오른쪽

        GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) view.getLayoutParams();
        int spanIndex = lp.getSpanIndex();

        if(spanIndex == 0) {
            //왼쪽 아이템
            outRect.left = 0;
            outRect.right = MARGIN_WIDTH;

        } else if(spanIndex == 1) {
            //오른쪽 아이템
            outRect.left = MARGIN_WIDTH;
            outRect.right = 0;
        }


        outRect.top = MARGIN_HEIGHT;
        outRect.right = MARGIN_WIDTH;
        outRect.bottom = MARGIN_HEIGHT;
        outRect.left = MARGIN_WIDTH;

    }

    // dp -> pixel 단위로 변경
    private int dpToPx(Context context, double dp) {

        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (float) dp, context.getResources().getDisplayMetrics());
    }
}
