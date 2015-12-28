package com.mitosis.shopsbacker.webservice;

import java.math.BigDecimal;
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
import com.mitosis.shopsbacker.inventory.service.DiscountService;
import com.mitosis.shopsbacker.inventory.service.ProductService;
import com.mitosis.shopsbacker.model.Customer;
import com.mitosis.shopsbacker.model.Discount;
import com.mitosis.shopsbacker.model.MyCart;
import com.mitosis.shopsbacker.model.Product;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.responsevo.MyCartResponseVo;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.customer.AddToCartRequestVo;
import com.mitosis.shopsbacker.vo.customer.MyCartProductVo;
import com.mitosis.shopsbacker.vo.customer.MyCartVo;
import com.mitosis.shopsbacker.vo.inventory.ProductVo;
import com.sun.imageio.plugins.common.BogusColorSpace;

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
	
	@Autowired
	DiscountService<T> discountService;

	@Path("/addtocart")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String addToCart(MyCartVo myCartVo) throws Exception {
		ResponseModel response = new ResponseModel();
		try{
			String customerId = null;
			String storeId = null;
			String productId = null;
			String discountId = null;
			
			if(myCartVo.getProductId() != null){
				productId = myCartVo.getProductId();
			}else {
				productId = myCartVo.getProduct().getProductId();
			}
			
			if(myCartVo.getStoreId() != null){
				storeId = myCartVo.getStoreId();
			}else {
				storeId = myCartVo.getStore().getStoreId();
			}
			
			if(myCartVo.getCustomerId() != null){
				customerId = myCartVo.getCustomerId();
			}else {
				customerId = myCartVo.getCustomer().getCustomerId();
			}

			
			Product product = productService.getProduct(productId);
			Store store = storeService.getStoreById(storeId);
			Customer customer = customerService.getCustomerInfoById(customerId);
			MyCart myCart = myCartService.getCartByCustomerStoreanProductId(customer, product, store);
			if(myCart == null){
				myCart = (MyCart) CommonUtil.setAuditColumnInfo(MyCart.class.getName(), null);
				myCart.setProduct(product);
				myCart.setStore(store);
				myCart.setCustomer(customer);
				myCart.setMerchant(product.getMerchant());
				myCart.setQty(myCartVo.getQty());
				myCart.setIsactive('Y');
				if(myCartVo.getDiscountId() != null){
					discountId = myCartVo.getDiscountId();
					Discount discount = discountService.getDiscountById(discountId);
					myCart.setDiscount(discount);
				}
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
	
	@Path("/addproductstocart")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String addProductsToCart(AddToCartRequestVo addToCartRequestVo) throws Exception {
		ResponseModel response = new ResponseModel();
		try{
			
			Customer customer = customerService.getCustomerInfoById(addToCartRequestVo.getCustomerId());
			Store store = storeService.getStoreById(addToCartRequestVo.getStoreId());
			List<MyCartProductVo> prodcuts = addToCartRequestVo.getProducts();
			for(MyCartProductVo productVo:prodcuts){
				Product product = productService.getProduct(productVo.getProductId());
				MyCart myCart = myCartService.getCartByCustomerStoreanProductId(customer, product, store);
				if(myCart == null){
					myCart = (MyCart) CommonUtil.setAuditColumnInfo(MyCart.class.getName(), null);
					myCart.setProduct(product);
					myCart.setStore(store);
					myCart.setCustomer(customer);
					myCart.setMerchant(product.getMerchant());
					myCart.setQty(productVo.getQty());
					myCart.setIsactive('Y');
					myCartService.addToCart(myCart);
				} else {
					myCart.setQty(productVo.getQty());
					myCart.setUpdated(new Date());
					myCartService.updateCart(myCart);
				}
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
			String customerId = null;
			String storeId = null;
			String productId = null;
			
			if(myCartVo.getProductId() != null){
				productId = myCartVo.getProductId();
			}else {
				productId = myCartVo.getProduct().getProductId();
			}
			
			if(myCartVo.getStoreId() != null){
				storeId = myCartVo.getStoreId();
			}else {
				storeId = myCartVo.getStore().getStoreId();
			}
			
			if(myCartVo.getCustomerId() != null){
				customerId = myCartVo.getCustomerId();
			}else {
				customerId = myCartVo.getCustomer().getCustomerId();
			}
			Product product = productService.getProduct(productId);
			Store store = storeService.getStoreById(storeId);
			Customer customer = customerService.getCustomerInfoById(customerId);
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
			String customerId = null;
			String storeId = null;
			if(myCartVo.getStoreId() != null){
				storeId = myCartVo.getStoreId();
			}else {
				storeId = myCartVo.getStore().getStoreId();
			}
			
			if(myCartVo.getCustomerId() != null){
				customerId = myCartVo.getCustomerId();
			}else {
				customerId = myCartVo.getCustomer().getCustomerId();
			}
			customer = customerService.getCustomerInfoById(customerId);
			store = storeService.getStoreById(storeId);
			if (customer != null && store != null) {
				myCart = myCartService.getMyCartList(customer, store);
				for(MyCart mycart : myCart){
					MyCartVo mycartvo = new MyCartVo();
					ProductVo productVo = productService.setProductVo(mycart.getProduct());
					mycartvo.setProduct(productVo);
					mycartvo.setQty(mycart.getQty());
					mycartvo.setMyCartId(mycart.getMyCartId());
					if(productVo.getDiscount() != null){
						if(productVo.getDiscount().getDiscountPercentage() != null){
							Double price = productVo.getPrice().doubleValue() - (productVo.getPrice().doubleValue() * productVo.getDiscount().getDiscountPercentage())/100;
							mycartvo.setDiscountPrice(price);
						}
						if(productVo.getDiscount().getDiscountAmount() != null){
							Double price = productVo.getPrice().doubleValue() - productVo.getDiscount().getDiscountAmount().doubleValue();
							mycartvo.setDiscountPrice(price);
						}
					}else{
						mycartvo.setDiscountPrice(productVo.getPrice().doubleValue());
					}
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
				.setAuditColumnInfo(MyCart.class.getName(), null);
		Product product = productService.getProduct(myCartVo.getProduct().getProductId());
		myCartDetails.setProduct(product);
		myCartDetails.setStore(storeService.getStoreById(myCartVo.getStore().getStoreId()));
		myCartDetails.setCustomer(customerService.getCustomerInfoById(myCartVo.getCustomer().getCustomerId()));
		myCartDetails.setMerchant(product.getMerchant());
		myCartDetails.setQty(myCartVo.getQty());
		return myCartDetails;
	}
	
}
