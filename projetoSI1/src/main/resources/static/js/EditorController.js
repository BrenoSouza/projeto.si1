var app = angular.module("editor", []);

app.controller("editorCtrl", function($scope, $http) {

	$scope.file = {}

	window.onload = function(){
		$scope.getTxt();
	}
	
	$scope.saveFile = function() {
		console.log($scope.file);

		var value = {
			data: document.getElementById('fileData').value
		}
		if ($scope.isReadOnly()){
			alert("As modificações não foram salvas, arquivo só de leitura!");
		} else {
			$http.post('/editor/saveData', value).success(function(data, status) {
				alert("Salvo com sucesso!");
			});
		}
	}
	
	$scope.getTxt = function() {
		
		//$http.get("/editor/viewSharedFile").success(function(data, status) {
			//console.log(data);
		//});
		
		$http.get("/editor/viewFile").success(function(data, status) {
			$scope.file = data;
			document.getElementById('fileData').value = data.data;

        });

	}
			
	
	$scope.discard = function() {
		document.getElementById('fileData').value = $scope.file.data;
	}
	
	$scope.isReadOnly = function() {
		return ($scope.file.typeSharing === "Read Only");
	}
	
	$scope.blankTextArea = function() {
		document.getElementById('fileData').value = "";
	}
	
});
