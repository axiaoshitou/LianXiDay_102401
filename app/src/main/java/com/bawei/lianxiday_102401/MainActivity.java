package com.bawei.lianxiday_102401;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRV;
    private String url="http://open.qyer.com/qyer/bbs/entry?client_id=qyer_android&client_secret=9fcaae8aefc4f9ac4915";

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            CommunityBean communityBean = (CommunityBean) msg.obj;
            mRV.setAdapter(new MyAdapter(MainActivity.this,communityBean));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //初始化控件
        initView();

        //得到数据
        getDatas();
    }

    private void getDatas() {

        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson=new Gson();
                CommunityBean communityBean = gson.fromJson(string, CommunityBean.class);
                Message msg=Message.obtain();
                msg.obj=communityBean;
                handler.sendMessage(msg);
            }
        });
    }

    private void initView() {
        mRV = (RecyclerView) findViewById(R.id.rcv);
        mRV.setLayoutManager(new LinearLayoutManager(this));
    }
}
