package neuroNation.apitest.utils;

import neuroNation.apitest.pojo.RegisterUser;


public class TestDataBuild {

    public RegisterUser RegisterUser(String email, String password)
    {
        RegisterUser r = new RegisterUser();
        r.setEmail(email);
        r.setPassword(password);
        return r;
    }

}
