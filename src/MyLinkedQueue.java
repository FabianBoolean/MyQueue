import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Diese Klasse implementiert eine Warteschlange mithilfe einer verketteten Liste.
 * Sie bietet Methoden zum Hinzufügen, Entfernen und Abrufen von Elementen sowie zur Iteration über die Warteschlange.
 * Sie enthält auch interne Hilfsmethoden zur Verwaltung der Listenknoten.
 */
public class MyLinkedQueue<E> implements MyQueue<E>, Iterable<E> {
    private Cell front;
    private Cell rear;
    private int count;

    /**
     * Diese Klasse repräsentiert einen Knoten der verketteten Liste.
     * Jeder Knoten speichert ein Element und einen Verweis auf das nächste Element.
     */
    private class Cell {
        E element;
        Cell next;

        Cell(E element) {
            this.element = element;
            this.next = null;
        }
    }

    /**
     * Konstruktor für eine leere Warteschlange.
     * Initialisiert die Warteschlange mit einem null-Element und setzt die Anzahl auf 0.
     */
    public MyLinkedQueue() {
        front = null;
        rear = null;
        count = 0;
    }

    @Override
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
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public E peek() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return front.element;
    }

    @Override
    public E peekLast() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return rear.element;
    }

    @Override
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
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    /**
     * Diese Klasse implementiert einen Iterator für die Warteschlange.
     * Sie ermöglicht das Durchlaufen der Elemente der Warteschlange.
     */
    private class MyIterator implements Iterator<E> {
        private Cell current = front;
        private Cell previous = null;
        private boolean canRemove = false;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements");
            }
            E element = current.element;
            previous = current;
            current = current.next;
            canRemove = true;
            return element;
        }

        @Override
        public void remove() throws IllegalStateException {
            if (!canRemove) {
                throw new IllegalStateException("No element to remove");
            }
            if (previous == front) {
                front = front.next;
                if (front == null) {
                    rear = null;
                }
            } else {
                Cell temp = front;
                while (temp.next != previous) {
                    temp = temp.next;
                }
                temp.next = previous.next;
                if (previous == rear) {
                    rear = temp;
                }
            }
            count--;
            canRemove = false;
        }
    }
}