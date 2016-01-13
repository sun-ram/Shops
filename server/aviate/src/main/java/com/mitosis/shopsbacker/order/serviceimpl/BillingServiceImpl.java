package com.mitosis.shopsbacker.order.serviceimpl;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.admin.service.MerchantService;
import com.mitosis.shopsbacker.admin.service.StoreService;
import com.mitosis.shopsbacker.model.Billing;
import com.mitosis.shopsbacker.model.BillingPayment;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.SalesOrder;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.order.dao.BillingDao;
import com.mitosis.shopsbacker.order.service.BillingService;
import com.mitosis.shopsbacker.order.service.SalesOrderService;
import com.mitosis.shopsbacker.order.service.TransactionService;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.HashGeneratorUtils;
import com.mitosis.shopsbacker.util.TransactionStatus;
import com.mitosis.shopsbacker.util.TransactionType;
import com.mitosis.shopsbacker.vo.admin.MerchantVo;
import com.mitosis.shopsbacker.vo.admin.StoreVo;
import com.mitosis.shopsbacker.vo.order.BillingVo;
import com.mitosis.shopsbacker.vo.order.SalesOrderVo;
import com.mitosis.shopsbacker.vo.order.TransactionDetailVo;

@Service("billingServiceImpl")
public class BillingServiceImpl implements BillingService<T>, Serializable  {
	final static Logger log = Logger.getLogger(SalesOrderServiceImpl.class
			.getName());
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	MerchantService<T> merchantService;

	@Autowired
	StoreService<T> storeService;
	
	@Autowired
	SalesOrderService<T> salesOrderService;
	
	@Autowired
	TransactionService<T> transactionService;
	
	@Autowired
	BillingDao<T> billingDao;
	
	public BillingDao<T> getBillingDao() {
		return billingDao;
	}

	public void setBillingDao(BillingDao<T> billingDao) {
		this.billingDao = billingDao;
	}

	BillingVo billingVo = null;
	StoreVo storeVo = null;
	MerchantVo merchantVo = null;
	SalesOrderVo salesOrderVo = null;
	
	public List<Billing> getBillsByMerchant(Merchant merchant,char isPaid){
		return getBillingDao().getBillsByMerchant(merchant, isPaid);
	}
	
	public void addNewBill(SalesOrder salesOrder){
		
		Billing billing = new Billing();
		BigDecimal feesPercent = salesOrder.getMerchant().getFeesPercentage();
		BigDecimal amount = salesOrder.getAmount();
		BigDecimal fees = (feesPercent.multiply(amount)).divide(new BigDecimal(100));
		fees = fees.setScale(2, RoundingMode.HALF_UP);
		billing.setAmount(amount);
		billing.setFees(fees);
		billing.setIsactive('Y');
		billing.setIsPaid('N');
		billing.setMerchant(salesOrder.getMerchant());
		billing.setOrderedDate(salesOrder.getCreated());
		billing.setSalesOrder(salesOrder);
		billing.setStore(salesOrder.getStore());
		
		billingDao.addNewBill(billing);
	}
	
	@Override
	public BillingVo setBillingVo(Billing billing) throws Exception {
		
		billingVo = new BillingVo();
		Merchant merchant = billing.getMerchant(); 
		Store store = billing.getStore();
		SalesOrder salesOrder = billing.getSalesOrder();
		
		if(merchant != null){
			merchantVo = merchantService.setMerchantVo(merchant);
			billingVo.setMerchant(merchantVo);
		}
		if(store != null){
			storeVo = storeService.setStoreVo(store);
			billingVo.setStore(storeVo);
		}
		if(salesOrder != null){
			salesOrderVo = salesOrderService.setSalesOrderVo(salesOrder, false);
			billingVo.setSalesOrder(salesOrderVo);
		}
		billingVo.setBillingId(billing.getBillingId());
		billingVo.setAmount(billing.getAmount());
		billingVo.setFees(billing.getFees());
		billingVo.setIsPaid(billing.getIsPaid());
		String orderedDate = CommonUtil.dateToString(billing.getOrderedDate());
		billingVo.setOrderedDate(orderedDate);
		String paidDate = CommonUtil.dateToString(billing.getPaidDate());
		billingVo.setPaidDate(paidDate);
		
		return billingVo;
	}

	@Override
	public TransactionDetailVo setTransactionDetails(List<Billing> billingList) throws Exception {
		
		TransactionDetailVo transaction = new TransactionDetailVo();
		// TODO: Need clarification this field move to db or property file
		Properties properties = new Properties();
		try {
			properties.load(getClass().getResourceAsStream(
					"/properties/serverurl.properties"));
		} catch (IOException e) {
			log.error(e);
			e.printStackTrace();
		}
		BigDecimal amount = new BigDecimal(0);	
		Merchant merchant = null;
		List<String> billingIds= new ArrayList<String>();
		for (Billing bills : billingList){
			String id = bills.getBillingId();
			billingIds.add(id);
			Billing bill = billingDao.getBillingById(id);
			merchant = bill.getMerchant();
			BigDecimal fees = bill.getFees();
			amount = amount.add(fees);
		}
		
		BillingPayment billingPayment=(BillingPayment)CommonUtil.setAuditColumnInfo(BillingPayment.class.getName(), null);
		billingPayment.setAmount(amount);
		billingPayment.setIsactive('Y');
		billingPayment.setMerchant(merchant);
		billingDao.saveBillingPayment(billingPayment);
		
		//add billing payment in billing
		billingDao.update(billingIds,billingPayment);
		
		
		String secret_key = "2abfa552700f8cf9b9cbdb22909dacfa";
		int account_Id = 18984;
		String algorithm = "MD5";
		String mode = "LIVE";
		int channel = 0; // 0-standerd mode 1-Direct mode

		// HDFC Merchant account Id
		transaction.setAccountId(account_Id);
		transaction.setAddress("Gokul Nagar");
		// Secure transaction algorithm type
		transaction.setAlgo(algorithm);
		transaction.setAmount(amount);
		transaction.setChannel(channel);
		transaction.setCity("Chennai (Madras)");
		transaction.setCountry("IND");
		transaction.setCurrency("INR");
		transaction.setCurrencyCode("INR");
		transaction.setDescription(billingPayment.getBillingPaymentId());
		// TODO: need to clarify with HDFC
		transaction.setEmail("info@mitosistech.com");
		transaction.setMode(mode);
		transaction.setName("Mitosistech");
		transaction.setPhone("12345");
		transaction.setPostalCode("600073");
		transaction.setReferenceNo("12345");
		transaction.setReturnUrl(properties
				.getProperty("paymentGatewayRedirectUrlForBilling"));
		/*
		 * transaction.setShipAddress(shippingAddress.getAddress1()+(shippingAddress
		 * .getAddress2()!=null ? ", "+shippingAddress.getAddress2() : ""));
		 * transaction.setShipCity(shippingAddress.getCity().getName());
		 * transaction.setShipCountry(shippingAddress.getCountry().getCode());
		 * transaction.setShipName(salesOrder.getStore().getName());
		 * transaction.setShipPhone(shippingAddress.getPhoneNo());
		 * transaction.setShipPostalCode(shippingAddress.getPinCode());
		 * transaction.setShipState(shippingAddress.getState().getName());
		 */
		transaction.setState("Tamil Nadu");

		return generateHashCode(transaction, secret_key);
	}

	private TransactionDetailVo generateHashCode(
			TransactionDetailVo transaction, String secret_key) {
		String hashMessage = "";
		String message = secret_key + "|" + transaction.getAccountId() + "|"
				+ transaction.getAddress() + "|" + transaction.getAlgo() + "|"
				+ transaction.getAmount() + "|" + transaction.getChannel()
				+ "|" + transaction.getCity() + "|" + transaction.getCountry()
				+ "|" + transaction.getCurrency() + "|"
				+ transaction.getCurrencyCode() + "|"
				+ transaction.getDescription() + "|" + transaction.getEmail()
				+ "|" + transaction.getMode() + "|" + transaction.getName()
				+ "|" + transaction.getPhone() + "|"
				+ transaction.getPostalCode() + "|"
				+ transaction.getReferenceNo() + "|"
				+ transaction.getReturnUrl() + "|" + transaction.getState();
		/*
		 * +"|"+transaction.getShipAddress() +"|"+transaction.getShipCity()
		 * +"|"+transaction.getShipCountry() +"|"+transaction.getShipName()
		 * +"|"+((transaction.getShipPhone() != null) ?
		 * transaction.getShipPhone()+"|":"") +transaction.getShipPostalCode()
		 * +"|"+transaction.getShipState()
		 */
		try {
			hashMessage = HashGeneratorUtils.generateMD5(message);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		transaction.setSecureHash(hashMessage.toUpperCase());
		return transaction;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean updateBilling(String billingNo,String paymentId,
			String paymentMethod, String requestId, String transactionNo,
			String responseCode, String responseMessage, String referenceNo) {
	boolean flag = false;
	try{
		// TODO: need to update in billing table
		BillingPayment billingPayment = billingDao.getBillingPaymentById(billingNo);
		transactionService.save(billingPayment.getBillingPaymentId(),
				transactionNo, paymentMethod, requestId,
				billingPayment.getAmount(), billingPayment.getMerchant(),
				paymentId, TransactionStatus.SUCCESS,
				null, responseCode, responseMessage,
				TransactionType.BILLING_FOR_COD, referenceNo);
		
		billingDao.update(billingPayment);
	
	} catch (Exception e) {
		e.printStackTrace();
	}
	return flag;
	}

}
