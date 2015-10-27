angular.module('aviate.constant').constant('CONSTANT', {


		STATUS:{
			SUCCESS:'SUCCESS',
			FAILURE:'FAILURE'
		},

		SUCCESS_CODE:{
			SIGNINSUCCESS: 'Sign in successfully',
			SIGNUPSUCCESS: 'Sign up successfully',
			FORGETPASSWORDCONFIRMATION: 'Password has been sent to your mail',
			ROLE: 'CUSTOMER'
		},

		ERROR_CODE:{
			
		},

		WARNING_CODE:{
			MISSMATCHPASSWORD: 'Confirm password miss match',
			FORGETPASSWORDNEEDMAILID:'Enter email-id'

		}

});
