<%@ page language="java" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="model.Book" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="jdbc.jdbcBook" %>
<%@ page import="jdbc.jdbcCopyOfBook" %>
<%@ page import="model.CopyOfTheBook" %>
<%@ page import="jdbc.jdbcPublisher" %>
<%@ page import="model.Publisher" %>
<%@ page import="static jdk.nashorn.internal.objects.Global.print" %>
<%@ page import="jdbc.jdbcUser" %>
<%@ page import="model.User" %>
<%@ page import="java.text.SimpleDateFormat" %>

<!DOCTYPE html>

<html>

<head>
            <link rel="stylesheet" href="start.css">
    <link rel="stylesheet" href="action.css">
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
                <input type="submit" value="Поиск"></input>
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
                        </td>
                        <td>
                            <p1>Издательство</p1>
                        </td>
                    </tr>
                    <% jdbcBook tmpBook = new jdbcBook();
                        ArrayList<Book> listBook = tmpBook.get();
                        for (Book book : listBook) {
                    %>
                    <tr>
                        <td><%out.print(book.getIdBook());%></td>
                        <td><%out.print(book.getName());%></td>
                        <td><%out.print(book.getAuthors());%></td>
                        <td><% SimpleDateFormat format = new SimpleDateFormat("yyyy"); %>
                        <%out.print(format.format(book.getYear()));%></td>
                        <td><%out.print(book.getPages());%></td>
                        <td><%out.print(book.getCatalog());%></td>
                        <td><% jdbcPublisher tmpPublisher = new jdbcPublisher();
                        ArrayList<Publisher> listPublisher = tmpPublisher.get();
                        for (Publisher publisher : listPublisher) {
                            if (publisher.getIdPublisher()==book.getIdPublisher()) {
                                out.print(publisher.getName());
                            }
                        } %></td>
                    </tr>
                    <% }
                    %>
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
            <div id="search">
                <select style="width:170px;" tabindex="1">
                    <option disabled>Выберите параметр</option>
                    <option value="Inventory Number">№</option>
                    <option value="Book ID">№ книги</option>
                    <option value="Issue">Информация о выдаче</option>
                    <option value="Reader">Читатель</option>
                </select>
                <input></input>
                <input type="submit" value="Поиск"></input>
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
                    </tr>
                    <% jdbcCopyOfBook tmpCopy = new jdbcCopyOfBook();
                        ArrayList<CopyOfTheBook> listCopy = tmpCopy.get();
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
                            if (copy.getIdUser()==1){
                                name = "Отсутствует";
                            } else {
                                jdbcUser jdbcuser = new jdbcUser();
                                ArrayList<User> listUser = jdbcuser.get();
                                for (User user : listUser) {
                                    if (user.getIdUser() == copy.getIdUser()) {
                                        name = user.getName() + " " + user.getMiddleName() + " " + user.getLastName();
                                    }
                                }
                            }
                        %>
                        <td><%out.print(name);%></td>
                    </tr>
                    <% }
                    %>
                </table>
            </div>
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
                <input type="submit" value="Поиск"></input>
            </div>
            <br>
            <br>
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
                    </tr>
                    <% jdbcPublisher tmpPublisher = new jdbcPublisher();
                        ArrayList<Publisher> listPublisher = tmpPublisher.get();
                        for (Publisher publisher : listPublisher) {
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
        </div>
    </ul>
</div>
</div>

<div id='footer' class="footer" style="height: 65px;">
    </br>
    <a href="google.com"><b>Сайт разработчиков</b></a><b> - </b>
    <a href="google.com"><b>Вопросы и предложения</b></a><b> - </b>
    <a href="google.com"><b>Партнёры</b></a></li><b> - </b>
    <a href="google.com"><b>Справка</b></a></li><b> - </b>
</div>
</body>
</html>
