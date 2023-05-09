package runge.weather;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;

public class CurrentWeatherFrame extends JFrame
{

    @Inject
    public CurrentWeatherFrame(CurrentWeatherView view, ForecastWeatherController controller) {


        setSize(780, 500);
        setTitle("Current Weather");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel northPanel = new JPanel(new BorderLayout());

        //BUG: button only pops up after it is clicked...
        JButton updateButton = new JButton("Update");
        northPanel.add(updateButton, BorderLayout.EAST);

        JTextField enterCityField = new JTextField("Minneapolis");
        northPanel.add(enterCityField);
        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(view, BorderLayout.CENTER);

        setContentPane(mainPanel);

        updateButton.addActionListener(e -> controller.updateLocation(enterCityField.getText()));
        controller.updateLocation(enterCityField.getText());

    }

}
