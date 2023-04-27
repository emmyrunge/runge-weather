package runge.weather;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.swing.*;
import java.awt.*;

public class CurrentWeatherFrame extends JFrame
{
    public CurrentWeatherFrame()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        WeatherService weatherService = retrofit.create(WeatherService.class);
        FiveDayForecast fiveDayForecast = weatherService.getFiveDayForecast("New York").blockingFirst();

        CurrentWeatherView currentWeatherView = new CurrentWeatherView(fiveDayForecast);
        currentWeatherView.setFiveDayForecast(fiveDayForecast);



        setSize(780,500);
        setTitle("Current Weather");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel northPanel = new JPanel(new BorderLayout());
        JButton updateButton = new JButton("Update");
        northPanel.add(updateButton, BorderLayout.EAST);

        JTextField enterCityField = new JTextField("Enter City, current weather shown is for New York");
        northPanel.add(enterCityField);

        updateButton.addActionListener(e ->
        {
            String newCity = enterCityField.getText();

            currentWeatherView.setFiveDayForecast(weatherService.getFiveDayForecast(newCity).blockingFirst());
            mainPanel.add(currentWeatherView, BorderLayout.CENTER);
        });

        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(currentWeatherView, BorderLayout.CENTER);
        setContentPane(mainPanel);

    }

}
