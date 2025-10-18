package org.example;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TodoDao {


    private static final TodoDao INSTANCE = new TodoDao();

    private final List<TodoItem> items = new CopyOnWriteArrayList<>();
    private long nextId = 1;

    // Private constructor: Initial items are added here, only once.
    private TodoDao() {
        items.add(new TodoItem(nextId++, "Fix the two tasks bug."));
        items.add(new TodoItem(nextId++, "Implement delete functionality."));
    }

    // Public method to get the single instance
    public static TodoDao getInstance() {
        return INSTANCE;
    }

    // CRUD methods
    public void addItem(String task) {
        TodoItem item = new TodoItem(nextId++, task);
        items.add(item);
    }

    public void toggleCompletion(long id) {
        for (TodoItem item : items) {
            if (item.getId() == id) {
                item.setCompleted(!item.isCompleted());
                return;
            }
        }
    }

    public void deleteItem(long id) {
        // Removes the item where the IDs match
        items.removeIf(item -> item.getId() == id);
    }

    public List<TodoItem> getItems() {
        return items;
    }

    public void clearItems() {
        items.clear();
    }
}