package edu.calpoly.android.intentcomposer;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import edu.calpoly.android.intentcomposer.databinding.ActivityViewingBinding;

public class ViewingActivity extends AppCompatActivity {

    public static final String EXTRA_TEXT = "extra_text";

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

        Intent i = getIntent();

        if (!i.hasExtra(EXTRA_TEXT)) {
            throw new IllegalStateException("HEY DUMMY, WE NEED AN EXTRA TEXT!!!!!!");
        }

        mViewModel.name.set(i.getStringExtra(EXTRA_TEXT));

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                // set the type to 'email'
                emailIntent.setType("vnd.android.cursor.dir/email");
                String to[] = {mViewModel.name.get()};
                emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
                // the mail subject
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject Text");
                emailIntent.putExtra(Intent.EXTRA_TEXT, TEXT);
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });

        binding.web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW);
                webIntent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.steadfastinnovation.android.projectpapyrus"));
                startActivity(webIntent);
            }
        });

    }


    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return mViewModel;
    }


    private static final String TEXT = "Nunc cursus vestibulum condimentum. Aliquam vestibulum lorem sapien, scelerisque cursus nisi ultrices bibendum. Etiam varius orci eget neque venenatis cursus. Ut fringilla iaculis ligula vitae tempor. Integer consequat, est vitae pulvinar pulvinar, est ligula tincidunt odio, a pretium mi leo id ligula. Vestibulum luctus neque non nunc semper, eget blandit nunc dictum. Suspendisse a velit quis justo auctor imperdiet. Donec eleifend quam metus, ultricies gravida eros pulvinar ac. Pellentesque dolor odio, scelerisque ac suscipit ut, congue in elit. Etiam vitae pellentesque justo, in aliquam velit. Cras sagittis risus enim, quis molestie eros ullamcorper vitae. Ut in tellus dignissim, auctor justo vel, sollicitudin neque. Sed quis justo erat.\n" +
            "\n" +
            "Mauris dictum gravida enim at eleifend. Donec eros ante, sodales mollis ante eget, finibus mollis nibh. Ut quis tortor nunc. Suspendisse sagittis interdum tincidunt. Fusce facilisis, metus at consectetur ultrices, diam metus hendrerit ante, ac aliquet risus velit sed dolor. Proin eu lacinia sem, non volutpat ex. Vestibulum maximus urna vel sem blandit tristique. Donec ornare id massa et tempus. Quisque ullamcorper eu dolor eu pretium. Nullam fermentum non est sit amet dictum. Nullam sapien erat, mattis sed magna quis, aliquet pellentesque ex. Duis aliquam pretium tincidunt. Donec fermentum vestibulum posuere. Donec accumsan tristique justo at suscipit. In tempor mollis interdum. Duis sodales nec metus at pharetra.\n" +
            "\n" +
            "Praesent sed lacus dictum, feugiat purus vel, convallis leo. Morbi hendrerit tellus ullamcorper nisl elementum, in tincidunt felis placerat. Integer pulvinar mi lacus, a euismod nulla aliquet vel. In elementum mauris et quam tempor, non vulputate eros tristique. Sed varius porta leo vitae cursus. Phasellus sit amet dignissim lorem. Etiam eu varius nibh. Mauris ultricies, lectus id bibendum malesuada, nibh ipsum convallis lectus, in scelerisque libero diam ultrices tellus. Nullam cursus nisl eu mauris pharetra, eget dictum diam sagittis. Donec tellus diam, elementum vel massa quis, congue ultrices sapien. Nullam eleifend ligula at augue maximus dictum. Fusce non tempus orci. Morbi at sodales justo. Etiam id felis sed nulla dignissim volutpat.\n" +
            "\n" +
            "Nunc cursus vel dolor eu pulvinar. Proin sed molestie nisi. In commodo lectus quis neque maximus, vitae rhoncus libero egestas. Praesent tincidunt est id finibus lobortis. Donec tempus id felis eget euismod. Nunc eu erat nisi. Sed suscipit erat dolor, eu tristique justo pretium consequat.";
}
