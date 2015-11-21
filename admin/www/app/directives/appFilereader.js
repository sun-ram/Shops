angular.module('aviateAdmin.directives')
.directive(
		'appFilereader',
		function($q) {
			var slice = Array.prototype.slice;
			return {
				restrict: 'A',
				require: '?ngModel',
				link: function(scope, element, attrs, ngModel) {
					if (!ngModel)
						return;
					ngModel.$render = function() {}
					element
					.bind(
							'change',
							function(e) {
								var element = e.target;
								if (element.files[0].size < 500000) {
									if (!element.value)
										return;
									element.disabled = true;
									$q
									.all(
											slice
											.call(
													element.files,
													0)
													.map(
															readFile))
															.then(
																	function(
																			values) {
																		if (element.multiple)
																			ngModel
																			.$setViewValue(values);
																		else
																			ngModel
																			.$setViewValue(values.length ? values[0] : null);
																		element.disabled = false;
																	});

									function readFile(file) {
										var deferred = $q
										.defer();
										var reader = new FileReader()
										reader.onload = function(
												e) {
											var str = e.target.result;
											deferred
											.resolve(e.target.result);
										}
										reader.onerror = function(
												e) {
											deferred.reject(e);
										}
										reader
										.readAsDataURL(file);
										return deferred.promise;
									}
								} else {
									alert("Image size should not be more than 50 kb")
								}
							});
				}
			};
		});