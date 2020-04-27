package com.example.myapplication.ui.Routine.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Model.Entities.RoutineEntity;
import com.example.myapplication.R;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class AdapterCardRoutine extends RecyclerView.Adapter<AdapterCardRoutine.ViewHolderCardRoutine> {

    List<RoutineEntity> routineList;
    private Context context;
    public AdapterCardRoutine(List<RoutineEntity>listaRutinas,Context context){
        this.routineList=listaRutinas;
        this.context=context;
    }
    @NonNull
    @Override
    public ViewHolderCardRoutine onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_routines,parent,false);//parent para ajustarlo al layout del padre
        return new ViewHolderCardRoutine(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCardRoutine holder, int position) {
        /*URL url = null;
        Bitmap bmp;
        try {
            url = new URL(routineList.get(position).getLink_preview());
           bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            holder.image.setImageBitmap(bmp);
        } catch (Exception e) {
            System.out.println("el error es "+e);
        }*/

        Uri uri=Uri.parse(routineList.get(position).getLink_preview());
        holder.video.setVideoURI(uri);
        //holder.video.start();
        MediaController mediaController=new MediaController(this.context);
        // holder.video.setBackgroundResource(R.drawable.ic_launcher_background);
        mediaController.setAnchorView(holder.video);
        holder.video.setMediaController(mediaController);

        //holder.video.requestFocus();

        holder.title.setText(routineList.get(position).getName());
        holder.description.setText(routineList.get(position).getDescription());
        holder.type.setText(routineList.get(position).getType().getName());

    }

    @Override
    public int getItemCount() {
        return this.routineList.size();
    }

    public class ViewHolderCardRoutine extends RecyclerView.ViewHolder {
     VideoView video;
     TextView description,title,type;
        public ViewHolderCardRoutine(@NonNull View itemView) {
            super(itemView);
            video=(VideoView)itemView.findViewById(R.id.imgRoutine);
            description=(TextView)itemView.findViewById(R.id.txtdescriptionRoutine);
            title=(TextView)itemView.findViewById(R.id.txtnameRoutine);
            type=(TextView)itemView.findViewById(R.id.txtTypeRoutine);
        }

    }
}
