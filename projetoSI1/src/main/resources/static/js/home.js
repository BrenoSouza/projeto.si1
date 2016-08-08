var app = angular.module("listaArquivos", []);

app.controller("listaArquivosCtrl", function($scope) {
    $scope.app = "lista Arquivos";
    $scope.arquivos = [

    ];

    $scope.adicionaPasta = function(nome) {
        var name = prompt("Nome da pasta:");

        if(name.length === 0){
            alert("A pasta nao pode ser criada, nome vazio!")
        } else {
            $scope.arquivos.push({nome: name, tipo: "pasta", dataCreat: ((new Date()).toString()).substring(0, 24) , ultimaModif:"--", folder:"../static/images/pasta.png" });
            delete $scope.nome;
        }
    };

    $scope.adicionaTxt = function() {
        var name = window.prompt("Nome da arquivo:");

        if(name.length === 0){
            alert("O arquivo nao pode ser criado, nome vazio!")
        } else {

            $scope.arquivos.push({nome: name, tipo: ".txt", dataCreat: ((new Date()).toString()).substring(0, 24) , ultimaModif:"--", folder:"../static/images/.txt.png" });
            delete $scope.nome;
        }
    };

    $scope.apagarArquivo = function() {

    }

    $scope.sortBy = function(valor) {
        $scope.tipoOrdenacao = valor;
        $scope.direction = !$scope.direction;
    }

});    

