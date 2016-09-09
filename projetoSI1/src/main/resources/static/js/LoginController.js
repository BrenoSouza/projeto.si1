var appL = angular.module("inicial", []);

appL.controller("loginCtrl", function($scope) {

		$scope.login = "";
		$scope.password = "";

});

appL.controller("RegisterCtrl", function($scope) {
		$scope.login = "";
		$scope.email = "";
		$scope.password = "";
	
		
		
	$scope.registerNewUser = function() {
		var newUser = {
				login: $scope.login,
				email: $scope.email,
				password: $scope.password
		}
		
		$http.post("http://localhost:8080/userRegister", newUser).success(function(data, status) {
			
		});
		
		
	}
			
});