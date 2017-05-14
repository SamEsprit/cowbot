package eprit.tn.cowbot.Utils;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Sami on 03/05/2017.
 */
public interface OnStartDragListener {

    /**
     * Called when a view is requesting a start of a drag.
     *
     * @param viewHolder The holder of the view to drag.
     */
    void onStartDrag(RecyclerView.ViewHolder viewHolder);
}