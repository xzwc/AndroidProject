package com.zhy.authproject.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zhy.authproject.R;
/**
 * Created by zhanghaoye on 11/7/16.
 */

public class ProgressDialogHandler extends Handler {

    public static final int SHOW_PROGRESS_DIALOG = 1;
    public static final int DISMISS_PROGRESS_DIALOG = 2;

    private Dialog pd;

    private Context context;
    private boolean cancelable;
    private ProgressCancelListener mProgressCancelListener;

    AnimationDrawable drawable;
    ImageView imageView;

    Dialog loadingDialog;
    public ProgressDialogHandler(Context context, ProgressCancelListener mProgressCancelListener,
                                 boolean cancelable) {
        super();
        this.context = context;
        this.mProgressCancelListener = mProgressCancelListener;
        this.cancelable = cancelable;
    }


    private void dismissProgressDialog(){
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }


    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SHOW_PROGRESS_DIALOG:
                initProgressDialog();
                break;
            case DISMISS_PROGRESS_DIALOG:
                dismissProgressDialog();
                break;
        }
    }

    private void initProgressDialog() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.loader_base_loading, null);        // 得到加载view
        FrameLayout layout = (FrameLayout) v.findViewById(R.id.id_loading_and_retry);

        imageView = (ImageView) v.findViewById(R.id.imageView);  // 加载布局
        drawable = (AnimationDrawable) imageView.getBackground();
        drawable.start();

         loadingDialog = new Dialog(context, R.style.loading_dialog); // 创建自定义样式dialog
        // loadingDialog.setCancelable(true);// 不可以用"返回键"取消
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        loadingDialog.show();
    }
}
