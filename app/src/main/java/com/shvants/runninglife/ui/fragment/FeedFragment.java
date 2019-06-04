package com.shvants.runninglife.ui.fragment;

//@Deprecated
//public class FeedFragment implements FeedContract.Fragment {
//
//    private static FeedFragment instance;
//    private RecyclerView recyclerView;
//    private RecyclerView.LayoutManager layoutManager;
//    private FeedPagerAdapter adapter;
//    private boolean isLoading = false;
//
//    private FeedContract.Presenter presenter =
//            new FeedPresenter(this, DbRepository.Companion.getInstance());
//
//    private FeedFragment() {
//
//    }
//
//    public static FeedFragment getInstance() {
//
//        if (instance == null) {
//            instance = new FeedFragment();
//        }
//
//        return instance;
//    }
//
//    @Override
//    public void onCreate(@Nullable final Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        ((MainActivity) getActivity()).setActionBarTitle(TITLE);
//    }
//
//    @Override
//    public View onCreateView(@NonNull final LayoutInflater inflater,
//                             final ViewGroup container,
//                             final Bundle savedInstanceState) {
//
//        final View feedView = inflater.inflate(getLayoutResId(), container, FALSE);
//
//        recyclerView = feedView.findViewById(R.id.recyclerView);
//        layoutManager = new LinearLayoutManager(getActivity());
//        recyclerView.setLayoutManager(layoutManager);
//
//        adapter = new FeedPagerAdapter(getContext());
//        recyclerView.setAdapter(adapter);
//
//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(@NonNull final RecyclerView recyclerView, final int dx,
//                                   final int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//            }
//        });
//
//
////        loadMoreItems(ZERO, RecyclerViewScrollListener.PAGE_SIZE);
//
////        new ItemTouchHelper(new ItemTouchCallback(recyclerView, adapter))
////                .attachToRecyclerView(recyclerView);
//
//        recyclerView.setItemAnimator(new DefaultItemAnimator() {
//            @Override
//            public boolean animateMove(final RecyclerView.ActivityViewHolder holder,
//                                       final int fromX,
//                                       final int fromY,
//                                       final int toX,
//                                       final int toY) {
//                return super.animateMove(holder, fromX, fromY, toX, toY);
//            }
//        });
//
//        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
//                DividerItemDecoration.VERTICAL));
//
//        return feedView;
//    }
//
//    @Override
//    public void loadMoreItems(final int start, final int end) {
//        isLoading = TRUE;
//        adapter.setShowLastViewAsLoading(TRUE);
//        moveService.getEntities(start, end, new ICallback<List<MoveModelUi>>() {
//
//            @Override
//            public void onResult(final List<MoveModelUi> result) {
//                adapter.addItems(result);
//                isLoading = FALSE;
//            }
//        });
//    }
//
//    @NotNull
//    @Override
//    public RecyclerView.LayoutManager getLayoutManager() {
//        return layoutManager;
//    }
//
//    @Override
//    public int getLayoutResId() {
//        return R.layout.fragment_feed;
//    }
//
//    @Override
//    public boolean isLoading() {
//        return false;
//    }
//}
