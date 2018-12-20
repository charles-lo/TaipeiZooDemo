package com.example.charleslo.taipeizoodemo.model;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.charleslo.taipeizoodemo.R;
import com.example.charleslo.taipeizoodemo.event.ActivityEvent;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Random;

import static com.example.charleslo.taipeizoodemo.model.Constant.AE_PLANT_DETAIL;
import static com.example.charleslo.taipeizoodemo.model.Constant.AE_SECTION_DETAIL;

public class SectionDetailAdapter extends RecyclerView.Adapter<SectionDetailAdapter.SectionHolder> {
    private ArrayList<ZooPlant> m_DataSet;
    private Context m_Ctx;

    public static class SectionHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private static final Random sRandom = new Random();
        public View m_Root;
        private final SimpleDraweeView mSimpleDraweeView;
        public TextView m_Name;
        public TextView m_Info;

        public SectionHolder(View v) {
            super(v);
            // Locate and cache view references:
            m_Root = v;
            m_Name = itemView.findViewById(R.id.name);
            m_Info = itemView.findViewById(R.id.info);
            mSimpleDraweeView = itemView.findViewById(R.id.drawee_view);
            mSimpleDraweeView.getHierarchy().setPlaceholderImage(new ColorDrawable(sRandom.nextInt()));
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int test = getLayoutPosition();
                    Log.d("", test + "");
                    EventBus.getDefault().post(new ActivityEvent(AE_PLANT_DETAIL, getLayoutPosition()));
                }
            });
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public SectionDetailAdapter(ArrayList<ZooPlant> zooPlantArrayList) {
        m_DataSet = zooPlantArrayList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public SectionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lt_section_detail, parent, false);
        m_Ctx = parent.getContext();
        SectionHolder vh = new SectionHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(SectionHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.m_Name.setText(m_DataSet.get(position).m_NameCh);
        holder.m_Info.setText(m_DataSet.get(position).m_AlsoKnow);
        final ImageRequest imageRequest =
                ImageRequestBuilder.newBuilderWithSource(Uri.parse(m_DataSet.get(position).m_Pic01URL))
                        .build();
        holder.mSimpleDraweeView.setImageRequest(imageRequest);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return m_DataSet.size();
    }
}
