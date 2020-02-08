package luiz787.finance.repository;

import luiz787.finance.FinanceApplication;
import luiz787.finance.entity.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = FinanceApplication.class)
class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository repository;

    private Transaction getTransaction() {
        final Transaction transaction = new Transaction();
        transaction.setName("Integration test");
        transaction.setDescription("Testing");
        transaction.setAmount(145L);
        final LocalDate date = LocalDate.of(2020, 1, 2);
        final LocalTime time = LocalTime.of(12, 0);
        transaction.setTimestamp(LocalDateTime.of(date, time));
        return transaction;
    }

    @Test
    public void findAndSave_IdIsGenerated() {
        final Transaction transaction = getTransaction();
        final Transaction savedTransaction = repository.save(transaction);
        assertNotNull(savedTransaction.getId());
    }

    @Test
    public void findAndSave_SavedNameIsEqual() {
        final Transaction transaction = getTransaction();
        final Transaction savedTransaction = repository.save(transaction);
        assertEquals(transaction.getName(), savedTransaction.getName());
    }

    @Test
    public void findAndSave_SavedDescriptionIsEqual() {
        final Transaction transaction = getTransaction();
        final Transaction savedTransaction = repository.save(transaction);
        assertEquals(transaction.getDescription(), savedTransaction.getDescription());
    }

    @Test
    public void findAndSave_SavedAmountIsEqual() {
        final Transaction transaction = getTransaction();
        final Transaction savedTransaction = repository.save(transaction);
        assertEquals(transaction.getAmount(), savedTransaction.getAmount());
    }

    @Test
    public void findAndSave_SavedTimestampIsEqual() {
        final Transaction transaction = getTransaction();
        final Transaction savedTransaction = repository.save(transaction);
        assertEquals(transaction.getTimestamp(), savedTransaction.getTimestamp());
    }
}