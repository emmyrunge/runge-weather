package runge.weather;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ForecastWeatherController
{

    private CurrentWeatherView currentWeatherView;
    private final WeatherService weatherService;

    public ForecastWeatherController(
            CurrentWeatherView currentWeatherView,
            WeatherService service

    ){


        this.currentWeatherView = currentWeatherView;
        this.weatherService = service;
    }

    public void updateLocation(String newCity) {
        Disposable disposable = weatherService.getFiveDayForecast(newCity)
                .subscribeOn(Schedulers.io())
                //.observeOn(Schedulers.newThread())
                .subscribe(
                        currentWeather ->
                        {
                            this.currentWeatherView.setFiveDayForecast(currentWeather);
                        },
                        Throwable::printStackTrace
                );

    }
}
