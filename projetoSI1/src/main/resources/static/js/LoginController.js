var appL = angular.module("inicial", []);

appL.controller("loginCtrl", function($scope) {

		$scope.login = "";
		$scope.password = "";
		
		
		$scope.validation = function() {

			$scope.EmptyUserName();
			$scope.EmptyPassword();
			$scope.ValidLogin()
			
			
		}
		
		

appL.controller("RegisterCtrl", function($scope) {
		$scope.login = "";
		$scope.email = "";
		$scope.password = "";
			
});