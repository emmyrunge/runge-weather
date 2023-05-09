package runge.weather;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.*;
import java.awt.*;

@Singleton
public class CurrentWeatherView extends JComponent
{
    FiveDayForecast fiveDayForecast;

    @Inject
    public CurrentWeatherView() {

    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);

        int height = getHeight();
        g.translate(0, height);


        if (fiveDayForecast == null)
        {
            return;
        }

        List[] temp = fiveDayForecast.getList();

        for (int i = 0; i < fiveDayForecast.getList().length - 1; i++)
        {
            g.drawLine(i * 20, (int) (-temp[i].getMain().getTemp()) * 5,
                    (i + 1) * 20, (int) (-temp[i + 1].getMain().getTemp()) * 5);
        }
    }

    public void setFiveDayForecast(FiveDayForecast fiveDayForecast) {
        this.fiveDayForecast = fiveDayForecast;
        repaint();
    }
}
