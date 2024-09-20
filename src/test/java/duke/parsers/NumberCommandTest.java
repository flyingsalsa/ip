package duke.parsers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.commands.NumberCommand;
import duke.tasks.Storage;







class NumberCommandTest {

    private Storage storage;

    @BeforeEach
    void setUp() {
        storage = new Storage();
        storage.clear();
        // Add mock tasks to storage for testing
        storage.todo("Task 1");
        storage.todo("Task 3");
    }

    @Test
    void testExecuteMarkValidTask() {
        String result = NumberCommand.execute(storage, "1", "mark");
        assertEquals("marked 1 as completed\nuse \"list\" to see changes", result);
    }

    @Test
    void testExecuteUnmarkValidTask() {
        // First mark the task
        NumberCommand.execute(storage, "1", "mark");
        // Now unmark it
        String result = NumberCommand.execute(storage, "1", "unmark");
        assertEquals("marked 1 as uncompleted\nuse \"list\" to see changes", result);
    }

    @Test
    void testExecuteDeleteValidTask() {
        String result = NumberCommand.execute(storage, "1", "delete");
        assertEquals("deleted 1\nuse \"list\" to see changes", result);
    }

    @Test
    void testExecuteInvalidNumber() {
        String result = NumberCommand.execute(storage, "10", "mark");
        assertEquals("that number isn't a valid task dude...\nit has to be from 1 to 2", result);
    }

    @Test
    void testExecuteNonNumericInput() {
        String result = NumberCommand.execute(storage, "abc", "mark");
        assertEquals("sorry bud that ain't a number\ni don't know which task u're referring to...", result);
    }
}
