angular.module('aviateAdmin.providers').provider('api', function ApiProvider() {

	var _apiUrl = null;
	this.setApiUrl = function (url) {
		_apiUrl = url;

	};

	var _apiHeaders = {};
	this.setApiHeaders = function (headers) {
		_apiHeaders = headers;
	};

	var http = null;
	var httpRequest = function (method, path, data, callback) {
		if (http === null) callback({
			error: true,
			errorCode: "HTTP_NULL"
		}, null);

		_apiHeaders['Content-Type'] = 'application/json';
		http({
			method: method,
			url: _apiUrl + path,
			headers: _apiHeaders,
			data: data
		})
		.success(function (data, status, headers, config) {
			if (data.error) {
				callback(data, null);
			} else {
				callback(null, data);
			}
		})
		.error(function (data, status, headers, config) {
			callback({
				error: true,
				errorCode: "CONNECTION_ERROR"
			}, null);
		});
	};

	var httpRequest1 = function (method, path, data, callback) {
		if (http === null) callback({
			error: true,
			errorCode: "HTTP_NULL"
		}, null);

		_apiHeaders['Content-Type'] = 'application/json';
		http({
			method: method,
			url: _apiUrl + path,
			headers: _apiHeaders,
			data: data,
			responseType: 'arraybuffer'
		})
		.success(function (data, status, headers, config) {
			if (data.error) {
				callback(data, null);
			} else {
				callback(null, data);
			}
		})
		.error(function (data, status, headers, config) {
			callback({
				error: true,
				errorCode: "CONNECTION_ERROR"
			}, null);
		});
	};



	this.$get = ['$http', function ($http) {
		http = $http;

		var apiClass = {};

		apiClass.User = {
				name: ""
		};

		apiClass.User.login = function (user, callback) {
			httpRequest("POST", "user/login", user, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.User.forgetpass = function (req, callback) {
			httpRequest("POST", "common/forgetpassword", req, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.User.verifytoken = function (req, callback) {
			httpRequest("POST", "common/verifytoken", req, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.User.resetpass = function (req, callback) {
			httpRequest("POST", "common/resetpassword", req, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.Region = {
				name: ""
		};

		apiClass.Region.getCountries = function (country, callback) {
			httpRequest("POST", "common/country", country, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};


		apiClass.Employee = {
				name: ""
		};

		apiClass.Employee.getList = function (employee, callback) {
			httpRequest("POST", "user/getemployees", employee, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};
		apiClass.Employee.save = function (employee, callback) {
			httpRequest("POST", "user/register", employee, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.Employee.update = function (employee, callback) {
			httpRequest("POST", "user/update", employee, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};   

		apiClass.Employee.deleteEmployee = function (employee, callback) {
			httpRequest("POST", "user/deleteemployee", employee, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		}; 

		apiClass.Employee.shopList = function (store, callback) {
			httpRequest("POST", "product/getshoplist", store, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		}; 



		/*Merchant Modules*/
		apiClass.Merchant = {
				name: ""
		};

		apiClass.Merchant.getList = function (merchant, callback) {
			httpRequest("POST", "merchant/getmerchant", merchant, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.Merchant.addNewMerchant = function (merchant, callback) {
			httpRequest("POST", "merchant/addmerchant", merchant, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.Merchant.updateMerchant = function (merchant, callback) {
			httpRequest("POST", "merchant/update", merchant, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.Merchant.deleteMerchant = function (merchant, callback) {
			httpRequest("POST", "merchant/deletemerchant", merchant, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};


		/*Delivery Time Slot*/
		apiClass.DeliveryTimeSlot = {
				name: ""
		};

		apiClass.DeliveryTimeSlot.getList = function (deliveryTimeSlot,callback){

			httpRequest("POST", "deliverytimeslot/get", deliveryTimeSlot, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});

		};

		apiClass.DeliveryTimeSlot.save = function (deliveryTimeSlot,callback){

			httpRequest("POST", "deliverytimeslot/add", deliveryTimeSlot, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});

		};
		apiClass.DeliveryTimeSlot.deleteDeliveryTimeSlot = function (deliveryTimeSlot,callback){

			httpRequest("POST", "deliverytimeslot/delete", deliveryTimeSlot, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});

		};


		/* UnitOfMeasure Modules*/
		apiClass.Unit = {
				name: ""
		};

		apiClass.Unit.unitList = function (uom, callback) {
			httpRequest("POST", "uom/getuoms", uom, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.Unit.Update = function (uom, callback) {
			httpRequest("POST", "uom/adduom", uom, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};            

		apiClass.Unit.Delete = function (uom, callback) {
			httpRequest("POST", "uom/deleteuom", uom, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};        


		/*Warehouse Modules*/

		apiClass.Warehouse = {
				name: ""
		};

		apiClass.Warehouse.save = function (warehouse, callback) {
			httpRequest("POST", "warehouse/addwarehouse", warehouse, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.Warehouse.update = function (warehouse, callback) {
			httpRequest("POST", "warehouse/addwarehouse", warehouse, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};		

		apiClass.Warehouse.warehouseList = function (warehouse, callback) {
			httpRequest("POST", "warehouse/warehouselist", warehouse, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};	
		apiClass.Warehouse.deleteWarehouse = function (warehouse, callback) {
			httpRequest("POST", "warehouse/removewarehouse", warehouse, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};	

		apiClass.Warehouse.deleteStorageBin = function (storageBin, callback) {
			httpRequest("POST", "storagebin/removestoragebin", storageBin, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};	

		apiClass.Warehouse.saveStorageBin = function (storageBin, callback) {
			httpRequest("POST", "storagebin/addstoragebin", storageBin, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.Warehouse.getStoragebins = function (storageBin, callback) {
			httpRequest("POST", "storagebin/getstoragebins", storageBin, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};


		apiClass.Warehouse.warehouseBins = function (warehouse, callback) {
			httpRequest("POST", "inventoryline/storagebinlist", warehouse, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		/*Store Modules*/
		apiClass.Store = {
				name: ""
		};

		apiClass.Store.getStore = function (store, callback) {
			httpRequest("POST", "store/getstorebymerchant", store, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};
		apiClass.Store.addNewStore = function (store, callback) {
			httpRequest("POST", "store/addstore", store, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.Store.updateStore = function (store, callback) {
			httpRequest("POST", "store/update", store, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.Store.deleteStore = function (store, callback) {
			httpRequest("POST", "store/delete", store, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.Store.activateStore = function (store, callback) {
			httpRequest("POST", "store/activate", store, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};


		/*Product Module*/


		apiClass.Product = {
				name: ""
		};

		apiClass.Product.getList = function (product, callback) {

			httpRequest("POST", "product/getproducts", product, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};


		apiClass.Product.getMeasureUnit = function (product, callback) {
			httpRequest("POST", "uom/getuoms", product, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};


		apiClass.Product.productType = function (product, callback) {
			httpRequest("POST", "update/product/getproducttypes", product, function (err, data) {
				if (err) {

					callback(err, null);

				} else {
					callback(null, data);

				}
			});
		};



		apiClass.Product.save = function (product, callback) {
			httpRequest("POST", "product/addproduct", product, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.Product.getAllProductList = function (product, callback) {
			httpRequest("POST", "productcategory/getallleafcategorylist", product, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.Product.getAllProductListByMerchant = function (product, callback) {
			httpRequest("POST", "product/getproductlist", product, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.Product.updateProduct = function (product, callback) {
			httpRequest("POST", "update/product/update", product, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.Product.deleteProduct = function (product, callback) {
			httpRequest("POST", "product/deleteproduct", product, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});


		};

		apiClass.Product.uploadExcelFile = function (file, callback) {
			httpRequest("POST", "product/excelupload", file, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});


		};

		apiClass.Product.addProductListFiles = function (files, callback) {
			httpRequest("POST", "product/addFilesData", files, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});


		};

		apiClass.Product.exportExcelFile = function (storeId, callback) {
			httpRequest("POST", "update/product/exportExcelFile", storeId, function (err, data) {
				if (err) {

					callback(err, null);

				} else {
					console.log("dataformat",data.fileInByteArrayString);
					var a = window.document.createElement('a');
					a.href = window.URL.createObjectURL(data.fileInByteArrayString);
					a.download = data.fileInByteArrayString;

					// Append anchor to body.
					document.body.appendChild(a);
					a.click();


					// Remove anchor from body
					document.body.removeChild(a);
					callback(null, data);

				}
			});


		};
		apiClass.Product.getProductCategory = function (product, callback) {
			httpRequest("POST", "productcategory/getallleafcategorylist", product, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.Product.getAllProductType = function (product, callback) {
			httpRequest("POST", "update/product/getproducttypesbystore", product, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};


		apiClass.Product.getAllProductListByCategory = function (product, callback) {
			httpRequest("POST", "product/getallproductsbycategory", product, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.Product.getProductType = function (product, callback) {
			httpRequest("POST", "producttype/getProductTypeByCategory", product, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};	

		apiClass.Product.deleteProductImage = function (product, callback) {
			httpRequest("POST", "product/deleteProductImage", product, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};
		/*apiClass.Product.getAllProductList = function (merchant, callback) {
			httpRequest("POST", "update/getmerchantproducts", merchant, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};*/


		//Movement Module
		apiClass.movement ={
				name:""
		};
		apiClass.movement.getMovementsByStore = function (store, callback) {
			httpRequest("POST", "movement/getmovement", store, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.movement.getMovement = function (movementId, callback) {
			httpRequest("GET", "movement/movement/"+movementId, null, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.movement.removeMovement = function (movement, callback) {
			httpRequest("POST", "movement/delete", movement, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.movement.addMovement = function (movement, callback) {
			httpRequest("POST", "movement/save", movement, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.movement.removeMovementLine = function (movementLine, callback) {
			httpRequest("POST", "movement/delete/line", movementLine, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};


		apiClass.movement.addMovementLine = function (movementLine, callback) {
			httpRequest("POST", "movement/save/line", movementLine, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		/*apiClass.movement.updatePhysicalInventory = function (physicalinventories, callback) {
			httpRequest("POST", "inventory/updateinventory", physicalinventories, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};*/

		apiClass.movement.processMovement = function (movement, callback) {
			httpRequest("POST", "movement/process", movement, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};



		/*apiClass.movementLine ={

				name: ""
		};*/


		/*apiClass.movementLine.getProducts = function (products, callback) {
			httpRequest("POST", "inventoryline/productlist", products, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};*/

		/*apiClass.movementLine.productUom = function (physicalinventories, callback) {
			httpRequest("POST", "inventoryline/getUomList", physicalinventories, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.movementLine.productUom = function (uom, callback) {
			httpRequest("POST", "inventoryline/getUomList", uom, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};*/


		/*apiClass.movementLine.warehouseBin = function (bin, callback) {
			httpRequest("POST", "inventoryline/storagebinlist", bin, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};*/



		/*apiClass.PhysicalInventoryLine.addInventoryLines = function (physicalinventoryline, callback) {
			httpRequest("POST", "inventoryline/addinventoryline", physicalinventoryline, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};*/

		/*apiClass.PhysicalInventoryLine.removeInventoryLines = function (physicalinventoryline, callback) {
			httpRequest("POST", "inventoryline/removeinventoryline", physicalinventoryline, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};*/

		/*apiClass.PhysicalInventoryLine.getInventoryLines = function (physicalinventoryline, callback) {
			httpRequest("POST", "inventoryline/inventorylinelist", physicalinventoryline, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};*/

		/*Physical Inventory*/
		apiClass.PhysicalInventory ={
				name:""
		};

		apiClass.PhysicalInventory.addPhysicalInventory = function (inventory, callback) {
			httpRequest("POST", "inventory/save", inventory, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.PhysicalInventory.addPhysicalInventoryLine = function (inventoryLine, callback) {
			httpRequest("POST", "inventory/save/Inventoryline", inventoryLine, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.PhysicalInventory.deletePhysicalInventory = function (inventoryId, callback) {
			httpRequest("DELETE", "inventory/delete/"+inventoryId, null, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.PhysicalInventory.deletePhysicalInventoryLine = function (inventoryLineId, callback) {
			httpRequest("DELETE", "inventory/delete/inventoryline/"+inventoryLineId, null, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.PhysicalInventory.getPhysicalInventoriesByStore = function (storeId, callback) {
			httpRequest("GET", "inventory/getinventories/"+storeId, null, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.PhysicalInventory.getPhysicalInventory = function (inventoryId, callback) {
			httpRequest("GET", "inventory/inventory/"+inventoryId, null, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.PhysicalInventory.physicalInventoryIsUpdate = function (inventoryId, callback) {
			httpRequest("PUT", "inventory/isupdate/"+inventoryId, null, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};



		/*Sales Order*/

		apiClass.SalesOrder = {
				name: ""
		};

		apiClass.SalesOrder.getSalesOrder = function (salesorder, callback) {
			httpRequest("POST", "sales/getsalesorder", salesorder, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.SalesOrder.getShoperDetails = function (userVo, callback) {
			httpRequest("POST", "user/getShoperDetails", userVo, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.SalesOrder.getBackerDetails = function (userVo, callback) {
			httpRequest("POST", "user/getBackerDetails", userVo, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.SalesOrder.updateShoperIntoSalesOrder = function (salesOrder, callback) {
			httpRequest("POST", "sales/updateShoperIntoSalesOrder", salesOrder, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.SalesOrder.updateBackerIntoSalesOrder = function (salesOrder, callback) {
			httpRequest("POST", "sales/updateBackerIntoSalesOrder", salesOrder, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.SalesOrder.getSalesByDate = function (salesorder, callback) {
			httpRequest("POST", "sales/getorderlistbydate", salesorder, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.SalesOrder.getMerchantStore = function (marchant, callback) {
			httpRequest("POST", "store/getstorebymerchant", marchant, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.SalesOrder.getSalesOrderByStore = function (store, callback) {
			httpRequest("POST", "sales/getorderlist", store, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};
		/*Sales Order Return Module*/

		apiClass.SalesOrderReturn = {
				name: ""
		};

		apiClass.SalesOrderReturn.getSalesOrderReturn = function (salesOrderReturnVo, callback) {
			httpRequest("POST", "salesorderreturn/getsalesorderreturn", salesOrderReturnVo, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		/*Product Stock Module*/

		apiClass.ProductStock = {
				name: ""
		};

		apiClass.ProductStock.getProductStockList = function (product, callback) {
			httpRequest("POST", "productinventory/getproductinventory", product, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		/* Galleries */
		apiClass.Gallery = {
				name: ""
		};

		apiClass.Gallery.getGalleryList= function (data,callback) {
			httpRequest("GET", "gallery/galleries",null, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.Gallery.add= function (gallery,callback) {
			httpRequest("POST", "gallery/add",gallery,  function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};


		/*product category modules*/
		apiClass.ProductCategory = {
				name: ""
		};
		apiClass.ProductCategory.getAllCategory = function (product, callback) {
			httpRequest("POST", "product/getallcategorylist", product, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};
		apiClass.ProductCategory.getParentCategory = function (product, callback) {
			console.log("Inside the provider",product);
			httpRequest("POST", "productcategory/getcategories", product, function (err, data) {
				if (err) {
					console.log("err",err);
					callback(err, null);

				} else {
					console.log("Success",data);
					callback(null, data);

				}
			});
		};
		apiClass.ProductCategory.getCategoryList = function (product, callback) {
			httpRequest("POST", "product/getLeafcategories", product, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};
		apiClass.ProductCategory.getproductTypeList = function (product, callback) {
			httpRequest("POST", "producttype/getproducttypesbystore", product, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.ProductCategory.removeProductCategory = function (product, callback) {
			httpRequest("POST", "productcategory/removecategory", product, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.ProductCategory.removeProductType = function (product, callback) {
			httpRequest("POST", "producttype/removeproducttype", product, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.ProductCategory.addProductCategory = function (product, callback) {
			httpRequest("POST", "productcategory/addparentcategory", product, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.ProductCategory.addProductCategory1 = function (product, callback) {
			httpRequest("POST", "productcategory/addcategory", product, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.ProductCategory.addProductType = function (product, callback) {
			httpRequest("POST", "producttype/addproducttypes", product, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.ProductCategory.updateproductCategory = function (product, callback) {
			httpRequest("POST", "productcategory/updatecategory", product, function (err, data) {
				if (err) {

					callback(err, null);
				} else {

					callback(null, data);

				}
			});
		};

		apiClass.ProductCategory.updateproductType = function (product, callback) {
			httpRequest("POST", "producttype/updateproducttypes", product, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		/*Shipping Charges modules*/
		apiClass.ShippingService = {
				name: ""
		};

		apiClass.ShippingService.addNewShippingCharge = function (shippingCharge, callback) {
			httpRequest("POST", "shippingcharges/addshippingcharges", shippingCharge, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.ShippingService.getShippingCharge = function (merchant, callback) {
			httpRequest("POST", "shippingcharges/getshippingcharges", merchant, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.ShippingService.deleteShippingCharge = function (id, callback) {
			httpRequest("POST", "shippingcharges/delete", id, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.ShippingService.updateShippingCharge = function (shippingCharge, callback) {
			httpRequest("POST", "shippingcharges/update", shippingCharge, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		/*Tax modules*/
		apiClass.TaxService = {
				name: ""
		};

		apiClass.TaxService.addNewTax = function (tax, callback) {
			httpRequest("POST", "tax/addtax", tax, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.TaxService.getTax = function (merchant, callback) {
			httpRequest("POST", "tax/gettax", merchant, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.TaxService.deleteTax = function (id, callback) {
			httpRequest("POST", "tax/deletetax", id, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.TaxService.updateTax = function (tax, callback) {
			httpRequest("POST", "tax/updatetax", tax, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		/*Banner Modules*/
		apiClass.Banner = {
				name: ""
		};

		apiClass.Banner.getList = function (banner, callback) {
			httpRequest("POST", "banner/getbannerlist", banner, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.Banner.addNewBanner = function (banner, callback) {
			httpRequest("POST", "banner/addbanner", banner, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.Banner.updateBanner = function (banner, callback) {
			httpRequest("POST", "banner/update", banner, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.Banner.deleteBanner = function (banner, callback) {
			httpRequest("POST", "banner/delete", banner, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};



		/*Order Number Modules*/
		apiClass.OrderNumber = {
				name: ""
		};

		apiClass.OrderNumber.getOrderNumber = function (store, callback) {
			httpRequest("POST", "ordernumber/getordernumber",store, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.OrderNumber.addOrderNumber = function (orderNumber, callback) {
			httpRequest("POST", "ordernumber/addordernumber", orderNumber, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.OrderNumber.updateOrderNumber = function (orderNumber, callback) {
			httpRequest("POST", "ordernumber/update", orderNumber, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.OrderNumber.deleteOrderNumber = function (orderNumber, callback) {
			httpRequest("POST", "ordernumber/delete", orderNumber, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		//Discount
		apiClass.Discount = {
				name: ""
		};

		apiClass.Discount.getMerchantDicountList = function (merchant, callback) {
			httpRequest("POST", "discount/getmerchantdiscountList", merchant, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.Discount.getStoreDicountList = function (store, callback) {
			httpRequest("POST", "discount/getstorediscountList", store, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};		

		apiClass.Discount.saveDiscount = function (discount, callback) {
			httpRequest("POST", "discount/savediscount", discount, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.Discount.saveProductDiscount = function (discount, callback) {
			httpRequest("POST", "productdiscount/savediscount", discount, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.Discount.getProductDicountList = function (merchant, callback) {
			httpRequest("POST", "productdiscount/getmerchantdiscountList", merchant, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.Discount.deleteProductDiscount = function (discount, callback) {
			httpRequest("POST", "productdiscount/deletediscount", discount, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.Discount.updateProductDiscount = function (discount, callback) {
			httpRequest("POST", "productdiscount/updatediscount", discount, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		/*Product Offer Modules*/
		apiClass.ProductOffer = {
				name: ""
		};

		apiClass.ProductOffer.getList = function (productoffer, callback) {
			httpRequest("POST", "productoffer/getofferbyMerchant", productoffer, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.ProductOffer.addNewProductOffer = function (productoffer, callback) {
			httpRequest("POST", "productoffer/addoffer", productoffer, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.ProductOffer.updateProductOffer = function (productoffer, callback) {
			httpRequest("POST", "productoffer/updateoffer", productoffer, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.ProductOffer.deleteProductOffer = function (productoffer, callback) {
			httpRequest("POST", "productoffer/deleteoffer", productoffer, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.ProductOffer.addNewProductOfferLine = function (productoffer, callback) {
			httpRequest("POST", "productofferline/addofferline", productoffer, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.ProductOffer.getOfferLineList = function (productoffer, callback) {
			httpRequest("POST", "productofferline/getofferline", productoffer, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.ProductOffer.deleteProductOfferLine = function (productofferline, callback) {
			httpRequest("POST", "productofferline/deleteofferline", productofferline, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.ProductOffer.updateProductOfferLine = function (productoffer, callback) {
			httpRequest("POST", "productofferline/updateofferline", productoffer, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};


		apiClass.Discount.deleteDiscount = function (discount, callback) {
			httpRequest("POST", "discount/deletediscount", discount, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		/*Billing Modules*/	

		apiClass.Billing = {
				name: ""
		};

		apiClass.Billing.getStoresByMerchant = function (merchant, callback) {
			httpRequest("POST", "store/getstorebymerchant", merchant, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		apiClass.Billing.getBillsByMerchant = function (billing, callback) {
			httpRequest("POST", "billing/getBillsByMerchant", billing, function (err, data) {
				if (err) {

					callback(err, null);

				} else {

					callback(null, data);

				}
			});
		};

		return apiClass;
	}];


});
