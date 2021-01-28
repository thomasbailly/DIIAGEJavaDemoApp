package diiage.potherat.demo.demoapp3.ui.vehicle;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;

import diiage.potherat.demo.demoapp3.common.Event;
import diiage.potherat.demo.demoapp3.dal.repository.QuoteRepository;
import diiage.potherat.demo.demoapp3.dal.repository.SWRepository;
import diiage.potherat.demo.demoapp3.dal.retrofit.livedata.ApiErrorResponse;
import diiage.potherat.demo.demoapp3.dal.retrofit.livedata.ApiSuccessResponse;
import diiage.potherat.demo.demoapp3.model.Quote;
import diiage.potherat.demo.demoapp3.model.sw.People;
import diiage.potherat.demo.demoapp3.model.sw.SWModelList;
import diiage.potherat.demo.demoapp3.model.sw.Vehicle;

public class VehicleViewModel extends ViewModel {

    SWRepository swRepository;
    ExecutorService executorService;

    private final MutableLiveData<String> id = new MutableLiveData<>();
    private LiveData<Vehicle> vehicle = new MutableLiveData<>();

    @Inject
    @ViewModelInject
    public VehicleViewModel(ExecutorService executorService, SWRepository swRepository ){
        this.swRepository = swRepository;
        this.executorService = executorService;
    }

    public LiveData<Vehicle> getVehicle() {
        return vehicle;
    }

    public MutableLiveData<String> getId() {
        return id;
    }

    public void  searchVehicle(){

        String value = this.id.getValue() != null ? this.id.getValue() : "";
        int idVehicle = value.isEmpty()? 0:Integer.parseInt(value);

        vehicle = Transformations.map(swRepository.getOneVehicle(idVehicle), input -> {
                    if(input instanceof ApiSuccessResponse) {
                        return ((ApiSuccessResponse<Vehicle>) input).getBody();
                    } else if (input instanceof ApiErrorResponse){
                        Log.e("debug",((ApiErrorResponse<Vehicle>) input).getErrorMessage()+"");
                    }
                    return null;
                }
        );
    }
}
