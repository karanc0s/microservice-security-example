package com.karan.accountsservice.mapper;

import com.karan.accountsservice.dto.AccountsDTO;
import com.karan.accountsservice.entity.Account;

public class AccountMapper {
    public static AccountsDTO mapToAccountsDto(Account accounts, AccountsDTO accountsDto) {
        accountsDto.setAccountNumber(accounts.getAccountNumber());
        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setBranchAddress(accounts.getBranchAddress());
        return accountsDto;
    }

    public static Account mapToAccounts(AccountsDTO accountsDto, Account accounts) {
        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        return accounts;
    }
}