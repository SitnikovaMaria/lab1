<%@ page language="java" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import = "model.Book" %>
<%@ page  import = "java.util.ArrayList" %>
<%@ page import = "jdbc.jdbcBook" %>
<%@ page import = "jdbc.jdbcCopyOfBook" %>
<%@ page import = "model.CopyOfTheBook" %>
<%@ page import = "jdbc.jdbcPublisher" %>
<%@ page import = "model.Publisher" %>

<!DOCTYPE html>

<html>

<head>
	<link rel="stylesheet" href="start.css">
	<title>Библиотека</title>
</head>

<style>
	p1 {
		font: bold 110% serif; 
	}
	p2 {
		font-style: italic; 
	}
	a { 
		text-decoration: none;
	}
	table {
		border: 1px solid #786b59;
		border-collapse: collapse;
	}
	td {
		border: 1px solid #786b59;
	}
	.footer a{
		color: #ADD8E6;
	}
	.footer a:visited {
		color: #ADD8E6;
	}
	.footer a:link {
		color: #ADD8E6;
		padding: 2px;
	}
	.footer a:hover {
		background: #786b59;  
		color: #ffe;  
	}
	.headerLeft a {
		color: #ADD8E6; /* Ð¦Ð²ÐµÑ ÑÐµÐºÑÑÐ° */
	}
	.headerLeft ul {
    		list-style: none; /* ÐÐ»Ñ ÑÐ¿Ð¸ÑÐºÐ° ÑÐ±Ð¸ÑÐ°ÐµÐ¼ Ð¼Ð°ÑÐºÐµÑÑ */
    		margin: 0; /* ÐÐµÑ Ð¾ÑÑÑÑÐ¿Ð¾Ð² Ð²Ð¾ÐºÑÑÐ³ */
    		padding: 0; /* Ð£Ð±Ð¸ÑÐ°ÐµÐ¼ Ð¿Ð¾Ð»Ñ Ð²Ð¾ÐºÑÑÐ³ ÑÐµÐºÑÑÐ° */
    		font-family: Arial, sans-serif; /* Ð ÑÐ±Ð»ÐµÐ½ÑÐ¹ ÑÑÐ¸ÑÑ Ð´Ð»Ñ ÑÐµÐºÑÑÐ° Ð¼ÐµÐ½Ñ */
    		font-size: 12pt; /* Ð Ð°Ð·Ð¼ÐµÑ Ð½Ð°Ð·Ð²Ð°Ð½Ð¸Ð¹ Ð² Ð¿ÑÐ½ÐºÑÐµ Ð¼ÐµÐ½Ñ */
   	}
   	.headerLeft li ul {
    		position: absolute; /* ÐÐ¾Ð´Ð¼ÐµÐ½Ñ Ð¿Ð¾Ð·Ð¸ÑÐ¸Ð¾Ð½Ð¸ÑÑÑÑÑÑ Ð°Ð±ÑÐ¾Ð»ÑÑÐ½Ð¾ */
    		display: none; /* Ð¡ÐºÑÑÐ²Ð°ÐµÐ¼ Ð¿Ð¾Ð´Ð¼ÐµÐ½Ñ */
	}
	.headerLeft li a {
    		display: block; /* Ð¡ÑÑÐ»ÐºÐ° ÐºÐ°Ðº Ð±Ð»Ð¾ÑÐ½ÑÐ¹ ÑÐ»ÐµÐ¼ÐµÐ½Ñ */
    		padding: 5px; /* ÐÐ¾Ð»Ñ Ð²Ð¾ÐºÑÑÐ³ Ð½Ð°Ð´Ð¿Ð¸ÑÐ¸ */
    		text-decoration: none; /* ÐÐ¾Ð´ÑÐµÑÐºÐ¸Ð²Ð°Ð½Ð¸Ðµ Ñ ÑÑÑÐ»Ð¾Ðº ÑÐ±Ð¸ÑÐ°ÐµÐ¼ */
    		border: 1px solid #ccc; /* Ð Ð°Ð¼ÐºÐ° Ð²Ð¾ÐºÑÑÐ³ Ð¿ÑÐ½ÐºÑÐ¾Ð² Ð¼ÐµÐ½Ñ */
    		background-color: #000000; /* Ð¦Ð²ÐµÑ ÑÐ¾Ð½Ð° */
   	}
   	.headerLeft li a:hover {
		outline: none;
    		color: #FFFFFF; /* Ð¦Ð²ÐµÑ ÑÐµÐºÑÑÐ° Ð°ÐºÑÐ¸Ð²Ð½Ð¾Ð³Ð¾ Ð¿ÑÐ½ÐºÑÐ° */
    	}
   	.headerLeft li:hover ul { 
		border-color: black;
    		display: block; /* ÐÑÐ¸ Ð²ÑÐ´ÐµÐ»ÐµÐ½Ð¸Ð¸ Ð¿ÑÐ½ÐºÑÐ° ÐºÑÑÑÐ¾ÑÐ¾Ð¼ Ð¼ÑÑÐ¸ Ð¾ÑÐ¾Ð±ÑÐ°Ð¶Ð°ÐµÑÑÑ Ð¿Ð¾Ð´Ð¼ÐµÐ½Ñ */
   	}
	ul.pixel-content {
		list-style-type: none;
		margin: 0;
		padding: 0;
		height: 532px;
		overflow: visible;
	}
	ul.pixel-content li {
		display: inline-block;
		line-height: 30px;
		border: 1px solid #786b59;
		border-bottom: 0 none;
	}
	ul.pixel-content li a.tab {
		border-bottom: 1px solid #786b59;
		background: #eee;
		height: 29px;
	}
	ul.pixel-content li a {
		color: #666;
		text-decoration: none;
		display: inline-block;
		padding: 0px 12px;
		white-space: nowrap;
	}
	ul.pixel-content li a:hover {
		border-bottom: none;
	}
	ul.pixel-content li a.active-tab {
		background: #fff;
		border-bottom: 0 none;
	}
	.content-tab {
		border: 1px solid #786b59;
		margin: -1px 0 0 0;
		padding: 20px;
		border-bottom-style: none;
	}
	div.content {
		height: 532px;
		overflow: hidden;
	}
</style>

<body>

	<div id='header'>

		<div id='headerLeft' class="headerLeft">
			<ul>
				<li>
					<img src="menu.png" height="50">	
					<ul>
						<li><a href="catalogsS.html">Каталоги</a></li>
						<li><a href="index.jsp">Войти</a></li>
						<li><a href="registration.html">Регистрация</a></li>
					</ul>
				</li>
			</ul>			
		</div>
		<div id='headerCenter'>
			<img src="books.png" height="50">
		</div>
		<div id='headerRight'>
			<h>Книжка - лучший товарищ</h>
		</div>
	</div>


	<div id='content' class="content">
		<ul class="pixel-content" id="one-tab">
         		<li><a href="#one-tab" class="active-tab">Книги</a></li>
         		<li><a href="#two-tab" class="tab">Экземпляры книг</a></li> 
			<li><a href="#three-tab" class="tab">Издательства</a></li>
            		<div class="content-tab">
				<div id="name">
					<h2>Книги</h2>
				</div>
				<div id="search">
					<select style="width:120px;" tabindex="1">
						<option disabled>Выберите параметр</option>
            					<option value="ID">№</option>
            					<option value="Name">Название</option>
						<option value="Authors">Авторы</option>
						<option value="Year">Год</option>
						<option value="Pages">Страницы</option>
						<option value="Catalog">Каталог</option>
						<option value="Publisher">Издательство</option>
					</select>
					<input></input>
					<input type="submit" value ="Поиск"></input>
				</div>
                            <br>
                            <br>
                                <div style="overflow-y:scroll !important; width: 1200px; height: 340px; margin:auto;">
				<table cols="7" width="100%" id="tab" style="margin-top: 0px;">
					<tr>
						<td><p1>№</p1></td><td><p1>Название</p1></td><td><p1>Авторы</p1></td>
						<td><p1>Год</p1></td><td><p1>Страницы</p1></td><td><p1>Каталог</p1></td></td>
						<td><p1>Издательство</p1></td>
					</tr>
					<%  jdbcBook tmpBook = new jdbcBook();
                                            ArrayList<Book> listBook = tmpBook.get();
             for(Book book: listBook){
             %>
             <tr>
              <td><%out.print(book.getIdBook());%></td>
              <td><%out.print(book.getName());%></td>
              <td><%out.print(book.getAuthors());%></td>
              <td><%out.print(book.getYear());%></td>
              <td><%out.print(book.getPages());%></td>     
              <td><%out.print(book.getCatalog());%></td>  
              <td><%out.print(book.getPublisher());%></td>  
             </tr>  
             <% }
             %>
				</table>
			</div> 
		</ul>
		<ul class="pixel-content" id="two-tab">
			<li><a href="#one-tab" class="tab">Книги</a></li>
			<li><a href="#two-tab" class="active-tab">Экземпляры книг</a></li>
			<li><a href="#three-tab" class="tab">Издательства</a></li> 
			<div class="content-tab">
				<div id="name">
					<h2>Экземпляры книг</h2>
				</div>
				<div id="search">
					<select style="width:170px;" tabindex="1">
						<option disabled>Выберите параметр</option>
            					<option value="Inventory Number">№</option>
            					<option value="Book ID">№ книги</option>
						<option value="Issue">Информация о выдаче</option>
						<option value="Reader">Читатель</option>
					</select>
					<input></input>
					<input type="submit" value ="Поиск"></input>
				</div>
                            <br>
                            <br>
                                <div style="overflow-y:scroll !important; width: 1200px; height: 340px; margin:auto;">
                          	<table cols="7" width="100%" id="tab" style="margin-top: 0px;">
					<tr>
						<td><p1>№</p1></td><td><p1>№ книги</p1></td>
						<td><p1>Информация о выдаче</p1></td><td><p1>Читатель</p1></td>
					</tr>
					<%  jdbcCopyOfBook tmpCopy = new jdbcCopyOfBook();
                                            ArrayList<CopyOfTheBook> listCopy = tmpCopy.get();
             for(CopyOfTheBook copy: listCopy){
             %>
             <tr>
              <td><%out.print(copy.getInventoryNumber());%></td>
              <td><%out.print(copy.getIdBook());%></td>
              <td><%out.print(copy.getIssue());%></td>
              <td><%out.print(copy.getReader());%></td>              
             </tr>  
             <% }
             %>
				</table>
            		</div> 
   		</ul>
		<ul class="pixel-content" id="three-tab">
			<li><a href="#one-tab" class="tab">Книги</a></li>
			<li><a href="#two-tab" class="tab">Экземпляры книг</a></li>
			<li><a href="#three-tab" class="active-tab">Издательства</a></li>
			<div class="content-tab">
				<div id="name">
					<h2>Издательства</h2>
				</div>
				<div id="search">
					<select style="width:120px;" tabindex="1">
						<option disabled>Выберите параметр</option>
            					<option value="ID">№</option>
            					<option value="Name">Название</option>
						<option value="Registered address">Адрес</option>
						<option value="Business address">Почта</option>
					</select>
					<input></input>
					<input type="submit" value ="Поиск"></input>
				</div>
                            <br>
                            <br>
                                <div style="overflow-y:scroll !important; width: 1200px; height: 340px; margin:auto;">
                          	<table cols="7" width="100%" id="tab" style="margin-top: 0px;">
					<tr>
						<td><p1>№</p1></td><td><p1>Название</p1></td>
						<td><p1>Адрес</p1></td><td><p1>Почта</p1></td>
					</tr>
					<%  jdbcPublisher tmpPublisher = new jdbcPublisher();
                                            ArrayList<Publisher> listPublisher = tmpPublisher.get();
             for(Publisher publisher: listPublisher){
             %>
             <tr>
              <td><%out.print(publisher.getIdPublisher());%></td>
              <td><%out.print(publisher.getName());%></td>
              <td><%out.print(publisher.getRegisteredAddress());%></td>
              <td><%out.print(publisher.getBusinessAddress());%></td>              
             </tr>  
             <% }
             %>
				</table>
            		</div> 
   		</ul>	
	</div>


	<div id='footer' class="footer" style="height: 65px;">

		</br>
		
		<a href="google.com"><b>Сайт разработчиков</b></a><b> - </b>
		<a href="google.com"><b>Вопросы и предложения</b></a><b> - </b>
		<a href="google.com"><b>Партнёры</b></a></li><b> - </b>
		<a href="google.com"><b>Справка</b></a></li><b> - </b> 
	</div>
</html>
