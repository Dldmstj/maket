<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
    <form>
        <input type="hidden" id="id" value="${principal.user.id}" />
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" value="${principal.user.username }" class="form-control" placeholder="Enter username" id="username" readonly>
        </div>

        <div class="form-group">
            <label for="pw">Password</label>
            <input type="password" class="form-control" placeholder="Enter password" id="pw">
        </div>

        <div class="form-group">
            <label for="name">Name</label>
            <input type="text" class="form-control" placeholder="Enter Name" id="name">
        </div>

        <div class="form-group">
            <label for="phoneNum">phoneNum</label>
            <input type="text" class="form-control" placeholder="Enter phoneNum" id="phoneNum">
        </div>

        <div class="form-group">
            <label for="address">Address</label>
            <input type="text" class="form-control" placeholder="Enter address" id="address" >
        </div>

        <div class="form-group">
            <label for="gender">Gender</label>
            <input type="text" value="${principal.user.gender}" class="form-control" placeholder="Enter gender" id="gender" readonly>
        </div>

        <div class="form-group">
            <label for="birth">Birth</label>
            <input type="text" class="form-control" placeholder="Enter birth" id="birth" >
        </div>

        <div class="form-group">
            <label for="bank">Bank</label>
            <input type="text" class="form-control" placeholder="Enter bank" id="bank">
        </div>

        <div class="form-group">
            <label for="account">Account</label>
            <input type="text" class="form-control" placeholder="Enter account" id="account" >
        </div>

    </form>
    <button id="btn-update" class="btn btn-primary">회원수정완료</button>

</div>

<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>
