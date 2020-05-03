package com.example.myapplication.Model.Store;

import android.widget.ListView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.apollographqlandroid.GetAllTypeRoutineQuery;
import com.example.apollographqlandroid.GetRequestByIdRoutineQuery;
import com.example.apollographqlandroid.GetResourcesByIdRoutineMutation;
import com.example.apollographqlandroid.GetRoutinesByIdOwnerQuery;
import com.example.apollographqlandroid.GetRoutinesByIdTypeQuery;
import com.example.apollographqlandroid.GetUserRoutineByIdUserQuery;
import com.example.myapplication.Model.Entities.RoutineEntity;

import java.util.List;

public class RoutineStore extends ViewModel {

  private MutableLiveData<GetRequestByIdRoutineQuery.Request>InformationRequest;
  
  
  private MutableLiveData<List<GetRequestByIdRoutineQuery.Request>>listRequest;
  
  
  
    private MutableLiveData<List<GetUserRoutineByIdUserQuery.UserRoutine>>userRoutineList;
    private MutableLiveData<GetUserRoutineByIdUserQuery.UserRoutine>userRoutine;
    private MutableLiveData<List<GetRoutinesByIdTypeQuery.Routine>> listSuggestedRoutines;
    private MutableLiveData<List<GetResourcesByIdRoutineMutation.Resource>> listResources;
    private MutableLiveData<GetRoutinesByIdOwnerQuery.Routine> InformationRoutine;
    private MutableLiveData<List<GetAllTypeRoutineQuery.TypeRoutine>> listTypeRoutines;
    private MutableLiveData<List<GetRoutinesByIdOwnerQuery.Routine>> listRoutine;
    private MutableLiveData<GetRoutinesByIdTypeQuery.Routine> InformationRoutinePreview;
    private MutableLiveData<GetResourcesByIdRoutineMutation.Resource>resourceInformation;
    public RoutineStore() {
        this.InformationRoutinePreview = new MutableLiveData<>();
        this.listRoutine = new MutableLiveData<>();
        this.listTypeRoutines = new MutableLiveData<>();
        this.InformationRoutine = new MutableLiveData<>();
        this.listResources = new MutableLiveData<>();
        this.listSuggestedRoutines = new MutableLiveData<>();
        this.userRoutineList=new MutableLiveData<>();
        this.userRoutine=new MutableLiveData<>();
        this.resourceInformation= new MutableLiveData<>();
      this.InformationRequest=new MutableLiveData<>();
      
        this.listRequest=new MutableLiveData<>();
    }

    public LiveData<GetRoutinesByIdTypeQuery.Routine> getInformationRoutinePreview() {
        return this.InformationRoutinePreview;
    }

    public void setInformationRoutinePreview(GetRoutinesByIdTypeQuery.Routine informationRoutinePreview) {
        this.InformationRoutinePreview.setValue(informationRoutinePreview);
    }

    public void setRoutines(List<GetRoutinesByIdOwnerQuery.Routine> listRoutine) {

        this.listRoutine.setValue(listRoutine);
    }

    public LiveData<List<GetRoutinesByIdOwnerQuery.Routine>> getListRoutine() {
        return this.listRoutine;
    }

    public void setTypeRoutines(List<GetAllTypeRoutineQuery.TypeRoutine> typeRoutines) {
        this.listTypeRoutines.setValue(typeRoutines);
    }

    public LiveData<List<GetAllTypeRoutineQuery.TypeRoutine>> getTypeRoutines() {
        return this.listTypeRoutines;
    }

    public void setRoutineInformation(GetRoutinesByIdOwnerQuery.Routine routine) {
        this.InformationRoutine.setValue(routine);
    }

    public LiveData<GetRoutinesByIdOwnerQuery.Routine> getInformationRoutine() {
        return this.InformationRoutine;
    }

    ;

    public void setListResources(List<GetResourcesByIdRoutineMutation.Resource> resources) {
        this.listResources.setValue(resources);
    }

    public LiveData<List<GetResourcesByIdRoutineMutation.Resource>> getListResources() {
        return this.listResources;
    }
    public void setInformationRequest(GetRequestByIdRoutineQuery.Request request){
        this.InformationRequest.setValue(request);
    }
    public LiveData<GetRequestByIdRoutineQuery.Request>getInformationRequest(){
        return this.InformationRequest;
    }


    public void setListSuggestedRoutines(List<GetRoutinesByIdTypeQuery.Routine> newlistSuggestedRoutines) {
        this.listSuggestedRoutines.setValue(newlistSuggestedRoutines);
    }

    public LiveData<List<GetRoutinesByIdTypeQuery.Routine>> getListSuggestedRoutines() {
        return this.listSuggestedRoutines;
    }

    public LiveData<GetUserRoutineByIdUserQuery.UserRoutine>getUserRoutine(){

        return this.userRoutine;

    };
    public void setUserRoutine(GetUserRoutineByIdUserQuery.UserRoutine userRoutine){
        this.userRoutine.setValue(userRoutine);
    }
    public LiveData<List<GetUserRoutineByIdUserQuery.UserRoutine>>getUserRoutineList(){
        return this.userRoutineList;
    }
    public void setUserRoutineList(List<GetUserRoutineByIdUserQuery.UserRoutine> userRoutines){
        this.userRoutineList.setValue(userRoutines);
    }
    //public LiveData<>
  public LiveData<List<GetRequestByIdRoutineQuery.Request>> getListRequest() {
        return listRequest;
    }
  public void setListRequest(List<GetRequestByIdRoutineQuery.Request> listRequest) {
        this.listRequest.setValue(listRequest);
    }

}
