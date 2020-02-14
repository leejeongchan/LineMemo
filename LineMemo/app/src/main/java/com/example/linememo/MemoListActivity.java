package com.example.linememo;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.List;

public class MemoListActivity extends AppCompatActivity {
    private MemoViewModel memoViewModel;
    private RecyclerView recyclerView;
    private MemoAdapter mAdapter;

    private int divider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_list);

        initSetting();
        initRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.memo_list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.write:
                intent = new Intent(this, MemoEditActivity.class);
                intent.putExtra("mode", MemoEditActivity.CREATE_MODE);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void initSetting() {
        recyclerView = findViewById(R.id.recycler);

        Toolbar myToolbar = findViewById(R.id.toolbar);
        myToolbar.setTitle("LINE MEMO");
        myToolbar.setTitleTextColor(getResources().getColor(R.color.colorIconGreen));
        setSupportActionBar(myToolbar);

        memoViewModel = new ViewModelProvider(this).get(MemoViewModel.class);
        divider = AndroidUtil.dpToPx(this, 10);
    }

    void initRecyclerView() {
        // 레이아웃 사이즈 설정
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(itemDecoration);
        mAdapter = new MemoAdapter(this);

        // 사용하는 레이아웃 = StaggeredGridLayoutManager
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

        memoViewModel.getAll().observe(this, new Observer<List<Memo>>() {
            @Override
            public void onChanged(List<Memo> memos) {
                mAdapter.setData(memos);
            }
        });
    }

    private RecyclerView.ItemDecoration itemDecoration = new RecyclerView.ItemDecoration() {
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            if (parent.getPaddingLeft() != divider) {
                parent.setPadding(divider, divider, divider, divider);
                parent.setClipToPadding(false);
            }
            outRect.top = divider;
            outRect.bottom = divider;
            outRect.left = divider;
            outRect.right = divider;
        }
    };
}
