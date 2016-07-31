var app = angular.module("listaArquivos", []);

app.controller("listaArquivosCtrl", function($scope) {
    $scope.app = "lista Arquivos";
    $scope.arquivos = [

    ];

    $scope.adicionaPasta = function(nome) {
        $scope.arquivos.push({nome: nome, tipo: "pasta", dataCreat: ((new Date()).toString()).substring(0, 24) , ultimaModif:"--", folder:"../static/images/pasta.png" });
        delete $scope.nome;
    };

    $scope.adicionaTxt = function(nome) {
        $scope.arquivos.push({nome: nome, tipo: ".txt", dataCreat: ((new Date()).toString()).substring(0, 24) , ultimaModif:"--", folder:"../static/images/.txt.png" });
        delete $scope.nome;
    };

    $scope.apagarArquivo = function() {

    }

    $scope.sortBy = function(valor) {
        $scope.tipoOrdenacao = valor;
        $scope.direction = !$scope.direction;
    }

});    

