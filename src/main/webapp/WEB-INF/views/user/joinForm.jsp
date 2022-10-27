<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
    <form>
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" class="form-control" placeholder="Enter username" id="username">
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
            <label for="phoneNum">Phone</label>
            <input type="text" class="form-control" placeholder="Enter phone number" id="phoneNum">
        </div>

        <div class="form-group">
            <label for="address">Address</label>
            <input type="text" class="form-control" placeholder="Enter address" id="address">
        </div>

        <div class="form-group">
            <label for="gender">Gender</label>
            <input type="text" class="form-control" placeholder="Enter gender" id="gender">
        </div>

        <div class="form-group">
            <label for="birth">Birth</label>
            <input type="text" class="form-control" placeholder="Enter Birth" id="birth">
        </div>

        <div class="form-group">
            <label for="bank">Bank</label>
            <input type="text" class="form-control" placeholder="Enter Bank" id="bank">
        </div>

        <div class="form-group">
            <label for="account">Account</label>
            <input type="text" class="form-control" placeholder="Enter Account" id="account">
        </div>

    </form>
    <button id="btn-save" class="btn btn-primary">회원가입완료</button>

</div>

<%--<script src="/js/user.js"></script>--%>
<%@ include file="../layout/footer.jsp"%>