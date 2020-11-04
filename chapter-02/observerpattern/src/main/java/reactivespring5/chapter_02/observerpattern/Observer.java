package reactivespring5.chapter_02.observerpattern;

public interface Observer<T> {
    void observe(T event);
}
