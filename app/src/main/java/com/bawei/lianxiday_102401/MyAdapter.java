package com.bawei.lianxiday_102401;

import android.content.Context;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/24.
 */
public class MyAdapter extends RecyclerView.Adapter implements TypeUtil{
    private final Context context;
    private final CommunityBean communityBean;
    private final ArrayList<Pair<Integer, Object>> superData;

    public MyAdapter(Context context, CommunityBean communityBean) {
        this.context = context;
        this.communityBean = communityBean;

        superData=new ArrayList<Pair<Integer,Object>>();
        //添加问答结伴部分
        superData.add(new Pair<Integer, Object>(COMMUNITY_TOP, communityBean));

        initOtherData();
    }

    private void initOtherData() {
        for (int i = 0; i < communityBean.data.forum_list.size(); i++) {

            communityBean.data.forum_list.get(i).group.get(0).setHasTitle(true,communityBean.data.forum_list.get(i).name);//设置有标头
            for (int j = 0; j < communityBean.data.forum_list.get(i).group.size(); j++) {
                j++;
                Pair<Object, Object> objectObjectPair;//两个数据的集合  超过两个可以用list集合
                if (j == communityBean.data.forum_list.get(i).group.size()) {
                    objectObjectPair = wrapData(communityBean.data.forum_list.get(i).group.get(j - 1), null);
                } else {
                    objectObjectPair = wrapData(communityBean.data.forum_list.get(i).group.get(j - 1), communityBean.data.forum_list.get(i).group.get(j));
                }

                superData.add(new Pair<Integer, Object>(COMMUNITY_OHTER, objectObjectPair));

            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case COMMUNITY_TOP:
                return new ViewHolder_01(View.inflate(context, R.layout.item_community_top, null),context);
            case COMMUNITY_OHTER:
                return new ViewHolder_02(View.inflate(context, R.layout.item_community_other, null),context);
        }

        return new ViewHolder_02(View.inflate(context, R.layout.item_community_other, null),context);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (superData.get(position).first){
            case COMMUNITY_TOP:
                ((ViewHolder_01)holder).initData(communityBean);
                break;
            case COMMUNITY_OHTER:
                ((ViewHolder_02)holder).initData(superData.get(position));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return superData.size();
    }

    public Pair<Object, Object> wrapData(Object f, Object s) {
        return new Pair<Object, Object>(f, s);
    }

    @Override
    public int getItemViewType(int position) {
        // TODO Auto-generated method stub
        return superData.get(position).first;
    }
}
