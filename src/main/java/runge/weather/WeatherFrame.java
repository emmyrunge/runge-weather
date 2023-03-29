/*
package runge.weather;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
public class WeatherFrame extends JFrame
{
    public WeatherFrame() throws IOException {

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        JLabel currentWeather = new JLabel();
        currentWeather.setFont(new Font("Serif", Font.PLAIN, 36));
        currentWeather.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(currentWeather, BorderLayout.CENTER);
        setContentPane(mainPanel);
        setSize(500, 500);
        setTitle("FBI MOST WANTED");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();


        WeatherService weatherService = retrofit.create(WeatherService.class);

        Disposable disposable = weatherService.getCurrentWeather()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(
                        featureCollection ->
                        {
                            String list = ;
                            currentWeather.setText(list);
                        },

                        Throwable::printStackTrace
                );

    }
}

*/
