package com.mitosis.shopsbacker.webservice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import com.mitosis.shopsbacker.admin.service.StoreService;
import com.mitosis.shopsbacker.customer.service.CustomerService;
import com.mitosis.shopsbacker.customer.service.FavouriteService;
import com.mitosis.shopsbacker.customer.service.MyCartService;
import com.mitosis.shopsbacker.inventory.service.ProductService;
import com.mitosis.shopsbacker.model.Customer;
import com.mitosis.shopsbacker.model.Favourite;
import com.mitosis.shopsbacker.model.Merchant;
import com.mitosis.shopsbacker.model.MyCart;
import com.mitosis.shopsbacker.model.SalesOrder;
import com.mitosis.shopsbacker.model.SalesOrderLine;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.order.service.SalesOrderService;
import com.mitosis.shopsbacker.responsevo.FavouriteResponseVo;
import com.mitosis.shopsbacker.responsevo.MyCartResponseVo;
import com.mitosis.shopsbacker.responsevo.ProductResponseVo;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.vo.ResponseModel;
import com.mitosis.shopsbacker.vo.customer.FavouriteVo;
import com.mitosis.shopsbacker.vo.customer.MyCartVo;
import com.mitosis.shopsbacker.vo.inventory.ProductVo;

@Path("favourite")
@Controller("favouriteRestServices")
public class FavouriteRestService {
	
	final static Logger log = Logger.getLogger(UserRestServices.class
			.getName());
	
	@Autowired
	FavouriteService<T> favouriteService;
	
	@Autowired
	CustomerService<T> customerService;
	
	@Autowired
	StoreService<T> storeService;
	
	@Autowired
	SalesOrderService<T> salesOrderService;
	
	@Autowired
	MyCartService<T> myCartService;
	
	@Autowired
	ProductService<T> productService;

	@Path("/addfavourite")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public FavouriteResponseVo addFavourite(FavouriteVo favouriteVo) {
		FavouriteResponseVo favouriteResponseVo = new FavouriteResponseVo();
		Favourite favourite;
		try {
			favourite = favouriteService.getFavouriteByName(favouriteVo.getName(), favouriteVo.getCustomerId());
			if(favourite == null){
				favourite = (Favourite) CommonUtil.setAuditColumnInfo(Favourite.class.getName());
				favourite.setIsactive('Y');
				favourite = setFavourite(favourite,favouriteVo);
				favouriteService.saveFavourite(favourite);
			} else {
				favourite = setFavourite(favourite,favouriteVo);
				favouriteService.updateFavourite(favourite);
			}
			favouriteResponseVo.setStatus(SBMessageStatus.SUCCESS
					.getValue());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			favouriteResponseVo.setStatus(SBMessageStatus.FAILURE.getValue());
			favouriteResponseVo.setErrorString(e.toString());
			return favouriteResponseVo;
		}
		return favouriteResponseVo;
	}
	
	@Path("/getfavourite")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public FavouriteResponseVo userLogin(FavouriteVo favouriteVo) {
		FavouriteResponseVo favouriteResponseVo = new FavouriteResponseVo();
		try {
			Customer customer = customerService.getCustomerInfoById(favouriteVo.getCustomerId());
			Store store = storeService.getStoreById(favouriteVo.getStoreId());
			List<Favourite> favourites = favouriteService.getFavourites(customer, store);
			
			List<FavouriteVo> favouriteVoList=new ArrayList<FavouriteVo>();
			for (Favourite favourite : favourites) {
				FavouriteVo favouriteVos = favouriteService.setFavouriteVo(favourite);
				favouriteVoList.add(favouriteVos);
			}
			
			favouriteResponseVo.setFavourites(favouriteVoList);
			favouriteResponseVo.setStatus(SBMessageStatus.SUCCESS
					.getValue());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			favouriteResponseVo.setStatus(SBMessageStatus.FAILURE.getValue());
			favouriteResponseVo.setErrorString(e.toString());
			return favouriteResponseVo;
		}
		return favouriteResponseVo;
	}
	
	
	private Favourite setFavourite(Favourite favourite, FavouriteVo favouriteVo){
		
		SalesOrder salesOrder = salesOrderService.getSalesOrderById(favouriteVo.getSalesOrderId());
		favourite.setSalesOrder(salesOrder);
		favourite.setStore(salesOrder.getStore());
		favourite.setCustomer(salesOrder.getCustomer());;
		favourite.setMerchant(salesOrder.getMerchant());
		favourite.setName(favouriteVo.getName());
		return favourite;
	}
	
	
	@Path("/addFavouriteToCart")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ResponseModel addFavouriteToCart(FavouriteVo favouriteVo) {
		ResponseModel response = new ResponseModel();
		try {
			Favourite favourite = favouriteService.getFavourites(favouriteVo.getFavouriteId());
			SalesOrder salesOrder = favourite.getSalesOrder();
			List<SalesOrderLine> salesOrderLines = salesOrder.getSalesOrderLines();
			
			Merchant merchant = salesOrder.getMerchant();
			Store store = salesOrder.getStore();
			Customer customer = salesOrder.getCustomer();
			
			for(SalesOrderLine salesOrderLine : salesOrderLines){
				
				MyCart myCarts = myCartService.getCartByCustomerStoreanProductId(customer, salesOrderLine.getProduct(), store);
				if(myCarts == null){
				MyCart myCart = (MyCart) CommonUtil.setAuditColumnInfo(MyCart.class.getName());
				myCart.setMerchant(merchant);
				myCart.setStore(store);
				myCart.setCustomer(customer);
				myCart.setProduct(salesOrderLine.getProduct());
				myCart.setQty(salesOrderLine.getQty());
				myCart.setIsactive('Y');
				myCartService.addToCart(myCart);
				}else{			
					Integer quantity = 	myCarts.getQty() + salesOrderLine.getQty();
					myCarts.setQty(quantity);
					myCarts.setUpdated(new Date());
					myCartService.updateCart(myCarts);
				}
				
			}
			response.setStatus(SBMessageStatus.SUCCESS.getValue());
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(SBMessageStatus.FAILURE.getValue());
		}
		return response;
		
	}
	
	@Path("/getProductInFavourite")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public MyCartResponseVo productInFavourite(FavouriteVo favouriteVo) {
		//ProductResponseVo productResponse = new ProductResponseVo();
		MyCartResponseVo myCartResponseVo = new MyCartResponseVo();
		try {
			Favourite favourite = favouriteService.getFavourites(favouriteVo.getFavouriteId());
			SalesOrder salesOrder = favourite.getSalesOrder();
			List<SalesOrderLine> salesOrderLines = salesOrder.getSalesOrderLines();
			
			
			//List<ProductVo> productVoList = new ArrayList<ProductVo>();
			List<MyCartVo> myCartVoList = new ArrayList<MyCartVo>();
			for(SalesOrderLine salesOrderLine : salesOrderLines){
				MyCartVo mycartvo = new MyCartVo();
				ProductVo productVo = productService.setProductVo(salesOrderLine.getProduct());
				mycartvo.setProduct(productVo);
				mycartvo.setQty(salesOrderLine.getQty());
				myCartVoList.add(mycartvo);
				
			}
			myCartResponseVo.setMyCart(myCartVoList);
			myCartResponseVo.setStatus(SBMessageStatus.SUCCESS.getValue());
		} catch (Exception e) {
			e.printStackTrace();
			myCartResponseVo.setStatus(SBMessageStatus.FAILURE.getValue());
		}
		return myCartResponseVo;
		
	}
}
