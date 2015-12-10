angular.module('aviateAdmin.controllers').controller("addproducttypecontroller", 
		['$scope', '$state','$filter','ProductCategoryServices','toastr','$mdDialog','CONSTANT','$rootScope',
		 function($scope, $state, $filter, ProductCategoryServices, toastr,$mdDialog,CONSTANT,$rootScope) {
			
			
			
			$scope.getParentCategoryList = function(){
				ProductCategoryServices.getParentCategory().then(function(data){
					$scope.parentCategoryList = data;
            	});
			};
			
			
			$scope.getProductCategoryList = function(){
				ProductCategoryServices.getproductTypeList().then(function(data){
					$scope.productCategoryList = data;
            	});
			};
			
		
			$scope.getParentCategoryList();
			
			$scope.getProductCategoryList();
			
			
			$rootScope.parentCategoryListValue = function() {
				ProductCategoryServices.getParentCategory().then(function(data) {
					$scope.parentCategoryList = data;

				});
			};
			
			$rootScope.productCategoryListValue = function() {
				//$scope.productCategoryList = "";
				ProductCategoryServices.getproductTypeList().then(function(data) {
					$scope.productCategoryList = data;

				})
			};


			$scope.draggableObjects = [{key:'category',name:'Category'}, {key:'subcategory',name:'Sub Category'}, {key:'producttype',name:'Product Type'}];


			$scope.addNewCategory = function(data, ev){
				$scope.dropObj = data;
				if($scope.dropObj.key=='category'){
					$mdDialog.show({
						templateUrl: 'app/modules/modals/productCategoryModal.html',
						parent: angular.element(document.body),
						targetEvent: ev,
						clickOutsideToClose:true,
						controller: function($scope){
							$scope.productCategory = {};
							$scope.categoryHeading="Category";
							$scope.addCategory = function() {
								$scope.product = { };
								$scope.product.name = $scope.productCategory.Name;
								$scope.product.merchant = {};
								$scope.product.merchant .merchantId = $rootScope.user.merchantId;
								ProductCategoryServices.addProductCategory($scope.product).then(function(data){
									toastr.success("Category Added Successfully");
									$rootScope.parentCategoryListValue();
									$rootScope.productCategoryListValue();
									$mdDialog.cancel();
								});
							};
	
							$scope.cancel = function() {
								$mdDialog.cancel();
							};
						}
					
					})
					.then(function(answer) {	
						$scope.status = 'You said the information was "' + answer + '".';
					}, function() {
						$scope.status = 'You cancelled the dialog.';
					});
				}
				else{
					toastr.error("Wrong Dragging Options, You can drag only category here.");
				}
			};
			
			
			
			$scope.addSubCategory = function(data, ev, cat){
				$scope.dropObj = data;
				$rootScope.dropObj = data;
				if($scope.dropObj.key=='category'){
					$mdDialog.show({
						templateUrl: 'app/modules/modals/productCategoryModal.html',
						parent: angular.element(document.body),
						targetEvent: ev,
						clickOutsideToClose:true,
						controller: function($scope){
							$scope.productCategory = {};
							$scope.categoryHeading="Sub-Category";
							$scope.addCategory = function() {
								var product = {
										name:$scope.productCategory.Name,
										merchant:{},
										category:{}
								};
								product.merchant.merchantId=$rootScope.user.merchantId;
								product.category.productCategoryId=cat.productCategoryId;
								ProductCategoryServices.addProductCategory1(product).then(function(data){
/*									if($rootScope.dropObj.key=='subcategory'){
*/										toastr.success("Product Sub-Category Added Successfully");
/*									}
*/									$rootScope.parentCategoryListValue();
									$rootScope.productCategoryListValue();
									$mdDialog.cancel();
								});
							};
	
							$scope.cancel = function() {
								$mdDialog.cancel();
							};
						}
					})
					.then(function(answer) {	
						$scope.status = 'You said the information was "' + answer + '".';
					}, function() {
						$scope.status = 'You cancelled the dialog.';
					});
				}
				else if($scope.dropObj.key=='producttype'){
					$mdDialog.show({
						templateUrl: 'app/modules/modals/productCategoryModal.html',
						parent: angular.element(document.body),
						targetEvent: ev,
						clickOutsideToClose:true,
						controller: function($scope){
							$scope.productCategory = {};
							$scope.categoryHeading="ProductType";
							$scope.addCategory = function() {
								/*var product = {
										"categoryId": cat.categoryId,
										"productTypeName": $scope.productCategory.Name,
										"merchantId":$rootScope.user.merchantId
								};*/
								var product = {
										name:$scope.productCategory.Name,
										merchant:{},
										productCategory:{}
								};
								product.merchant.merchantId=$rootScope.user.merchantId;
								product.productCategory.productCategoryId=cat.productCategoryId;
								ProductCategoryServices.addProductType(product).then(function(data){
									toastr.success("Product Type added successfully");
									$rootScope.parentCategoryListValue();
									$rootScope.productCategoryListValue();
									$mdDialog.cancel();
								});
							};

							$scope.cancel = function() {
								$mdDialog.cancel();
							};
						}
					})
					.then(function(answer) {	
						$scope.status = 'You said the information was "' + answer + '".';
					}, function() {
						$scope.status = 'You cancelled the dialog.';
					});
				}
				else{
					toastr.error("Wrong Dragging Options, You can drag only subcategory and product-type here.");
				}
			};
			
			$scope.addProductType = function(data, ev, cat){
				$scope.dropObj = data;
					if($scope.dropObj.key =='producttype'){
					$mdDialog.show({
						templateUrl: 'app/modules/modals/productCategoryModal.html',
						parent: angular.element(document.body),
						targetEvent: ev,
						clickOutsideToClose:true,
						controller: function($scope){
							$scope.productCategory = {};
							$scope.categoryHeading="ProductType";
							$scope.addCategory = function() {
								var product = {
										"categoryId": cat.categoryId,
										"productTypeName": $scope.productCategory.Name,
										"merchantId":$rootScope.user.merchantId
								};
								var product = {
										name:$scope.productCategory.Name,
										merchant:{},
										productCategory:{}
								};
								product.merchant.merchantId=$rootScope.user.merchantId;
								product.productCategory.productCategoryId=cat.productCategoryId;
								ProductCategoryServices.addProductType(product).then(function(data){
									toastr.success("Product Type added successfully");
									$rootScope.parentCategoryListValue();
									$rootScope.productCategoryListValue();
									$mdDialog.cancel();
								});
							};
	
							$scope.cancel = function() {
								$mdDialog.cancel();
							};
						}
					})
					.then(function(answer) {	
						$scope.status = 'You said the information was "' + answer + '".';
					}, function() {
						$scope.status = 'You cancelled the dialog.';
					});
				}
				else
				{
					toastr.error("Wrong Dragging Options,Only You can allow to drag product type here.");
				}
			};
			
			$scope.removeProductCategory= function(Id, name) {
				var confirm = $mdDialog.confirm()
				.title('Would you like to delete Product Category?')
				.ok('Delete')
				.cancel('Cancel');
				$mdDialog.show(confirm).then(function() {
					ProductCategoryServices.removeProductCategory(Id).then(function(data){
						toastr.success("Product "+name+" Deleted Successfully");
						$rootScope.parentCategoryListValue();
						$rootScope.productCategoryListValue();
					});
				}, function() {

				});		
				
			};

			$scope.removeProductType= function(Id) {

				var confirm = $mdDialog.confirm()
				.title('Would you like to delete Product Type?')
				.ok('Delete')
				.cancel('Cancel');
				$mdDialog.show(confirm).then(function() {
					ProductCategoryServices.removeProductType(Id).then(function(data){
						toastr.success("Product Type Deleted Successfully");
						$rootScope.parentCategoryListValue();
						$rootScope.productCategoryListValue();
					});
				}, function() {

				});		
				
				
			};
			$scope.updateproductCategory=function(categoryId,name,categoryName){
				var product = {
						productCategoryId : categoryId,
						name : name
					};
				ProductCategoryServices.updateproductCategory(product).then(function(data){
					toastr.success("Product "+categoryName+" Updated Successfully");
					$rootScope.parentCategoryListValue();
					$rootScope.productCategoryListValue();
				});

			};

			$scope.updateproductType=function(categoryId,name){
				var product = {
						productTypeId : categoryId,
						name : name
					};
				ProductCategoryServices.updateproductType(product).then(function(data){
					toastr.success("Product Type Updated Successfully");
					$rootScope.parentCategoryListValue();
					$rootScope.productCategoryListValue();
				});

			};

			$scope.showConfirm = function(categoryId, ev) {				
				$mdDialog.show({
					templateUrl: 'app/modules/modals/productCategoryModal.html',
					parent: angular.element(document.body),
					targetEvent: ev,
					clickOutsideToClose:true,
					controller: function($scope){
						$scope.addCategory = function(answer) {
							console.log(answer);
							$mdDialog.hide(answer);
						};

						$scope.cancel = function() {
							$mdDialog.cancel();
						};
					}
				})
				.then(function(answer) {	
					$scope.addProductCategory(categoryId,answer.Name)
					$scope.status = 'You said the information was "' + answer + '".';
				}, function() {
					$scope.status = 'You cancelled the dialog.';
				});
			};

			$scope.showConfirm1 = function(categoryId, ev) {				
				$mdDialog.show({
					templateUrl: 'app/modules/modals/productCategoryModal.html',
					parent: angular.element(document.body),
					targetEvent: ev,
					clickOutsideToClose:true,
					controller: function($scope){
						$scope.addCategory = function(answer) {
							console.log(answer);
							$mdDialog.hide(answer);
						};

						$scope.cancel = function() {
							$mdDialog.cancel();
						};
					}
				})
				.then(function(answer) {	
					$scope.addProductType(categoryId,answer.Name)
					$scope.status = 'You said the information was "' + answer + '".';
				}, function() {
					$scope.status = 'You cancelled the dialog.';
				});
			};

			$scope.showConfirmUpdate = function(categoryId,name, ev, categoryName) {				
				$mdDialog.show({
					templateUrl: 'app/modules/modals/ProductUpdateCategoryModal.html',
					parent: angular.element(document.body),
					targetEvent: ev,
					clickOutsideToClose:true,
					controller: function($scope){
						$scope.name=name;
						$scope.addCategory = function(answer) {
							console.log(answer);
							$mdDialog.hide(answer);
						};

						$scope.cancel = function() {
							$mdDialog.cancel();
						};
					}
				})
				.then(function(answer) {
					$scope.updateproductCategory(categoryId,answer,categoryName)
					$scope.status = 'You said the information was "' + answer + '".';
				}, function() {
					$scope.status = 'You cancelled the dialog.';
				});
			};

			$scope.showConfirmUpdateProduct = function(categoryId,name, ev) {				
				$mdDialog.show({
					templateUrl: 'app/modules/modals/ProductUpdateCategoryModal.html',
					parent: angular.element(document.body),
					targetEvent: ev,
					clickOutsideToClose:true,
					controller: function($scope){
						$scope.name=name;
						$scope.addCategory = function(answer) {
							console.log(answer);
							$mdDialog.hide(answer);
						};

						$scope.cancel = function() {
							$mdDialog.cancel();
						};
					}
				})
				.then(function(answer) {
					$scope.updateproductType(categoryId,answer)
					$scope.status = 'You said the information was "' + answer + '".';
				}, function() {
					$scope.status = 'You cancelled the dialog.';
				});
			};
		}]);
