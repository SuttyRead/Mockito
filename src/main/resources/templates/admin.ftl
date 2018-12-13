<#import "parts/common.ftl" as c>

<@c.page>
    Hello, Admin
    <a href="/add">Add new User</a>

    <#if successfullyUpdated??>
        <div class="alert alert-success" role="alert">
            User was successfully update!
        </div>
    </#if>

    <#if successfullyAdded??>
        <div class="alert alert-success" role="alert">
            User was successfully added!
        </div>
    </#if>

    <#if successfullyDeleted??>
        <div class="alert alert-success" role="alert">
            User was successfully deleted!
        </div>
    </#if>

    <#if unknownId??>
        <div class="alert alert-danger" role="alert">
            We don't find this user!
        </div>
    </#if>

    <#if deleteYourself??>
        <div class="alert alert-danger" role="alert">
            You don't delete yourself!
        </div>
    </#if>

    <table class="table table-hover table-bordered">
    <thead>
    <tr>
        <th>#</th>
        <th>Login</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Age</th>
        <th>Role</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <#assign count = 0>
    <#list users as user>
        <#assign count = count + 1>
        <tr>
        <th>${count}</th>
        <td>${user.login}</td>
        <td>${user.firstName}</td>
        <td>${user.lastName}</td>
        <td>${user.age}</td>
        <td>${user.role.name}</td>
        <td><a href="/edit/${user.id}">Edit</a>
    <a href="/delete/${user.id}" onclick="return confirm('Are you sure?')">Delete</a></td>
        </tr>
    </#list>
    </tbody>
    </table>

</@c.page>