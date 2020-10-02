package com.rabo.demoron.userinfo;

import com.rabo.demoron.FatalDemoRonException;
import com.rabo.demoron.repos.Authorization;
import com.rabo.demoron.repos.CardStatus;
import com.rabo.demoron.repos.CardType;
import com.rabo.demoron.repos.account.Account;
import com.rabo.demoron.repos.account.AccountRepo;
import com.rabo.demoron.repos.debitcard.DebitCard;
import com.rabo.demoron.repos.debitcard.DebitCardRepo;
import com.rabo.demoron.repos.poa.PowerOfAttorney;
import com.rabo.demoron.repos.poa.PowerOfAttorneyRepo;
import com.rabo.demoron.userinfo.model.AccountVO;
import com.rabo.demoron.userinfo.model.DebitCardVO;
import com.rabo.demoron.userinfo.model.UserInfoVO;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetUserInfo {

    private static final Logger logger = LoggerFactory.getLogger(GetUserInfo.class);

    private PowerOfAttorneyRepo powerOfAttorneyRepo;
    private AccountRepo accountRepo;
    private DebitCardRepo debitCardRepo;

    @Autowired
    public GetUserInfo(PowerOfAttorneyRepo powerOfAttorneyRepo,
                       AccountRepo accountRepo,
                       DebitCardRepo debitCardRepo){
        this.accountRepo = accountRepo;
        this.powerOfAttorneyRepo = powerOfAttorneyRepo;
        this.debitCardRepo = debitCardRepo;
    }

    public UserInfoVO execute(String user){

        if(user == null || user.isEmpty()){
            throw new FatalDemoRonException("USER CANNOT BE EMPTY");
        }

        val userInfoVOBuilder = UserInfoVO.builder();
        List<PowerOfAttorney> allPowerOfAttorneys = powerOfAttorneyRepo.findAll().stream()
                .filter(powerOfAttorney -> powerOfAttorney.getGrantee() != null && user.equals(powerOfAttorney.getGrantee()))
                .collect(Collectors.toUnmodifiableList());

        addAccounts(userInfoVOBuilder, allPowerOfAttorneys);
        addDebitCards(userInfoVOBuilder, allPowerOfAttorneys);
        //TODO CreditCards. Of Course Cards is a superclass of DebitCards and CreditCards.

        return userInfoVOBuilder.build();
    }

    private void addDebitCards(UserInfoVO.UserInfoVOBuilder userInfoVOBuilder,
                               List<PowerOfAttorney> allPowerOfAttorneys){

        List<DebitCardVO> debitCards = allPowerOfAttorneys.stream()
                .filter(powerOfAttorney -> powerOfAttorney.getAuthorizations().contains(Authorization.DEBIT_CARD))
                .flatMap(powerOfAttorney -> powerOfAttorney.getCards().stream())
                .filter(cardReference -> cardReference.getType() == CardType.DEBIT_CARD)
                .map(cardReference -> debitCardRepo.findById(cardReference.getId()))
                .filter(debitCard -> CardStatus.ACTIVE == debitCard.getStatus())
                .map(GetUserInfo::mapDebitCard)
                .collect(Collectors.toUnmodifiableList());

        userInfoVOBuilder.debitCards(
                debitCards);

    }

    private static DebitCardVO mapDebitCard(DebitCard debitCard){
        return DebitCardVO.builder()
                .cardNumber(debitCard.getCardNumber())
                .build();
    }

    private void addAccounts(UserInfoVO.UserInfoVOBuilder userInfoVOBuilder,
                             List<PowerOfAttorney> allPowerOfAttorneys){

        //add all accounts.
        List<AccountVO> accounts = allPowerOfAttorneys.stream()
                .map(powerOfAttorney -> powerOfAttorney.getAccount().substring(8, 17)) //TODO maak nullsafe or implement findByIBAN in accountRepo
                .map(accountId -> accountRepo.findById(accountId))
                .filter(account -> account.getEnded()==null || account.getEnded().after(new Date())) // filter ended accounts
                .filter(account -> account.getCreated()!=null && account.getCreated().before(new Date()))//filter created accounts in future
                .map(GetUserInfo::mapAccount)
                .collect(Collectors.toUnmodifiableList());
        //TODO check if the granter is the account owner.

        userInfoVOBuilder.accounts(accounts);
    }

    private static AccountVO mapAccount(Account account){
        return AccountVO.builder()
                .balance(account.getBalance())
                .created(account.getCreated())
                .ended(account.getEnded())
                .owner(account.getOwner())
                .build();
    }
}
