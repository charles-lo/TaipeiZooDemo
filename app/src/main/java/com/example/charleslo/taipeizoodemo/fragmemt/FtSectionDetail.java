package com.example.charleslo.taipeizoodemo.fragmemt;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.charleslo.taipeizoodemo.MainActivity;
import com.example.charleslo.taipeizoodemo.R;
import com.example.charleslo.taipeizoodemo.model.Cache;
import com.example.charleslo.taipeizoodemo.model.SectionDetailAdapter;
import com.example.charleslo.taipeizoodemo.model.ZooPlant;
import com.example.charleslo.taipeizoodemo.model.ZooSection;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.util.ArrayList;

public class FtSectionDetail extends Fragment {
    private static String ARG_PARAM = "param_key";
    private int mParam;
    private MainActivity mActivity;

    private RecyclerView mRecyclerView;
    private SimpleDraweeView mSimpleDraweeView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (MainActivity) context;
        mParam = getArguments().getInt(ARG_PARAM);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_section_detail, container, false);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.recycler_view);
        mSimpleDraweeView = root.findViewById(R.id.drawee_view);
        ZooSection data = Cache.getInstance().m_ZooSectionList.get(mParam);
        final ImageRequest imageRequest =
                ImageRequestBuilder.newBuilderWithSource(Uri.parse(data.m_PicUrl))
                        .build();
        mSimpleDraweeView.setImageRequest(imageRequest);

        ((TextView) root.findViewById(R.id.info)).setText(data.m_Info);
        if (TextUtils.isEmpty((data.m_Memo))) {
            ((TextView) root.findViewById(R.id.memo)).setText(getString(R.string.no_rest_info));
        } else {
            ((TextView) root.findViewById(R.id.memo)).setText(data.m_Info);
        }
        ((TextView) root.findViewById(R.id.category)).setText(data.m_Category);
        TextView tvUrl = ((TextView) root.findViewById(R.id.url));
        tvUrl.setText(
                Html.fromHtml(
                        "<a href=\"" + data.m_URL + "\">" + tvUrl.getText() + "</a> "));
        tvUrl.setMovementMethod(LinkMovementMethod.getInstance());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(mActivity);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        ArrayList<ZooPlant> tmp = new ArrayList<>();
        for (ZooPlant item : Cache.getInstance().m_ZooPlantList) {
            if (item.m_Location.contains(data.m_Name)) {
                tmp.add(item);
            }
        }
        Cache.getInstance().m_sectionTmpPlantList = tmp;
        mAdapter = new SectionDetailAdapter(Cache.getInstance().m_sectionTmpPlantList);
        mRecyclerView.setAdapter(mAdapter);
        mActivity.setToobarTitle(data.m_Name);
        mActivity.setToobarIcon(R.drawable.arrow_back);
        return root;
    }

    public static FtSectionDetail newInstance(int idx) {
        FtSectionDetail frag = new FtSectionDetail();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_PARAM, idx);
        frag.setArguments(bundle);
        return frag;
    }
}


