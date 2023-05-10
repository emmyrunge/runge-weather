package runge.weather;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import javax.inject.Inject;

public class ForecastWeatherController
{

    private final CurrentWeatherView currentWeatherView;
    private final WeatherService weatherService;

    @Inject
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
                .observeOn(Schedulers.newThread())
                .subscribe(
                        this.currentWeatherView::setFiveDayForecast,
                        Throwable::printStackTrace
                );

    }
}
