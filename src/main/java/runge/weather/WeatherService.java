package runge.weather;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService
{
    @GET("/data/2.5/weather?appid=ee8e45823782bd84feb9e1a2eab70e3a&units=imperial")
    Observable<CurrentWeather> getCurrentWeather(@Query("q") String location);

}
