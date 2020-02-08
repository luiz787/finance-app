package luiz787.finance.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
@ApiModel(description = "Representa uma transação financeira.")
public class Transaction {
    @Id
    @GeneratedValue
    @ApiModelProperty("Identificador da transação.")
    private Long id;
    @ApiModelProperty("Nome dado à transação.")
    private String name;
    @ApiModelProperty("Descrição da transação.")
    private String description;
    @ApiModelProperty("Timestamp de ocorrência da transação.")
    private LocalDateTime timestamp;
    @ApiModelProperty("Valor da transação.")
    private long amount;
}
