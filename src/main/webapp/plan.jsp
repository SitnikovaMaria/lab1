<%@page import="java.util.Map" %>
<%@ page language="java" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="model.Rack" %>
<%@ page import="model.Room" %>
<%@ page import="jdbc.jdbcRack" %>
<%@ page import="jdbc.jdbcRoom" %>
<%@ page import="utils.TestSVGGen" %>
<%@ page import="java.io.Writer" %>
<%@ page import="java.io.OutputStreamWriter" %>
<%@ page import="org.w3c.dom.DOMImplementation" %>
<%@ page import="org.apache.batik.dom.GenericDOMImplementation" %>
<%@ page import="org.w3c.dom.Document" %>
<%@ page import="org.apache.batik.svggen.SVGGraphics2D" %>
<%@ page import="java.util.HashMap" %>

<!DOCTYPE html>

<html>

<head>
    <link rel="stylesheet" href="start.css">
    <link rel="stylesheet" href="action.css">
    <link rel="stylesheet" href="style-modal.css" rel="stylesheet">
    <title>План библиотеки</title>
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

    .footer a {
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

    .forminoneline {
        display: inline-block;
    }

    .content {
        padding: 1%;
    }
</style>

<body>
<div id='header'>
    <div id='headerLeft' class="headerLeft">
        <ul>
            <li>
                <img src="menu.png" height="50">
                <ul>
                    <li><a href="admin.jsp">Книги</a></li>
                    <li><a href="catalogsA.html">Каталоги</a></li>
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

        <% jdbcRack tmpRack = new jdbcRack();
        HashMap<Integer, Rack> listRack = tmpRack.get();
        jdbcRoom tmpRoom = new jdbcRoom();
        HashMap<Integer, Room> listRoom = tmpRoom.get();
    %>
    <div id="name">
        <h2>План библиотеки</h2>
    </div>

    <div>

        <div>
            <%
                TestSVGGen svgG = new TestSVGGen();
                out.print(svgG.gen());
            %>
        </div>

        <div id="name">
            <h2>Стеллажи</h2>
        </div>
        <table cols="6" width="100%" id="tab">
            <tr>
                <td>
                    <p1>№</p1>
                </td>
                <td>
                    <p1>Комната</p1>
                </td>
                <td>
                    <p1>Высота</p1>
                </td>
                <td>
                    <p1>Ширина</p1>
                </td>
                <td>
                    <p1>Расположение X</p1>
                </td>
                <td>
                    <p1>Расположение Y</p1>
                </td>
            </tr>
            <% for (HashMap.Entry<Integer, Rack> lRack : listRack.entrySet()) {
                Rack rack = lRack.getValue();
            %>
            <tr>
                <td><%out.print(lRack.getKey());%></td>
                <td><%out.print(rack.getIdRoom());%></td>
                <td><%out.print(rack.getHeight());%></td>
                <td><%out.print(rack.getWidth());%></td>
                <td><%out.print(rack.getPositionX());%></td>
                <td><%out.print(rack.getPositionY());%></td>
            </tr>
            <% } %>
        </table>

        <div id="name">
            <h2>Комнаты</h2>
        </div>
        <table cols="6" width="100%" id="tab">
            <tr>
                <td>
                    <p1>№</p1>
                </td>
                <td>
                    <p1>Комната</p1>
                </td>
                <td>
                    <p1>Этаж</p1>
                </td>
            </tr>
            <% for (HashMap.Entry<Integer, Room> lRoom : listRoom.entrySet()) {
                Room room = lRoom.getValue();
            %>
            <tr>
                <td><%out.print(lRoom.getKey());%></td>
                <td><%out.print(room.getRoomNumber());%></td>
                <td><%out.print(room.getLevel());%></td>
            </tr>
            <% } %>
        </table>
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
