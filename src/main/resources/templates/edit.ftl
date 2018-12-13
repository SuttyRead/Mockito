<#import "parts/common.ftl" as c>
<#setting date_format="yyyy-MM-dd">

<@c.page>

    <#if successfullyUpdate??>
        <div class="alert alert-success" role="alert">
            User was successfully update!
        </div>
    </#if>

<form method="post" class="form-horizontal" action="/edit/${userForEdit.id}">
    <div class="form-group">
        <label class="control-label col-sm-3" for="login">Login:</label>
        <div class="col-sm-6">
            <input readonly="readonly"
                   type="text"
                   class="form-control"
                   id="login"
                   placeholder="Enter login"
                   name="login"
                   pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$"
                   title="Uppercase and lowercase letter. Must be 2-20 characters. Without specifically characters #,$,% and so on. For example SuttyRead"
                   aria-describedby="loginHelpInline"
                   value="${userForEdit.login}"
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
            <input type="password" class="form-control" id="password"
                   placeholder="Enter password" name="password"
                   aria-describedby="passwordHelpInline"
                   pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$"
                   title="Password must be have lowercase and uppercase Latin letters, number. Minimum 8 characters"
                   value="${userForEdit.password}" required>
            <small id="passwordHelpInline" class="text-muted">
                Lowercase and uppercase Latin letters, numbers, special characters. Minimum 8 characters
            </small>
        </div>
        <div class="col-sm-offset-3 col-sm-6 err-message">
            <#if passwordNotEquals??>
                <div class="alert alert-danger" role="alert">
                    Password don't match!
                </div>
            </#if>
            <#if passwordNotPattern??>
                <div class="alert alert-danger" role="alert">
                    This login doesn't match pattern
                    ^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$.
                    With a limit of 2-20 characters, which can be letters
                    and numbers, the first character is necessarily the letter.
                    For example SuttyRead007
                </div>
            </#if>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-3"
               for="confirmPassword">Confirm Password:</label>
        <div class="col-sm-6">
            <input type="password" class="form-control" id="confirmPassword"
                   placeholder="Confirm password" name="confirmPassword"
                   pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$"
                   title="Password must be have lowercase and uppercase Latin letters, number. Minimum 8 characters"
                   value="${userForEdit.password}" required>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-3"
               for="email">Email:</label>
        <div class="col-sm-6">
            <input type="text" class="form-control" id="email"
                   placeholder="Enter email" name="email" aria-describedby="emailHelpInline"
                   pattern="\w+([\.-]?\w+)*@\w+([\.-]?\w+)*\.\w{2,4}"
                   title="Enter correct email. Email must be have @. For example SuttyRead@gmail.com"
                   value="${userForEdit.email}" required>
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
                   value="${userForEdit.firstName}" required>
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
                   pattern="^[A-Z]{1}[a-z]{1,25}" value="${userForEdit.lastName}" required>
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
                   placeholder="Enter birthday" name="birthday" required value="${userForEdit.birthday}">
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

    <div class="form-group">
        <label class="control-label col-sm-3" for="Role">Role:</label>
        <div class="col-sm-6">
            <#if userForEdit.role.id == 1>
                <select class="form-control" id="Role" name="role">
                    <option value="1">Admin</option>
                    <option value="2">User</option>
                </select>
            <#else>
                <select class="form-control" id="Role" name="role">
                    <option value="2">User</option>
                    <option value="1">Admin</option>
                </select>
            </#if>
        </div>
    </div>

    <div class="form-inline">
        <div class="col-sm-1 col-sm-offset-4">
            <button type="submit" onclick="validate(this.form)" class="btn btn-success">Save</button>
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
        var pass2 = document.getElementById("password").value;
        var pass1 = document.getElementById("confirmPassword").value;
        if (pass1 !== pass2)
            document.getElementById("confirmPassword").setCustomValidity("Passwords Don't Match");
        else
            document.getElementById("confirmPassword").setCustomValidity('');
    }
</script>


</@c.page>