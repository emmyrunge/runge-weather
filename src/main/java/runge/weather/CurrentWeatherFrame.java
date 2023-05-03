package runge.weather;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.swing.*;
import java.awt.*;

public class CurrentWeatherFrame extends JFrame
{
    private CurrentWeatherView currentWeatherView;
    private WeatherService weatherService;
    private ForecastWeatherController forecastWeatherController;

    public CurrentWeatherFrame() {
        setSize(780, 500);
        setTitle("Current Weather");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel northPanel = new JPanel(new BorderLayout());

        //BUG: button only pops up after it is clicked...
        JButton updateButton = new JButton("Update");
        northPanel.add(updateButton, BorderLayout.EAST);

        JTextField enterCityField = new JTextField("Enter City"
                + " (Currently showing Minneapolis)");
        northPanel.add(enterCityField);

        mainPanel.add(northPanel, BorderLayout.NORTH);

        currentWeatherView = new CurrentWeatherView();
        mainPanel.add(currentWeatherView, BorderLayout.CENTER);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        weatherService = retrofit.create(WeatherService.class);


        forecastWeatherController = new ForecastWeatherController(currentWeatherView, weatherService);

        forecastWeatherController.updateLocation("Minneapolis");


        updateButton.addActionListener(e ->
        {
           forecastWeatherController.updateLocation(enterCityField.getText());
        });

        setContentPane(mainPanel);




    }

    //need to create method to get current location (ie ny or newCity) looking at
    //then able to make action button listen to new location

}