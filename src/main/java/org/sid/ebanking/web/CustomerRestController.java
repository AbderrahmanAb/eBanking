package org.sid.ebanking.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sid.ebanking.dtos.AccountOperationDTO;
import org.sid.ebanking.dtos.CustomerDTO;
import org.sid.ebanking.entities.Customer;
import org.sid.ebanking.exeptions.CustomerNotFoundException;
import org.sid.ebanking.services.BankAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class CustomerRestController {
    private BankAccountService bankAccountService;
    @GetMapping("/customers")
    public List<CustomerDTO> customer(){
        return bankAccountService.listeCustomer();
    }
    @GetMapping("/customers/search")
    public List<CustomerDTO> searchcustomers(@RequestParam(name = "keyword",defaultValue= "") String keyword){
        return bankAccountService.searchCustomer("%"+keyword+"%");
    }
    @GetMapping("/customers/{id}")
    public CustomerDTO getCustomer(@PathVariable(name = "id") Long customerId) throws CustomerNotFoundException {
        return bankAccountService.geteCustomer(customerId);

    }

    @PostMapping("/customers")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
       return bankAccountService.saveCustomer(customerDTO);
    }
    @PutMapping("/custmers/{customerId}")
    public CustomerDTO updateCustomer(@PathVariable Long customerId,@RequestBody CustomerDTO customerDTO){
        customerDTO.setId(customerId);
        return bankAccountService.updateCustomer(customerDTO);
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable Long id){
        bankAccountService.deleteCustomer(id);
    }


}
