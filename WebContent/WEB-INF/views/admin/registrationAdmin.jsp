<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="page-title">
    <div class="title_left">
        <h3>${pageTitle}</h3>
    </div>

    <div class="title_right">      
    </div>
</div>

<div class="row">

    <div class="col-md-12 col-sm-12 col-xs-12">
        <div class="x_panel">
            <div class="x_title">
                <h2>Admin to approve</h2>           
                <div class="clearfix"></div>
            </div>
            <div class="x_content">
                <p class="text-muted font-13 m-b-30">
                    Lorem ipsum
                </p>
                <table class="table datatable table-hover table-centered table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
                    <thead>
                        <tr>
                            <th>Username</th>
                            <th>First name</th>
                            <th>Last name</th>
                            <th>Date of birth</th>
                            <th>Email</th>
                            <th>&nbsp;</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="admin" items="${listAdministrators}">
                            <tr>
                                <td>${admin.username}</td>
                                <td>${admin.firstName}</td>
                                <td>${admin.lastName}</td>
                                <td>${admin.dateOfBirth}</td>
                                <td>${admin.email}</td>
                                <td>
                                    <span class="inline"><form method="post"><button class="btn btn-primary" type="submit" name="accept" value="${admin.username}">accept</button></form></span>
                                    <span class="inline"><form method="post"><button class="btn btn-danger" type="submit" name="refuse" value="${admin.username}">refuse</button></form></span>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>