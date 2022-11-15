<#import "macro/header.ftlh" as header/>
<#import "macro/footer.ftlh" as footer/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="../../CheckProject.css">
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
            <p id="hPersonalPage" class="h">Проверка заявок на создание проекта</p>
            <div class="switch">
                <a href="/admin/checkProject/0?status=WAIT" <#if statusFind=="WAIT">class="active"</#if>><span >Проверить</span></a>
                <a href="/admin/checkProject/0?status=VERIFY" <#if statusFind=="VERIFY">class="active"</#if>><span >Проверено</span></a>
                <a href="/admin/checkProject/0?status=CHECK" <#if statusFind=="CHECK">class="active"</#if>><span >Ждёт изменений от пользователя</span></a>
            </div>
            <div class="formContainer">
                <p class="description">
                    Проверьте проекты и измените их статус
                </p>
                <div>
                    <div class="results">
                        <#list projects as project>
                            <div class="declaration">
                                <div class="blockDeclaration">
                                    <div class="panel">
                                        <a class="statusDeclaration">Статус:
                                            <span class="${project.status}">
                                                <#if project.status=="WAIT">На проверке</#if>
                                                <#if project.status=="CHECK">Ошибка в заполнение</#if>
                                                <#if project.status=="VERIFY">Проверено</#if>
                                            </span>
                                        </a>
                                    </div>
                                    <div class="changeStatusPanel panel">
                                        <form action="/admin/changeStatus/${project.id}" method="post">
                                            <p class="changeStatusBlock"><label for="status">Выберите статус</label>
                                                <input type="radio" value="WAIT" name="status" <#if project.status="WAIT">checked</#if>>Ожидание
                                                <input type="radio" value="CHECK" name="status" <#if project.status="CHECK">checked</#if>>Отправить на доработку
                                                <input type="radio" value="VERIFY" name="status" <#if project.status="VERIFY">checked</#if>>Проверено
                                                <input type="submit" value="Сохранить">
                                            </p>
                                        </form>
                                    </div>
                                    <p class="h">${project.nameProject}</p>
                                    <div class="description">
                                        <p>${project.description}</p>
                                    </div>
                                    <hr>
                                    <div class="contacts">
                                        <p>Инн: ${project.inn}</p>
                                    </div>
                                    <div class="tags">
                                        <p>#${project.mainDeclaration.nameDeclaration} #${project.dopDeclaration.nameDeclaration}</p>
                                    </div>
                                    <div class="views">
                                        <span>Предложений: 11</span>
                                    </div>
                                </div>
                            </div>
                        </#list>
                        <#if totalPage!=0>
                            <div class="pagination">
                                <div class="blockPagination">
                                    <a <#if activePage!=0>href="/admin/checkProject/${activePage-1}?status=${statusFind}"</#if>>
                                        <div class="prev <#if activePage==0>noActive</#if>">
                                            &#60;
                                        </div>
                                    </a>
                                    <div class="numeration">
                                        <#list 1..totalPage as page>
                                            <a href="/admin/checkProject/${page-1}?status=${statusFind}">
                                                <div class="num <#if page==activePage+1>active</#if>">${page}</div>
                                            </a>
                                        </#list>
                                    </div>
                                    <a <#if activePage+1!=totalPage>href="/admin/checkProject/${activePage+1}?status=${statusFind}"</#if>>
                                        <div class="next <#if activePage+1==totalPage>noActive</#if>">
                                            &#62;
                                        </div>
                                    </a>
                                </div>
                            </div>
                        <#else>
                            <p class="noResults">Результатов не найдено</p>
                        </#if>
                </div>
            </div>
        </div>
    </div>
    </div>
    <@footer.footer/>
</div>

</body>
</html>