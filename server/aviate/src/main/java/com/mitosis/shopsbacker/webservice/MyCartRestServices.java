package com.mitosis.shopsbacker.webservice;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mitosis.shopsbacker.admin.service.StoreService;
import com.mitosis.shopsbacker.customer.service.CustomerService;
import com.mitosis.shopsbacker.customer.service.MyCartService;
import com.mitosis.shopsbacker.inventory.service.ProductService;
import com.mitosis.shopsbacker.model.Customer;
import com.mitosis.shopsbacker.model.MyCart;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.responsevo.MyCartResponseVo;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.vo.customer.MyCartVo;

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
	public MyCartResponseVo addToCart(MyCartVo myCartVo) throws Exception {
		MyCartResponseVo myCartResponseVo = new MyCartResponseVo();
		MyCart myCart = new MyCart();
		myCart = setMycartDetails(myCartVo);
		myCart.setIsactive('Y');
		myCartService.addToCart(myCart);
		if (myCart.getMyCartId() != null) {
			myCartResponseVo.setStatus(SBMessageStatus.SUCCESS.getValue());
		} else {
			myCartResponseVo.setStatus(SBMessageStatus.FAILURE.getValue());
		}
		return myCartResponseVo;

	}

	@Path("/removefromcart")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public MyCartResponseVo deletefromcart(MyCartVo myCartVo) {
		MyCartResponseVo myCartResponseVo = new MyCartResponseVo();
		MyCart myCart = new MyCart();
		myCart = myCartService.getCartDetailFromId(myCartVo.getMyCartId());
		if (myCart != null) {
			myCartService.removeFromCart(myCart);
			myCartResponseVo.setStatus(SBMessageStatus.SUCCESS.getValue());
		} else {
			myCartResponseVo.setStatus(SBMessageStatus.FAILURE.getValue());
		}
		return myCartResponseVo;

	}

	@Path("/getMyCartList")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public MyCartResponseVo getMyCartList(MyCartVo myCartVo) {
		MyCartResponseVo myCartResponseVo = new MyCartResponseVo();
		List<MyCart> myCart = new ArrayList<MyCart>();
		Customer customer = new Customer();
		Store store = new Store();
		customer = customerService.getCustomerInfoById(myCartVo.getCustomer()
				.getCustomerId());
		store = storeService.getStoreById(myCartVo.getStore().getStoreId());
		if (customer != null && store != null) {
			myCart = myCartService.getMyCartList(customer, store);
		}
		return myCartResponseVo;

	}

	public MyCart setMycartDetails(MyCartVo myCartVo) throws Exception {
		MyCart myCartDetails = (MyCart) CommonUtil
				.setAuditColumnInfo(MyCart.class.getName());
		myCartDetails.getProduct().setProductId(
				myCartVo.getProduct().getProductId());
		myCartDetails.getStore().setStoreId(myCartVo.getStore().getStoreId());
		myCartDetails.getCustomer().setCustomerId(
				myCartVo.getCustomer().getCustomerId());
		return myCartDetails;

	}
}
