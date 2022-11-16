<#macro header user>
    <header>
        <nav class="top-menu">
            <ul class="main-logo">
                <li>
                    <a href="/" class="logo">Фонд НОИ</a>
                </li>
            </ul>
            <ul class="menu-main">
                <li><a href="/">Главная</a></li>
                <li><a href="/createProject">Создать проект</a></li>
                <li><a href="/myProjects">Мои проекты</a></li>
                <#if user="ADMIN">
                    <li><a href="/admin/checkProject/0">Проверить проекты</a></li>
                    <li><a href="/admin/createDeclaration">Создать направления</a></li>
                </#if>
                <li><a href="/logout">Выйти</a></li>
            </ul>
        </nav>
    </header>
</#macro>
<#macro headerNotAuth>
    <header>
        <nav class="top-menu">
            <ul class="main-logo">
                <li>
                    <a href="/" class="logo">Фонд НОИ</a>
                </li>
            </ul>
            <ul class="menu-main">
                <li><a href="/">Главная</a></li>
                <li><a href="/createProject">Создать проект</a></li>
                <li><a href="/myProjects">Мои проекты</a></li>
                <li><a href="/signIn">Войти</a></li>
                <li><a href="/signUp">Зарегистрироваться</a></li>
            </ul>
        </nav>
    </header>
</#macro>