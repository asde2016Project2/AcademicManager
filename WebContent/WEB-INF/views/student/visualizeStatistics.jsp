<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="panel panel-default">
    <div class="panel-body" style="min-height: 100; max-height: 100;">

        <label>Statistics of ${studentName}</label>

        <table class="table table-striped table-hover ">
            <thead>
                <tr>
                    <th width="120">Average Weighted Score</th>
                    <th width="120">Earned Credits</th>
                    <th width="120">Graduation Base Grade</th>

                </tr>
            </thead>

            <tbody>
                <tr>
                    <td>${averageWeightedScore}</td>
                    <td>${earnedCredits}</td>
                    <td>${graduationBaseGrade}</td>


                </tr>
            </tbody>




        </table>

    </div>

</div>