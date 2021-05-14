<%--
  Created by IntelliJ IDEA.
  User: eunjikim
  Date: 2021/05/09
  Time: 9:01 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../includes/header.jsp"%>

<div class="row">
  <div class="col-lg-12">
    <h1 class="page-header">Board Register</h1>
  </div>
</div>
<!-- /.col-lg-12 -->

<div class="row">
  <div class="col-lg-12">
    <div class="card">

      <div class="card-header">Board Register</div>
      <!-- /.panel-heading -->
      <div class="card-body">

        <form role="form" action="/board/register" method="post">
          <div class="form-group">
            <label>Title</label> <input class="form-control" name="title">
          </div>

          <div class="form-group">
            <label>Text area</label> <textarea class="form-control" rows="3" name="content"></textarea>
          </div>

          <div class="form-group">
            <label>Writer</label> <input class="form-control" name="writer">
          </div>

          <button type="submit" class="btn btn-default">Submit Button</button>
          <button type="reset" class="btn btn-default">Reset Button</button>
        </form>

      </div>
      <!-- end panel-body -->

    </div>
    <!-- end panel -->
  </div>

</div>
<!-- /.row -->
<%@include file="../includes/footer.jsp"%>
