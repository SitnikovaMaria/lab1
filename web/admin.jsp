<%@page import="model.Meeting"%>
<%@page import="SSB.MeetingJpaController"%>
<%@page import="java.util.List"%>
<%@page import="SSB.UserJpaController"%>
﻿<%@ page language="java" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="model.Book" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="jdbc.jdbcBook" %>
<%@ page import="jdbc.jdbcCopyOfBook" %>
<%@ page import="model.CopyOfTheBook" %>
<%@ page import="jdbc.jdbcPublisher" %>
<%@ page import="model.Publisher" %>
<%@ page import="jdbc.jdbcUser" %>
<%@ page import="model.User" %>
<%@ page import="jdbc.jdbcCatalog" %>
<%@ page import="model.Catalog" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>

<html>

<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="start.css">
    <link rel="stylesheet" href="action.css">
    <link rel="stylesheet" href="style-modal.css" rel="stylesheet">
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
		
		transition: all 1s;	
	}
	.footer a:visited {
		color: #ADD8E6;
	}
	.footer a:link {
		color: #ADD8E6;
		padding: 2px;
	}
	.footer a:hover {
		color: #FFFFFF;
		font-size: 16pt;
		transition: all 1s;	 
	}
	.headerLeft a {
		color: #ADD8E6; /* Цвет текста */
	}
	.headerLeft ul {
    		list-style: none; /* Для списка убираем маркеры */
    		margin: 0; /* Нет отступов вокруг */
    		padding: 0; /* Убираем поля вокруг текста */
    		font-family: Arial, sans-serif; /* Рубленый шрифт для текста меню */
    		font-size: 12pt; /* Размер названий в пункте меню */
			
		transition: all 1s;	
   	}
   	.headerLeft li ul {
    		position: absolute; /* Подменю позиционируются абсолютно */
    		display: none; /* Скрываем подменю */
	}
	.headerLeft li a {
    		display: block; /* Ссылка как блочный элемент */
    		padding: 5px; /* Поля вокруг надписи */
    		text-decoration: none; /* Подчеркивание у ссылок убираем */
    		border: 1px solid #ccc; /* Рамка вокруг пунктов меню */
    		background-color: #000000; /* Цвет фона */
			
		transition: all 1s;	
   	}
   	.headerLeft li a:hover {
		
		outline: none;
    	color: #FFFFFF; /* Цвет текста активного пункта */ 
			
    	}
   	.headerLeft li:hover ul { 
	position: fixed; 
		z-index: 10000;
		border-color: black;
    		display: block; /* При выделении пункта курсором мыши отображается подменю */
			
		transition: all 1s;	
   	}
	ul.pixel-content {
		
		list-style-type: none;
		margin: 0;
		padding: 0;
		height: 100%;
		width: 100%;
		overflow: hidden;
	}
	ul.pixel-content li {
		
		display: inline-block;
		line-height: 30px;
		border: 1px solid #786b59;
		border-bottom: 0 none;
	}
	ul.pixel-content li a.tab {
		border-bottom: 1px solid #786b59;
		height: 29px;
		transition: all 1s;	 
	}
	ul.pixel-content li a {
		color: #666;
		text-decoration: none;
		display: inline-block;
		padding: 0px 12px;
		white-space: nowrap;
	}
	ul.pixel-content li a:hover {
		background-color: #fff;
		font-size: 12pt;
		transition: all 1s;	 
		border-bottom: none;
	}
	ul.pixel-content li a.active-tab {
		background: #fff;
		border-bottom: 0 none;
	}
	.content-tab {	
		overflow: hidden;
		width: 96%;
		height: 80%; 	
		border: 1px solid #786b59;
		margin: -1px 20px 20px 20px;
		padding: 20px;
		border-bottom-style: none;
	}
	div.content {
		position: absolute;
		height: 80%; 
		width: 98%;
		overflow: hidden;
	}
	::-webkit-scrollbar {
		width: 12px;
	}
 
	::-webkit-scrollbar-track {
		-webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3); 
		border-radius: 10px;
	}
 
	::-webkit-scrollbar-thumb {
		border-radius: 10px;
		background-color: rgba(0, 0, 0, 0.4);
		-webkit-box-shadow: inset 0 0 6px rgba(0,0,0,1); 		
	}
	::-webkit-scrollbar-thumb:hover {
		background-color: rgba(0 ,0, 0, 0.2);
	}
	.backg{
		overflow: hidden;		
		font-family: Helvetica, Sans-serif;	
		background-image: url("back.jpg");
		background-position: center; 
		background-size: 250% 300%;
		position:absolute;
		height: 100%;
		width: 100%;
	}
	.forminoneline {
    display: inline-block;
	}
</style>

<body>
<div class="backg">
<div id='header'>
    <div id='headerLeft' class="headerLeft">
        <ul>
            <li>
                <img src="menu.png" height="50">
                <ul>
                    <li><a href="xedni.jsp">Каталоги</a></li>
                    <li><a href="users_table.jsp">Пользователи</a></li>
                    <li><a href="start.jsp">Выйти</a></li>
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
            <form method="POST" action="admin.jsp">
                <div id="search">
                <select style="width:120px;" tabindex="1"  name="finder">
                    <option disabled>Выберите параметр</option>
                    <option value="idBook">№</option>
                    <option value="name">Название</option>
                    <option value="authors">Авторы</option>
                    <option value="year">Год</option>
                    <option value="pages">Страницы</option>
                    <option value="catalog">Каталог</option>
                    <option value="idPublisher">Издательство</option>
                </select>
                <input type="text" name="text" value="<%request.getParameter("text");%>">
                <input type="submit" value="Поиск"></input>          
				</div>
            </form>
			<div class="forminoneline">
				<form method="post" action="ImportBooks">
                    <img src="import.jpg" height="15"> <input type="submit" value="Загрузить книги">
                </form>
			</div>
			<div class="forminoneline">
			    <form method="post" action="ExportAllLines">
                    <img src="export.jpg" height="15"> <input type="submit" value="Сохранить все">
                </form>
			</div>	
            <div id="add">
                <a href="#win1">
                    <button><img src="add.png" height="15">Добавить</button>
                </a>
            </div>
            <a href="#x" class="overlay1" id="win1"></a>
            <div class="popup">
                <form method="Post" action="AddBook">
                    <p1>Добавление книги</p1>
                    <div id="act">
                        <p1>Название</p1>
                        <input type="text" name="name">
                    </div>
                    <div id="act">
                        <p1>Авторы</p1>
                        <input type="text" name="authors">
                    </div>
                    <div id="act">
                        <p1>Год</p1>
                        <input type="text" name="year">
                    </div>
                    <div id="act">
                        <p1>Страницы</p1>
                        <input type="text" name="pages">
                    </div>
                    <div id="act">
                        <p1>Каталог</p1>
                        <select style="width:165px;" tabindex="1" name="catalog">
                            <option disabled>Выберите каталог</option>
                            <%     MeetingJpaController test = new MeetingJpaController();    
                            List<Meeting> testuserList = test.getAll();
                            System.out.println(testuserList);
    
                                jdbcCatalog tmpCatalog = new jdbcCatalog();
                                ArrayList<Catalog> listCatalog = tmpCatalog.get();
                                for (Catalog catalog : listCatalog) {
                            %>
                            <option value="<%out.print(catalog.getName());%>"><%
                                out.print(catalog.getName());%></option>
                            <% }
                            %>
                        </select>
                    </div>
                    <div id="act">
                        <p1>Издательство</p1>
                        <select style="width:165px;" tabindex="1" name="publisher">
                            <option disabled>Выберите издательство</option>
                            <% jdbcPublisher tmpPublisher = new jdbcPublisher();
                                ArrayList<Publisher> listPublisher = tmpPublisher.get();
                                for (Publisher publisher : listPublisher) { %>
                                    <option value="<%out.print(publisher.getName());%>">
                                    <%out.print(publisher.getName());%></option>
                                <% } %>
                        </select>
                    </div>
                    </br>
                    <div>
                        <p1>
                            <input type="submit" value="Принять">
                        </p1>
                    </div>
                    <a class="close" title="Закрыть" href="#close"></a>
                </form>
            </div>
            <br><br>
            <div style="overflow-y:auto; width: 90%; height: 80%; margin:auto;">
				<table cols="7" width="100%" id="tab" style="margin-top: 0px;">
                    <tr>
                        <td>
                            <p1>№</p1>
                        </td>
                        <td>
                            <p1>Название</p1>
                        </td>
                        <td>
                            <p1>Авторы</p1>
                        </td>
                        <td>
                            <p1>Год</p1>
                        </td>
                        <td>
                            <p1>Страницы</p1>
                        </td>
                        <td>
                            <p1>Каталог</p1>
                        </td>
                        <td>
                            <p1>Издательство</p1>
                        </td>
                        <td>
                            <p1>Действие</p1>
                        </td>
                    </tr>
                    <% jdbcBook tmpBook = new jdbcBook();
                        ArrayList<Book> listBook;
                        if (request.getParameter("finder") != null && request.getParameter("text") != null) {
                            listBook = tmpBook.get(request.getParameter("finder"), request.getParameter("text"));
                        } else {
                            listBook = tmpBook.get();
                        }
                        for (Book book : listBook) { %>
                    <tr>
                        <td><%out.print(book.getIdBook());%></td>
                        <td><%out.print(book.getName());%></td>
                        <td><%out.print(book.getAuthors());%></td>
                        <td><% SimpleDateFormat format = new SimpleDateFormat("yyyy"); %>
                        <%out.print(format.format(book.getYear()));%></td>
                        <td><%out.print(book.getPages());%></td>
                        <td><%out.print(book.getCatalog());%></td>
                        <td><% for (Publisher publisher : listPublisher) {
                                if (publisher.getIdPublisher()==book.getIdPublisher()) {
                                    out.print(publisher.getName());
                                }
                            } %>
                        </td>
                        <td>
                            <div id="change">
                                <a href="#win400<%out.print(book.getIdBook());%>">
                                    <button><img src="change.png" height="15">Изменить</button>
                                </a>
                            </div>

                            <a href="#x" class="overlay2" id="win400<%out.print(book.getIdBook());%>"></a>
                            <div class="popup">
                                <form method="post" action="ChangeBook">
                                    <p1>Изменение книги</p1>
                                    <input type="hidden" value="<%out.print(book.getIdBook());%>" name="idBook">
                                    <div id="act">
                                        <p1>Название</p1>
                                        <input type="text" name="name" value="<%out.print(book.getName());%>">
                                    </div>
                                    <div id="act">
                                        <p1>Авторы</p1>
                                        <input type="text" name="authors" value="<%out.print(book.getAuthors());%>">
                                    </div>
                                    <div id="act">
                                        <p1>Год</p1>
                                        <input type="text" name="year" value="<%out.print(format.format(book.getYear()));%>">
                                        <p2>ГГГГ</p2>
                                    </div>
                                    <div id="act">
                                        <p1>Страницы</p1>
                                        <input type="text" name="pages" value="<%out.print(book.getPages());%>">
                                    </div>
                                    <div id="act">
                                        <p1>Каталог</p1>
                                        <select style="width:165px;" tabindex="1" name="catalog">
                                            <option disabled>Выберите каталог</option>
                                            <% for (Catalog catalog : listCatalog) {
                                                if (catalog.getName()==book.getCatalog()) {%>
                                                    <option selected value="<%out.print(catalog.getIdCatalog());%>">
                                                    <%out.print(catalog.getName());%></option>
                                                <% } else { %>
                                                    <option value="<%out.print(catalog.getIdCatalog());%>">
                                                    <%out.print(catalog.getName());%></option>
                                                <% } %>
                                            <% } %>
                                        </select>
                                    </div>
                                    <div id="act">
                                        <p1>Издательство</p1>
                                        <select style="width:165px;" tabindex="1" name="idPublisher">
                                            <option disabled>Выберите издательство</option>
                                            <% for (Publisher publisher : listPublisher) {
                                                if (publisher.getIdPublisher()==book.getIdPublisher()) {%>
                                                    <option selected value="<%out.print(publisher.getIdPublisher());%>">
                                                    <%out.print(publisher.getName());%></option>
                                                <%} else {%>
                                                    <option value="<%out.print(publisher.getIdPublisher());%>">
                                                    <%out.print(publisher.getName());%></option>
                                                <%}%>
                                            <% } %>
                                        </select>
                                    </div>
                                    </br>
                                    <input type="submit" value="Принять">
                                    <a class="close" title="Закрыть" href="#close"></a>
                                </form>
                            </div>

                            <form method="post" action="DeleteBook">
                                <input type="hidden" value="<%out.print(book.getIdBook());%>" name="id">
                                <img src="delete.png" height="15"> <input type="submit" value="Удалить">
                            </form>
                            <form method="post" action="ExportOneLine">
                                <input type="hidden" value="<%out.print(book.getIdBook());%>" name="id">
                                <img src="save.png" height="15"> <input type="submit" value="Сохранить">
                            </form>
                        </td>
                    </tr>
                    <% } %>
                </table>
            </div>
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
			<form method="POST" action="admin.jsp#two-tab">
				<div id="search">
					<select style="width:120px;" tabindex="1" name="findC">
						<option disabled>Выберите параметр</option>
						<option value="inventoryNumber">№</option>
						<option value="idBook">№ книги</option>
						<option value="issue">Информация о выдаче</option>
						<option value="idUser">Читатель</option>
					</select>
					<input type="text" name="textCopy">
					<input type="submit" value="Поиск"></input>
				</div>
			</form>
            <div id="add">
                <a href="#win2">
                    <button><img src="add.png" height="15">Добавить</button>
                </a>
            </div>
            <a href="#x" class="overlay2" id="win2"></a>
            <div class="popup">
                <form method="GET" action="AddCopyOfBook">
                    <p1>Добавление экземпляра книги</p1>
                    <div id="act">
                        <p1>№ книги</p1>
                        <select style="width:165px;" tabindex="1" name="idBook">
                            <option disabled>Выберите книгу</option>
                            <% for (Book book : listBook) { %>
                            <option value="<%out.print(book.getIdBook());%>"><%
                                out.print(book.getIdBook());%></option>
                            <% } %>
                        </select>
                    </div>
                    <div id="act">
                        <p1>Информация о выдаче</p1>
                        <select style="width:165px;" tabindex="1" name="issue">
                            <option value="">Не выдана</option>
                            <option value="true">Выдана</option>
                        </select>
                    </div>
                    <div id="act">
                        <p1>Читатель</p1>
                        <select style="width:165px;" tabindex="1" name="user">
                            <option disabled>Выберите читателя</option>
                            <option value="0">Отсутствует</option>
                            <% jdbcUser tmpUser = new jdbcUser();
                                ArrayList<User> listUser = tmpUser.get();
                                for (User user : listUser) { %>
                            <option value="<%out.print(user.getLastName()+" "+user.getName()+" "+user.getMiddleName());%>">
                                <%out.print(user.getLastName()+" "+user.getName()+" "+user.getMiddleName());%>                                
                            </option>
                            <% }
                            %>
                        </select>
                    </div>
                    </br>
                    <input type="submit" value="Принять">
                    <a class="close" title="Закрыть" href="#close"></a>
                </form>
            </div>
			<br>
		<br>
		<div style="overflow-y:auto; width: 90%; height: 80%; margin:auto;">
        
        
            <table cols="7" width="100%" id="tab" style="margin-top: 0px;">
                <tr>
                    <td>
                        <p1>№</p1>
                    </td>
                    <td>
                        <p1>№ книги</p1>
                    </td>
                    <td>
                        <p1>Информация о выдаче</p1>
                    </td>
                    <td>
                        <p1>Читатель</p1>
                    </td>
                    <td>
                        <p1>Действие</p1>
                    </td>
                </tr>
                <% jdbcCopyOfBook tmpCopy = new jdbcCopyOfBook();
                    ArrayList<CopyOfTheBook> listCopy;
                    if (request.getParameter("findC") != null && request.getParameter("textCopy") != null)
			{
                            listCopy = tmpCopy.get(request.getParameter("findC"), request.getParameter("textCopy"));
                        } else {
                            listCopy = tmpCopy.get();
                        }
                    for (CopyOfTheBook copy : listCopy) {
                %>
                <tr>
                    <td><%out.print(copy.getInventoryNumber());%></td>
                    <td><%out.print(copy.getIdBook());%></td>
                    <%  String issue="";
                        if (copy.getIssue()==false){
                            issue = "Не выдана";
                        } else {
                            issue = "Выдана";
                        }
                    %>
                    <td><%out.print(issue);%></td>
                    <%  String name="";
                        if (copy.getIdUser()==0){
                            name = "Отсутствует";
                        } else {
                            for (User user : listUser) {
                                if (user.getIdUser() == copy.getIdUser()) {
                                    name = user.getName() + " " + user.getMiddleName() + " " + user.getLastName();
                                }
                            }
                        }
                    %>
                    <td><%out.print(name);%></td>
                    <td><div id="change">
                            <a href="#win500<%out.print(copy.getInventoryNumber());%>">
                                <button><img src="change.png" height="15">Изменить</button>
                            </a>
                        </div>

                        <a href="#x" class="overlay2" id="win500<%out.print(copy.getInventoryNumber());%>"></a>
                        <div class="popup">
                            <form method="post" action="ChangeCopyOfBook">
                                <p1>Изменение экземпляра книги</p1>
                                <input type="hidden" value="<%out.print(copy.getInventoryNumber());%>" name="inventoryNumber">
                                <div id="act">
                                    <p1>№ книги</p1>
                                    <select style="width:165px;" tabindex="1" name="idBook">
                                        <option disabled>Выберите книгу</option>
                                        <% for (Book book : listBook) { %>
                                            <%if (copy.getIdBook()==book.getIdBook()) {%>
                                                <option selected value="<%out.print(book.getIdBook());%>">
                                                <%out.print(book.getIdBook()+" - '"+book.getName()+"' - '"+book.getAuthors()+"'");%></option>
                                            <% } else { %>
                                                <option value="<%out.print(book.getIdBook());%>">
                                                <%out.print(book.getIdBook()+" - '"+book.getName()+"' - '"+book.getAuthors()+"'");%></option>
                                            <% } %>
                                        <% } %>
                                    </select>
                                </div>
                                <div id="act">
                                    <p1>Информация о выдаче</p1>
                                    <select style="width:165px;" tabindex="1" name="issue">
                                        <% if (copy.getIssue()==false){ %>
                                            <option selected value="0">Не выдана</option>
                                            <option value="1">Выдана</option>
                                        <% } else { %>
                                            <option value="0">Не выдана</option>
                                            <option selected  value="1">Выдана</option>
                                        <% } %>
                                    </select>
                                </div>
                                <div id="act">
                                    <p1>Читатель</p1>
                                    <select style="width:165px;" tabindex="1" name="idUser">
                                        <option disabled>Выберите читателя</option>
                                        <% for (User user : listUser) { %>
                                            <% if (user.getIdUser()==0){ %>
                                                <% if(copy.getIdUser()==0){ %>
                                                    <option selected value="0">
                                                    Отсутствует</option>
                                                <% } else { %>
                                                    <option value="0">
                                                    Отсутствует</option>
                                                <% } %>
                                            <% } else { %>
                                                <% if(copy.getIdUser()==user.getIdUser()){ %>
                                                    <option selected value="<%out.print(user.getIdUser());%>">
                                                    <%out.print(user.getLastName());%>
                                                    <%out.print(user.getName());%>
                                                    <%out.print(user.getMiddleName());%>
                                                    </option>
                                                <% } else { %>
                                                    <option value="<%out.print(user.getIdUser());%>">
                                                    <%out.print(user.getLastName());%>
                                                    <%out.print(user.getName());%>
                                                    <%out.print(user.getMiddleName());%>
                                                    </option>
                                                <% } %>
                                            <% } %>
                                        <% } %>
                                    </select>
                                </div>
                                </br>
                                <input type="submit" value="Принять">
                                <a class="close" title="Закрыть" href="#close"></a>
                            </form>
                        </div>

                        <form method="post" action="DeleteCopyOfBook">
                            <input type="hidden" value="<%out.print(copy.getInventoryNumber());%>" name="id">
                            <img src="delete.png" height="15"> <input type="submit" value="Удалить">
                        </form>
                    </td>
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
            <form method="POST" action="admin.jsp#three-tab">
            <div id="search">
                <select style="width:120px;" tabindex="1" name="fPublisher">
                    <option disabled>Выберите параметр</option>
                    <option value="idPublisher">№</option>
                    <option value="name">Название</option>
                    <option value="registeredAddress">Адрес</option>
                    <option value="businessAddress">Почта</option>
                </select>
                <input type="text" name="textPublisher">
		<input type="submit" value="Поиск"></input>
            </div>
            </form>
            <div id="add">
                <a href="#win3">
                    <button><img src="add.png" height="15">Добавить</button>
                </a>
            </div>
            <a href="#x" class="overlay3" id="win3"></a>
            <div class="popup">
                <form method="GET" action="AddPublisher">
                    <p1>Добавление издательства</p1>
                    <div id="act">
                        <p1>Название</p1>
                        <input type="text" name="name">
                    </div>
                    <div id="act">
                        <p1>Адрес</p1>
                        <input type="text" name="address">
                    </div>
                    <div id="act">
                        <p1>Почта</p1>
                        <input type="text" name="mail">
                    </div>
                    </br>
                    <input type="submit" value="Принять">
                    <a class="close" title="Закрыть" href="#close"></a>
                </form>
            </div>
            <br><br>
            <div style="overflow-y:auto; width: 90%; height: 90%; margin:auto;">
                <table cols="7" width="100%" id="tab" style="margin-top: 0px;">
                    <tr>
                        <td>
                            <p1>№</p1>
                        </td>
                        <td>
                            <p1>Название</p1>
                        </td>
                        <td>
                            <p1>Адрес</p1>
                        </td>
                        <td>
                            <p1>Почта</p1>
                        </td>
                        <td>
                            <p1>Действие</p1>
                        </td>
                    </tr>
                    <%
                        tmpPublisher = new jdbcPublisher();
                        if (request.getParameter("fPublisher") != null && request.getParameter("textPublisher") != null)
			{
                            listPublisher = tmpPublisher.get(request.getParameter("fPublisher"), request.getParameter("textPublisher"));
                        } else {
                            listPublisher = tmpPublisher.get();
                        }
                        for (Publisher publisher : listPublisher) {
                    %>
                    <tr>
                        <td><%out.print(publisher.getIdPublisher());%></td>
                        <td><%out.print(publisher.getName());%></td>
                        <td><%out.print(publisher.getRegisteredAddress());%></td>
                        <td><%out.print(publisher.getBusinessAddress());%></td>
                        <td>
                            <div id="change">
                                <a href="#win300<%out.print(publisher.getIdPublisher());%>">
                                    <button><img src="change.png" height="15">Изменить</button>
                                </a>
                            </div>

                            <a href="#x" class="overlay2" id="win300<%out.print(publisher.getIdPublisher());%>"></a>
                            <div class="popup">
                                <form method="post" action="ChangePublisher">
                                    <p1>Изменение издательства</p1>
                                    <input type="hidden" value="<%out.print(publisher.getIdPublisher());%>" name="idPublisher">
                                    <div id="act">
                                        <p1>Название</p1>
                                        <input type="text" name="name" value="<%out.print(publisher.getName());%>">
                                    </div>
                                    <div id="act">
                                        <p1>Адрес</p1>
                                        <input type="text" name="address" value="<%out.print(publisher.getRegisteredAddress());%>">
                                    </div>
                                    <div id="act">
                                        <p1>Почта</p1>
                                        <input type="text" name="mail" value="<%out.print(publisher.getBusinessAddress());%>">
                                    </div>
                                    </br>
                                    <input type="submit" value="Принять">
                                    <a class="close" title="Закрыть" href="#close"></a>
                                </form>
                            </div>

                            <form method="post" action="DeletePublisher">
                                <input type="hidden" value="<%out.print(publisher.getIdPublisher());%>"
                                       name="id">
                                <img src="delete.png" height="15"> <input type="submit" value="Удалить">
                            </form>
                        </td>
                    </tr>
                    <% }
                    %>
                </table>
            </div>
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
</div>
</body>
</html>
