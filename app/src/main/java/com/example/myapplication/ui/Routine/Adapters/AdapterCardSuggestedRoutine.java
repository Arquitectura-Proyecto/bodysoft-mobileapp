package com.example.myapplication.ui.Routine.Adapters;

import android.app.Activity;
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
import com.example.apollographqlandroid.GetRoutinesByIdTypeQuery;
import com.example.myapplication.Model.Entities.RoutineEntity;
import com.example.myapplication.Model.Store.RoutineStore;
import com.example.myapplication.R;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class AdapterCardSuggestedRoutine extends RecyclerView.Adapter<AdapterCardSuggestedRoutine.ViewHolderCardSuggestedRoutine> {

    List<GetRoutinesByIdTypeQuery.Routine> routineList;
    NavController navigationController;
    private Context context;

    public AdapterCardSuggestedRoutine(List<GetRoutinesByIdTypeQuery.Routine> routineList, NavController navigationController, Context context) {
        this.routineList = routineList;
        this.navigationController = navigationController;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderCardSuggestedRoutine onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_suggested_routines, parent, false);//parent para ajustarlo al layout del padre
        //view.setOnClickListener(this);
        return new ViewHolderCardSuggestedRoutine(view);
    }
//    cardSuggestedRoutine
//    imgResource
//    txtnameResource
//    txtdescriptionResource
//    txtTypeResource
//    btnGoToRoutinePreview

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCardSuggestedRoutine holder, int position) {

        Uri uri = Uri.parse(routineList.get(position).getLinkPreview());
        holder.video.setVideoURI(uri);
        //holder.video.start();
        MediaController mediaController = new MediaController(this.context);
        // holder.video.setBackgroundResource(R.drawable.ic_launcher_background);
        mediaController.setAnchorView(holder.video);
        holder.video.setMediaController(mediaController);

        //holder.video.requestFocus();

        holder.title.setText(routineList.get(position).getName());
        holder.description.setText(routineList.get(position).getDescription());
        holder.type.setText(routineList.get(position).getType().getName());
        holder.btnGotoRoutinePreview.setOnClickListener(new GotoRoutinePreviewListener(routineList.get(position)));
    }

    @Override
    public int getItemCount() {
        return this.routineList.size();
    }


    public class ViewHolderCardSuggestedRoutine extends RecyclerView.ViewHolder {
        VideoView video;
        TextView description, title, type;
        Button btnGotoRoutinePreview;

        public ViewHolderCardSuggestedRoutine(@NonNull View itemView) {
            super(itemView);
            video = (VideoView) itemView.findViewById(R.id.imgRoutine);
            description = (TextView) itemView.findViewById(R.id.txtdescriptionRoutine);
            title = (TextView) itemView.findViewById(R.id.txtnameRoutine);
            type = (TextView) itemView.findViewById(R.id.txtTypeRoutine);

            btnGotoRoutinePreview = (Button) itemView.findViewById(R.id.btnGotoRoutinePreview);

        }

    }

    private class GotoRoutinePreviewListener implements View.OnClickListener {
        private GetRoutinesByIdTypeQuery.Routine routine;

        public GotoRoutinePreviewListener(GetRoutinesByIdTypeQuery.Routine routine) {
            this.routine = routine;
        }

        @Override
        public void onClick(View v) {

            RoutineStore routineStore = ViewModelProviders.of((FragmentActivity) context).get(RoutineStore.class);
            routineStore.setInformationRoutinePreview(routine);
            navigationController.navigate(R.id.action_userRoutineListFragment_to_userRoutinePreviewFragment);

        }

    }
}
