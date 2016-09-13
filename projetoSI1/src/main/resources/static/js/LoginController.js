var appL = angular.module("inicial", []);

appL.controller("loginCtrl", function($scope) {

		$scope.login = "";
		$scope.password = "";
				
		
		$scope.validation = function() {

			$scope.EmptyUserName();
			$scope.EmptyPassword();
			$scope.ValidLogin();
			
			
		}
		$scope.EmptyUserName = function() {
			if ($scope.login.length === 0) 
				alert("O login não pode ser vazio");
			
		}
		
		$scope.EmptyPassword = function() {
			if ($scope.password.length === 0) 
				alert("A senha não pode ser vazia");
			
		}
		
		$scope.ValidLogin = function(){
			
			var EMAIL_REGEXP = '^[a-z0-9](\.?[a-z0-9_-]){0,}@[a-z0-9-]+\.([a-z]{1,6}\.)?[a-z]{2,6}$';
			var VALID_CHARACTERS = '^[a-zA-Z0-9_.-]*$'; 

			if(!($scope.login.match(EMAIL_REGEXP)) && (!($scope.login.match(VALID_CHARACTERS))))
				alert("O login informado é inválido");
			
			
		}	

});

		

appL.controller("RegisterCtrl", function($scope) {
		$scope.login = "";
		$scope.email = "";
		$scope.password = "";
		
		
		$scope.registerValidation = function() {
			$scope.EmptyUserName();
			$scope.EmptyPassword();
			$scope.EmptyEmail();
			$scope.ValidEmail();
			$scope.ValidLogin();
		
			
		}

		$scope.EmptyUserName = function() {
			if ($scope.login.length === 0) 
				alert("O login não pode ser vazio");
			
		}
		
		$scope.EmptyPassword = function() {
			if ($scope.password.length === 0) 
				alert("A senha não pode ser vazia");
			
		}
		
		$scope.ValidEmail = function(){
			var EMAIL_REGEXP = '^[a-z0-9](\.?[a-z0-9_-]){0,}@[a-z0-9-]+\.([a-z]{1,6}\.)?[a-z]{2,6}$';
			if(!($scope.email.match(EMAIL_REGEXP)))
				alert("O e-mail informado é inválido");
			
		}
		
		$scope.ValidLogin = function(){
			var VALID_CHARACTERS = '^[a-zA-Z0-9_.-]*$'; 

			if(!($scope.login.match(VALID_CHARACTERS))){
				alert("O login deve conter apenas caracteres alfa-numéricos ou _ . -");
				return false;
			}
		}
		
		$scope.EmptyEmail = function(){
			if($scope.email.length === 0)
				alert ("O e-mail não pode ser vazio")
			
		}
			
});