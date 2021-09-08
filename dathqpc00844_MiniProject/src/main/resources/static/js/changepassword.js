const app = angular.module("change-app", []);
app.controller("change-ctrl", function($scope, $http){
	
	$scope.items=[];
	$scope.changeData=[];
	
	$scope.initialize = function(){
		$http.get("/rest/accounts/signup").then(resp => {
			$scope.items = resp.data;
		});
	}
	
	$scope.change = function(){
		$scope.changeData.username = $("#username").text();
		var item = $scope.items.find(ac => ac.username==$scope.changeData.username);
		if($scope.changeData.password == null){
			Swal.fire({
				  icon: 'error',
				  title: 'Thất bại',
				  text: 'Vui lòng nhập mật khẩu hiện tại!'
				})
		} else if(item.password != $scope.changeData.password){
			Swal.fire({
				  icon: 'error',
				  title: 'Thất bại',
				  text: 'Mật khẩu hiện tại không chính xác!'
				})
		} else{
			if($scope.changeData.npassword == null){
				Swal.fire({
				  icon: 'error',
				  title: 'Thất bại',
				  text: 'Vui lòng nhập mật khẩu mới!'
				})
			} else if($scope.changeData.confirm == null){
				Swal.fire({
				  icon: 'error',
				  title: 'Thất bại',
				  text: 'Vui lòng nhập xác nhận mật khẩu!'
				})
			} else if($scope.changeData.npassword != $scope.changeData.confirm){
				Swal.fire({
				  icon: 'error',
				  title: 'Thất bại',
				  text: 'Xác nhận mật khẩu không đúng!'
				})
			} else {
				var account ={
					username: $scope.changeData.username,
					password: $scope.changeData.npassword,
					fullname: item.fullname,
					email: item.email
				}
				$http.put("/rest/accounts/changepassword", account).then(resp => {
					var index = $scope.items.findIndex(u => u.username==account.username);
					$scope.items[index]=account;
					Swal.fire({
					  position: 'top-end',
					  icon: 'success',
					  title: 'Đổi mật khẩu thành công!',
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
	}
	
	$scope.initialize();
	
})