package com.ibm.fitnesse.account.demo.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface AccountDao {

   @SqlUpdate("INSERT INTO ACCOUNT (ACCOUNT_NUMER, BALANCE, CUSTOMER_ID) VALUES (acct_seq.NEXTVAL, :balance, :customerId)")
   int createAccount(@Bind("balance") int balance, @Bind("customerId") int customerId);
   
   @SqlUpdate("UPDATE ACCOUNT SET BALANCE = :balance WHERE ACCOUNT_NUMER = :accountNumber ")
   int updateAccount(@Bind("balance") int balance, @Bind("accountNumber") int accountNumber);
   
   @SqlQuery("SELECT BALANCE FROM ACCOUNT WHERE CUSTOMER_ID = :customerId AND ACCOUNT_NUMER = :accountNumber ")
   int getBalance(@Bind("customerId") int customerId, @Bind("accountNumber") int accountNumber);
   
   @SqlQuery("SELECT ACCOUNT_NUMER FROM ACCOUNT WHERE CUSTOMER_ID = :customerId ")
   int getAccountNumberByCustomerId(@Bind("customerId") int customerId);
}
