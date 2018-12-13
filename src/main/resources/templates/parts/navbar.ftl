<#assign known = Session.SPRING_SECURITY_CONTEXT??>
<#if known>
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    name = user.getUsername()>
</#if>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Spring</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">Main</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/home">Home</a>
            </li>
            <#if !name??>
                <li class="nav-item">
                    <a class="nav-link" href="/login">Login</a>
                </li>
            </#if>
            <#if !name??>
                <li class="nav-item">
                    <a class="nav-link" href="/registration">Registration</a>
                </li>
            </#if>
        </ul>
        <#if name??>
            ${name},
            <a href="/logout"> Logout</a>
        </#if>
    </div>
</nav>