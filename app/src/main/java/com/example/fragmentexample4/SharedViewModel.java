package com.example.fragmentexample4;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    // store the information that needs to be shared between fragments
    // getter and a setter

    private MutableLiveData<UserInformation> information = new MutableLiveData<>();

    public void setInformation(UserInformation userInformation){
        information.setValue(userInformation);
    }

    public MutableLiveData<UserInformation> getInformation(){
        return information;
    }

    // LiveData: setValue, postValue

}
