package mj.jyoti.com.groovewithmj;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

///////////////////////////////////////////////////////////////////////////////////
/////                      Created by Jyoti Bansal                          ///////
/////                                                                       ///////
/////        This class creates a new activity to display the additional    ///////
/////                   details of the song track clicked                   ///////
/////                                                                       ///////
/////                                                                       ///////
///////////////////////////////////////////////////////////////////////////////////

public class SongActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songdetails);

        ImageView img = (ImageView) findViewById(R.id.song_image);
        TextView track = (TextView) findViewById(R.id.trackName_textView);
        TextView artist = (TextView) findViewById(R.id.artistName_textView);
        TextView price = (TextView) findViewById(R.id.trackPrice_textView);

        Intent intent = getIntent();
        final String trackName = intent.getStringExtra("KEY_NAME");
        final String artistName = intent.getStringExtra("KEY_ARTISTNAME");
        final String trackPrice = intent.getStringExtra("KEY_PRICE");
        String image = intent.getStringExtra("KEY_IMAGE");

        Picasso.get().load(image).into(img);

        track.setText("Track Name: " + trackName);
        artist.setText("Artist Name : " + artistName);
        price.setText("Price: " + trackPrice);
    }
}
