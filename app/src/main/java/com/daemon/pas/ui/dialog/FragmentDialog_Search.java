package com.daemon.pas.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.daemon.pas.utils.ToastUtil;

/**
 * Created by Daemon on 2015/12/14.
 */
public class FragmentDialog_Search extends DialogFragment {

    private AlertDialog dialog;

    public interface SearchContentListener {
        void onSearchComplete(String key);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final EditText editText = new EditText(getActivity());


        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity())
                .setTitle("输入要搜索的图片信息")
                .setView(editText)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("搜索", null);


        dialog = alertDialog.create();

        dialog.show();

        if(dialog.getButton(AlertDialog.BUTTON_POSITIVE)!=null) {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (TextUtils.isEmpty(editText.getText().toString().trim())) {
                        ToastUtil.showToast("输入点什么吧");
                        return;
                    } else {
                        SearchContentListener searchContentListener = (SearchContentListener) getActivity();
                        searchContentListener.onSearchComplete(editText.getText().toString().trim());
                        dialog.dismiss();
                    }
                }
            });
        }

        return dialog;
    }



}
