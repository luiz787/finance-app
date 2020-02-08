package luiz787.finance.service;

import luiz787.finance.entity.Transaction;
import luiz787.finance.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TransactionServiceTest {

    private TransactionService service;

    @Mock
    private TransactionRepository repository;

    @BeforeEach
    public void initService() {
        MockitoAnnotations.initMocks(this);
        service = new TransactionService(repository);
    }

    @Test
    void save_RepositoryIsCalledWithSameObjectPassedAsArgument() {
        final Transaction transaction = getTransaction();

        service.save(transaction);

        verify(repository).save(transaction);
    }

    @Test
    void save_ReturnsSameObjectAsRepository() {
        final Transaction transaction = getTransaction();

        when(repository.save(transaction)).thenReturn(getTransaction());

        final Transaction returnedFromService = service.save(transaction);

        assertEquals(getTransaction(), returnedFromService);
    }

    @Test
    void delete_RepositoryIsCalledWithSameIdPassedAsArgument() {
        final long id = 1337L;

        service.delete(id);

        verify(repository).deleteById(id);
    }

    @Test
    void findById_RepositoryIsCalledWithSameIdPassedAsArgument() {
        final long id = 1337L;

        when(repository.findById(id)).thenReturn(Optional.of(getTransaction()));

        service.findById(id);

        verify(repository).findById(id);
    }

    @Test
    void findById_ThrowsRuntimeExceptionIfRepositoryReturnsEmptyOptional() {
        final long id = 1337L;

        when(repository.findById(id)).thenReturn(Optional.empty());

        final RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            service.findById(id);
        });
        assertEquals("No transaction matching this ID.", thrown.getMessage());
    }

    @Test
    void findById_ReturnsSameObjectAsRepository() {
        final long id = 1337L;

        when(repository.findById(id)).thenReturn(Optional.of(getTransaction()));

        final Transaction returnedFromService = service.findById(id);

        assertEquals(getTransaction(), returnedFromService);
    }


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
}