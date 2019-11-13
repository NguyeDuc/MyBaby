<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Make Baby</title>
<!-- 	<link class="jsbin" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" /> -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	<script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
	<script class="jsbin" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.0/jquery-ui.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	
	<style type="text/css">
	
		img{
		  max-width:180px;
		}
		input[type=file]{
		padding:10px;
		background:#2d2d2d;}
	</style>
	
</head>
<body>

<!-- 	<input type='file' onchange="readURL(this);" /> -->
<!-- 	<img id="imgUpload" src="http://placehold.it/180" alt="your image" /> -->
	
<!-- 	<input type='file' onchange="readURLPartner(this);" /> -->
<!-- 	<img id="imgUploadPartner" src="http://placehold.it/180" alt="your image" /> -->
	<div class="">
	
	</div>
	<form action="MakeBaby" method="POST" enctype="multipart/form-data" class="choose-file-form">
		<div class="btn btn-md-2 btn-green">
			<input type='file' id="upImg" name="fileImg" accept="image/*" onchange = "readURL(this);"/>
		</div>
		<img id="imgUpload" src="#" alt="your image" class="col-md-4"/>
		<img id="imgUploadPartner" src="#" alt="your image" class="col-md-4"/>
		<div class="btn btn-md-2 btn-green">
			<input type='file' id="upImg" name="fileImg2" accept="image/*" onchange = "readURLPartner(this);"/>
		</div>
		<br/>
		<div class="form-check-inline" style="margin-left: 15px">
      		<label class="form-check-label" for="radio1">
        		<input type="radio" class="form-check-input" name="gender" value="1" checked>Baby boy
      		</label>
    	</div>
	    <div class="form-check-inline">
	      	<label class="form-check-label" for="radio2">
	        	<input type="radio" class="form-check-input" name="gender" value="0">Baby girl
	      	</label>
	    </div>
	    <div class="form-check-inline">
	      	<label class="form-check-label" for="radio3">
	        	<input type="radio" class="form-check-input" name="gender" value="-1">Either
	      	</label>
	    </div>
		<br/>
		<div class="form-check-inline" style="margin-left: 15px">
      		<label class="form-check-label" for="radio4">
        		<input type="radio" class="form-check-input" name="skin" value="0" checked>Light
      		</label>
    	</div>
    	<div class="form-check-inline">
      		<label class="form-check-label" for="radio5">
        		<input type="radio" class="form-check-input" name="skin" value="1" checked>Dark
      		</label>
    	</div>
    	<div class="form-check-inline">
      		<label class="form-check-label" for="radio6">
        		<input type="radio" class="form-check-input" name="skin" value="2" checked>Very dark
      		</label>
    	</div>
    	<div class="form-check-inline">
      		<label class="form-check-label" for="radio7">
        		<input type="radio" class="form-check-input" name="skin" value="3" checked>Asian
      		</label>
    	</div>
    	<div class="form-check-inline">
      		<label class="form-check-label" for="radio8">
        		<input type="radio" class="form-check-input" name="skin" value="-1" checked>Auto
      		</label>
    	</div>
		<br/>
		<input type="submit" class="btn btn-primary" style="margin-left: 15px" value="Make My Baby"/>
	</form>


</body>

	<script>
// 		function uploadImg(){
// 			var upImg = $('#upImg').val().replace(/C:\\fakepath\\/i, '')
// // 			var data = {
// // 				"upImg" : upImg
// // 			};
// 			$.ajax({
// 				url : 'MakeBaby',
// 				processData: false,
// 				cache: false,
// 				contentType: false,
// 				dataType: "json",
// 				data : 
// 					"upImg=" + upImg,

// 				success : function(data) {
// 					$('#abc').text(data.message);
// 				}
// 			});
// 		}
		
	
	
		function readURL(input) {
	        if (input.files && input.files[0]) {
	            var reader = new FileReader();
	
	            reader.onload = function (e) {
	                $('#imgUpload')
	                    .attr('src', e.target.result);
	            };
	            reader.readAsDataURL(input.files[0]);
	        }
	    }
		
		function readURLPartner(input) {
	        if (input.files && input.files[0]) {
	            var reader = new FileReader();
	
	            reader.onload = function (e) {
	                $('#imgUploadPartner')
	                    .attr('src', e.target.result);
	            };
	            reader.readAsDataURL(input.files[0]);
	        }
	    }

	
	</script>
</html>