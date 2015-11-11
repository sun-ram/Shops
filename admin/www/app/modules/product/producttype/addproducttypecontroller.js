angular.module('aviateAdmin.controllers').controller("addproducttypecontroller",
		['$scope', '$state','$filter','ProductCategoryServices','toastr','$mdDialog','CONSTANT','$rootScope',
		 function($scope, $state, $filter, ProductCategoryServices, toastr,$mdDialog,CONSTANT,$rootScope) {

			 $scope.dragControlListeners = {

     itemMoved: function(event) {
         //Do what you want
         console.log("itemMoved");
     },
     orderChanged: function(event) {
         //Do what you want
         console.log("orderChanged :",JSON.stringify($scope.parentCategoryList));
     }
 };

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
				ProductCategoryServices.getproductTypeList().then(function(data) {
					$scope.productCategoryList = data;

				});
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
								var product = {
										"parentCategoryName":$scope.productCategory.Name,
										"merchantId": $rootScope.user.merchantId,
										"categoryId": ""
								};
								ProductCategoryServices.addProductCategory(product).then(function(data){
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
				if($scope.dropObj.key=='subcategory'){
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
										"categoryName":$scope.productCategory.Name,
										"merchantId":$rootScope.user.merchantId,
										"parentCategoryId": cat.categoryId
								};
								ProductCategoryServices.addProductCategory1(product).then(function(data){
									if($rootScope.dropObj.key=='subcategory'){
										toastr.success("Product Sub-Category Added Successfully");
									}
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
								var product = {
										"categoryId": cat.categoryId,
										"productTypeName": $scope.productCategory.Name,
										"merchantId":$rootScope.user.merchantId
								};
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
					if($scope.dropObj=='producttype'){
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

			$scope.removeProductCategory= function(Id) {
				ProductCategoryServices.removeProductCategory(Id).then(function(data){
					toastr.success("Product Category Deleted Successfully");
					$rootScope.parentCategoryListValue();
					$rootScope.productCategoryListValue();
				});
			};

			$scope.removeProductType= function(Id) {
				ProductCategoryServices.removeProductType(Id).then(function(data){
					toastr.success("Product Type Deleted Successfully");
					$rootScope.parentCategoryListValue();
					$rootScope.productCategoryListValue();
				});
			};
			$scope.updateproductCategory=function(categoryId,name){
				var product = {
						categoryId : categoryId,
						categoryName : name
				};
				ProductCategoryServices.updateproductCategory(product).then(function(data){
					toastr.success("Product Category Updated Successfully");
					$rootScope.parentCategoryListValue();
					$rootScope.productCategoryListValue();
				});

			};

			$scope.updateproductType=function(categoryId,name){
				var product = {
						productTypeId : categoryId,
						productTypeName : name
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

			$scope.showConfirmUpdate = function(categoryId,name, ev) {
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
					$scope.updateproductCategory(categoryId,answer)
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
