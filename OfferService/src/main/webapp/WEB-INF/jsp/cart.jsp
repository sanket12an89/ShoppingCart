<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<title>Cart</title>
<link href="${contextPath}/static/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="${contextPath}/static/js/bootstrap.min.js"></script>
<script src="${contextPath}/static/js/jquery-1.11.1.min.js"></script>
</head>
<body>
 <!-- Static navbar -->
    <nav class="navbar navbar-default navbar-static-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Shopping Cart</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li>
            
            </li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>

<div class="container">
    <div class="row">
      <div class="col-md-1"></div>
      <div class="col-md-1">Customer Type:</div>
      <div class="col-md-3">
      	   <select class="form-control" id="customerType" name="customerType" onchange="showProducts();">
		      <c:forEach var="item" items="${customerType}">
		        <option value="${item}">${item}</option>
		      </c:forEach>
		    </select>  
       </div>  
      <div class="col-md-7">
      </div>
        <div class="col-sm-12 col-md-10 col-md-offset-1">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Product</th>
                        <th>Quantity</th>
                        <th class="text-center">Price</th>
                        <th class="text-center">Total</th>
                        <th> </th>
                    </tr>
                </thead>
                <tbody id="load_product_list">
                    
                </tbody>
            </table>
        </div>
    </div>
</div>

	
</body>
<script>

$( document ).ready(function() {
	showProducts();
    console.log( "ready!");
    
});


function showProducts(){
	var selectedCustomerType = $('#customerType').find(":selected").text();
	$("#load_product_list").empty();
	$.ajax({
		url : "/api/cart/loadProduct/"+selectedCustomerType,
		type : "GET",
		beforeSend : function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		complete : function() {
		},
		success : function(result) {
			loadProductList(result);
		},
		error : function(result) {
			alert('Error');
		}
	});			
}


function changeQty(id){
	
	var qty =$( '#qty_'+id).val();
	var selectedCustomerType = $('#customerType').find(":selected").text();
	$("#load_product_list").empty();

	var jsonCartObj={};
	jsonCartObj["id"]=id;
	jsonCartObj["qty"]=qty;
	jsonCartObj["customerType"]=selectedCustomerType;
	
	$.ajax({
		url : "/api/cart/loadProductByQty",
		type : "POST",
		dataType : 'json',
		data : JSON.stringify(jsonCartObj),
		beforeSend : function(xhr) {
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
		},
		complete : function() {
		},
		success : function(result) {
			loadProductList(result);
		},
		error : function(result) {
			alert('Error');
		}
	});		
		
}

function loadProductList(result){
	if (result.status == "success") {
		var productList =result.data.productList;
		var createProduct="";
		$.each( productList, function( key, value ) {
			createProduct +='<tr>';
			
			//productName
			createProduct +='<td class="col-md-6">';
			createProduct +='<div class="media">';
			createProduct +='<a class="thumbnail pull-left" href="#"> <img class="media-object" src="http://icons.iconarchive.com/icons/custom-icon-design/flatastic-2/72/product-icon.png" style="width: 72px; height: 72px;"></a>';
			createProduct +='<div class="media-body">';
			createProduct +='<h4 class="media-heading"><a href="#">'+value.name+'</a></h4>';
			createProduct +='</div>';
			createProduct +='<div>';
			createProduct +='</td>';
			
			//QTY
			createProduct +='<td class="col-md-2" style="text-align: center">';
			createProduct +='<input type="number" class="form-control" id="qty_'+value.id+'" min="1" max="100" onchange="changeQty(\''+value.id+'\')" value="'+value.qty+'"/>';
			createProduct +='</td>';
			
			//PRICE
			createProduct +='<td class="col-md-2 text-center"><strong>'+result.data.currency+' '+value.price+'</strong></td>';
			
			//TOTAL PRICE
			createProduct +='<td class="col-md-2 text-center"><strong>'+result.data.currency+' '+value.totalPrice+'</strong></td>';
			createProduct +='</tr>'
			
		});
		
		//Total Price
		createProduct +="<tr><td></td><td></td><td><h5><strong>Total Price</strong></h5></td><td class='text-right'><h5><strong>"+result.data.currency+" "+result.data.totalPrice+"</strong></h5></td></tr>"

		
		//DISCOUNT
		createProduct +="<tr><td></td><td></td><td><h5><strong>Discount</strong></h5></td><td class='text-right'><h5><strong>"+result.data.currency+" "+result.data.discount+"</strong></h5></td></tr>"
			
		//CART TOTAL 
		createProduct +="<tr><td></td><td></td><td><h5><strong>Grand Total</strong></h5></td><td class='text-right'><h5><strong>"+result.data.currency+" "+result.data.grantTotal+"</strong></h5></td></tr>"
		
		
		$("#load_product_list").append(createProduct);

	}else if (result.status == "error") {
		alert(result.message);
	}

}
</script>
</html>
