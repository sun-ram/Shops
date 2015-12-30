angular.module('app')
.config(['$stateProvider', '$urlRouterProvider', "apiProvider", "$httpProvider", "myConfig", "uiGmapGoogleMapApiProvider",
         function ($stateProvider, $urlRouterProvider, apiProvider, $httpProvider, myConfig,GoogleMapApiProviders){

             GoogleMapApiProviders.configure({
                 key: 'AIzaSyBMkNRqSlcPaUERbJfrpc5lLEKqJze1nD4',
                 v: '3.20', //defaults to latest 3.X anyhow
                 libraries: 'weather,geometry,visualization'
             });

	$urlRouterProvider.otherwise('/login');
	$stateProvider

	.state('app', {
		abstract: true,
		templateUrl: 'app/modules/main/app.html',
		controller: 'appCtrl',
		data: {
			requireLogin: true
		}
	})

	// authentication module
	.state('login', {
		url: '/login',
		data: {
			requireLogin: false
		},
		templateUrl: 'app/modules/auth/login.html',
		controller: 'logincontroller'
	})

	.state('resetpassword', {
		url: '/resetpassword/{tokenId}',
		data: {
			requireLogin: false
		},
		templateUrl: 'app/modules/auth/resetpassword.html',
		controller: 'logincontroller'
	})

	// merchant module
	.state('app.merchants', {
		url: '/merchants',
		templateUrl: 'app/modules/merchant/merchantList.html',
		controller : 'merchantcontroller',
		resolve:   {
			merchants:  function(MerchantServices, $http, $stateParams){
				return MerchantServices.getMerchant().then(function(data){
					localStorage.removeItem('merchantDetails');
					return data;
				});

			}
		}
	})


	.state('app.merchantdetails', {
		url: '/merchant/details',
		templateUrl: 'app/modules/merchant/details/merchantDetails.html',
		controller : 'merchantDetailsCtrl'
	})
	.state('app.newmerchant', {
		url: '/merchant/new',
		templateUrl: 'app/modules/merchant/add/merchantCreate.html',
		controller : 'createMerchantCtrl'
	})
	.state('app.editmerchant', {
		url: '/merchant/edit',
		templateUrl: 'app/modules/merchant/edit/merchantEdit.html',
		controller : 'merchantEditCtrl'
	})


	// employee module
	.state('app.aviateemployees', {
		url: '/employees',
		templateUrl: 'app/modules/employee/employees.html',
		controller : 'employeecontroller'
	})
	.state('app.addemployee', {
		url: '/addemployee',
		templateUrl: 'app/modules/employee/addEmployee/createEmployee.html',
		controller : 'employeecontroller'
	})
	.state('app.employeedetailsview', {
		url: '/employee/details',
		templateUrl: 'app/modules/employee/details/employeeDetailsView.html',
		controller : 'employeecontroller'
	})
	// store module
	.state('app.store', {
		url: '/store',
		templateUrl: 'app/modules/store/store.html',
		controller : 'storecontroller',
		resolve:   {
			stores:  function(StoreServices, $http, $stateParams, $rootScope){
				return StoreServices.getStore({'merchantId':$rootScope.user.merchantId}).then(function(data){
					return data;
				});

			}
		}
	})
	.state('app.storedetails', {
		url: '/store/details',
		templateUrl: 'app/modules/store/details/storeDetails.html',
		controller : 'storeDetailsCtrl'
	})
	.state('app.newstore', {
		url: '/store/new',
		templateUrl: 'app/modules/store/add/storeCreate.html',
		controller : 'storeCreateCtrl'
	})
	.state('app.editstore', {
		url: '/store/edit',
		templateUrl: 'app/modules/store/edit/storeEdit.html',
		controller : 'storeEditCtrl'
	})


	//Delivery Time Slot
	/*.state('app.deliveryTimeSlot', {
		url: '/deliveryTimeSlot',
		templateUrl: 'app/modules/deliverytimeslot/deliveryTimeSlot.html',
		controller : 'deliveryTimeSlot'
	})*/

	.state('app.addDeliveryTimeSlot', {
		url: '/createDeliveryTimeSlot',
		templateUrl: 'app/modules/deliverytimeslot/addDeliveryTimeSlot.html',
		controller : 'deliveryTimeSlot',
		resolve:   {
			deliverTimes:  function(deliveryTimeSlotService, $http, $stateParams){
				return deliveryTimeSlotService.getDeliveryTimeSlots();

			}
		}
	})
	/*.state('app.deliveryTimeSlotDetails', {
		url: '/deliveryTimeSlotDetails',
		templateUrl: 'app/modules/deliverytimeslot/deliveryTimeSlotDetail.html',
		controller : 'deliveryTimeSlot'
	})*/

	//Unit of Measure
	.state('app.units', {
		url: '/unitOfMeasure',
		templateUrl: 'app/modules/units/unitOfMeasure.html',
		controller : 'unitController'
	})

	.state('app.createUnit', {
		url: '/createUnit',
		templateUrl: 'app/modules/units/createUnit.html',
		controller : 'unitController'
	})

	.state('app.detailsUnit', {
		url: '/detailsUnit',
		templateUrl: 'app/modules/units/detailsUnit.html',
		controller : 'unitController'
	})			

	// product module

	.state('app.products', {
		url: '/products',
		templateUrl: 'app/modules/product/products/products.html',
		controller : 'productcontroller'
	})
	.state('app.productdetailsview', {
		url: '/productsDetail',
		templateUrl: 'app/modules/product/products/details/productDetailsView.html',
		controller : 'productDetailController'
	})
	.state('app.addproduct', {
		url: '/addproduct',
		templateUrl: 'app/modules/product/products/addproduct.html',
		controller : 'productcontroller'
	})

	.state('app.editproduct', {
		url: '/editproduct',
		templateUrl: 'app/modules/product/products/edit/editproduct.html',
		controller : 'editproductcontroller'
	})
	.state('app.addproducttype', {
		url: '/addproducttype',
		templateUrl: 'app/modules/product/producttype/addproducttype.html',
		controller : 'addproducttypecontroller'
	})

	// warehouse module
	.state('app.warehouse', {
		url: '/warehouse',
		templateUrl: 'app/modules/warehouse/warehouse.html',
		controller : 'warehousecontroller'
	})
	.state('app.warehouseDetails', {
		url: '/warehouseDetails',
		templateUrl: 'app/modules/warehouse/warehouseDetails.html',
		controller : 'warehousecontroller'
	})
	.state('app.addWarehouse', {
		url: '/addWarehouse',
		templateUrl: 'app/modules/warehouse/addWarehouse.html',
		controller : 'warehousecontroller'
	})
	.state('app.storagebinDetails', {
		url: '/storagebinDetails',
		templateUrl: 'app/modules/warehouse/storagebin/storagebinDetails.html',
		controller : 'warehousecontroller'
	})
	.state('app.addStorageBin', {
		url: '/addStorageBin',
		templateUrl: 'app/modules/warehouse/storagebin/addStorageBin.html',
		controller : 'warehousecontroller'
	})
	.state('app.storagebin', {
		url: '/storagebin',
		templateUrl: 'app/modules/warehouse/storagebin/storagebin.html',
		controller : 'warehousecontroller'
	})

	// Movement
	.state('app.movement', {
		url: '/movement',
		templateUrl: 'app/modules/movement/movementList.html',
		controller : 'movementcontroller',
		resolve:   {
			movementLists:  function(movementServices, $http, $stateParams, $rootScope){
				return movementServices.getMovementsByStore({'store':{'storeId':$rootScope.user.storeId}}).then(function(data){
					localStorage.removeItem('movementDetails');
					$rootScope.isNewMovement = false;
					return data;
				});

			}
		}

	})
	.state('app.newmovement', {
		url: '/movement/new',
		templateUrl: 'app/modules/movement/add/movementCreate.html',
		controller : 'movementCreateCtrl'
	})
	.state('app.editmovement', {
		url: '/movement/edit/:movementId',
		templateUrl: 'app/modules/movement/add/movementCreate.html',
		controller : 'movementEditCtrl',
		resolve:   {
			movement:  function(movementServices, $http, $stateParams, $rootScope){
				return movementServices.getMovement($stateParams.movementId).then(function(data){
					return data;
				});

			}
		}
	})
	.state('app.movementdetails', {
		url: '/movement/details',
		templateUrl: 'app/modules/movement/details/movementDetails.html',
		controller : 'movementDetailsCtrl'
	})
	
	//Physical Inventory Adjustment
	.state('app.physicalInventoryAdjustment', {
		url: '/inventory/adjustments',
		templateUrl: 'app/modules/physical_inventory_adjustment/inventories.html',
		controller : 'physicaInventoryCtrl',
		resolve:   {
			inventories:  function(physicalInventoryService, $http, $stateParams, $rootScope){
				return physicalInventoryService.getPhysicalInventoriesByStore().then(function(data){
					return data;
				});

			}
		}
	})
	
	.state('app.addNewInventory', {
		url: '/inventory/new/:inventoryId',
		templateUrl: 'app/modules/physical_inventory_adjustment/add/inventoryCreate.html',
		controller : 'physicalInventoryCreateCtrl',
		resolve:   {
			inventory:  function(physicalInventoryService, $http, $stateParams, $rootScope){
				if($stateParams.inventoryId){
					return physicalInventoryService.getPhysicalInventory($stateParams.inventoryId).then(function(data){
						return data;
					});
				}else{
					return {};
				}
			}
		}
	})
	
	.state('app.editInventory', {
		url: '/inventory/Edit/:inventoryId',
		templateUrl: 'app/modules/physical_inventory_adjustment/add/inventoryCreate.html',
		controller : 'physicalInventoryCreateCtrl',
		resolve:   {
			inventory:  function(physicalInventoryService, $http, $stateParams, $rootScope){
				if($stateParams.inventoryId){
					return physicalInventoryService.getPhysicalInventory($stateParams.inventoryId).then(function(data){
						return data;
					});
				}else{
					return {};
				}
			}
		}
	})
	
	.state('app.physicalInventoryDetail', {
		url: '/inventory/detail/:inventoryId',
		templateUrl: 'app/modules/physical_inventory_adjustment/details/inventoryDetails.html',
		controller : 'inventoryDetailsCtrl',
		resolve:   {
			inventory:  function(physicalInventoryService, $http, $stateParams, $rootScope){
				return physicalInventoryService.getPhysicalInventory($stateParams.inventoryId).then(function(data){
					return data;
				});

			}
		}
	})
	
	//Product stock
	.state('app.viewproductstock', {
		url: '/productstock',
		templateUrl: 'app/modules/product_stock/productStock.html',
		controller : 'productstockcontroller'
	})
	
	// user module
	.state('app.user', {
		url: '/user',
		templateUrl: 'views/user.html',
		controller : 'usercontroller'
	})
	.state('app.adduser', {
		url: '/adduser',
		templateUrl: 'views/adduser.html',
		controller : 'usercontroller'
	})

	// sales order module
	.state('app.salesorder', {
		url: '/salesorder',
		templateUrl: 'app/modules/sales_order/salesOrder.html',
		controller : 'salesordercontroller'
	})
	.state('app.salesOrderLine', {
		url: '/salesOrderLine',
		templateUrl: 'app/modules/sales_order/salesOrderLine.html',
		controller : 'salesordercontroller'
	})
	
	// sales order return module
	
	.state('app.salesorderreturn', {
		url: '/salesorderReturn',
		templateUrl: 'app/modules/sales_order_return/salesOrderReturn.html',
		controller : 'salesorderreturncontroller'
	})

	//shipping charges module
	.state('app.shippingCharges',{
		url: '/shippingCharges',
		templateUrl: 'app/modules/shipping_charges/shippingCharges.html',
		controller : 'shippingChargesController'
	})
	.state('app.shippingchargedetails', {
		url: '/shippingCharges/details',
		templateUrl: 'app/modules/shipping_charges/details/shippingChargesDetails.html',
		controller : 'shippingChargesDetailsCtrl'
	})
	.state('app.newshippingcharges', {
		url: '/shippingCharges/new',
		templateUrl: 'app/modules/shipping_charges/add/shippingChargeCreate.html',
		controller : 'shippingChargeCreateCtrl'
	})
	.state('app.editshippingcharges', {
		url: '/shippingCharges/edit',
		templateUrl: 'app/modules/shipping_charges/edit/shippingChargesEdit.html',
		controller : 'shippingChargesEditCtrl'
	})

	//tax module
	.state('app.tax',{
		url: '/tax',
		templateUrl: 'app/modules/tax/tax.html',
		controller : 'taxController'
	})
	.state('app.taxdetails', {
		url: '/tax/details',
		templateUrl: 'app/modules/tax/details/taxDetails.html',
		controller : 'taxDetailsCtrl'
	})
	.state('app.newtax', {
		url: '/tax/new',
		templateUrl: 'app/modules/tax/add/taxCreate.html',
		controller : 'taxCreateCtrl'
	})
	.state('app.edittax', {
		url: '/tax/edit',
		templateUrl: 'app/modules/tax/edit/taxEdit.html',
		controller : 'taxEditCtrl'
	})


	//banner module
	.state('app.banner', {
		url: '/banner',
		templateUrl: 'app/modules/banner/bannerlist.html',
		controller : 'bannerCtrl'
	})
	.state('app.uploadbanner', {
		url: '/banner/uploadbanner',
		templateUrl: 'app/modules/banner/uploadBanner/bannerupload.html',
		controller : 'uploadBannerCtrl'
	})
	.state('app.editbanner', {
		url: '/banner/editbanner',
		templateUrl: 'app/modules/banner/editBanner/editbanner.html',
		controller : 'editBannerCtrl'
	})

	//Dashboard 
	.state('app.super_admin_dashboard', {
		url: '/dashboard/superadmin',
		templateUrl: 'app/modules/dash_board/super_admin/superAdmin.html',
		controller : 'superDashboardCtrl'
	})
	.state('app.merchant_admin_dashboard', {
		url: '/dashboard/merchantadmin',
		templateUrl: 'app/modules/dash_board/merchant_admin/merchantAdmin.html',
		controller : 'merchantDashboardCtrl'
	})
	.state('app.store_admin_dashboard', {
		url: '/dashboard/storeadmin',
		templateUrl: 'app/modules/dash_board/strore_admin/storeAdmin.html',
		controller : 'storeDashboardCtrl'
	})

	//orderNumber

	.state('app.ordernumber',{
		url: '/ordernumber',
		templateUrl: 'app/modules/order_number/orderNumber.html',
		controller : 'orderNumberController'
	})

	.state('app.newordernumber', {
		url: '/ordernumber/new',
		templateUrl: 'app/modules/order_number/add/orderNumberCreate.html',
		controller : 'orderNumberCreateCtrl'
	})
	.state('app.editordernumber', {
		url: '/ordernumber/edit',
		templateUrl: 'app/modules/order_number/edit/orderNumberEdit.html',
		controller : 'orderNumberEditCtrl'
	})
	
	//Discounts
	
		.state('app.adddiscount', {
		url: '/adddiscount',
		templateUrl: 'app/modules/discount/addDiscount.html',
		controller : 'discountController'
	})
	
		.state('app.editdiscount', {
		url: '/editdiscount',
		templateUrl: 'app/modules/discount/editDiscount.html',
		controller : 'discountController'
	})
		.state('app.detaildiscount', {
		url: '/detailsdiscount',
		templateUrl: 'app/modules/discount/detailsDiscount.html',
		controller : 'discountController'
	})	
		.state('app.discount', {
		url: '/discount',
		templateUrl: 'app/modules/discount/discount.html',
		controller : 'discountController'
	})
		.state('app.productdiscount', {
		url: '/productdiscount/:discountId',
		templateUrl: 'app/modules/discount/productDiscount.html',
		controller : 'discountController'
	})
	
	//Product Offer
	.state('app.productoffer', {
		url: '/productoffer',
		templateUrl: 'app/modules/productoffer/productoffer.html',
		controller : 'productoffercontroller'
	})
	.state('app.newproductoffer', {
		url: '/productoffer/new',
		templateUrl: 'app/modules/productoffer/Add/addProductOffer.html',
		controller : 'productoffercontroller'
	})
	.state('app.productofferline', {
		url: '/productofferline/:offerId',
		templateUrl: 'app/modules/productoffer/productOfferLine.html',
		controller : 'productoffercontroller'
	})
	.state('app.newproductofferline', {
		url: '/productofferline/new/:offerId',
		templateUrl: 'app/modules/productoffer/Add/addProductOfferLine.html',
		controller : 'productoffercontroller'
	})
	.state('app.productofferdetails', {
		url: '/productoffer/details',
		templateUrl: 'app/modules/productoffer/details/productOfferDetails.html',
		controller : 'productoffercontroller'
	});




	$httpProvider.defaults.useXDomain = true;
	$httpProvider.defaults.headers.common = 'Content-Type: application/json';
	delete $httpProvider.defaults.headers.common['X-Requested-With'];

	apiProvider.setApiUrl(myConfig.backend);


}]);
