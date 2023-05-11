package runge.weather;

import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.*;
import java.awt.*;

public class CurrentWeatherFrame extends JFrame
{
    @Inject
    public CurrentWeatherFrame(CurrentWeatherView view,
                               ForecastWeatherController controller,
                               CurrentWeatherController currentWeatherController,
                               @Named("imageLabel") JLabel imageLabel,
                               @Named("degreeLabel") JLabel degreeLabel) {


        setSize(780, 500);
        setTitle("Current Weather");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel northPanel = new JPanel(new BorderLayout());
        JPanel currentWeatherPanel = new JPanel();

        currentWeatherPanel.add(imageLabel);
        currentWeatherPanel.add(degreeLabel);
        northPanel.add(currentWeatherPanel, BorderLayout.SOUTH);

        //BUG: button only pops up after it is clicked...
        JButton updateButton = new JButton("Update");
        northPanel.add(updateButton, BorderLayout.EAST);

        JTextField enterCityField = new JTextField("Minneapolis");
        northPanel.add(enterCityField);
        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(view, BorderLayout.CENTER);

        setContentPane(mainPanel);

        updateButton.addActionListener(e ->
        {
            controller.updateLocation(enterCityField.getText());
            currentWeatherController.updateWeather(enterCityField.getText());
        });
        controller.updateLocation(enterCityField.getText());
        currentWeatherController.updateWeather(enterCityField.getText());



    }

}
