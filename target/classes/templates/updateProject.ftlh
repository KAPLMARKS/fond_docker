<#import "macro/header.ftlh" as header/>
<#import "macro/footer.ftlh" as footer/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="/../form.css">
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
            <form action="/updateProject/${project.id}" method="post">
                <table class="tableChooseDirection">
                    <tr>
                        <th colspan="2">
                            <div class="tdContainer">
                                Информация для пользователей
                            </div>
                        </th>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <div class="tdContainer">
                                текст (информация)
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2">
                            <div class="tdContainer">
                                Тематика проекта
                            </div>
                        </th>
                    </tr>
                    <tr>
                        <td>
                            <div class="tdContainer">
                                Название проекта
                            </div>
                        </td>
                        <td>
                            <div class="tdContainer">
                                <input name="nameProject" type="text" value="${project.nameProject}" required>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="tdContainer">
                                Ваш инн или огранизации
                            </div>
                        </td>
                        <td>
                            <div class="tdContainer">
                                <input name="inn" type="text" minlength="10" maxlength="12" value="${project.inn}" required>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="tdContainer">
                                Описание
                            </div>
                        </td>
                        <td>
                            <div class="tdContainer">
                                <textarea style="height: 500px" name="description" required>${project.description}</textarea>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="tdContainer">
                                Основные направления
                            </div>
                        </td>
                        <td>
                            <div class="tdContainer">
                                <select name="mainDeclaration" id="mainDirection" class="mainDeclaration" required>
                                    <option value="-1" selected disabled>Выберите направление</option>
                                    <#list mainDeclarations as mainDeclaration>
                                        <option value="${mainDeclaration.id}" <#if mainDeclaration.id=project.mainDeclaration.id>selected</#if>>${mainDeclaration.nameDeclaration}</option>
                                    </#list>
                                </select>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="tdContainer">
                                Поднаправления
                            </div>
                        </td>
                        <td>
                            <div class="tdContainer">
                                <select name="dopDeclaration" id="dopDirection" class="dopDeclaration" required>
                                    <#list project.mainDeclaration.dopDeclarationSet as dopDeclaration>
                                        <option value="${dopDeclaration.id}" <#if dopDeclaration.id=project.dopDeclaration.id>selected</#if>>${dopDeclaration.nameDeclaration}</option>
                                    </#list>
                                </select>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="tdContainer">
                                Ключевые слова
                            </div>
                        </td>
                        <td>
                            <div class="tdContainer">
                                <input name="keywords" type="text" value="${project.keywords}">
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" value="Обновить проект"></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <@footer.footer/>
</div>
<script src="/../getDeclarationEditProject.js"></script>
</body>
</html>