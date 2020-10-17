package reactivespring5.chapter_02.observerpattern;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@DisplayName("ConcreteSubjectTest")
class ConcreteSubjectTest {

    @Test
    void observersHandleEventsFromSubject() {
        // given
        Subject<String> subject = new ConcreteSubject();

        Observer<String> observerA = Mockito.spy(new ConcreteObserverA());
        Observer<String> observerB = Mockito.spy(new ConcreteObserverB());

        // when
        subject.notifyObservers("No listeners");

        subject.registerObserver(observerA);
        subject.notifyObservers("Message for A");


        subject.registerObserver(observerB);
        subject.notifyObservers("Message for A & B");

        subject.unregisterObserver(observerA);
        subject.notifyObservers("Message for B");

        subject.unregisterObserver(observerB);
        subject.notifyObservers("No listeners");

        // then
        ArgumentCaptor<String> observerAArgs = ArgumentCaptor.forClass(String.class);
        verify(observerA, times(2)).observe(observerAArgs.capture());
        verifyNoMoreInteractions(observerA);
        assertThat(observerAArgs.getAllValues()).containsExactly("Message for A", "Message for A & B");

        ArgumentCaptor<String> observerBArgs = ArgumentCaptor.forClass(String.class);
        verify(observerB, times(2)).observe(observerBArgs.capture());
        verifyNoMoreInteractions(observerB);
        assertThat(observerBArgs.getAllValues()).containsExactly("Message for A & B", "Message for B");
    }
}