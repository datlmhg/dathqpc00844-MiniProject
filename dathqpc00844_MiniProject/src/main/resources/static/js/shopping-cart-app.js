const app = angular.module("shopping-cart-app",[]);
app.controller("shopping-cart-ctrl", function($scope, $http){
	$scope.cart={
		products:[],
		add(id){
			var item = this.products.find(item => item.id==id);
			if(item){
				item.qty++;
				this.saveToLocalStorage();
			} else{
				$http.get(`/products/${id}`).then(resp => {
					resp.data.qty = 1;
					this.products.push(resp.data);
					this.saveToLocalStorage();
				})
			}
		},
		remove(id){
			var index = this.products.findIndex(item => item.id==id);
			this.products.splice(index, 1);
			this.saveToLocalStorage();
		},
		clear(){
			this.products=[]
			this.saveToLocalStorage();
		},
		get count(){
			return this.products.map(item => item.qty).reduce((total, qty) => total+=qty,0);
		},
		get amount(){
			return this.products.map(item => item.qty*item.price).reduce((total, qty) => total+=qty,0);
		},
		saveToLocalStorage(){
			var json = JSON.stringify(angular.copy(this.products));
			localStorage.setItem("cart", json);
		},
		loadFromLocalStorage(){
			var json = localStorage.getItem("cart");
			this.products=json?JSON.parse(json):[];
		}
	}
	$scope.cart.loadFromLocalStorage();
	$scope.order={
		createDate: new Date(),
		address: "",
		account:{username:$("#username").text()},
		get orderDetails(){
			return $scope.cart.products.map(item => {
				return {
					product:{id:item.id},
					price: item.price,
					quantity: item.qty
				}
			});
		},
		purchase(){
			var order = angular.copy(this);
			if(order.orderDetails.length==0){
				Swal.fire(
					  'Giỏ hàng trống',
					  'Bạn có muốn tiếp tục mua sắm?',
					  'question'
					)
			}else if(order.address==""){
				Swal.fire({
					  icon: 'error',
					  title: 'Thất bại',
					  text: 'Vui lòng nhập địa chỉ!'
					})
			}else{
				$http.post("/rest/orders", order).then(resp => {
					Swal.fire({
						  position: 'top-end',
						  icon: 'success',
						  title: 'Đặt hàng thành công',
						  showConfirmButton: false,
						  timer: 1500
						})
					$scope.cart.clear();
					location.href="/order/detail/"+resp.data.id;
				}).catch(error => {
					alert("Đặt hàng lỗi!")
					console.log(error)
				})
			}
		}
	}
})