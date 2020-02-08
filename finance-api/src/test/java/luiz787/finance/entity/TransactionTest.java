package luiz787.finance.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionTest {

    private Transaction transaction;

    @BeforeEach
    void createTransaction() {
        transaction = new Transaction();
    }

    @Test
    void getId() {
        transaction.setId(4L);
        assertEquals(4L, transaction.getId());
    }

    @Test
    void getName() {
        transaction.setName("Test name");
        assertEquals("Test name", transaction.getName());
    }

    @Test
    void getDescription() {
        transaction.setDescription("Description test");
        assertEquals("Description test", transaction.getDescription());
    }

    @Test
    void getTimestamp() {
        final LocalDate date = LocalDate.of(2020, 8, 2);
        final LocalTime time = LocalTime.of(12, 0);
        final LocalDateTime dateTime = LocalDateTime.of(date, time);
        transaction.setTimestamp(dateTime);
        assertEquals(dateTime, transaction.getTimestamp());
    }

    @Test
    void getAmount() {
        transaction.setAmount(6055L);
        assertEquals(6055L, transaction.getAmount());
    }
}
