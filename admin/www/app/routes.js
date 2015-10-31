angular.module('app')
.config(['$stateProvider', '$urlRouterProvider', "apiProvider", "$httpProvider", "myConfig",
         function ($stateProvider, $urlRouterProvider, apiProvider, $httpProvider, myConfig){

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
				url: '/aviateemployees',
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
				templateUrl: 'app/modules/product/products/productCategoryList.html',
				controller : 'productCategoryListController'
			})
			
			.state('app.producttype', {
				url: '/productstype',
				templateUrl: 'app/modules/product/products/products.html',
				controller : 'productcontroller'
			})
			.state('app.productdetailsview', {
				url: '/productsDetail',
				templateUrl: 'app/modules/product/products/details/productDetailsView.html',
				controller : 'productDetailController'
			})
			.state('app.addcategory', {
				url: '/addcategory',
				templateUrl: 'app/modules/product/category/addcategory.html',
				controller : 'productcontroller'
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
			.state('app.productprice', {
				url: '/productprice',
				templateUrl: 'app/modules/product/productPrice.html',
				controller : 'productpricecontroller'
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
			.state('app.addStorageBin', {
				url: '/addStorageBin',
				templateUrl: 'app/modules/warehouse/storagebin/addStorageBin.html',
				controller : 'warehousecontroller'
			})
			
			// physical inventory module
			.state('app.physical_inv', {
				url: '/physical_inv',
				templateUrl: 'app/modules/physical_inventory/physicalinventoryList.html',
				controller : 'physicalinventorycontroller',
				 resolve:   {
					 physicalinventorys:  function(PhysicalInventoryServices, $http, $stateParams, $rootScope){
	                        return PhysicalInventoryServices.getInventory({'storeId':$rootScope.user.storeId}).then(function(data){
	                        	return data;
	        				});
	                        
	                    }
	                }

			})
			.state('app.newphysicalinventory', {
				url: '/physicalinventory/new',
				templateUrl: 'app/modules/physical_inventory/add/physicalinventoryCreate.html',
				controller : 'physicalinventoryCreateCtrl'
			})
			.state('app.physicalinventorydetails', {
				url: '/physicalinventory/details',
				templateUrl: 'app/modules/physical_inventory/details/physicalinventoryDetails.html',
				controller : 'physicalinventoryDetailsCtrl'
			})
			.state('app.editphysicalinventory', {
				url: '/physicalinventory/edit',
				templateUrl: 'app/modules/physical_inventory/edit/physicalinventoryEdit.html',
				controller : 'physicalinventoryEditCtrl'
			})
			
			
			.state('app.addInventoryLines', {
				url: '/addInventoryLines',
				templateUrl: 'app/modules/physical_inventory/physical_inventoryLine/physicalinventoryLine.html',
				controller : 'physicalinventoryLineCtrl'
			})
			
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
	});
	
	$httpProvider.defaults.useXDomain = true;
	$httpProvider.defaults.headers.common = 'Content-Type: application/json';
	delete $httpProvider.defaults.headers.common['X-Requested-With'];

	apiProvider.setApiUrl(myConfig.backend);


}]);
