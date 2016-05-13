package edu.calpoly.android.intentcomposer;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import edu.calpoly.android.intentcomposer.databinding.ActivityIntentComposerBinding;

public class IntentComposerActivity extends AppCompatActivity {

    IntentComposerViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityIntentComposerBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_intent_composer);

        mViewModel = (IntentComposerViewModel) getLastCustomNonConfigurationInstance();
        if (mViewModel == null) {
            mViewModel = new IntentComposerViewModel();
        }

        binding.setViewModel(mViewModel);

        binding.clicky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(IntentComposerActivity.this, "Click", Toast.LENGTH_LONG).show();
            }
        });

        binding.email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(IntentComposerActivity.this, "Click 2", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return mViewModel;
    }
}
