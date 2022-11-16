<#import "macro/header.ftlh" as header/>
<#import "macro/footer.ftlh" as footer/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="home.css">
    <!-- <link rel="stylesheet" href="test.css"> -->
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
        <div class="main-info">
            <div class="header nameBlockInfo">
                <p>Фонд научно-образовательной интеграции</p>
                <p >
                    <span style="background: #0B0C10;color: white;">Описание... Наш фонд...</span>
                </p>
            </div>
            <div class="contentBlock">
                <div class="content">
<#--                    <div class="search">-->
<#--                        <input type="text">-->
<#--                        <button>🔍︎</button>-->
<#--                    </div>-->
                    <div class="blockFiltersAndResults">
                        <div class="filters">
                            <p class="h">Фильтры</p>
                            <form action="/" class="form-inline" method="get">
                            <select name="mainDeclaration" class="filterSearch mainDeclaration">
                                <option value="-1" selected disabled>Выберите направление</option>
                                <#list mainDeclarations as mainDeclaration>
                                    <option value="${mainDeclaration.id}" <#if mainDeclarationSel?? && mainDeclarationSel.id=mainDeclaration.id>selected</#if>>${mainDeclaration.nameDeclaration}</option>
                                </#list>
                            </select>
                            <select name="dopDeclaration" class="filterSearch dopDeclaration">
                                <#if mainDeclarationSel??>
                                    <option value="0" disabled selected>Не выбрано</option>
                                    <#list mainDeclarationSel.dopDeclarationSet as dopDeclaration>
                                        <option value="${dopDeclaration.id}" <#if dopDeclarationSel?? && dopDeclarationSel.id=dopDeclaration.id>selected</#if>>${dopDeclaration.nameDeclaration}</option>
                                    </#list>
                                <#else>
                                    <option value="-1" selected disabled>Не выбрано</option>
                                </#if>
                            </select>
                            <div class="filterSearch search">
                                <button>🔍︎</button>
                            </div>
                            </form>
                        </div>
                        <div class="blockResults">
                            <div class="results">
                                <#list projects as project>
                                    <div class="declaration">
                                        <div class="blockDeclaration">
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
                                            <div class="response"><a href="/cv/${project.id}">Откликнуться</a></div>
                                            <div class="views">
                                                <span>Предложений: ${project.cvs?size}</span>
                                            </div>
                                        </div>
                                    </div>
                                </#list>
                            </div>
                            <#if totalPage!=0>
                                <div class="pagination">
                                    <div class="blockPagination">
                                        <a <#if activePage!=0>href="/?page=${activePage-1}<#if mainDeclarationSel??>&mainDeclaration=${mainDeclarationSel.id}</#if><#if dopDeclarationSel??>&dopDeclaration=${dopDeclarationSel.id}</#if>"</#if>>
                                            <div class="prev <#if activePage==0>noActive</#if>">
                                                &#60;
                                            </div>
                                        </a>
                                        <div class="numeration">
                                            <#list 1..totalPage as page>
                                                <a href="/?page=${page-1}<#if mainDeclarationSel??>&mainDeclaration=${mainDeclarationSel.id}</#if><#if dopDeclarationSel??>&dopDeclaration=${dopDeclarationSel.id}</#if>">
                                                    <div class="num <#if page==activePage+1>active</#if>">${page}</div>
                                                </a>
                                            </#list>
                                        </div>
                                        <a <#if activePage+1!=totalPage>href="/?page=${activePage+1}<#if mainDeclarationSel??>&mainDeclaration=${mainDeclarationSel.id}</#if><#if dopDeclarationSel??>&dopDeclaration=${dopDeclarationSel.id}</#if>"</#if>>
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
    </div>
    <@footer.footer/>
</div>
<script src="/../getDeclarationRoot.js"></script>
</body>
</html>