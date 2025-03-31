import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Eine verkettete Warteschlange, die das FIFO-Prinzip nutzt.
 * Implementiert Iterable zur Nutzung eines Iterators.
 * @author Fabian Steinhauser
 * @version 31-03-2025
 * @param <E> der Typ der gespeicherten Elemente
 */
public class MyLinkedQueue<E> implements MyQueue<E>, Iterable<E> {
    private Cell front;
    private Cell rear;
    private int count;

    /**
     * Ein Knoten der verketteten Liste.
     */
    private class Cell {
        E element;
        Cell next;

        /**
         * Erstellt eine neue Zelle.
         * @param element das gespeicherte Element
         */
        Cell(E element) {
            this.element = element;
            this.next = null;
        }
    }

    /**
     * Erstellt eine leere Warteschlange.
     */
    public MyLinkedQueue() {
        front = null;
        rear = null;
        count = 0;
    }

    @Override
    /**
     * Fügt ein Element ans Ende an.
     * @param element das hinzuzufügende Element
     * @return die aktualisierte Warteschlange
     */
    public MyQueue<E> append(E element) {
        Cell newCell = new Cell(element);
        if (rear == null) {
            front = newCell;
        } else {
            rear.next = newCell;
        }
        rear = newCell;
        count++;
        return this;
    }

    @Override
    /**
     * Entfernt das erste Element.
     * @return das entfernte Element
     * @throws NoSuchElementException falls die Warteschlange leer ist
     */
    public E delete() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        E element = front.element;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        count--;
        return element;
    }

    @Override
    /**
     * Gibt die Anzahl der Elemente zurück.
     * @return die Größe der Warteschlange
     */
    public int size() {
        return count;
    }

    @Override
    /**
     * Prüft, ob die Warteschlange leer ist.
     * @return true, wenn leer, sonst false
     */
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    /**
     * Gibt das erste Element zurück.
     * @return das erste Element
     * @throws NoSuchElementException falls die Warteschlange leer ist
     */
    public E peek() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return front.element;
    }

    @Override
    /**
     * Gibt das letzte Element zurück.
     * @return das letzte Element
     * @throws NoSuchElementException falls die Warteschlange leer ist
     */
    public E peekLast() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return rear.element;
    }

    @Override
    /**
     * Gibt die Warteschlange als String aus.
     * @return die String-Darstellung der Warteschlange
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Cell current = front;
        while (current != null) {
            sb.append(current.element);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    /**
     * Erstellt einen Iterator für die Warteschlange.
     * @return einen Iterator
     */
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    /**
     * Ein Iterator für die Warteschlange.
     */
    private class MyIterator implements Iterator<E> {
        private Cell previous;
        private Cell current;
        private boolean removable;

        /**
         * Erstellt einen neuen Iterator.
         */
        public MyIterator() {
            this.current = front;
            this.previous = null;
            this.removable = false;
        }

        @Override
        /**
         * Prüft, ob ein weiteres Element vorhanden ist.
         * @return true, wenn ja, sonst false
         */
        public boolean hasNext() {
            return current != null;
        }

        @Override
        /**
         * Gibt das nächste Element zurück.
         * @return das nächste Element
         * @throws NoSuchElementException falls kein weiteres Element vorhanden ist
         */
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements");
            }
            E element = current.element;
            previous = current;
            current = current.next;
            removable = true;
            return element;
        }

        @Override
        /**
         * Entfernt das aktuelle Element.
         * @throws IllegalStateException falls kein Element entfernt werden kann
         */
        public void remove() {
            if (!removable) {
                throw new IllegalStateException("No element to remove");
            }
            if (previous == front) {
                front = front.next;
                if (front == null) {
                    rear = null;
                }
            }
            count--;
            removable = false;
        }
    }
}