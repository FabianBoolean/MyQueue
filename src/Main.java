/**
 * Testklasse für die MyLinkedQueue Implementierung.
 * Führt verschiedene Tests zur Überprüfung der Funktionalität durch.
 * @author Fabian Steinhauser
 * @version 31-03-2025
 */
public class Main {
    public static void main(String[] args) {
        // Erstellen einer neuen Warteschlange
        MyLinkedQueue<Integer> queue = new MyLinkedQueue<>();

        // Test: Elemente anfügen
        queue.append(10).append(20).append(30);
        System.out.println("Queue nach Einfügen: " + queue); // [10, 20, 30]

        // Test: Erstes und letztes Element abrufen
        System.out.println("Erstes Element: " + queue.peek()); // 10
        System.out.println("Letztes Element: " + queue.peekLast()); // 30

        // Test: Erstes Element entfernen
        queue.delete();
        System.out.println("Queue nach Entfernen des ersten Elements: " + queue); // [20, 30]

        // Test: Überprüfen, ob die Queue leer ist
        System.out.println("Ist die Queue leer? " + queue.isEmpty()); // false

        // Test: Größe der Queue ermitteln
        System.out.println("Größe der Queue: " + queue.size()); // 2

        // Test: Iteration durch die Queue mit Iterator
        System.out.println("Durchlaufen der Queue mit Iterator:");
        for (Integer element : queue) {  // Kein Cast nötig, da MyLinkedQueue Iterable implementiert
            System.out.println(element);
        }

        // Test: Letztes Element nach einer Löschung abrufen
        System.out.println("Letztes Element nach Löschen des ersten: " + queue.peekLast()); // 30
    }
}
