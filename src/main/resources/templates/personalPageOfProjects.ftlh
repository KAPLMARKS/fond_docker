<#import "macro/header.ftlh" as header/>
<#import "macro/footer.ftlh" as footer/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="CheckProject.css">
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
                    <p id="hPersonalPage" class="h">Мои проекты</p>
                    <div class="formContainer">
                        <p class="description">
                            Здесь вы можете редактировать свои проекты и видеть их статус.
                        </p>
                        <div>
                            <div class="results">
                                <#list projects as project>
                                    <div class="declaration">
                                        <div class="blockDeclaration">
                                            <div class="panel">
                                                <a class="statusDeclaration">Статус:
                                                    <span class="${project.status}">
                                                        <#if project.status="WAIT">На проверке</#if>
                                                        <#if project.status="CHECK">Отправлен на доработку</#if>
                                                        <#if project.status="VERIFY">Одобрен</#if>
                                                    </span>
                                                </a>
                                                <div class="editDelBlock">
                                                    <a href="/editProject/${project.id}">Редактировать</a>
                                                    <form action="/deleteProject/${project.id}" method="post" onsubmit="if(!confirm('Вы уверены что хотите удалить проект?')){  return false}">
                                                        <input type="submit" value="Удалить">
                                                    </form>
                                                </div>
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
                                            <div class="response"><a href="/cvsOfProject/${project.id}">Посмотреть заявки</a></div>
                                            <div class="views">
                                                <span>Предложений: ${project.cvs?size}</span>
                                            </div>
                                        </div>
                                    </div>
                                </#list>
                            <#if totalPage!=0>
                                <div class="pagination">
                                    <div class="blockPagination">
                                        <a <#if activePage!=0>href="/myProjects?page=${activePage-1}"</#if>>
                                            <div class="prev <#if activePage==0>noActive</#if>">
                                                &#60;
                                            </div>
                                        </a>
                                        <div class="numeration">
                                            <#list 1..totalPage as page>
                                                <a href="/myProjects?page=${page-1}">
                                                    <div class="num <#if page==activePage+1>active</#if>">${page}</div>
                                                </a>
                                            </#list>
                                        </div>
                                        <a <#if activePage+1!=totalPage>href="/myProjects?page=${activePage+1}"</#if>>
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