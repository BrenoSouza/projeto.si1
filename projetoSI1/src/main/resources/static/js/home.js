var app = angular.module("listaArquivos", []);

app.controller("listaArquivosCtrl", function($scope, $http) {
	
    $scope.arquivos = [];
    
    $scope.myFilesAndFolders = [];
    
    $scope.mySharedFilesAndFolders = [];
    
    $scope.filesAndFoldersSharedWithMe = [];
    
    $scope.deletedFilesAndFolders = [];
    
    $scope.notificationList = [];
    
    $scope.sharedFileAux;
    
    $scope.notificationAux;
    
    $scope.notificationString = "";
        
    window.onload = function() {

    	$http.get("/home/explorer").success(function(data, status) {
    	
    	}); 
    	
    	loadingFolder();
    }
    
    
    
    var loadingFolder = function() {
		$scope.arquivos = [];
		$scope.notificationList = [];
    	
    	$http.get("/home/explorer").success(function(data, status) {
    		
    		for (var int = 0; int < data.folderDirectory.length; int++) {
				$scope.arquivos.push(data.folderDirectory[int]);
			}
    		
    		for (var int = 0; int < data.fileDirectory.length; int++) {
				$scope.arquivos.push(data.fileDirectory[int]);
			}
    	
    		if(data.path.length === 0) {
    			$http.get("/home/SharedFiles").success(function(data, status) {
	    		
    				if(data != undefined) {
    					for (var i = 0; i < data.length; i++) {
    						for (var j = 0; j < data[i].second.length; j++) {
    							$scope.arquivos.push(data[i].second[j]);
    						}
    					}	
    				}
        			
    			});
    			
    			$http.get("/home/notification").success(function(data, status) {
    				for (var i = 0; i < data.length; i++) {
    					if(data[i].unread) {
    						$scope.notificationList.push(data[i]);
    					}
    				}
    			});
    			
    		}
    		
    	});	
    	
    };
    
    $scope.showOnTable = function(lista) {
    	for (var i = 0; i < lista.length; i++) {
        	$scope.arquivos.push(lista[i]);
		}
    };


    $scope.addFolder = function() {

    	var nome = document.getElementById("newFolderName").value;
        var date = new Date();

        var folder = {
        	name:nome,
            	
           	Permission: "private",
            	            	
           	dateCreation: date.toISOString().substring(0, 10) + " " + date.toString().substring(16, 24)
        };
        
        $http.post('/home/newFolder', folder).success(function(data, status) {
        	loadingFolder();

        });
        document.getElementById("newFolderName").value = "";

	};


    $scope.addTxt = function() {

    	var nome = document.getElementById("newFileName").value;
    	var selectedType = document.getElementById("selectType").value;

        var date = new Date();
        	
      	var file = {
       		name: nome,
        		
       		type: selectedType,
        		
       		Permission: "private",
        		
           	dateCreation: date.toISOString().substring(0, 10) + " " + date.toString().substring(16, 24)
       	}
        	
       	$http.post("/home/newFile", file).success(function(data, status) {
       		loadingFolder();
   		});
        	
       	document.getElementById("newFileName").value = "";

    };

    $scope.sortBy = function(valor) {
        $scope.tipoOrdenacao = valor;
        $scope.direction = !$scope.direction;
    }

    $scope.isTxt = function(type) {
        if (type === "MD" || type === "TXT") {
            return true;
        }
        return false;
    }

    $scope.changeFolder = function(name) {
        $http.get("/home/explorer/" + name).success(function(data, status) {
			loadingFolder();
    	});
    }

    $scope.backFolder = function() {
    	$http.get("/home/explorer/back").success(function(data, status) {
			loadingFolder();
    	});
    }
    
    $scope.deleteFolder = function(file) {
    	
    	$http.post("/home/deleteFolder", file).success(function(data, status) {

    	});
    }
    
    $scope.showModalRenameFolder = function(arquivo) {
    	$scope.sharedFileAux = arquivo;
        $("#renameFolderModal").modal();
    	
    }
    
    $scope.renameFolder = function() {
    	var nName = document.getElementById('newRenameFolderName').value;
    	    		
    	var folder = {
    		oldName: $scope.sharedFileAux.name,
    		newName: nName
    	}
    		
    	$http.post("/home/renameFolder", folder).success(function(data, status) {
    		loadingFolder();
        });
  
    	$scope.blankInput('newRenameFolderName');
		$scope.sharedFileAux = undefined;
    	
    }
    
    
    $scope.deleteFile = function(file) {
    		
   	    $http.post("/home/deleteFile", file).success(function(data, status) {

   	    });

    }
    
    $scope.showModalRenameFile = function(arquivo) {
    	$scope.sharedFileAux = arquivo;
        $("#renameFileModal").modal();
    }
    
    $scope.renameFile = function() {    	
    	var nName = document.getElementById("newRenameFileName").value;
    	var nType = document.getElementById("selectRenameType").value;
    	
    	if (nName === "") {
    		nName = $scope.sharedFileAux.name;
    	} 
    	
    	var fileInfo = {
    		oldName: $scope.sharedFileAux.name,
    		newName: nName,
    		oldType: $scope.sharedFileAux.type,
    		newType: nType
    	}
    	
    	$http.post("/home/renameFile", fileInfo).success(function(data, status) {
    		loadingFolder();
    	});
    	$scope.sharedFileAux = undefined;
    	$scope.blankInput("newRenameFileName");
    } 
    
    
    $scope.showSharedFilesWithMe = function() {
    	//Vai alterar o $scope.arquivos para a lista de arquivos que foram compartilhados com o usuario
    	$scope.showOnTable($scope.filesAndFoldersSharedWithMe);
    	
    }
    
    $scope.showMySharedFiles = function() {
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
    	if (arquivo.owner === undefined) {
    		var file = {
    			name: arquivo.name,
    			type: arquivo.type
    		}
   
    		$http.post("/editor/viewFile", file).success(function(data, status) {

    		});
    	} else {
    		$scope.viewSharedFile(arquivo);
    	}
    	
    }
    
    $scope.viewSharedFile = function(arquivo) {
    	var file = {
    		owner: arquivo.owner,
    		index: arquivo.index
    	}
    	
    	$http.post("/editor/viewSharedFile", file).success(function(data, status) {

    	});
    }
    
    $scope.deleteL = function(arquivo) {
    	
		if (arquivo.type != "folder") {
			file = {
				name: arquivo.name,
        		type: arquivo.type
			}
			
			$scope.deleteFile(file);
		} else {
			file = {
    			name: arquivo.name
    		}  
			
			$scope.deleteFolder(file);
		}
	    loadingFolder();
    }
    
    
    $scope.blankInput = function(id) {
    	document.getElementById(id).value = "";
    }
    
    $scope.openShareModal = function(arquivo){
    	$scope.sharedFileAux = arquivo;
        $("#shareModal").modal();
    }
    
    $scope.shareFile = function() {
    	 var userLogin = document.getElementById("userLoginShare").value;
         var readOnly = document.getElementById("readOnly").checked;
         var readAndWrite = document.getElementById("readAndWrite").checked;
    	 
         if(readAndWrite) {
        	 readOnly = true;
         }
         
         if(userLogin.length != 0) {
         	var file = {
         		user: userLogin,
         		name: $scope.sharedFileAux.name,
         		type: $scope.sharedFileAux.type,
         		read: readOnly.toString(),
         		write: readAndWrite.toString()
         	}

         	$http.post("/home/shareFile", file).success(function(data, status) {

        	});
         } else {
     		Alert.render("Nome inválido", "Nome não pode ser vazio!");
        	 
         }
         $scope.blankInput("userLoginShare");
         $scope.sharedFileAux = undefined;
    }
    
    $scope.showModalCreateFile = function() {
        $("#createFileModal").modal('show');
    }
    
    $scope.showModalCreateFolder = function() {
        $("#createFolderModal").modal('show');
    }
    
    $scope.isShare = function(arquivo) {
    	if(arquivo != undefined) {	
    		return (arquivo.owner != undefined);
    	}
    	return false;

    }
    
    $scope.showModalNotification = function(notification) {
    	$scope.notificationAux = notification;
        $scope.notificationString = notification.owner + " compartilhou o arquivo " + notification.fileName + "." + notification.fileType + 
        							" com você com permissão para " + notification.typeSharing + "!";
    	
        $("#notificationModal").modal();
        $scope.visualization();
    }
    
    $scope.visualization = function() {
    	for (var i = 0; i < $scope.notificationList.length; i++) {
			if($scope.notificationList[i] === $scope.notificationAux) {
				var ind = {
					index: i
				}
				//$http.post("/home/setReadNotification", ind).success(function(data, status) {
	    		//});
				//loadingFolder();
				//break;
			}
		}
    }
    
});    
