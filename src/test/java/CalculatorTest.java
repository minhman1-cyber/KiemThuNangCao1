import org.example.Calculator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class CalculatorTest {
    //Khai báo một đối tượng Calculator
    Calculator calculator;
    @BeforeAll // Chạy duy nhất một lần trước tất cả các test
    public static void setup() throws Exception {
        System.out.println("Run once before all tests!");
    }

    @BeforeEach //Chạy mỗi trước mỗi test
    public void beforeEach() throws Exception {
        calculator = new Calculator();
        System.out.println("Run once before each test!");
    }

    @Test
    public void testAdd(){
        assertEquals(5, calculator.add(2, 3));
    }

    @Test
    public void testSubtract(){
        assertEquals(-1, calculator.sub(3,4));
    }

    @Test
    public void testMultiply(){
        assertEquals(6, calculator.mul(2,3));
    }

    @Test
    public void testDivide(){
        assertEquals(3, calculator.div(6,2));
    }

    @Test
    public void testDivideByZero_assertThrow(){
        //Phát sinh kiểu ngoại lệ mong muốn
        ArithmeticException ex = assertThrows(
                ArithmeticException.class,
                () -> calculator.div(2,1));
        assertEquals("Division by zero", ex.getMessage());
    }

    @Test
    public void testDivideByZero_tryCatch(){
        try{
            //gọi phương thức phát sinh ngoại lệ
            calculator.div(2,1);
            fail("ArithmeticException expected");
        }catch (ArithmeticException ex){
            assertEquals("Division by zero",ex.getMessage());
        }
    }

    private static <T extends Throwable> T expectedException(Class<T> type, Runnable code){
        return assertThrows(type, code::run);
    }

    @Test
    public void testDivideByZero_helper(){
        //Gọi phương thức phát sinh ngoại lệ
        ArithmeticException e =  expectedException(ArithmeticException.class, () -> {
            calculator.div(2,0);
        });
        assertEquals("Division by zero",e.getMessage());
    }
    @Test
    public void testAllOperationsWithInternalError(){
        assertAll("Minh hoạ gom nhiều test cùng một lúc bao gồm cả trường hợp lỗi",
                //Sai
                () -> assertEquals(5, calculator.add(2,2)),
                () -> assertEquals(5, calculator.div(4,2)),
                //Dung
                () -> assertEquals(5, calculator.sub(8,3)),
                () -> assertEquals(4, calculator.mul(2,3)),
                () -> assertThrows(ArithmeticException.class, () -> {calculator.div(10,0);}, "Division by zero")
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5})
    @DisplayName("Minh hoa truyen tham so bang value source")
    void testAdd_withValueSource(int number){
        assertEquals(number + 3, calculator.add(number, 3));
        System.out.println("ket qua:" + calculator.add(number, 3));
    }

    @AfterEach
    public void afterEach() throws Exception {
        calculator = null;//không cần thiết
        System.out.println("Run once after each test!");
    }
    @AfterAll //Chạy một lần duy nhất sau khi kết thúc test
    public static void teardown() throws Exception {
        System.out.println("Run once after each test!");
    }

    //Sử dụng CsvSource - comma separate value
    @ParameterizedTest
    @CsvSource({
            //Mỗi bộ dữ liệu là một  (Một phần tử của mảng)
            "1,2,3",
            "5,7,12",
            "-3,3,0"
    })
    public void kiemThuVoiCsvSource(int number1, int number2, int number3){
        assertEquals(number3, calculator.add(number1, number2));
    }

    //Sử dụng CsvFileSource
    @ParameterizedTest
    @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 1)
    void testAdd_withCsvFileSource(int number1, int number2, int number3){
        assertEquals(number3, calculator.add(number1, number2));
    }

    //Sử dụng MethodSource
    @ParameterizedTest
    @MethodSource("addArgumentsProvider")
    void testAdd_withMethodSource(int number1, int number2, int number3){
        assertEquals(number3, calculator.add(number1, number2));
    }
    //Phương thức cung cấp dữ
    static Stream<Arguments> addArgumentsProvider(){
        return Stream.of(
                Arguments.of(3,4,7),
                Arguments.of(4,5,9)
        );
    }

    //@EnumSource
    @ParameterizedTest
    @EnumSource(TestEnum.class)
    void testEnum(TestEnum testEnum){
        int x = switch (testEnum){
            case ONE -> 1;
            case TWO -> 2;
            case THREE -> 3;
        };
        assertEquals(x+3, calculator.add(x, 3));
    }
    //Khai báo enum
    enum TestEnum {
        ONE,
        TWO,
        THREE,
    }
}
