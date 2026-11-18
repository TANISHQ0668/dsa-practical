import java.util.Scanner;

public class CircularGameTurns {

    // Node representing a player in the circle
    static class Player {
        String name;
        Player next;

        Player(String name) {
            this.name = name;
            this.next = null;
        }
    }

    static Player tail = null; // Points to the last player; tail.next is the first player
    static Player currentTurn = null;

    // 1. Insert a new player into the circle
    static void addPlayer(String name) {
        Player newPlayer = new Player(name);
        if (tail == null) {
            tail = newPlayer;
            tail.next = tail; // Points to itself
            currentTurn = tail;
        } else {
            newPlayer.next = tail.next; // Point new player to first player
            tail.next = newPlayer;      // Old last player points to new player
            tail = newPlayer;           // Update tail to new player
        }
        System.out.println(name + " joined the game circle.");
    }

    // 2. Delete a player from the circle
    static void removePlayer(String name) {
        if (tail == null) {
            System.out.println("No players in the game.");
            return;
        }

        Player curr = tail.next;
        Player prev = tail;

        do {
            if (curr.name.equalsIgnoreCase(name)) {
                if (curr == tail && curr.next == tail) { // Only one player left
                    tail = null;
                    currentTurn = null;
                } else {
                    prev.next = curr.next;
                    if (curr == tail) tail = prev; // Update tail if last player was removed
                    if (curr == currentTurn) currentTurn = curr.next; // Move turn to next player
                }
                System.out.println(name + " left the game.");
                return;
            }
            prev = curr;
            curr = curr.next;
        } while (curr != tail.next);

        System.out.println("Player '" + name + "' not found.");
    }

    // 3. Advance and play the next cyclic turn
    static void playNextTurn() {
        if (currentTurn == null) {
            System.out.println("No players in the game.");
            return;
        }
        System.out.println("Current Turn: " + currentTurn.name);
        currentTurn = currentTurn.next; // Automatically wraps around after the last player
    }

    // 4. Traverse and display all players in the circle
    static void displayCircle() {
        if (tail == null) {
            System.out.println("Game circle is empty.");
            return;
        }
        System.out.print("Player Circle: ");
        Player temp = tail.next; // Start from the first player
        do {
            System.out.print("[" + temp.name + "] -> ");
            temp = temp.next;
        } while (temp != tail.next);
        System.out.println("(back to " + tail.next.name