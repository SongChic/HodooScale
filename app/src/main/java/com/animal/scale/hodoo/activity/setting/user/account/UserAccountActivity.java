package com.animal.scale.hodoo.activity.setting.user.account;

import android.content.Context;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

import com.animal.scale.hodoo.R;
import com.animal.scale.hodoo.base.BaseActivity;
import com.animal.scale.hodoo.databinding.ActivityUserAccountBinding;
import com.animal.scale.hodoo.domain.ActivityInfo;
import com.animal.scale.hodoo.domain.User;

import java.util.List;

public class UserAccountActivity extends BaseActivity<UserAccountActivity> implements UserAccountIn.View {

    ActivityUserAccountBinding binding;

    UserAccountGridAdapter adapter;

    UserAccountIn.Presenter presenter;

    public static final int USER_REGIST = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_account);
        binding.setActivity(this);
        binding.setActivityInfo(new ActivityInfo("펫 관리"));
        super.setToolbarColor();

        presenter = new UserAccountPresenter(this);
        presenter.initUserData(getApplicationContext());
        presenter.getData();
    }

    @Override
    protected BaseActivity<UserAccountActivity> getActivityClass() {
        return UserAccountActivity.this;
    }


    /*public void showPopup(final String SSID) {
        AlertDialog.Builder builder = new AlertDialog.Builder(WifiSearchActivity.this);
        builder.setTitle(SSID);

        LayoutInflater inflater = this.getLayoutInflater();
        View viewInflated = inflater.inflate(R.layout.dialog_text_inpu_password, null);

        final EditText input = (EditText) viewInflated.findViewById(R.id.input);
        builder.setView(viewInflated);

        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String password = input.getText().toString();
                connect(SSID, password);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
*/
    @Override
    public void showPopup(String title, String message) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_text_inpu_email, null);
        final EditText input = (EditText) view.findViewById(R.id.input);
        super.showBasicOneBtnPopup(title, null)
                .setView(view)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                showToast(input.getText().toString());
                                dialog.dismiss();

                            }
                        }
                )
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showToast(input.getText().toString());
                        dialog.dismiss();
                    }
                }).show();
    }

    @Override
    public void setAdapter(List<User> data) {
        adapter = new UserAccountGridAdapter(UserAccountActivity.this, data);
        binding.userGridView.setAdapter(adapter);
        binding.userGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == USER_REGIST) {
                   showPopup("+친구초대", "이메일");
                }
            }
        });

    }
}
