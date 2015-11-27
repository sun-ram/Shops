package com.mitosis.shopsbacker.webservice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mitosis.shopsbacker.admin.service.StoreService;
import com.mitosis.shopsbacker.customer.service.CustomerService;
import com.mitosis.shopsbacker.customer.service.MyCartService;
import com.mitosis.shopsbacker.inventory.service.ProductService;
import com.mitosis.shopsbacker.model.Customer;
import com.mitosis.shopsbacker.model.MyCart;
import com.mitosis.shopsbacker.model.Product;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.responsevo.MyCartResponseVo;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.customer.MyCartVo;
import com.mitosis.shopsbacker.vo.inventory.ProductVo;

@Path("mycart")
@Controller("myCartRestServices")
public class MyCartRestServices<T> {

	@Autowired
	CustomerService<T> customerService;

	@Autowired
	ProductService<T> productService;

	@Autowired
	StoreService<T> storeService;

	@Autowired
	MyCartService<T> myCartService;

	@Path("/addtocart")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String addToCart(MyCartVo myCartVo) throws Exception {
		ResponseModel response = new ResponseModel();
		try{
			Product product = productService.getProduct(myCartVo.getProduct().getProductId());
			Store store = storeService.getStoreById(myCartVo.getStore().getStoreId());
			Customer customer = customerService.getCustomerInfoById(myCartVo.getCustomer().getCustomerId());
			MyCart myCart = myCartService.getCartByCustomerStoreanProductId(customer, product, store);
			if(myCart == null){
				myCart = (MyCart) CommonUtil.setAuditColumnInfo(MyCart.class.getName());
				myCart.setProduct(product);
				myCart.setStore(store);
				myCart.setCustomer(customer);
				myCart.setMerchant(product.getMerchant());
				myCart.setQty(myCartVo.getQty());
				myCart.setIsactive('Y');
				myCartService.addToCart(myCart);
			} else {
				myCart.setQty(myCartVo.getQty());
				myCart.setUpdated(new Date());
				myCartService.updateCart(myCart);
			}
			if (myCart.getMyCartId() != null) {
				response.setStatus(SBMessageStatus.SUCCESS.getValue());
			} else {
				response.setStatus(SBMessageStatus.FAILURE.getValue());
			}
		}catch(Exception e){
			e.printStackTrace();
			response.setStatus(SBMessageStatus.FAILURE.getValue());
			response.setErrorString(e.getMessage());
		}
		return CommonUtil.getObjectMapper(response);
	}

	@Path("/removefromcart")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String deletefromcart(MyCartVo myCartVo) throws Exception {
		ResponseModel response = new ResponseModel();
		try{
			Product product = productService.getProduct(myCartVo.getProduct().getProductId());
			Store store = storeService.getStoreById(myCartVo.getStore().getStoreId());
			Customer customer = customerService.getCustomerInfoById(myCartVo.getCustomer().getCustomerId());
			MyCart myCart = myCartService.getCartByCustomerStoreanProductId(customer, product, store);
			if (myCart != null) {
				myCartService.removeFromCart(myCart);
				response.setStatus(SBMessageStatus.SUCCESS.getValue());
			} else {
				response.setStatus(SBMessageStatus.FAILURE.getValue());
			}
		}catch(Exception e){
			e.printStackTrace();
			response.setStatus(SBMessageStatus.FAILURE.getValue());
			response.setErrorString(e.getMessage());
		}
		
		return CommonUtil.getObjectMapper(response);
	}

	@Path("/getMyCartList")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String getMyCartList(MyCartVo myCartVo) throws Exception {
		MyCartResponseVo myCartResponseVo = new MyCartResponseVo();
		List<MyCart> myCart = new ArrayList<MyCart>();
		List<MyCartVo> myCartVoList = new ArrayList<MyCartVo>();
		Customer customer = new Customer();
		Store store = new Store();
		try {
			customer = customerService.getCustomerInfoById(myCartVo.getCustomer()
					.getCustomerId());
			store = storeService.getStoreById(myCartVo.getStore().getStoreId());
			if (customer != null && store != null) {
				myCart = myCartService.getMyCartList(customer, store);
				for(MyCart mycart : myCart){
					MyCartVo mycartvo = new MyCartVo();
					ProductVo productVo = productService.setProductVo(mycart.getProduct());
					mycartvo.setProduct(productVo);
					mycartvo.setQty(mycart.getQty());
					mycartvo.setMyCartId(mycart.getMyCartId());
					myCartVoList.add(mycartvo);
				}
			}
			myCartResponseVo.setMyCart(myCartVoList);
		} catch (Exception e) {
			e.printStackTrace();
			myCartResponseVo.setStatus(SBMessageStatus.FAILURE.getValue());
			myCartResponseVo.setErrorString(e.getMessage());
		}
		return CommonUtil.getObjectMapper(myCartResponseVo);

	}

	public MyCart setMycartDetails(MyCartVo myCartVo) throws Exception {
		MyCart myCartDetails = (MyCart) CommonUtil
				.setAuditColumnInfo(MyCart.class.getName());
		Product product = productService.getProduct(myCartVo.getProduct().getProductId());
		myCartDetails.setProduct(product);
		myCartDetails.setStore(storeService.getStoreById(myCartVo.getStore().getStoreId()));
		myCartDetails.setCustomer(customerService.getCustomerInfoById(myCartVo.getCustomer().getCustomerId()));
		myCartDetails.setMerchant(product.getMerchant());
		myCartDetails.setQty(myCartVo.getQty());
		return myCartDetails;
	}
	
}
