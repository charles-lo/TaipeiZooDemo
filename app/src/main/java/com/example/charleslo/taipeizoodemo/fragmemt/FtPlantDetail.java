package com.example.charleslo.taipeizoodemo.fragmemt;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.charleslo.taipeizoodemo.MainActivity;
import com.example.charleslo.taipeizoodemo.R;
import com.example.charleslo.taipeizoodemo.model.Cache;
import com.example.charleslo.taipeizoodemo.model.ZooPlant;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

public class FtPlantDetail extends Fragment {
    private static String ARG_PARAM = "param_key";
    private int mParam;
    private MainActivity mActivity;

    private SimpleDraweeView mSimpleDraweeView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (MainActivity) context;
        mParam = getArguments().getInt(ARG_PARAM);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_plant_detail, container, false);
        mSimpleDraweeView = root.findViewById(R.id.drawee_view);
        ZooPlant data = Cache.getInstance().m_sectionTmpPlantList.get(mParam);
        final ImageRequest imageRequest =
                ImageRequestBuilder.newBuilderWithSource(Uri.parse(data.m_Pic01URL))
                        .build();
        mSimpleDraweeView.setImageRequest(imageRequest);

        ((TextView) root.findViewById(R.id.name)).setText(data.m_NameCh);
        ((TextView) root.findViewById(R.id.name_latin)).setText(data.m_NameLatin);
        ((TextView) root.findViewById(R.id.alsoknown)).setText(data.m_AlsoKnow);
        ((TextView) root.findViewById(R.id.brief)).setText(data.m_Brief);
        ((TextView) root.findViewById(R.id.feature)).setText(data.m_Feature);
        ((TextView) root.findViewById(R.id.functionApplication)).setText(data.m_FunctionApplication);
        ((TextView) root.findViewById(R.id.update)).setText(data.m_Update);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(mActivity);
        mActivity.setToobarTitle(data.m_NameCh);
        mActivity.setToobarIcon(R.drawable.arrow_back);
        return root;
    }

    public static FtPlantDetail newInstance(int idx) {
        FtPlantDetail frag = new FtPlantDetail();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_PARAM, idx);
        frag.setArguments(bundle);
        return frag;
    }
}


