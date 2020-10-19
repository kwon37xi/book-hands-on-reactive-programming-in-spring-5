package reactivespring5.chapter_02.rxjavaeventlistener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class Chapter2RxJavaEventListenerApplication implements AsyncConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(Chapter2RxJavaEventListenerApplication.class);
    }
}
