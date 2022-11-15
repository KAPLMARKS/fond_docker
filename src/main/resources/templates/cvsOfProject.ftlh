<#import "macro/header.ftlh" as header/>
<#import "macro/footer.ftlh" as footer/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="/../CheckProject.css">
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
            <p id="hPersonalPage" class="h">Заявки на проект</p>
            <div class="formContainer">
                <p class="description">
                    Здесь вы можете посмотреть заявки на свой проект.
                </p>
                <div>
                    <div class="results">
                        <#list cvs as cv>
                            <div class="declaration">
                                <div class="blockDeclaration">
                                    <p class="h">ФИО: ${cv.name}</p>
                                    <div class="description">
                                        <p>Обо мне: ${cv.aboutMe}</p>
                                    </div>
                                    <hr>
                                    <div class="contacts">
                                        <p>Телефон: ${cv.phone}</p>
                                        <p>e-mail: ${cv.email}</p>
                                        <p>инн: ${cv.inn}</p>
                                    </div>
                                </div>
                            </div>
                        </#list>
                        <#if totalPage!=0>
                            <div class="pagination">
                                <div class="blockPagination">
                                    <a <#if activePage!=0>href="/cvsOfProject/${projectId}?page=${activePage-1}"</#if>>
                                        <div class="prev <#if activePage==0>noActive</#if>">
                                            &#60;
                                        </div>
                                    </a>
                                    <div class="numeration">
                                        <#list 1..totalPage as page>
                                            <a href="/cvsOfProject/${projectId}?page=${page-1}">
                                                <div class="num <#if page==activePage+1>active</#if>">${page}</div>
                                            </a>
                                        </#list>
                                    </div>
                                    <a <#if activePage+1!=totalPage>href="/cvsOfProject/${projectId}?page=${activePage+1}"</#if>>
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