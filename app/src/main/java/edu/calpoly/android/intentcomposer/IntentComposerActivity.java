package edu.calpoly.android.intentcomposer;

import android.content.Intent;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import edu.calpoly.android.intentcomposer.databinding.ActivityIntentComposerBinding;

public class IntentComposerActivity extends AppCompatActivity {

    private static final int CONTACT_PICKER_RESULT = 0x01;

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
                Intent i = new Intent(IntentComposerActivity.this, ViewingActivity.class);
                i.putExtra(ViewingActivity.EXTRA_TEXT, mViewModel.name.get());
                startActivity(i);
            }
        });

        binding.email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent contactPickerIntent = new Intent(Intent.ACTION_PICK);
                contactPickerIntent.setType(ContactsContract.CommonDataKinds.Email.CONTENT_TYPE);
                startActivityForResult(contactPickerIntent, CONTACT_PICKER_RESULT);
            }
        });
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return mViewModel;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CONTACT_PICKER_RESULT) {
                String email = "";
                Cursor cursor = null;
                try {
                    Uri contactUri = data.getData();
                    String[] projection = new String[]{ContactsContract.CommonDataKinds.Email.ADDRESS};
                    cursor = getContentResolver().query(contactUri, projection,
                            null, null, null);
                    // If the cursor returned is valid, get the phone number
                    if (cursor != null && cursor.moveToFirst()) {
                        int numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS);
                        email = cursor.getString(numberIndex);
                    }
                } catch (Exception e) {
                    Log.e("ERROR", "Failed to get email data", e);
                } finally {
                    if (cursor != null) {
                        cursor.close();
                    }
                    //   Toast.makeText(this, name, Toast.LENGTH_LONG).show();
                    mViewModel.name.set(email);
                }
            }
        } else {
            Toast.makeText(IntentComposerActivity.this, "NOT OK", Toast.LENGTH_SHORT).show();
        }
    }


















}
