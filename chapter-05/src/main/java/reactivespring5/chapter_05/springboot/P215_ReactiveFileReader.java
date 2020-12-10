package reactivespring5.chapter_05.springboot;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import reactor.core.publisher.Flux;

import java.nio.charset.StandardCharsets;

public class P215_ReactiveFileReader {
    public Flux<DataBuffer> backpressureShakespeare() {
        return DataBufferUtils.read(
            new DefaultResourceLoader().getResource("hamlet.txt"),
            new DefaultDataBufferFactory(),
            1024
        );
    }

    public static void main(String[] args) {
        P215_ReactiveFileReader reactiveFileReader = new P215_ReactiveFileReader();
        reactiveFileReader.backpressureShakespeare()
            .map(dataBuffer -> {
                return dataBuffer.read();
            })
            .doOnNext(aByte -> {
                System.out.print(new String(new byte[]{aByte}, StandardCharsets.ISO_8859_1));
            })

    }
}
