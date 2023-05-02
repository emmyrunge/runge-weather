package runge.weather;

import javax.swing.*;
import java.awt.*;

public class CurrentWeatherView extends JComponent
{
    FiveDayForecast fiveDayForecast;


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);

        int height = getHeight();
        g.translate(0, height);

        List[] temp = fiveDayForecast.getList();

        if (fiveDayForecast == null)
        {
            return;
        }
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
