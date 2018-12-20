package com.example.charleslo.taipeizoodemo.fragmemt;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.charleslo.taipeizoodemo.MainActivity;
import com.example.charleslo.taipeizoodemo.R;
import com.example.charleslo.taipeizoodemo.model.Cache;
import com.example.charleslo.taipeizoodemo.model.SectionListAdapter;

public class FragmentSection extends Fragment {
    private static String ARG_PARAM = "param_key";
    private String mParam;
    private MainActivity mActivity;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (MainActivity) context;
        mParam = getArguments().getString(ARG_PARAM);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_section, container, false);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(mActivity);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new SectionListAdapter(Cache.getInstance().m_ZooSectionList);
        mRecyclerView.setAdapter(mAdapter);
        mActivity.setToobarTitle(R.string.taipei_zoo);
        mActivity.setToobarIcon(R.drawable.menu);
        return root;
    }

    public static FragmentSection newInstance(String str) {
        FragmentSection frag = new FragmentSection();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PARAM, str);
        frag.setArguments(bundle);
        return frag;
    }
}


