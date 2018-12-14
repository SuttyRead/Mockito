<#import "parts/common.ftl" as c>

<@c.page>
    <#if error??>
        <div class="alert alert-danger" role="alert">Login or password incorrect</div>
    </#if>
    <#if signUpSuccess??>
        <div class="alert alert-success" role="alert">Registration success</div>
    </#if>
    <div>
        <form action="/login" method="post">
            <div class="form-group">
                <label for="login">Login</label>
                <input type="text" class="form-control" id="login" aria-describedby="emailHelp"
                       placeholder="Enter login" name="login" required="required"/>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" placeholder="Enter password"
                       name="password" required="required"/>
            </div>

            <label for="remember-me">
                <input type="checkbox" id="remember-me" name="remember-me"/>Remember me</label>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
</@c.page>