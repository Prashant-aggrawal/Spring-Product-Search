<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<style type="text/css">
* {
	padding: 0;
	margin: 0;
	/*  font-family: 'Roboto', sans-serif;*/
	font-family: "AtlasTypewriterRegular", "Andale Mono", "Consolas",
		"Lucida Console", "Menlo", "Luxi Mono", monospace;
	font-style: normal;
	font-weight: 400;
	font-size: : 80%;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<body>
	<table>

		<tr style="color: red; margin-left: 30px;">
			
			<td><b>FUNCTIONALITY</b></td>
			<td><b>WEB PAGE</b></td>
			<td><b>URL DESCRIPTION</b></td>
		</tr>

		<tr></tr>

		<tr>
			
			<td>List all categories</td>
			<td><a href="http://localhost:8080/search/category">Categories</a></td>
			<td>/search/category</td>
		</tr>

		<tr></tr>
		<tr>
			

			<td>List all product records in the given category</td>
			<td><a href="http://localhost:8080/search/category/SANDPAPER">Preffered
					category</a></td>
			<td>/search/category/{category}</td>
		</tr>

		<tr></tr>

		<tr>
			<td>Returns all records in the given </br>category</br> which also match
				the keyword
			</td>
			<td><a
				href="http://localhost:8080/search/category/SANDPAPER/keyword/TOOLS">Search
					inside the preferred category</a></td>
			<td>/search/category/{category}/keyword/{word}</td>
		</tr>

		<tr>
			<td>Return all keywords in the system</td>
			<td><a href="http://localhost:8080/search/keyword">Get all
					keyword</a></td>
			<td>/search/keyword</td>
		</tr>

		<tr></tr>
		
		<tr>
			<td>Returns all records matching the single </br>keyword in any
				category
			</td>
			<td><a href="http://localhost:8080/search/keyword/TOOLS">Get
					products with given keywords</a></td>
			<td>/search/keyword/{word}</td>
		</tr>
	</table>

</body>
</html>