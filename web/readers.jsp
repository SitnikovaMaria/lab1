<%@page import="java.util.Map"%>
﻿<%@ page language="java" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page  import = "model.User" %>
<%@ page  import = "jdbc.jdbcUser" %>
<%@ page  import = "java.util.ArrayList" %>

<!DOCTYPE html>

<html>

<head>
        <link rel="stylesheet" href="start.css">
	<link rel="stylesheet" href="action.css">
	<link rel="stylesheet" href="style-modal.css" rel="stylesheet">


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
		color: #ADD8E6; /* Цвет текста */
	}
	.headerLeft ul {
    		list-style: none; /* Для списка убираем маркеры */
    		margin: 0; /* Нет отступов вокруг */
    		padding: 0; /* Убираем поля вокруг текста */
    		font-family: Arial, sans-serif; /* Рубленый шрифт для текста меню */
    		font-size: 12pt; /* Размер названий в пункте меню */
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
   	}
   	.headerLeft li a:hover {
		outline: none;
    		color: #FFFFFF; /* Цвет текста активного пункта */
    	}
   	.headerLeft li:hover ul { 
		border-color: black;
    		display: block; /* При выделении пункта курсором мыши отображается подменю */
   	}
	ul.pixel-content {
		list-style-type: none;
		margin: 0;
		padding: 0;
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
		
		overflow: hidden;
	}
</style>

</head>
<body>

	<div id='header'>

		<div id='headerLeft' class="headerLeft">
			<ul>
				<li>
					<img src="menu.png" height="50">	
					<ul>
						<li><a href="admin.jsp">Назад</a></li>
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
            		<div class="content-tab">
				<div id="name">
					<h2>Читатели</h2>
				</div>
				<div id="search">
					<select style="width:120px;" tabindex="1">
						<option disabled>Выберите параметр</option>
            			<option value="ID">№</option>
            			<option value="midleName">Фамилия</option>
						<option value="name">Имя</option>
						<option value="lastName">Отчество</option>
                                                <option value="type">Тип</option>
					</select>
					<input></input>
					<input type="submit" value ="Поиск"></input>
				</div>
                            
                                <div id="add">
					<a href="#win1"><button><img src="add.png" height="15">Добавить</button></a>
				</div>
				
                                <a href="#x" class="overlay1" id="win1"></a>
   				<div class="popup">
					<div id="act">
						<p1>Название</p1><input></input>
					</div>
					<div id="act">
						<p1>Авторы</p1><input></input>
					</div>
					<div id="act">
						<p1>Год</p1><input></input>
					</div>
					<div id="act">
						<p1>Страницы</p1><input></input>
					</div>
					<div id="act">
						<p1>Каталог</p1>
						<select style="width:165px;" tabindex="1">
							<option disabled>Выберите каталог</option>
            						<option value="type1">Общий</option>
            						<option value="type2">Романы</option>
            						<option value="type2">Поэзия</option>
            						<option value="type3">Поэмы</option>
						</select>
					</div>
					<div id="act">
						<p1>Издательство</p1>
						<select style="width:165px;" tabindex="1">
							<option disabled>Выберите издательство</option>
            						<option value="type1">Дрофа</option>
            						<option value="type2">Просвещение</option>
						</select>
					</div>
					</br>
					<a href="admin.jsp"><button>Принять</button></a> 
    					<a class="close" title="Закрыть" href="#close"></a>
    				</div>
                                
				<table cols="6" width="130%" id="tab">
					<tr>
						<td><p1>№</p1></td><td><p1>Фамилия</p1></td><td><p1>Имя</p1></td>
						<td><p1>Отчество</p1></td><td><p1>Тип</p1></td><td><p1>Действие</p1></td>
					</tr>
					<%  jdbcUser tmpUser = new jdbcUser();
                                            Map<Long,User> listUser = tmpUser.getWithId();
             for(Map.Entry<Long, User> lUser : listUser.entrySet()){
                 User user = lUser.getValue();
             %>
             <tr>
              <td><%out.print(lUser.getKey());%></td>
              <td><%out.print(user.getName());%></td>
              <td><%out.print(user.getLastName());%></td>
              <td><%out.print(user.getMiddleName());%></td>
              <td><%out.print(user.getType());%></td>  
              <td>
							<a href="#win1">
								<button><img src="change.png" height="15">Изменить</button>
							</a>
							<a href="admin.jsp">
								<button><img src="delete.png" height="15"/>Удалить</button>
							</a>
						</td>
             </tr>  
             <% }
             %>
				</table>
			</div> 
		</ul>
	
	</div>


	<div id='footer' class="footer">

		</br>
		</br>
		<a href="google.com"><b>Сайт разработчиков</b></a><b> - </b>
		<a href="google.com"><b>Вопросы и предложения</b></a><b> - </b>
		<a href="google.com"><b>Партнёры</b></a></li><b> - </b>
		<a href="google.com"><b>Справка</b></a></li><b> - </b> 
	</div>
</html>
