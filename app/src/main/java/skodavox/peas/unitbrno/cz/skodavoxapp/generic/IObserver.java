package skodavox.peas.unitbrno.cz.skodavoxapp.generic;

/**
 * Generic inteface for observer design pattern.
 * Observer have collection of classes that implements IListener interface and notifies them when state that he observes changes.
 * @param <T> Data that describes what change (e. g. Click event with button)
 */
public interface IObserver<T> {
    /**
     * Register listener with observer. After calling this method listener will be notified when changes occur.
     * @param listener
     */
    void registerListener(IListener<T> listener);
}
