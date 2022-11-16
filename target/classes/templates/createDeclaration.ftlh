<#import "macro/footer.ftlh" as footer/>
<#import "macro/header.ftlh" as header/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="/../profile.css">
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
        <div class="formsChoose">
            <p id="hPersonalPage" class="h">Личный кабинет работодателя</p>
            <p class="description">
                Создайте направления и поднаправления
            </p>
            <div class="formContainer declarationController">
                <form action="/admin/createMainDeclaration" method="post">
                    <p class="nameForm"><span>Создать направление</span></p>
                    <p><label for="mainDeclaration">Введите направление</label></p>
                    <p><input name="mainDeclaration" type="text"></p>
                    <p><input type="submit" value="Создать новое направление"></p>
                </form>
                <#if mainDeclarations?size!=0>
                    <form action="/admin/updateMainDeclaration" method="post">
                        <p class="nameForm"><span>Редактировать направление</span></p>
                        <p><label for="mainDeclaration">Выберите направление</label></p>
                        <p><select name="mainDeclaration" id="updateMainDeclaration">
                                <#list mainDeclarations as mainDeclaration>
                                    <option value="${mainDeclaration.id}">${mainDeclaration.nameDeclaration}</option>
                                </#list>
                            </select></p>
                        <p><label for="nameDeclaration">Введите новое название</label></p>
                        <p><input type="text" name="nameDeclaration" id="newMainDeclaration"></p>
                        <p><input type="submit" value="Изменить направление"></p>
                    </form>
                    <form action="/admin/deleteMainDeclaration" method="post">
                        <p class="nameForm"><span>Удалить направление</span></p>
                        <p><label for="mainDeclaration">Выберите направление</label></p>
                        <p><select name="mainDeclaration" id="">
                                <#list mainDeclarations as mainDeclaration>
                                    <option value="${mainDeclaration.id}">${mainDeclaration.nameDeclaration}</option>
                                </#list>
                            </select></p>
                        <p><input type="submit" value="Удалить направление"></p>
                    </form>
                    <form action="/admin/createDopDeclaration" method="post">
                        <p class="nameForm"><span>Создать поднаправление</span></p>
                        <p><label for="mainDeclaration">Выберите направление</label></p>
                        <p><select name="mainDeclaration" id="">
                                <#list mainDeclarations as mainDeclaration>
                                    <option value="${mainDeclaration.id}">${mainDeclaration.nameDeclaration}</option>
                                </#list>
                            </select></p>
                        <p><label for="dopDeclaration">Введите поднаправление</label></p>
                        <p><input name="dopDeclaration" type="text"></p>
                        <p><input type="submit" value="Создать новое поднаправление"></p>
                    </form>
                    <form action="/admin/updateDopDopDeclaration" method="post">
                        <p class="nameForm"><span>Изменить поднаправление</span></p>
                        <p><label for="mainDeclaration">Выберите направление</label></p>
                        <p><select name="mainDeclaration" id="mainDirection" class="mainDeclaration">
                                <option selected disabled>Выберите категорию</option>
                                <#list mainDeclarations as mainDeclaration>
                                    <option value="${mainDeclaration.id}">${mainDeclaration.nameDeclaration}</option>
                                </#list>
                            </select></p>
                        <p><label for="dopDeclaration">Выберите поднаправление</label></p>
                        <p><select name="dopDeclaration" id="updateDopDeclaration" class="dopDeclaration">
                            </select></p>
                        <p><label for="nameDeclaration">Введите новое название</label></p>
                        <p><input type="text" name="nameDeclaration" id="newDopDeclaration"></p>
                        <p><input type="submit" value="Изменить поднаправление"></p>
                    </form>
                    <form action="/admin/deleteDopDeclaration" method="post">
                        <p class="nameForm"><span>Удалить поднаправление</span></p>
                        <p><label for="mainDeclaration">Выберите направление</label></p>
                        <p><select name="mainDeclaration" id="mainDirection" class="mainDeclaration">
                                <option selected disabled>Выберите категорию</option>
                                <#list mainDeclarations as mainDeclaration>
                                    <option value="${mainDeclaration.id}">${mainDeclaration.nameDeclaration}</option>
                                </#list>
                            </select></p>
                        <p><label for="dopDeclaration">Выберите поднаправление</label></p>
                        <p><select name="dopDeclaration" id="dopDirection" class="dopDeclaration">
                            </select></p>
                        <p><input type="submit" value="Удалить поднаправление"></p>
                    </form>
                </#if>
            </div>
        </div>
    </div>
    <script src="/../getDeclarationAdmin.js"></script>
    <@footer.footer/>
</div>
</body>
</html>