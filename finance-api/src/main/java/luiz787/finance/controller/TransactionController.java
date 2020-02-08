package luiz787.finance.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import luiz787.finance.entity.Transaction;
import luiz787.finance.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
@Api("API de transações.")
public class TransactionController {

    private final TransactionService service;

    @PostMapping("/")
    @ApiOperation(value = "Adiciona ou edita uma transação.",
            response = Transaction.class,
            httpMethod = "POST"
    )
    public ResponseEntity<Transaction> addTransaction(
            @RequestBody @ApiParam(value = "Transação a ser salva.", example = "") final Transaction transaction) {
        return ResponseEntity.ok(service.save(transaction));
    }

    @ApiOperation(value = "Busca uma transação por id.",
            response = Transaction.class,
            httpMethod = "GET"
    )
    @GetMapping("/{transactionId}")
    public ResponseEntity<Transaction> findTransactionById(
            @PathVariable @ApiParam(value = "ID da transação.", example = "3") final long transactionId) {
        return ResponseEntity.ok(service.findById(transactionId));
    }

    @ApiOperation(value = "Deleta uma transação por id.",
            httpMethod = "DELETE"
    )
    @DeleteMapping("/{transactionId}")
    public ResponseEntity<Void> deleteTransaction(
            @PathVariable @ApiParam(value = "ID da transação.", example = "3") final long transactionId) {
        service.delete(transactionId);
        return ResponseEntity.noContent().build();
    }

}
