package mj.jyoti.com.groovewithmj;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

///////////////////////////////////////////////////////////////////////////////////
/////                      Created by Jyoti Bansal                          ///////
/////                                                                       ///////
/////        This class creates a recyclerview that defines the layout      ///////
///                 of the cardview and uses the MainActivity.class         ///////
/////               to display the data in the cardview.                    ///////
/////         This class also send the data to SongActivity.class           ///////
/////                                                                       ///////
///////////////////////////////////////////////////////////////////////////////////

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder> {

    //public static final String KEY_NAME = "trackName";
    //public static final String KEY_IMAGE = "image";

    private List<musicData> data;
    private Context context;

    public RecyclerviewAdapter(List<musicData> data, Context context){
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final musicData songList = data.get(position);
        holder.trackName.setText(songList.getName());

        Picasso.get().load(songList.getImage()).into(holder.image);

        holder.linearLayout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                musicData obj = data.get(position);
                Intent intent = new Intent(context,SongActivity.class);
                intent.putExtra("KEY_NAME",obj.getName());
                intent.putExtra("KEY_IMAGE",obj.getImage());
                intent.putExtra("KEY_ARTISTNAME",obj.getArtistName());
                intent.putExtra("KEY_PRICE",obj.getTrackPrice());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView trackName;
        public ImageView image;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView){
            super(itemView);

            trackName = (TextView) itemView.findViewById(R.id.collName_textView);
            image = (ImageView) itemView.findViewById((R.id.song_imageView));
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);

        }

    }


}
