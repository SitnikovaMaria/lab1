﻿<!DOCTYPE html>

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
		<div id="name">
			<h2>Каталоги</h2>
		</div>
		<br>
		<div class="forminoneline">
				<form method="post" action="ImportCatalogsServlet">
                    <img src="import.jpg" height="15"> <input type="submit" value="Загрузить каталоги">
                </form>
			</div>
			<div class="forminoneline">
			    <form method="post" action="ExportCatalogsServlet">
                    <img src="export.jpg" height="15"> <input type="submit" value="Сохранить каталоги">
                </form>
			</div>			
		<div id="multi-derevo">
			<ul>
				<li><span><a href="#0">
				Общий</a></span>
					<ul>
						<li><span><a href="#01">
						Зарубежное</a></span>
    							<ul>
     								<li><span>
								<a href="#011">Романы</a>
								</span></li>
								<li><span>
								<a href="#012">Поэзия</a>
								</span></li>
								<li><span>
								<a href="#013">Поэмы</a>
								</span></li>
							</ul>
   						</li>
						<li><span><a href="#02">
						Отечественное</a></span>
    							<ul>
     								<li><span>
								<a href="#021">Романы</a>
								</span></li>
								<li><span>
								<a href="#022">Поэзия</a>
								</span></li>
								<li><span>
								<a href="#023">Поэмы</a>
								</span></li>
							</ul>
   						</li>
   					</ul>
  				</li>
 			</ul>
		</div>
	</div>
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
