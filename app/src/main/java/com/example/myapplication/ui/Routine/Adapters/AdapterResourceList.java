package com.example.myapplication.ui.Routine.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apollographqlandroid.GetResourcesByIdRoutineMutation;
import com.example.myapplication.Model.Store.RoutineStore;
import com.example.myapplication.R;
import com.example.myapplication.ui.GlobalState;

import java.util.List;

public class AdapterResourceList extends RecyclerView.Adapter<AdapterResourceList.ViewHolderResourceList> {
    private List<GetResourcesByIdRoutineMutation.Resource> resourceList;
    private NavController navigationController;
    private Context context;

    public AdapterResourceList(List<GetResourcesByIdRoutineMutation.Resource> resourceList, NavController navigationController, Context context) {
        this.resourceList = resourceList;
        this.navigationController = navigationController;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterResourceList.ViewHolderResourceList onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_user_list_resources, parent, false);//parent para ajustarlo al layout del padre
        //view.setOnClickListener(this);
        return new ViewHolderResourceList(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterResourceList.ViewHolderResourceList holder, int position) {
        holder.txtTitleResource.setText(this.resourceList.get(position).getTitle());
        holder.goToResourceVideo.setOnClickListener(new GoToVideo(this.resourceList.get(position)));
    }

    @Override
    public int getItemCount() {
        return this.resourceList.size();
    }

    public class ViewHolderResourceList extends RecyclerView.ViewHolder {

        TextView txtTitleResource;
        Button goToResourceVideo;

        public ViewHolderResourceList(@NonNull View itemView) {
            super(itemView);
            this.txtTitleResource = (TextView) itemView.findViewById(R.id.txtTitleUserResource);
            this.goToResourceVideo = (Button) itemView.findViewById(R.id.btnViewResource);


        }
    }

    private class GoToVideo implements View.OnClickListener {
        private GetResourcesByIdRoutineMutation.Resource resource;

        public GoToVideo(GetResourcesByIdRoutineMutation.Resource resource) {
            this.resource = resource;
        }

        @Override
        public void onClick(View v) {
            //System.out.println("THE ROUTINE IS " + routine.getDescription());
            RoutineStore routineStore = ViewModelProviders.of((FragmentActivity) context).get(RoutineStore.class);
            routineStore.setResourceInformation(resource);

            navigationController.navigate(R.id.action_userMyResourcesFragment_to_userRoutineVideoFragment);
        }

    }


}
