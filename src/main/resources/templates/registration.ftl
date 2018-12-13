<#import "parts/common.ftl" as c>
<#setting date_format="yyyy-MM-dd">

<@c.page>
<script src="https://www.google.com/recaptcha/api.js"></script>
<form method="post" class="form-horizontal" action="/registration">
    <div class="form-group">
        <label class="control-label col-sm-3" for="login">Login:</label>
        <div class="col-sm-6">
            <input type="text"
                   class="form-control"
                   id="login"
                   placeholder="Enter login"
                   name="login"
                   pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$"
                   title="Uppercase and lowercase letter. Must be 2-20 characters. Without specifically characters #,$,% and so on. For example SuttyRead"
                   aria-describedby="loginHelpInline"
                   <#if newUser??>value="${newUser.login}"</#if>
                   required>
            <small id="loginHelpInline" class="text-muted">
                Must be 2-20 characters long.
            </small>
        </div>
        <div class="col-sm-offset-3 col-sm-6 err-message">
            <#if loginNotPattern??>
                <div class="alert alert-danger" role="alert">
                    This login doesn't match pattern ^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$.
                    With a limit of 2-20 characters, which can be letters
                    and numbers, the first character is necessarily the letter.
                    For example SuttyRead
                </div>
            </#if>
            <#if loginAlreadyExist??>
                <div class="alert alert-danger" role="alert">
                    This login already exists!
                </div>
            </#if>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-3"
               for="password">Password:</label>
        <div class="col-sm-6">
            <input type="password" class="form-control ${(passwordError??)?string('is-invalid', '')}" id="password"
                   placeholder="Enter password" name="password"
                   aria-describedby="passwordHelpInline"
                   pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$"
                   title="Password must be have lowercase and uppercase Latin letters, number. Minimum 8 characters"
                   <#if newUser??>value="${newUser.password}"</#if>
                   required>
            <small id="passwordHelpInline" class="text-muted">
                Lowercase and uppercase Latin letters, numbers, special characters. Minimum 8 characters
            </small>
        </div>
        <div class="col-sm-offset-3 col-sm-6 err-message">
            <#if passwordError??>
                <div class="invalid-feedback">
                ${passwordError}
                </div>
            </#if>
            <#if passwordNotEquals??>
                <div class="alert alert-danger" role="alert">
                    Password don't match!
                </div>
            </#if>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-3"
               for="confirmPassword">Confirm Password:</label>
        <div class="col-sm-6">
            <input type="password" class="form-control ${(confirmPasswordError??)?string('is-invalid', '')}"
                   id="confirmPassword"
                   placeholder="Confirm password" name="confirmPassword"
                   pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$"
                   title="Password must be have lowercase and uppercase Latin letters, number. Minimum 8 characters"
                   <#if newUser??>value="${newUser.password}"</#if>
                   required>
        </div>
        <#--<div class="col-sm-offset-3 col-sm-6 err-message">-->
        <#--<#if confirmPasswordError??>-->
        <#--<div class="invalid-feedback">-->
        <#--${confirmPasswordError}-->
        <#--</div>-->
        <#--</#if>-->
        <#--</div>-->
    </div>

    <div class="form-group">
        <label class="control-label col-sm-3"
               for="email">Email:</label>
        <div class="col-sm-6">
            <input type="text" class="form-control" id="email"
                   placeholder="Enter email" name="email" aria-describedby="emailHelpInline"
                   pattern="\w+([\.-]?\w+)*@\w+([\.-]?\w+)*\.\w{2,4}"
                   title="Enter correct email. Email must be have @. For example SuttyRead@gmail.com"
                   <#if newUser??>value="${newUser.email}"</#if>
                   required>
        </div>
        <div class="col-sm-offset-3 col-sm-6 err-message">
            <#if emailNotPattern??>
                <div class="alert alert-danger" role="alert">
                    This email doesn't match pattern
                    \w+([\.-]?\w+)*@\w+([\.-]?\w+)*\.\w{2,4}
                    For example SuttyRead@gmail.com
                </div>
            </#if>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-3"
               for="First Name">First Name:</label>
        <div class="col-sm-6">
            <input type="text" class="form-control" id="First Name"
                   placeholder="Enter first name" name="firstName" aria-describedby=firstNameHelpInline"
                   pattern="^[A-Z]{1}[a-z]{1,25}"
                   title=" Only latin letter. First letter must be uppercase. For example Sutty"
                   <#if newUser??>value="${newUser.firstName}"</#if>
                   required>
        </div>
        <div class="col-sm-offset-3 col-sm-6 err-message">
            <#if firstNameNotPattern??>
                <div class="alert alert-danger" role="alert">
                    This first name doesn't match pattern
                    ^[A-Z]{1}[a-z]{1,25}
                    For example Sutty
                </div>
            </#if>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3"
               for="Last Name">Last Name:</label>
        <div class="col-sm-6">
            <input type="text" class="form-control" id="Last Name"
                   placeholder="Enter last name" name="lastName" aria-describedby=lastNameHelpInline"
                   title=" Only latin letter. First letter must be uppercase. For example Read"
                   <#if newUser??>value="${newUser.lastName}"</#if>
                   pattern="^[A-Z]{1}[a-z]{1,25}" required>
        </div>
        <div class="col-sm-offset-3 col-sm-6 err-message">
            <#if lastNameNotPattern??>
                <div class="alert alert-danger" role="alert">
                    This first name doesn't match pattern
                    ^[A-Z]{1}[a-z]{1,25}
                    For example Read
                </div>
            </#if>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3"
               for="Birthday">Birthday:</label>
        <div class="col-sm-6">
            <input type="date" class="form-control" id="Birthday"
                   placeholder="Enter birthday" name="birthday"
                   <#if newUser??>value="${newUser.birthday}"</#if>
                   required>
        </div>
        <div class="col-sm-offset-3 col-sm-6 err-message">
            <#if incorrectDate??>
                <div class="alert alert-danger" role="alert">
                    Incorrect birthday
                    For example 1982-7-27
                </div>
            </#if>
            <#if birthdayNotPattern??>
                <div class="alert alert-danger" role="alert">
                    BirthdayNotPattern
                    For example 1982-7-27
                </div>
            </#if>
        </div>

    </div>
    <div class="col-sm-6">
        <div class="g-recaptcha"
             data-sitekey="6LcZDYAUAAAAADb8KgCHNMpBC-rklAFI36qbweTb">
        </div>
        <#if captchaError??>
            <div class="alert alert-danger" role="alert">
            ${captchaError}
            </div>
        </#if>
    </div>

    <div class="form-group">
        <div class="col-sm-1 col-sm-offset-4">
            <button type="submit" class="btn btn-success">OK</button>
        </div>
        <div class="col-sm-1">
            <a href="/home"
               class="btn btn-primary"
               role="button"
               aria-pressed="true">Cancel</a>
        </div>
    </div>

</form>

<script type="text/javascript">

    window.onload = function () {
        document.getElementById("password").onchange = validatePassword;
        document.getElementById("confirmPassword").onchange = validatePassword;
    };

    function validatePassword() {
        var password = document.getElementById("password").value;
        var confirmPassword = document.getElementById("confirmPassword").value;
        if (confirmPassword !== password)
            document.getElementById("confirmPassword").setCustomValidity("Passwords Don't Match");
        else
            document.getElementById("confirmPassword").setCustomValidity('');
    }

</script>

</@c.page>