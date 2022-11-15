<#import "macro/header.ftlh" as header/>
<#import "macro/footer.ftlh" as footer/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../../form.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
</head>
<body>
<div class="wrapper">
    <#if user??>
        <@header.header user="${user.role}"/>
    <#else>
        <@header.headerNotAuth/>
    </#if>
    <div class="container">
        <div class="formContainer">
            <form action="/cv/${project.id}" method="post">
                <table class="tableChooseDirection">
                    <tr>
                        <th colspan="2">
                            <div class="tdContainer">
                               Заполните форму для рассмотрения в проект
                            </div>
                        </th>
                    </tr>
                    <tr>
                        <td>
                            <div class="tdContainer">
                                Имя
                            </div>
                        </td>
                        <td>
                            <div class="tdContainer">
                                <input name="name" type="text" required>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="tdContainer">
                                Почта
                            </div>
                        </td>
                        <td>
                            <div class="tdContainer">
                                <input name="email" type="email" required>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="tdContainer">
                                Телефон
                            </div>
                        </td>
                        <td>
                            <div class="tdContainer">
                                <input name="phone" type="text" required>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="tdContainer">
                                Инн
                            </div>
                        </td>
                        <td>
                            <div class="tdContainer">
                                <input name="inn" type="text" minlength="10" maxlength="12" required>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="tdContainer">
                                О себе
                            </div>
                        </td>
                        <td>
                            <div class="tdContainer">
                                <textarea style="height: 500px" name="aboutMe" minlength="50" maxlength="500"></textarea>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" value="Создать"></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <@footer.footer/>
</div>
<script src="getDeclaration.js"></script>
</body>
</html>