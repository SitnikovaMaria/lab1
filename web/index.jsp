<!DOCTYPE html>

<html>

<head>
	<link rel="stylesheet" href="action.css">
	<title>Библиотека</title>


<style>
	p1 {
		font: bold 110% serif;
	}
	a { 
		text-decoration: none;
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
		transition: all 2s;
	}
	.headerLeft a:hover {			
				color: #FFFFFF;
		transition: all 1s;		
	}
	.headerLeft ul {
    		list-style: none; /* Для списка убираем маркеры */
    		margin: 0; /* Нет отступов вокруг */
    		padding: 0; /* Убираем поля вокруг текста */
    		font-family: Arial, sans-serif; /* Рубленый шрифт для текста меню */
    		font-size: 12pt; /* Размер названий в пункте меню */
   	}
	.headerLeft a:hover ul{
		
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
   	}
   	.headerLeft li a:hover {
		outline: none;
    	color: #FFFFFF;		/* Цвет текста активного пункта */
    	}
   	.headerLeft li:hover ul { 
		border-color: black;
    		display: block;		/* При выделении пункта курсором мыши отображается подменю */
   	}
	.center {
                
		margin: auto; /* Выравниваем по центру */
		
	}
	.enterform {
		background-color: white;
		width: 350px;
		height: 180px;
		margin: auto;
		border-style: solid;
		bordercolor: black;
	}
	.backg{		
		font-family: Helvetica, Sans-serif;	
		background-color: rgba(255, 255, 255, 0.4);
		background-image: url("back.jpg");
		background-position: center; 
		background-size: 250% 300%;
		position:absolute;
		height: 100%;
		width: 100%;
	}
</style>
</head>
<body>
<div class="backg">
	<div id='header'>

		<div id='headerLeft' class="headerLeft">
			<ul>
				<li>
					<img src="menu.png" height="50">	
					<ul>
						<li><a href="start.jsp">Просмотр книг</a></li>
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


	<div id='content' class="content" style="margin-top: 10%;">
        <div id='name' class='center' style="margin-top: 50px;" >
			<div class='enterform'>
			<h2>Войти</h2>
                        <form method ="POST" action="CheckUser">                      
			<div id="act">
				<p1>Логин </p1><input type="text" name="login">
			</div>
			<div id="act">
				<p1>Пароль </p1><input type="password" name="pass">
			</div>
			<div id="act">
                            <img src="login.png" height="15"> <input type="submit" value="Вход">
			</div>
                        </form>
			</div>
			</br>
						
		</div>
	</div>


    <div id='footer' class="footer" style="height: 65px;">

		</br>
		<a href="google.com"><b>Сайт разработчиков</b></a><b> - </b>
		<a href="google.com"><b>Вопросы и предложения</b></a><b> - </b>
		<a href="google.com"><b>Партнёры</b></a></li><b> - </b>
		<a href="google.com"><b>Справка</b></a></li><b> - </b> 
	</div>
</div>
</html>
