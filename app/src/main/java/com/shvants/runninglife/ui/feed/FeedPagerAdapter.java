package com.shvants.runninglife.ui.feed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shvants.runninglife.R;
import com.shvants.runninglife.ui.model.UiMoveModel;
import com.shvants.runninglife.ui.view.RunMoveView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import static java.lang.Boolean.FALSE;

public class FeedPagerAdapter extends RecyclerView.Adapter<FeedPagerAdapter.ViewHolder> {

    private final List<UiMoveModel> moves;

    public FeedPagerAdapter(final Context context, final List<UiMoveModel> moves) {
        this.moves = moves;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {

        if (viewType == ViewType.MOVE) {
            return new ViewHolder(new RunMoveView(parent.getContext()));
        } else {
            final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            return new ViewHolder(inflater.inflate(R.layout.layout_progress, parent, FALSE));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

    }

    @Override
    public int getItemCount() {
        return moves.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(final View view) {
            super(view);
        }
    }

    @IntDef({ViewType.MOVE, ViewType.LOADING})
    @Retention(RetentionPolicy.SOURCE)
    @interface ViewType {

        int MOVE = 0;
        int LOADING = 1;
    }
}
