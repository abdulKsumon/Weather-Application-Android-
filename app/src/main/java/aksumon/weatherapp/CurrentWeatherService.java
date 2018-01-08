package aksumon.weatherapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by SUMON on 03-Jan-18.
 */

public interface CurrentWeatherService {
    @GET()
    Call<WeatherResponse>getCurrentWeatherData(@Url String endUrl);
}
