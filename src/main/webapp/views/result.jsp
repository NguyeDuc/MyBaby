<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Baby</title>
	  <meta property="og:url" content="https://www.your-domain.com/your-page.html" />
	  <meta property="og:type" content="website" />
	  <meta property="og:title" content="Your Website Title" />
	  <meta property="og:description" content="Your description" />
	  <meta property="og:image" content="https://www.your-domain.com/path/image.jpg" />
</head>
<body>
	
	<p><%=request.getAttribute("nameBaby")%></p>
	<img alt="ABC" src="data:image/jpg;base64,<%=request.getAttribute("imgBaby")%>">
	<div id="fb-root"></div>
<script async defer crossorigin="anonymous" src="http://connect.facebook.net/en_US/sdk.js#xfbml=1&version=v5.0">
</script>
<div class="fb-share-button" data-href="http:/192.168.1.29/MakeBaby/MakeBaby" 
data-layout="button_count" data-size="large"><a target="_blank" 
href="https://www.facebook.com/sharer/sharer.php?u=http%3A%2F%2Flocalhost%3A8080%2FMakeBaby%2FMakeBaby&amp;src=sdkpreparse" 
class="fb-xfbml-parse-ignore">Share</a></div>
	
</body>
</html>