angular.module('aviate.constant').constant('CONSTANT', {


		STATUS:{
			SUCCESS:'SUCCESS',
			FAILURE:'FAILURE',
		},

		SUCCESS_CODE:{
			SIGNINSUCCESS: 'Sign in successfully',
			SIGNUPSUCCESS: 'Sign up successfully',
			FORGETPASSWORDCONFIRMATION: 'Password has been sent to your mail',
			ROLE: 'CUSTOMER',
				ADDPRODUCT:'Product Added Successfully',
				REMOVEPRODUCT:'Product Removed Successfully',
				INCREMENTPRODUCT:'Product Increased Successfully',
				DECREASEPRODUCT:'Product Decreased Successfully',
				ADDADDRESS:'Address Added Successfully',
				UPDATEADDRESS:'Address Updated Successfully',
				REMOVEADDRESS:'Address Removed Successfully'				
		},

		ERROR_CODE:{
			VALIDADDRESS: 'Enter valid address',
		},

		WARNING_CODE:{
			MISSMATCHPASSWORD: 'Confirm password miss match',
			FORGETPASSWORDNEEDMAILID:'Enter email-id',
			ADDTHEADDERSS: 'Add The Delivery Address'

		}

});
