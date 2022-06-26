package org.sid.ebanking.web;

import lombok.AllArgsConstructor;
import org.sid.ebanking.dtos.*;
import org.sid.ebanking.entities.AccountOperation;
import org.sid.ebanking.exeptions.BalanceNotSufficentException;
import org.sid.ebanking.exeptions.BanlAccountNotFountException;
import org.sid.ebanking.services.BankAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class BankAccountRestAPI {
    private BankAccountService bankAccountService;
    @GetMapping("/accounts/{accountId}")
    public BankAccountDTO getBankAccount(@PathVariable String accountId) throws BanlAccountNotFountException {
        return bankAccountService.getBankAccount(accountId);

    }
    @GetMapping("/accounts")
    public List<BankAccountDTO> listAccount(){
        return bankAccountService.bankAccountList();
    }
    @GetMapping("/accounts/{accountId}/operations")
    public List<AccountOperationDTO> getHistory(@PathVariable String accountId) throws BanlAccountNotFountException {
        return bankAccountService.accountHistory(accountId);
    }
    @GetMapping("/accounts/{Id}/pageOperations")
    public AccountHistoryDTO getAccountHistory(@PathVariable String Id, @RequestParam(name = "page",defaultValue = "0") int page, @RequestParam(name = "size",defaultValue = "5") int size) throws BanlAccountNotFountException {

        return bankAccountService.getAccountHistory(Id,page,size);
    }
    @PostMapping("/accounts/debit")
    public DebitDTO debit(@RequestBody DebitDTO debitDTO) throws BanlAccountNotFountException, BalanceNotSufficentException {
        this.bankAccountService.debit(debitDTO.getAccountId(),debitDTO.getAmount(),debitDTO.getDescription());
        return debitDTO;
    }
    @PostMapping("/accounts/credit")
    public CreditDTO debit(@RequestBody CreditDTO creditDTO) throws BanlAccountNotFountException, BalanceNotSufficentException {
        this.bankAccountService.debit(creditDTO.getAccountId(),creditDTO.getAmount(),creditDTO.getDescription());
        return creditDTO;
    }
    @PostMapping("/accounts/transfer")
    public void  transfer(@RequestBody TransferRequestDTO transferRequestDTO) throws BanlAccountNotFountException, BalanceNotSufficentException {
        this.bankAccountService.transfer(transferRequestDTO.getAccountSource(),transferRequestDTO.getAccountDestination(),transferRequestDTO.getAmount());

    }


}
