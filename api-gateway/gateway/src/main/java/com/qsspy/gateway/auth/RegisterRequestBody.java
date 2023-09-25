package com.qsspy.gateway.auth;


record RegisterRequestBody(
        String email,
        String password,
        String firstName,
        String lastName
) { }
