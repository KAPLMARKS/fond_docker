<#import "macro/footer.ftlh" as footer/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="profile.css">
</head>
<body>
<div class="wrapper">
    <header>
        <nav class="top-menu">
            <ul class="main-logo">
                <li>
                    <a href="" class="logo">Фонд НОИ</a>
                </li>
            </ul>
            <ul class="menu-main">
                <li><a href="" class="current">Главная</a></li>
                <li><a href="">Создать проект</a></li>
                <li><a href="">Войти</a></li>
                <li><a href="">Зарегистрироваться</a></li>
            </ul>
        </nav>
    </header>
    <div class="container">
        <div class="formsChoose">
            <div id='chooseRole' class="switch">
                <span data-type="employer" class="active">Работодатель</span>
                <span data-type="scientist">Учёный</span>
            </div>
            <p id="hPersonalPage" class="h">Личный кабинет работодателя</p>
            <p class="description">
                Заполните данные для продолжения
            </p>
            <div class="formContainer">
                <form action="">
                    <p><label for="name">Имя</label></p>
                    <p><input name="name" type="text"></p>
                    <p class="sumbitP"><input type="submit" value="Сохранить"></p>
                </form>
            </div>
            <div style="display: none;" class="formContainer">
                <form action="">
                    <p><label for="name">Тест</label></p>
                    <p><input name="name" type="text"></p>
                    <p class="sumbitP"><input type="submit" value="Сохранить"></p>
                </form>
            </div>
        </div>
    </div>
    <#list cvs as cv>
      <p>${cv.name}</p>
    </#list>
    <@footer.footer/>
</div>
<script src="profile.js" ></script>
</body>
</html>