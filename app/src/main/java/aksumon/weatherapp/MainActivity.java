package aksumon.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static final String BASE_URL="http://api.openweathermap.org/data/2.5/";
    private double latitude=23.7465176;
    private double longitude=90.3944841;
    private String units="metric";
    private CurrentWeatherService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        service=retrofit.create(CurrentWeatherService.class);

        String endUrl=String.format("weather?lat=%f&lon=%f&units=%s&appid=%s",
                latitude,longitude,units,getString(R.string.weather_api_key));

        Call<WeatherResponse> call=service.getCurrentWeatherData(endUrl);
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.code()==200){
                    WeatherResponse weatherResponse=response.body();
                    Log.e("Current","Temp of"+weatherResponse.getCity()+":"+weatherResponse.getCod());


                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {

            }
        });

    }
}
