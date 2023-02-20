package org.gunitha.sitemanagementsystem.service.account;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.gunitha.sitemanagementsystem.controller.beans.AccountBean;
import org.gunitha.sitemanagementsystem.controller.beans.AddressBean;
import org.gunitha.sitemanagementsystem.controller.user.UserBean;
import org.gunitha.sitemanagementsystem.model.Address;
import org.gunitha.sitemanagementsystem.model.AddressType;
import org.gunitha.sitemanagementsystem.model.account.Account;
import org.gunitha.sitemanagementsystem.model.role.PrivilegeType;
import org.gunitha.sitemanagementsystem.model.role.RoleLevel;
import org.gunitha.sitemanagementsystem.repository.account.AccountRepository;
import org.gunitha.sitemanagementsystem.service.user.AccountUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	ApplicationService applicationService;

	@Autowired
	AccountUserService accountUserService;

	@Override
	public Account register(Account account) {
		account = accountRepository.save(account);
		accountUserService.register(new UserBean(null, account.getNumber(), account.getNumber(), account.getNumber(),
				account.getEmail(), account.getEmail(), account.getEmail(), account.getEmail(), account.getPhone(),
				RoleLevel.ADMIN.name(), PrivilegeType.WRITE.name(),
				new AddressBean(null, account.getAddress().getName(), account.getAddress().getNumber(),
						account.getAddress().getAddressLine1(), account.getAddress().getAddressLine2(),
						account.getAddress().getAddressLine3(), account.getAddress().getVillage(),
						account.getAddress().getCity(), account.getAddress().getState(),
						account.getAddress().getCountry(), account.getAddress().getZipCode()),
				null, account.getApplication().getId(), account.getId(), null));
		return account;
	}

	@Override
	public Account findByName(String name) {
		return accountRepository.findByName(name).get(0);
	}

	@Override
	public Account findById(Long id) {
		return accountRepository.findById(id).get();
	}

	@Override
	public Account transformToAccount(AccountBean accountBean, Long applicationId) {
		Address address = new Address(accountBean.getAddress().getNumber(), accountBean.getAddress().getNumber(),
				accountBean.getAddress().getAddressLine1(), accountBean.getAddress().getAddressLine2(),
				accountBean.getAddress().getAddressLine3(), null, accountBean.getAddress().getCity(),
				accountBean.getAddress().getState(), accountBean.getAddress().getCountry(),
				accountBean.getAddress().getZipCode(), AddressType.CURRENT);

		Account account = new Account(accountBean.getName(), accountBean.getNumber(), accountBean.getEmail(),
				accountBean.getPhone(), address, applicationService.findById(applicationId));

		return account;
	}

	@Override
	public Account transformToAccount(AccountBean accountBean) {
		Optional<Account> findById = accountRepository.findById(accountBean.getId());
		Account account = findById.get();
		account.setName(accountBean.getName());
		account.setNumber(accountBean.getNumber());
		account.setEmail(accountBean.getEmail());
		account.setPhone(accountBean.getPhone());
		account.getAddress().setName(accountBean.getAddress().getName());
		account.getAddress().setNumber(accountBean.getAddress().getNumber());
		account.getAddress().setAddressLine1(accountBean.getAddress().getAddressLine1());
		account.getAddress().setAddressLine2(accountBean.getAddress().getAddressLine2());
		account.getAddress().setAddressLine3(accountBean.getAddress().getAddressLine3());
		account.getAddress().setCity(accountBean.getAddress().getCity());
		account.getAddress().setState(accountBean.getAddress().getState());
		account.getAddress().setCountry(accountBean.getAddress().getCountry());
		account.getAddress().setZipCode(accountBean.getAddress().getZipCode());
		return account;
	}

	@Override
	public Account update(Account account) {
		Account savedAccount = accountRepository.save(account);
		return savedAccount;
	}

	@Override
	public List<Account> findAll() {
		return accountRepository.findAll();
	}

}
