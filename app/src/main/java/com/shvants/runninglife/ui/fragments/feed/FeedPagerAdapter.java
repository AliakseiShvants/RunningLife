package com.shvants.runninglife.ui.fragments.feed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shvants.runninglife.R;
import com.shvants.runninglife.ui.model.UiMoveModel;
import com.shvants.runninglife.ui.model.UiUserModel;
import com.shvants.runninglife.ui.view.RunMoveView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import static java.lang.Boolean.FALSE;

public class FeedPagerAdapter extends RecyclerView.Adapter<FeedPagerAdapter.ViewHolder> {

    //    private MoveItemClickListener itemClickListener;
//    private final LayoutInflater inflater;
    private final Context context;
    private final UiUserModel user;
    private final List<UiMoveModel> moves;

    public FeedPagerAdapter(final Context context,
                            final UiUserModel user,
                            final List<UiMoveModel> moves) {
        this.context = context;
        this.user = user;
        this.moves = moves;
//        this.itemClickListener = (MoveItemClickListener) context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent,
                                         final int viewType) {

        if (viewType == ViewType.MOVE) {
            return new ViewHolder(new RunMoveView(context));
        } else {
            return
                    new ViewHolder(LayoutInflater.from(context)
                            .inflate(R.layout.layout_progress, parent, FALSE));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder,
                                 final int position) {

        final UiMoveModel move = moves.get(position);

        ((RunMoveView) holder.itemView).setView(user, move);
//        if (getItemViewType(position) == ViewType.MOVE){
//
//            final UiMoveModel move = moves.get(position);
//
//            ((RunMoveView)holder.itemView).setView(user, move);
//        }
    }

    @ViewType
    @Override
    public int getItemViewType(final int position) {

        if (position < moves.size()) {
            return ViewType.MOVE;
        } else {
            return ViewType.LOADING;
        }
    }


    @Override
    public int getItemCount() {
        return moves.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(final View view) {
            super(view);

            final TextView userName = view.findViewById(R.id.moveUserFullName);
        }
    }

    @IntDef({ViewType.MOVE, ViewType.LOADING})
    @Retention(RetentionPolicy.SOURCE)
    @interface ViewType {
        int MOVE = 0;
        int LOADING = 1;
    }
}
