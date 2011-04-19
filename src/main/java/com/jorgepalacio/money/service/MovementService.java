package com.jorgepalacio.money.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import com.jorgepalacio.money.dao.impl.JpaDaoTemplate;
import com.jorgepalacio.money.model.Account;
import com.jorgepalacio.money.model.Movement;
import com.jorgepalacio.money.model.MovementDetail;

public class MovementService {
	private UserService userService;
	private AccountService accountService;
	JpaDaoTemplate<Movement> movementDao;

	@Autowired
	public void setMovementDao(
			@Qualifier("movementDao") JpaDaoTemplate<Movement> movementDao) {
		this.movementDao = movementDao;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Autowired
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	public List<Movement> lastMovements() {
		return movementDao.pagedByNamedQuery(20, "movement.byUser",
				userService.getCurrentUser()).firstPage();
	}

	@Transactional(readOnly = false)
	public void registerMovement(final Movement movement) {
		movement.setClient(userService.getCurrentUser());

		for (MovementDetail detail : movement.getDetails()) {
			double value = detail.getAmount();
			Account account = detail.getAccount();

			value *= account.getAccountType().isAsset()
					&& !movement.isExpense() ? 1 : -1;
			account.setCurrentAmount(account.getCurrentAmount() + value);
			accountService.save(account);
			detail.setMovement(movement);
		}
		movementDao.add(movement);
	}
}
