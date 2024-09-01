package duke.parsers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DateParserTest {
    DateParser dateParser = new DateParser();
    @Test
    public void dummyTest(){
        assertEquals("11 Nov 2020", dateParser.giveDate("2020.11.11"));
    }

    @Test
    public void anotherDummyTest(){
        assertEquals(null, dateParser.giveDate("2020/11.11"));
    }
    @Test
    public void thirdTest(){
        assertNotEquals(null, "2");
    }
}


