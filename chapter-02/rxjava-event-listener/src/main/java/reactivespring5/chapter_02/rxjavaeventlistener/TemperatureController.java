package reactivespring5.chapter_02.rxjavaeventlistener;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TemperatureController {
    private final RxTemperatureSensor temperatureSensor;

    public TemperatureController(RxTemperatureSensor temperatureSensor) {
        this.temperatureSensor = temperatureSensor;
    }

    @RequestMapping(value = "/temperature-stream", method = RequestMethod.GET)
    public SseEmitter events(HttpServletRequest request) {
        RxSseEmitter emitter = new RxSseEmitter();

        temperatureSensor.temperatureStream().subscribe(emitter.getSubscriber());

        return emitter;
    }
}
