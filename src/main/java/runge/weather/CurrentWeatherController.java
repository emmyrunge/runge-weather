package runge.weather;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URL;

public class CurrentWeatherController
{

    private final WeatherService service;
    private final JLabel imageLabel;
    private final JLabel degreeLabel;


    @Inject
    public CurrentWeatherController(WeatherService service,
                                    @Named("imageLabel")JLabel imageLabel,
                                    @Named("degreeLabel")JLabel degreeLabel) {
        this.service = service;
        this.imageLabel = imageLabel;
        this.degreeLabel = degreeLabel;
    }

    public void updateWeather(String newCity) {
        Disposable disposable = service.getCurrentWeather(newCity)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(this::setCurrentWeather,
                        Throwable::printStackTrace
                );

    }
    public void setCurrentWeather(CurrentWeather currentWeather) throws MalformedURLException {
        degreeLabel.setText(String.valueOf(currentWeather.getMain().getTemp()));
        String icon = currentWeather.getWeather().get(0).getIcon();
        String url = "http://openweathermap.org/img/w/" + icon + ".png";
        imageLabel.setIcon(new ImageIcon(new URL(url)));
    }
}
