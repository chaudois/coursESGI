package com.esgi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by damie on 03/05/2017.
 */
@RunWith(Parameterized.class)
public class PhoneValidatorTest {

    PhoneValidator x=new PhoneValidator();
    @Parameters(name="{0} is {1}")
    public static Collection<Object[]> validate(){
        return Arrays.asList(new Object[][]{
                {"0603269550",true},
                {"0",false},
                {"-1",false},
                {"azertyuiop",false},
                {"0000000000",false},
                {"12",false},
                {"-089456123",false},
                {"06-02-56-89-97",false},
                {"06.23.69.45.78",false},
                {"06 03 26 59 98",false}
        });

    }
    @Parameter(0)
    public String phone;

    @Parameter(1)
    public boolean result;

    @Test
    public void should_validate_number(){
        assertThat(x.validate(phone)).isEqualTo(result);
    }

}