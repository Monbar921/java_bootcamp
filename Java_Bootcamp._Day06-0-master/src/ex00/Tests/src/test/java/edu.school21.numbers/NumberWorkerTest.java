package edu.school21.numbers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
public class NumberWorkerTest {
    private final NumberWorker calculator = new NumberWorker();

    @Test
    void addition() {
        assertEquals(2, calculator.add(1, 1));
    }
}
