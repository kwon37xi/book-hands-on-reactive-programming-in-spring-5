package reactivespring5.chapter_02.springeventlistener;

import lombok.Getter;

@Getter
public final class Temperature {
    private final double value;

    public Temperature(double value) {
        this.value = value;
    }
}
