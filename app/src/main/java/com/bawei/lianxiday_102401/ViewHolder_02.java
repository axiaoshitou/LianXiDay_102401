package com.bawei.lianxiday_102401;

import android.content.Context;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 类型二对应的holder：：城市列表
 * 
 *
 */
public class ViewHolder_02 extends BaseViewHolder{


    View divider;//分割线
    TextView tvTitle;
    ImageView ivSecond;
    ImageView ivFirst;
    View divider_02;//长线
    View divider_03;//短线
    RelativeLayout rl_item_community_second;
    RelativeLayout rl_item_community_first;
    TextView tv_item_community_title_first;
    TextView tv_item_community_title_second;
    TextView tv_item_community_num_first;
    TextView tv_item_community_num_second;

    CommunityBean.DataEntity.ForumListEntity.GroupEntity first;
    CommunityBean.DataEntity.ForumListEntity.GroupEntity second;

    public ViewHolder_02(View itemView,Context context) {
    	super(itemView, context);
        divider=itemView.findViewById(R.id.item_community_divider);
        tvTitle= (TextView) itemView.findViewById(R.id.tv_item_community_title);
        divider_02=itemView.findViewById(R.id.item_community_divider02);
        ivSecond= (ImageView) itemView.findViewById(R.id.iv_item_community_second);
        ivFirst= (ImageView) itemView.findViewById(R.id.iv_item_community_first);
        divider_03=itemView.findViewById(R.id.item_community_divider03);
        rl_item_community_second= (RelativeLayout) itemView.findViewById(R.id.rl_item_community_second);
        rl_item_community_first= (RelativeLayout) itemView.findViewById(R.id.rl_item_community_first);
        tv_item_community_title_first= (TextView) itemView.findViewById(R.id.tv_item_community_title_first);
        tv_item_community_title_second= (TextView) itemView.findViewById(R.id.tv_item_community_title_second);
        tv_item_community_num_first= (TextView) itemView.findViewById(R.id.tv_item_community_num_first);
        tv_item_community_num_second= (TextView) itemView.findViewById(R.id.tv_item_community_num_second);

    }

    @Override
    public void initData(Object bean) {
        //super.initData(bean);

        Pair<Integer, Object> originbean= (Pair<Integer, Object>) bean;
        final Pair<Object, Object> objectObjectPair= (Pair<Object, Object>) originbean.second;

        if(objectObjectPair!=null){

            first = (CommunityBean.DataEntity.ForumListEntity.GroupEntity) objectObjectPair.first;
            //填充网络数据
            ImageUtil.newInstance(context).displayImage(first.photo,ivFirst);
            tv_item_community_title_first.setText(first.name);
            tv_item_community_num_first.setText(first.total_threads+"个帖子");
            if(first.hasTitle){//需要显示标头
                divider.setVisibility(View.VISIBLE);
                tvTitle.setVisibility(View.VISIBLE);
                tvTitle.setText(first.title);
                divider_02.setVisibility(View.VISIBLE);
                divider_03.setVisibility(View.GONE);

            }else{//不需要显示标头
                divider.setVisibility(View.GONE);
                tvTitle.setVisibility(View.GONE);
                divider_02.setVisibility(View.INVISIBLE);
                divider_03.setVisibility(View.VISIBLE);

            }
            if(objectObjectPair.second==null){//第二个数据为空
               // ivSecond.setVisibility(View.GONE);
                if(rl_item_community_second!=null)
                rl_item_community_second.setVisibility(View.INVISIBLE);
            }else {//第二个数据不为空

                second = (CommunityBean.DataEntity.ForumListEntity.GroupEntity) objectObjectPair.second;
                if(rl_item_community_second!=null)
                rl_item_community_second.setVisibility(View.VISIBLE);
                //填充网络数据
                ImageUtil.newInstance(context).displayImage(second.photo, ivSecond);
                tv_item_community_title_second.setText(second.name);
                tv_item_community_num_second.setText(second.total_threads+"个帖子");
            }


        }




        //添加点击事件
        rl_item_community_second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(second!=null){

                	Toast.makeText(context,second.name, Toast.LENGTH_SHORT).show();
                }

            }
        });

        if(rl_item_community_first!=null)
        rl_item_community_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Toast.makeText(context,first.name, Toast.LENGTH_SHORT).show();
            }
        });


    }
}
