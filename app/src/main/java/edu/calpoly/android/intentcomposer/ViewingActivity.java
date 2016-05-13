package edu.calpoly.android.intentcomposer;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import edu.calpoly.android.intentcomposer.databinding.ActivityViewingBinding;

public class ViewingActivity extends AppCompatActivity {

    ViewingViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ActivityViewingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_viewing);

        mViewModel = (ViewingViewModel) getLastCustomNonConfigurationInstance();
        if (mViewModel == null) {
            mViewModel = new ViewingViewModel();
        }

        binding.setViewModel(mViewModel);

    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return mViewModel;
    }
}
