var app = angular.module("listaArquivos", []);

app.controller("listaArquivosCtrl", function($scope, $http) {
    
    $scope.arquivos = [];
    
    $scope.myFilesAndFolders = [];
    
    $scope.mySharedFilesAndFolders = [];
    
    $scope.filesAndFoldersSharedWithMe = [];
    
    $scope.deletedFilesAndFolders = [];
    
    
    var loadingFolder = function() {
    	$http.get("http://localhost:8080/home/explorer").success(function(data, status) {
    		
    		$scope.arquivos = [];
    		
    		for (var int = 0; int < data.folderDirectory.length; int++) {
				$scope.arquivos.push(data.folderDirectory[int]);
			}
    		
    		for (var int = 0; int < data.fileDirectory.length; int++) {
				$scope.arquivos.push(data.fileDirectory[int]);
			}
    		
    	});
    };
    
    $scope.showOnTable = function(lista) {
    	for (var i = 0; i < lista.length; i++) {
        	$scope.arquivos.push(lista[i]);
		}
    };


    loadingFolder();


    $scope.addFolder = function(nome) {
        var nome = prompt("Nome da pasta:");

        var date = new Date();

        var folder = {
            	name:nome,
            	
            	Permission: "private",
            	            	
            	dateCreation: date.toISOString().substring(0, 10) + " " + date.toString().substring(16, 24)
         };
        
        $http.post('http://localhost:8080/home/newFolder', folder).success(function(data, status) {
            loadingFolder();

        });

	};


    $scope.addTxt = function() {
        var nome = window.prompt("Nome da arquivo:");

        if(nome.length === 0){
            alert("O arquivo nao pode ser criado, nome vazio!")
        } else {
            var date = new Date();
        	
        	var file = {
        		name: nome,
        		
        		type: "MD",
        		
        		Permission: "private",
        		
            	dateCreation: date.toISOString().substring(0, 10) + " " + date.toString().substring(16, 24)
        			
        	}

        	
        	$http.post("http://localhost:8080/home/newFile", file).success(function(data, status) {
        		loadingFolder();
        		if ($scope.arquivos.length === 0) {
        			$scope.viewFile(file);
        		}
    		});
        }
    };

    $scope.sortBy = function(valor) {
        $scope.tipoOrdenacao = valor;
        $scope.direction = !$scope.direction;
    }

    $scope.isTxt = function(type) {
        if (type === "MD") {
            return true;
        }
        return false;
    }

    $scope.changeFolder = function(name) {
        $http.get("http://localhost:8080/home/explorer/" + name).success(function(data, status) {
			loadingFolder();
    	});
    }

    $scope.backFolder = function() {
    	$http.get("http://localhost:8080/home/explorer/back").success(function(data, status) {
			loadingFolder();
    	});
    }
    
    $scope.deleteFolder = function(name) {
    	
    	$http.post("http://localhost:8080/home/deleteFolder", name).success(function(data, status) {
			loadingFolder();
    	});
    }
    
    $scope.renameFolder = function(name, nName) {
    	
    	if (nName.length != 0) {
    		
    		data = {
    			oldName: name,
    			newName: nName
    		}
    		
    		$http.post("http://localhost:8080/home/renameFolder", data).success(function(data, status) {
    			loadingFolder();
        	});
    	}
    	else {
    		alert("Nome não pode ser vazio!");
    	}
    	
    }
    
    
    $scope.deleteFile = function(fileName, fileType) {
    	
    	var file = {
    		name: fileName,
    		type: fileType
    	}
    	
    	$http.post("http://localhost:8080/home/deleteFile", file).success(function(data, status) {
			loadingFolder();
    	});
    	
    }
    
    $scope.renameFile = function(name, nName, type, nType){
    	
    	if(nName.length != 0) {
    	
    		data = {
    			oldName: name,
    			newName: nName,
    			oldType: type,
    			newType: nType
    		}
    	
    		$http.post("http://localhost:8080/home/renameFile", data).success(function(data, status) {
    			loadingFolder();
    		});
    	}
    	else {
    		alert("Nome não pode ser vazio!!");
    		
    	}
    } 
    
    
    $scope.showSharedFilesWithMe = function() {
    	//Vai alterar o $scope.arquivos para a lista de arquivos que foram compartilhados com o usuario
    	$scope.showOnTable($scope.filesAndFoldersSharedWithMe);
    	
    }
    
    $scope.showMySharedFiles = function() {
    	//Vai alterar o $scope.arquivos para a lista de arquivos que o usuario compartilhou com outros usuarios    	
    	for (var i = 0; i < $scope.arquivos.length; i++) {
			if ($scope.arquivos[i].permission === "public") {
				$scope.mySharedFiles.push(arquivos[i]);
			}
		}
    	
    	$showOnTable($scope.mySharedFilesAndFolders);
    }
    
    $scope.showDeletedFilesAndFolders = function() {
    	$showOnTable($scope.deletedFilesAndFolders);
    }
    
    $scope.viewFile = function(arquivo) {

    	var file = {
    		name: arquivo.name,
    		type: arquivo.type
    		
    	}
    	
    	$http.post("http://localhost:8080/editor/viewFile", file).success(function(data, status) {

    	});
    	
    }
    
});    
