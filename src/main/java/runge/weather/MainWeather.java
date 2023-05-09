package runge.weather;

public class MainWeather
{
    public static void main(String[] args) {
        ForecastWeatherComponent component = DaggerForecastWeatherComponent
                .builder()
                .build();
        CurrentWeatherFrame frame = component.providesCurrentWeatherFrame();
        frame.setVisible(true);
    }
}