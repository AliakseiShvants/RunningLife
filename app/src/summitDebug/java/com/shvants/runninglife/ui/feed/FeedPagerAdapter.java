package com.shvants.runninglife.ui.feed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shvants.runninglife.R;
import com.shvants.runninglife.ui.custom.RunMoveView;
import com.shvants.runninglife.ui.model.MoveModelUi;
import com.shvants.runninglife.ui.model.UserModelUi;
import com.shvants.runninglife.utils.IAdapter;
import com.shvants.runninglife.utils.service.IService;
import com.shvants.runninglife.utils.service.RunMoveService;
import com.shvants.runninglife.utils.service.UserService;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import static java.lang.Boolean.FALSE;

public class FeedPagerAdapter extends RecyclerView.Adapter<FeedPagerAdapter.ViewHolder>
        implements IAdapter {

    private final LayoutInflater inflater;
    private final UserModelUi user;
    private final List<MoveModelUi> moves;
    private final IService<MoveModelUi> moveService;
    private final IService<UserModelUi> userService;

    private boolean isShowLastViewAsLoading = FALSE;

    public FeedPagerAdapter(final Context context) {
        userService = new UserService();
        moveService = new RunMoveService();

        this.user = userService.getEntity(0);
        this.moves = moveService.getEntities();

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent,
                                         final int viewType) {
        if (viewType == ViewType.MOVE) {
            return new ViewHolder(inflater.inflate(R.layout.adapter_item_run_move, parent, FALSE));
        } else {
            return new ViewHolder(inflater.inflate(R.layout.layout_progress, parent, FALSE));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder,
                                 final int position) {

        if (getItemViewType(position) == ViewType.MOVE) {

            final MoveModelUi move = moves.get(position);

            ((RunMoveView) holder.itemView).setView(user, move);

        }
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

    @Override
    public void setShowLastViewAsLoading(final boolean isShow) {
        if (isShow != isShowLastViewAsLoading) {
            isShowLastViewAsLoading = isShow;
            notifyDataSetChanged();
        }
    }

    @Override
    public void addItems(@NotNull final List<? extends MoveModelUi> result) {
        moves.addAll(result);
        notifyDataSetChanged();
    }

    @IntDef({ViewType.MOVE, ViewType.LOADING})
    @Retention(RetentionPolicy.SOURCE)
    @interface ViewType {
        int MOVE = 0;
        int LOADING = 1;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(final View view) {
            super(view);

            view.findViewById(R.id.moveCard).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {
                    showFullParameter(view);
                }
            });
        }

        private void showFullParameter(final View view) {
            view.findViewById(R.id.moveBaseParameter).setVisibility(View.GONE);
            view.findViewById(R.id.moveFullParameter).setVisibility(View.VISIBLE);
        }
    }
}
