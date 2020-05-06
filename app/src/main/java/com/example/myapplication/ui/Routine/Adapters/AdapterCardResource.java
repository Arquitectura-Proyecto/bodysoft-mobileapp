package com.example.myapplication.ui.Routine.Adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apollographqlandroid.GetResourcesByIdRoutineMutation;
import com.example.myapplication.Model.Store.RoutineStore;
import com.example.myapplication.R;

import java.util.List;

public class AdapterCardResource extends RecyclerView.Adapter<AdapterCardResource.ViewHolderCardResource> {



    private List<GetResourcesByIdRoutineMutation.Resource> resourceList;
    private NavController navigationController;
    private Context context;

    public AdapterCardResource(List<GetResourcesByIdRoutineMutation.Resource> resourceList, NavController navigationController, Context context) {
        this.resourceList = resourceList;
        this.navigationController = navigationController;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterCardResource.ViewHolderCardResource onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_resource,parent,false);//parent para ajustarlo al layout del padre
        //view.setOnClickListener(this);
        return new ViewHolderCardResource(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCardResource.ViewHolderCardResource holder, int position) {
        Uri uri=Uri.parse(resourceList.get(position).getLink());
        holder.video.setVideoURI(uri);
        //holder.video.start();
        MediaController mediaController=new MediaController(this.context);
        // holder.video.setBackgroundResource(R.drawable.ic_launcher_background);
        mediaController.setAnchorView(holder.video);
        holder.video.setMediaController(mediaController);

        //holder.video.requestFocus();

        holder.title.setText(resourceList.get(position).getTitle());
        holder.description.setText(resourceList.get(position).getDescription());
        holder.type.setText(resourceList.get(position).getType().getName());
        //holder.btnGoToRoutineInformationFragment.setOnClickListener(new AdapterCardRoutine.GoToRoutineInformationFragmentLister(routineList.get(position)));

    }

    @Override
    public int getItemCount() {
        return this.resourceList.size();
    }

    public class ViewHolderCardResource extends RecyclerView.ViewHolder {
        VideoView video;
        TextView description,title,type;
        Button btnGoToRegisterResourceFragment;
        public ViewHolderCardResource(@NonNull View itemView) {
            super(itemView);
            video=(VideoView)itemView.findViewById(R.id.imgResource);
            description=(TextView)itemView.findViewById(R.id.txtdescriptionResource);
            title=(TextView)itemView.findViewById(R.id.txtnameResource);
            type=(TextView)itemView.findViewById(R.id.txtTypeResource);
//            btnGoToRegisterResourceFragment=(Button)itemView.findViewById(R.id.btnGoToEditResource);
            //btnGoToRoutineInformationFragment.setOnClickListener(listener);
        }
    }
    private class GoToRoutineInformationFragmentLister implements View.OnClickListener{
        private GetResourcesByIdRoutineMutation.Resource resource;

        public GoToRoutineInformationFragmentLister(GetResourcesByIdRoutineMutation.Resource resource) {
            this.resource = resource;
        }

        @Override
        public void onClick(View v) {
            //System.out.println("THE ROUTINE IS "+routine.getDescription());
            RoutineStore routineStore= ViewModelProviders.of((FragmentActivity) context).get(RoutineStore.class);
           // routineStore.setRoutineInformation(routine);
            navigationController.navigate(R.id.fromRootRoutineFragment_to_routineInformationFragment);
        }
    }
}
