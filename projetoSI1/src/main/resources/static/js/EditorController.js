var app = angular.module("editor", []);

app.controller("editorCtrl", function($scope, $http) {

	$scope.file = undefined;
	$scope.isShared = false;

	window.onload = function(){
		$scope.getTxt();
	}
	
	$scope.saveFile = function() {

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
		
		$http.get("/editor/viewSharedFile").success(function(data, status) {
			if (Object.keys(data).length != 0) {
				$scope.file = data;
				$scope.isShared = true;
				document.getElementById('fileData').value = data.data;
			} 
		});
		
		if(!$scope.isShared) {
			$http.get("/editor/viewFile").success(function(data, status) {
				$scope.file = data;
				if (data.data != undefined) {
					document.getElementById('fileData').value = data.data;
				}
			});
		}
	}
			
	
	$scope.discard = function() {
		document.getElementById('fileData').value = $scope.file.data;
	}
	
	$scope.isReadOnly = function() {
		if ($scope.file != undefined) {
			if ($scope.file.typeSharing === "Read Only") {
				return true;
			}
		}
		return false;
	}
	
	$scope.blankTextArea = function() {
		document.getElementById('fileData').value = "";
	}
	
});
