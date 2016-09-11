var appL = angular.module("inicial", []);

appL.controller("loginCtrl", function($scope) {

		$scope.login = "";
		$scope.password = "";
		
		
		$scope.validation = function() {

			$scope.EmptyUserNameOrEmail();
			
			
			
		}
		
		$scope.EmptyUserNameOrEmail = function() {
			if ($scope.login.length === 0) {

			}
		}
		
		

});

appL.controller("RegisterCtrl", function($scope) {
		$scope.login = "";
		$scope.email = "";
		$scope.password = "";
			
});