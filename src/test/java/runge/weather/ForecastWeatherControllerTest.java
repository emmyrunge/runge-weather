package runge.weather;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.internal.operators.observable.ObservableAll;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class ForecastWeatherControllerTest
{

    static{
        //makes it so that our service returns right away
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
        RxJavaPlugins.setNewThreadSchedulerHandler(scheduler -> Schedulers.trampoline());
    }

    @Test
    void updateWeather(){
        //given
        WeatherService weatherService = mock();
        CurrentWeatherView view = mock();
        ForecastWeatherController controller = new ForecastWeatherController(view, weatherService);
        FiveDayForecast fiveDayForecast = mock();
        Observable<FiveDayForecast> observable = Observable.just(fiveDayForecast);
        doReturn(observable).when(weatherService).getFiveDayForecast("Minneapolis");

        //when
        controller.updateLocation("Minneapolis");

        //then
        verify(weatherService).getFiveDayForecast("Minneapolis");
        verify(view).setFiveDayForecast(fiveDayForecast);

    }
}
