package com.shvants.runninglife.ui.feed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shvants.runninglife.R;


import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import static java.lang.Boolean.FALSE;

//public class FeedPagerAdapter
//        extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {
//
//    private final Context context;
//    private final LayoutInflater inflater;
//    private final UserModelUi user;
//    private final List<MoveModelUi> moves;
//    private final IService<MoveModelUi> moveService;
//    private final IService<UserModelUi> userService;
//
//    private boolean isShowLastViewAsLoading = FALSE;
//
//    public FeedPagerAdapter(final Context context) {
//        userService = new UserService();
//        moveService = new RunMoveService();
//
//        this.context = context;
//        this.user = userService.getEntity(0);
//        this.moves = moveService.getEntities();
//
//        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent,
//                                         final int viewType) {
//        if (viewType == ViewType.MOVE) {
//            return new ViewHolder(new RunMoveView(parent.getContext()));
//        } else {
//            return new ViewHolder(inflater.inflate(R.layout.layout_progress, parent, FALSE));
//        }
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull final ViewHolder holder,
//                                 final int position) {
//
//        if (getItemViewType(position) == ViewType.MOVE) {
//
//            final MoveModelUi move = moves.get(position);
//
//            ((RunMoveView) holder.itemView).setView(user, move);
//        }
//    }
//
//    @ViewType
//    @Override
//    public int getItemViewType(final int position) {
//
//        if (position < moves.size()) {
//            return ViewType.MOVE;
//        } else {
//            return ViewType.LOADING;
//        }
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return moves.size();
//    }
//
//    @Override
//    public void setShowLastViewAsLoading(final boolean isShow) {
//        if (isShow != isShowLastViewAsLoading) {
//            isShowLastViewAsLoading = isShow;
//            notifyDataSetChanged();
//        }
//    }
//
//    @Override
//    public void addItems(@NotNull final List<? extends MoveModelUi> result) {
//        moves.addAll(result);
//        notifyDataSetChanged();
//    }
//
//    class ViewHolder extends RecyclerView.ViewHolder {
//
//        ViewHolder(final View view) {
//            super(view);
//        }
//    }
//
//    @IntDef({ViewType.MOVE, ViewType.LOADING})
//    @Retention(RetentionPolicy.SOURCE)
//    @interface ViewType {
//        int MOVE = 0;
//        int LOADING = 1;
//    }
//}
