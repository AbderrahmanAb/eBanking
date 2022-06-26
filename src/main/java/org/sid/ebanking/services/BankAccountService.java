package org.sid.ebanking.services;

import org.sid.ebanking.dtos.*;
import org.sid.ebanking.entities.BankAccount;
import org.sid.ebanking.entities.CurrentAccount;
import org.sid.ebanking.entities.Customer;
import org.sid.ebanking.entities.SavingAccount;
import org.sid.ebanking.exeptions.BalanceNotSufficentException;
import org.sid.ebanking.exeptions.BanlAccountNotFountException;
import org.sid.ebanking.exeptions.CustomerNotFoundException;

import java.util.List;

public interface BankAccountService {
     CustomerDTO saveCustomer(CustomerDTO customerDTO);

     CurrentBankAccountDTO savingcurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFoundException;
     SavingBankAccountDTO savingsavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFoundException;

     List<CustomerDTO> listeCustomer();
     BankAccountDTO getBankAccount(String accountId) throws BanlAccountNotFountException;
     void debit(String accountId, double amount,String description) throws BanlAccountNotFountException, BalanceNotSufficentException;
     void credit(String accountId, double amount,String description) throws BanlAccountNotFountException, BalanceNotSufficentException;
     void transfer(String accountIdSource,String accountIdDestination,double amount) throws BanlAccountNotFountException, BalanceNotSufficentException;

     List<BankAccountDTO> bankAccountList();

     CustomerDTO geteCustomer(Long customerId) throws CustomerNotFoundException;

     CustomerDTO updateCustomer(CustomerDTO customerDTO);

     void deleteCustomer(Long customerId);

     List<AccountOperationDTO> accountHistory(String accountId) throws BanlAccountNotFountException;

     AccountHistoryDTO getAccountHistory(String accountId, int page, int size) throws BanlAccountNotFountException;

     List<CustomerDTO> searchCustomer(String keyword);
}
