var appL = angular.module("inicial", []);

appL.controller("loginCtrl", function($scope) {

		$scope.login = "";
		$scope.password = "";

});

appL.controller("RegisterCtrl", function($scope) {
			$scope.login = "";
			$scope.email = "";
			$scope.password = "";
			
});