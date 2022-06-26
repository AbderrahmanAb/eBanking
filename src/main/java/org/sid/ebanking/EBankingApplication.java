package org.sid.ebanking;

import org.sid.ebanking.dtos.BankAccountDTO;
import org.sid.ebanking.dtos.CustomerDTO;
import org.sid.ebanking.entities.*;
import org.sid.ebanking.enums.AccountStatus;
import org.sid.ebanking.enums.OperationType;
import org.sid.ebanking.exeptions.BalanceNotSufficentException;
import org.sid.ebanking.exeptions.BanlAccountNotFountException;
import org.sid.ebanking.exeptions.CustomerNotFoundException;
import org.sid.ebanking.repositories.AccountOperationRepository;
import org.sid.ebanking.repositories.BankAccountRepository;
import org.sid.ebanking.repositories.CustomerRepository;
import org.sid.ebanking.services.BankAccountService;
import org.sid.ebanking.services.BankService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EBankingApplication {


	public static void main(String[] args) {
		SpringApplication.run(EBankingApplication.class, args);
	}
	//@Bean
	/*CommandLineRunner start(BankAccountService bankAccountService){
		return args -> {
			Stream.of("Hassan","Mohammed","Imane").forEach(name->{
				CustomerDTO customer=new CustomerDTO();
				customer.setName(name);
				customer.setEmail(name+"@email.com");
				bankAccountService.saveCustomer(customer);
			});
			bankAccountService.listeCustomer().forEach(customer->{
				try {
					bankAccountService.savingsavingBankAccount(Math.random()*12000,5.5,customer.getId());
					bankAccountService.savingcurrentBankAccount(Math.random()*9000,9000, customer.getId());
					List<BankAccountDTO> bankAccounts= bankAccountService.bankAccountList();
					for(BankAccountDTO bankAccount:bankAccounts){
						for (int i = 0; i < 10; i++) {
							bankAccountService.credit(bankAccount.getId(),10000+Math.random()*120000,"Credit");
							bankAccountService.debit(bankAccount.getId(), 1000,"Debit");
						}

					}
				} catch (CustomerNotFoundException e) {
					e.printStackTrace();
				} catch (BanlAccountNotFountException e) {
					e.printStackTrace();
				} catch (BalanceNotSufficentException e) {
					e.printStackTrace();
				}
			});

		};
	}*/
	//@Bean
	CommandLineRunner start(CustomerRepository customerRepository, BankAccountRepository bankAccountRepository, AccountOperationRepository accountOperationRepository){
		return args -> {
			Stream.of("hassan","yassin","Aicha").forEach(name->{
				Customer customer = new Customer();
				customer.setName(name);
				customer.setEmail(name+"@gmail.com");
				customerRepository.save(customer);

			});
			customerRepository.findAll().forEach(cust->{
				CurrentAccount currentAccount = new CurrentAccount();
				currentAccount.setBalance(Math.random()*90000);
				currentAccount.setId(UUID.randomUUID().toString());
				currentAccount.setCustomer(cust);
				currentAccount.setCreatedAt(new Date());
				currentAccount.setStatus(AccountStatus.CREATED);
				currentAccount.setOverDraft(9000);
				bankAccountRepository.save(currentAccount);

				SavingAccount savingAccount = new SavingAccount();
				savingAccount.setBalance(Math.random()*90000);
				savingAccount.setId(UUID.randomUUID().toString());
				savingAccount.setCustomer(cust);
				savingAccount.setCreatedAt(new Date());
				savingAccount.setStatus(AccountStatus.CREATED);
				savingAccount.setInterestRate(5.5);
				bankAccountRepository.save(savingAccount);


			});
			bankAccountRepository.findAll().forEach(acc->{
				for (int i = 0; i < 10; i++) {
					AccountOperation accountOperation = new AccountOperation();
					accountOperation.setOperationDate(new Date());
					accountOperation.setType(Math.random()>0.5? OperationType.DEBIT:OperationType.CREDIT);
					accountOperation.setBankAccount(acc);
					accountOperationRepository.save(accountOperation);


				}

			});



		};
	}

}
