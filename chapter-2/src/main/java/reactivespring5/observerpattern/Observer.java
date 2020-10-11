package reactivespring5.observerpattern;

public interface Observer<T> {
    void observe(T event);
}
