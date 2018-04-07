package skodavox.peas.unitbrno.cz.skodavoxapp.generic;

/**
 * Generic listener.
 * @param <T>
 */
public interface IListener<T> {
    /**
     * Method that is called when observers state changes (for example when button is clicked)
     * @param data information about change
     */
    void notify(T data);
}
