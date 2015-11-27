package com.mitosis.shopsbacker.webservice;

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
import com.mitosis.shopsbacker.model.Customer;
import com.mitosis.shopsbacker.model.Favourite;
import com.mitosis.shopsbacker.model.SalesOrder;
import com.mitosis.shopsbacker.model.Store;
import com.mitosis.shopsbacker.order.service.SalesOrderService;
import com.mitosis.shopsbacker.responsevo.FavouriteResponseVo;
import com.mitosis.shopsbacker.util.CommonUtil;
import com.mitosis.shopsbacker.util.SBMessageStatus;
import com.mitosis.shopsbacker.vo.customer.FavouriteVo;

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

	@Path("/addfavourite")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public FavouriteResponseVo addFavourite(FavouriteVo favouriteVo) {
		FavouriteResponseVo favouriteResponseVo = new FavouriteResponseVo();
		Favourite favourite;
		try {
			favourite = favouriteService.getFavouriteByName(favouriteVo.getName());
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
			favouriteResponseVo.setFavourites(favourites);
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
		return favourite;
	}
}
