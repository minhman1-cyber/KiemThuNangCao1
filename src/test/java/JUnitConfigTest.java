import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JUnitConfigTest {
    @Test
    public void testConfig(){
        String str = "Test JUnit Config1";
        assertEquals("Test JUnit Config", str);
    }
}
