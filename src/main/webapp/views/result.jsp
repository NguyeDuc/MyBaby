<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@ page import="java.util.List" %>
<%@ page import="models.ImageUpload" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Baby</title>
	  <meta name="viewport" content="width=device-width, initial-scale=1">
	  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
	  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	  	<meta property="og:title" content="My title" />
		<meta property="og:site_name" content="Grobmeier.de"/>
		<meta property="og:url" content="http://www.grobmeier.de" />
		<meta property="og:description" content="Hello World" />
		<meta property="og:image" content="http://www.grobmeier.de/myimage.jpg" />
		<meta property="og:image:width" content="1200" />
		<meta property="og:image:height" content="630" />
	  
	  <style type="text/css">
	  	i {
		  border: 5px solid green;
		  border-width: 0 25px 25px 0;
		  display: inline-block;
		  padding: 25px;
		}
		.right {
		  transform: rotate(-45deg);
		  -webkit-transform: rotate(-45deg);
		}
		.clickedRow {
			background-color: #FFE4C4;
		}
		.tableImg tr:first-child {
		    background-color: #FFE4C4;
		}
	  </style>
</head>
<body>
	<div class="container" style="background-color: #A9A9A9">
		<div class="row">
			<div class="col-md-4">
				<div class="row" style="margin-top: 20px; margin-left: 10px">
					<img id="imgUpload01" src="data:image/jpg;base64,<%=request.getAttribute("imgUp1")%>" style="height: 300px; width: 320px;">
				</div>
				<div class="row" style="margin-top: 20px; margin-left: 10px; margin-bottom: 20px">
					<img id="imgUpload02" src="data:image/jpg;base64,<%=request.getAttribute("imgUp2")%>" style="height: 300px; width: 320px;">
				</div>
			</div>
			<div class="col-md-2">
				<i class="right" style="margin-top: 288px"></i>
				
			</div>
			<div class="col-md-6">
			
				<h3 id="babyName" style="text-align: center; margin-top: 15px">Hi I'm <%=request.getAttribute("nameBaby")%></h3>
				<img alt="ABC" id="imgBaby" src="data:image/jpg;base64,<%=request.getAttribute("imgBaby")%>" style="height: 500px; width: 530px; margin-top: 5px">
				<p id="createDate" style="float: right; margin-top: 20px">Create date: <%=request.getAttribute("createDate")%></p>
				<div id="fb-root"></div>
				<script async defer crossorigin="anonymous" src="http://connect.facebook.net/en_US/sdk.js#xfbml=1&version=v5.0">
				</script>
				<div style="float: left; margin-top: 20px"" class="fb-share-button" data-href="https://www.facebook.com/tentao.haykhong.79" 
					data-layout="button_count" data-size="large"><a target="_blank" 
					href="https://www.facebook.com/sharer/sharer.php?u=http%3A%2F%2Flocalhost%3A8080%2FMakeBaby%2FMakeBaby&amp;src=sdkpreparse" 
					class="fb-xfbml-parse-ignore">Share</a></div>
			</div>
		</div>
	</div>
	
	<div class="container">
		<table class="table" id="historyMakeImage" class="tableImg">
			<thead align="left" style="display: table-header-group">
			  <tr>
			     <th>No</th>
			     <th>Baby Name</th>
			     <th>DOB</th>
			  </tr>
			</thead>
			<tbody class="tableImg">
				<%
		        List<ImageUpload> listImg = null;
		        listImg = (ArrayList<ImageUpload>) request.getAttribute("listImgNew");
    			%>
    			<%  for(int i = 0; i < listImg.size(); i++ ){
    			%>
				<tr>
					<td><%=i+1%></td>
					<td style="display: none"><%=listImg.get(i).getId()%></td>
					<td><%=listImg.get(i).getName()%></td>
					<td><%=listImg.get(i).getCreateDate()%></td>
					<td style="display: none"><%=listImg.get(i).getImgBabyBase64()%></td>
					<td style="display: none"><%=listImg.get(i).getImgUp1Base64()%></td>
					<td style="display: none"><%=listImg.get(i).getImgUp2Base64()%></td>
				</tr>
				<%
    				};
    			%>
			</tbody>
		</table>
		<input type="hidden" id="idTemp" name="abc"/>
	</div>
	
</body>
	<script type="text/javascript">

		$(document).ready(function(){
			$("#historyMakeImage tbody tr").on("click", function(event){
				var selected = $(this).hasClass("clickedRow");
			    $("#historyMakeImage tr").removeClass("clickedRow");
			    if(!selected){
			    	$(this).addClass("clickedRow");
			    }
			    var currentRow = $(this).closest("tr");
			    var col1 = currentRow.find("td:eq(1)").text();
			    var col2 = currentRow.find("td:eq(2)").text();
			    var col3 = currentRow.find("td:eq(3)").text();
			    var col4 = currentRow.find("td:eq(4)").text();
			    var col5 = currentRow.find("td:eq(5)").text();
			    var col6 = currentRow.find("td:eq(6)").text();
			    
			    var srcImg = "data:image/jpg;base64,";
			    var srcImgUp1 = srcImg.concat(col5);
			    var srcImgUp2 = srcImg.concat(col6);
			    var srcImgBaby = srcImg.concat(col4);
			    $('#imgUpload01').attr("src", srcImgUp1);
			    $('#imgUpload02').attr("src", srcImgUp2);
			    $('#imgBaby').attr("src", srcImgBaby);
			    $('#babyName').text(col2);
			    $('#createDate').text(col3);
			    
// 			    $.ajax({
// 			    	url : 'LoadImage',
// 			    	type : "POST",
// 			    	data: {
// 			    		id : col1
// 			    	},
// 			    	success : function(data){
			    		
// 			    	}
// 			    });
			    
			});
		});
		
// 		window.fbAsyncInit = function() {
// 		  FB.init({
// 		    appId      : FB_ID,
// 		    xfbml      : true,
// 		    version    : 'v2.4'
// 		  });
// 		};
	
// 		(function(d, s, id){
// 		   var js, fjs = d.getElementsByTagName(s)[0];
// 		   if (d.getElementById(id)) {return;}
// 		   js = d.createElement(s); js.id = id;
// 		   js.src = "//connect.facebook.net/en_US/sdk.js";
// 		   fjs.parentNode.insertBefore(js, fjs);
// 		 }(document, 'script', 'facebook-jssdk'));
		
// 		var ENV, FB_ID, BASE_URL;
// 		if (document.domain === 'box.dev') {
// 		  ENV = 'local';
// 		  FB_ID = 'XXXXXX';
// 		  BASE_URL = 'http://box.dev';
// 		} else if (document.domain === 'mytest.grobmeier.de') {
// 		  ENV = 'staging';
// 		  FB_ID = 'XXXXXX';
// 		  BASE_URL = 'http://mytest.grobmeier.de';
// 		} else {
// 		  ENV = 'production';
// 		  FB_ID = 'XXXXXXX';
// 		  BASE_URL = 'http://www.example.com';
// 		}
		
	
	</script>

</html>