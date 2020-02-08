package luiz787.finance.service;

import lombok.RequiredArgsConstructor;
import luiz787.finance.entity.Transaction;
import luiz787.finance.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository repository;

    public Transaction save(final Transaction transaction) {
        return repository.save(transaction);
    }

    public void delete(final long id) {
        repository.deleteById(id);
    }

    public Transaction findById(final long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("No transaction matching this ID."));
    }
}
