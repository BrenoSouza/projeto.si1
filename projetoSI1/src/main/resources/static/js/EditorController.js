var app = angular.module("editor", []);

app.controller("editorCtrl", function($scope, $http) {

	$scope.file = {}

	window.onload = function(){
		$scope.getTxt();
	}
	
	$scope.saveFile = function() {
		var value = {
			data: document.getElementById('fileData').value
		}
				
		$http.post('http://localhost:8080/editor/saveData', value).success(function(data, status) {
			alert("Salvo com sucesso!");
        });

	}
	
	$scope.getTxt = function() {
        $http.get("http://localhost:8080/editor/viewFile").success(function(data, status) {
			$scope.file = data;
			document.getElementById('fileData').value = data.data;

        });
	}
	
	$scope.discard = function() {
		document.getElementById('fileData').value = $scope.file.data;
	}
		
});
