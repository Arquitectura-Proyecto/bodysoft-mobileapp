package com.example.myapplication.ui.Routine.Adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apollographqlandroid.GetRoutinesByIdOwnerQuery;
import com.example.myapplication.Model.Entities.RoutineEntity;
import com.example.myapplication.Model.Store.RoutineStore;
import com.example.myapplication.R;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class AdapterCardRoutine extends RecyclerView.Adapter<AdapterCardRoutine.ViewHolderCardRoutine>  {

    List<GetRoutinesByIdOwnerQuery.Routine> routineList;
    NavController navigationController;
    private Context context;
    public AdapterCardRoutine(List<GetRoutinesByIdOwnerQuery.Routine>listaRutinas, Context context,NavController navController){
        this.routineList=listaRutinas;
        this.context=context;
        this.navigationController=navController;
    }
    @NonNull
    @Override
    public ViewHolderCardRoutine onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_routines,parent,false);//parent para ajustarlo al layout del padre
        //view.setOnClickListener(this);
        return new ViewHolderCardRoutine(view);
    }
//    cardSuggestedRoutine
//    imgResource
//    txtnameResource
//    txtdescriptionResource
//    txtTypeResource
//    btnGoToRoutinePreview

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

        try {
            holder.video.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    return true;//evita la ventana de error si no puede reproducirlo
                }
            });

            Uri uri=Uri.parse(routineList.get(position).getLinkPreview());
            holder.video.setVideoURI(uri);

            MediaController mediaController=new MediaController(this.context);
            mediaController.setMediaPlayer(holder.video);

            mediaController.setAnchorView(holder.video);
            holder.video.setMediaController(mediaController);

            holder.video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {


                    holder.placeholder.setVisibility(View.GONE);//el place holder de video que no se puede reproducir se hace invisible si se puede reproducir el video
                }
            });

        }catch (Exception e){}


        //holder.video.requestFocus();

        holder.title.setText(routineList.get(position).getName());
        holder.description.setText(routineList.get(position).getDescription());
        holder.type.setText(routineList.get(position).getType().getName());
        holder.btnGoToRoutineInformationFragment.setOnClickListener(new GoToRoutineInformationFragmentLister(routineList.get(position)));
    }

    @Override
    public int getItemCount() {
        return this.routineList.size();
    }



    public class ViewHolderCardRoutine extends RecyclerView.ViewHolder {
        View placeholder ;
     VideoView video;
     TextView description,title,type;
     Button btnGoToRoutineInformationFragment;
        public ViewHolderCardRoutine(@NonNull View itemView) {
            super(itemView);
            video=(VideoView)itemView.findViewById(R.id.imgRoutine);
            description=(TextView)itemView.findViewById(R.id.txtdescriptionRoutine);
            title=(TextView)itemView.findViewById(R.id.txtnameRoutine);
            type=(TextView)itemView.findViewById(R.id.txtTypeRoutine);
            btnGoToRoutineInformationFragment=(Button)itemView.findViewById(R.id.btnGoToInformationRoutine);
            placeholder = (View) itemView.findViewById(R.id.placeholder);
            //btnGoToRoutineInformationFragment.setOnClickListener(listener);
        }

    }
    private class GoToRoutineInformationFragmentLister implements View.OnClickListener{
        private GetRoutinesByIdOwnerQuery.Routine routine;

        public GoToRoutineInformationFragmentLister(GetRoutinesByIdOwnerQuery.Routine routine) {
            this.routine = routine;
        }

        @Override
        public void onClick(View v) {
            //System.out.println("THE ROUTINE IS "+routine.getDescription());
            RoutineStore routineStore= ViewModelProviders.of((FragmentActivity) context).get(RoutineStore.class);
            routineStore.setRoutineInformation(routine);
            navigationController.navigate(R.id.fromRootRoutineFragment_to_routineInformationFragment);
        }
    }
}
