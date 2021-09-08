const app = angular.module("update-app", []);
app.controller("update-ctrl", function($scope, $http){
	
	$scope.items=[];
	$scope.account=[];
	
	$scope.initialize = function(){
		$scope.account.username=$("#username").text();
		$http.get("/rest/accounts/signup").then(resp => {
			$scope.items = resp.data;
			var item = $scope.items.find(ac => ac.username==$scope.account.username);
			$scope.account.fullname = item.fullname ;
		});
	}
	
	$scope.update = function(){
		$scope.account.username=$("#username").text();
		var item = $scope.items.find(ac => ac.username==$scope.account.username);
		var account={
			username: $scope.account.username,
			password: item.password,
			fullname: $scope.account.fullname,
			email: item.email
		}
		if(account.fullname == null){
			Swal.fire({
				  icon: 'error',
				  title: 'Thất bại',
				  text: 'Vui lòng nhập họ và tên!'
				})
		} else {
			$http.put("/rest/accounts/updateaccount", account).then(resp => {
				var index = $scope.items.findIndex(u => u.username==account.username);
				$scope.items[index]=account;
				Swal.fire({
					  position: 'top-end',
					  icon: 'success',
					  title: 'Cập nhật thông tin tài khoản thành công!',
					  showConfirmButton: false,
					  timer: 1500
					})
				$scope.initialize();
			}).catch(error => {
				alert("Lỗi");
				console.log("Error", error)
			})
		}
	}
	
	$scope.initialize();
	
})