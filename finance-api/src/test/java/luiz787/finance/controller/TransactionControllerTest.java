package luiz787.finance.controller;

import luiz787.finance.entity.Transaction;
import luiz787.finance.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class TransactionControllerTest {

    private TransactionController controller;
    @Mock
    private TransactionService service;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        controller = new TransactionController(service);
    }

    @Test
    void addTransaction_BodyIsSameObjectReturnedFromService() {
        final Transaction transaction = getTransaction();
        final Transaction transactionWithId = getTransaction();
        transactionWithId.setId(3L);

        when(service.save(transaction)).thenReturn(transactionWithId);

        final var response = controller.addTransaction(transaction);

        assertEquals(response.getBody(), transactionWithId);
    }

    @Test
    void addTransaction_ServiceIsCalledWithSameTransactionPassedAsArgument() {
        final Transaction transaction = getTransaction();

        controller.addTransaction(transaction);

        verify(service).save(transaction);
    }

    @Test
    void addTransaction_ResponseCodeIsOKWhenServiceSucceeds() {
        final Transaction transaction = getTransaction();

        when(service.save(transaction)).thenReturn(transaction);

        final var response = controller.addTransaction(transaction);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void findTransactionById_ServiceIsCalledWithSameIdPassedAsArgument() {
        final long id = 1L;

        controller.findTransactionById(id);

        verify(service).findById(id);
    }

    @Test
    void findTransactionById_BodyIsSameObjectReturnedFromService() {
        final long id = 1L;
        final Transaction transaction = getTransaction();
        when(service.findById(id)).thenReturn(transaction);

        final var response = controller.findTransactionById(id);

        assertEquals(transaction, response.getBody());
    }

    @Test
    void findTransactionById_ResponseIsOkWhenServiceSucceeds() {
        final long id = 1L;
        final Transaction transaction = getTransaction();
        when(service.findById(id)).thenReturn(transaction);

        final var response = controller.findTransactionById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deleteTransaction_ServiceIsCalledWithSameIdPassedAsArgument() {
        final long id = 1L;

        controller.deleteTransaction(id);

        verify(service).delete(id);
    }

    @Test
    void deleteTransaction_ResponseIsNoContentWhenServiceSucceeds() {
        final long id = 1L;
        doNothing().when(service).delete(id);

        final var response = controller.deleteTransaction(id);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deleteTransaction_BodyIsEmptyWhenServiceSucceeds() {
        final long id = 1L;
        doNothing().when(service).delete(id);

        final var response = controller.deleteTransaction(id);
        assertNull(response.getBody());
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
