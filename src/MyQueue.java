import java.util.NoSuchElementException;

/**
 * Diese Schnittstelle definiert die grundlegenden Operationen einer Warteschlange.
 * Sie ermöglicht das Hinzufügen und Entfernen von Elementen, das Abfragen der Größe und das Überprüfen, ob die Warteschlange leer ist.
 * Außerdem bietet sie Methoden zum Abrufen des ersten und letzten Elements, ohne sie zu entfernen.
 */
public interface MyQueue<E> {
    /**
     * Fügt ein Element am Ende der Warteschlange hinzu.
     * @param element Das hinzuzufügende Element.
     * @return Die Warteschlange.
     */
    MyQueue<E> append(E element);

    /**
     * Entfernt das erste Element der Warteschlange.
     * @return Das entfernte Element.
     * @throws NoSuchElementException Wenn die Warteschlange leer ist.
     */
    E delete() throws NoSuchElementException;

    /**
     * Gibt die Anzahl der Elemente in der Warteschlange zurück.
     * @return Die Größe der Warteschlange.
     */
    int size();

    /**
     * Überprüft, ob die Warteschlange leer ist.
     * @return true, wenn die Warteschlange leer ist, sonst false.
     */
    boolean isEmpty();

    /**
     * Gibt das erste Element der Warteschlange zurück, ohne es zu entfernen.
     * @return Das erste Element.
     * @throws NoSuchElementException Wenn die Warteschlange leer ist.
     */
    E peek() throws NoSuchElementException;

    /**
     * Gibt das letzte Element der Warteschlange zurück, ohne es zu entfernen.
     * @return Das letzte Element.
     * @throws NoSuchElementException Wenn die Warteschlange leer ist.
     */
    E peekLast() throws NoSuchElementException;

    /**
     * Gibt eine String-Darstellung der Warteschlange zurück.
     * @return Die String-Darstellung.
     */
    String toString();
}