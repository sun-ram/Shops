/**
 * 
 */
package com.mitosis.shopsbacker.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.common.service.AccountService;
import com.mitosis.shopsbacker.model.Account;
import com.mitosis.shopsbacker.responsevo.AccountResponseVo;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.util.SBSecurity;
import com.mitosis.shopsbacker.vo.common.AccountVo;

/**
 * @author Anbukkani Gajendiran
 *
 */
@Path("account")
@Controller("accountRestService")
public class AccountRestService {
	final static Logger log = Logger.getLogger(AccountRestService.class);

	@Autowired
	AccountService<T> accountService;

	@Path("/get")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String getAccount(AccountVo accountVo) {
		String responseStr = "";
		AccountResponseVo accountResponseVo = new AccountResponseVo();
		try {
			String accountNo = accountVo.getAccountNo();
			if (accountNo != null) {
				Account account = accountService.getAccountByNo(accountNo);
				AccountVo accountvo = new AccountVo();
				accountvo.setAccountNo(account.getAccountNo());
				accountvo.setAccountId(account.getAccountId());
				//String secKey = SBSecurity.decrypt(account.getSecKey());
				accountvo.setSecKey(account.getSecKey());
				accountResponseVo.setAccount(accountvo);
				accountResponseVo.setStatus(SBMessageStatus.SUCCESS.getValue());
			}
		} catch (Exception e) {
			String message = CommonUtil.getErrorMessage(e);
			accountResponseVo.setStatus(SBMessageStatus.FAILURE.getValue());
			accountResponseVo.setErrorString(message);
			log.error(message);
		}
		try {
			responseStr = CommonUtil.getObjectMapper(accountResponseVo);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return responseStr;
	}
}
