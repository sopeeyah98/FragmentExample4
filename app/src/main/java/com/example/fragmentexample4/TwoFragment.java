package com.example.fragmentexample4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class TwoFragment extends Fragment {

    private TextView textView;
    private SharedViewModel viewModel;

    protected static final String api_url = "http://api.zippopotam.us/us/";
    protected static AsyncHttpClient client = new AsyncHttpClient();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_two, container, false);

        textView = v.findViewById(R.id.text_view_two);
        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        // observe changes
        viewModel.getInformation().observe(getViewLifecycleOwner(), new Observer<UserInformation>() {
            @Override
            public void onChanged(UserInformation information) {
                if (information != null){
                    //textView.setText("Hi " + information.getName() + "!\nYou live in zip code:" + information.getZipcode());
                    setCitybyZip(information, textView);
                }
            }
        });
        return v;
    }
    public void setCitybyZip(UserInformation information, TextView textView){
        // get the zipcode
        // call the api
        // display info
        client.get(api_url + information.getZipcode(), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try{
                    JSONObject results = new JSONObject(new String(responseBody));
                    String city = results.getJSONArray("places").getJSONObject(0).getString("place name");
                    textView.setText("Hi " + information.getName() + "!\nYou live in " + information.getZipcode());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
}
