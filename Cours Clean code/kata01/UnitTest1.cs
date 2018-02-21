using System;
using Xunit;

namespace kata01
{
    public class UnitTest1
    {
        [Fact]
        public void sould_return_1()
        {
        		Assert.Equal("1",fizzBuzz(1)); 
        }
        [Fact]
        public void sould_return_2()
        {
        		Assert.Equal("2",fizzBuzz(2));
        }
        [Fact]
        public void sould_return_fizz_when_3()
        {
        		Assert.Equal("fizz",fizzBuzz(3));
        }      
        [Fact]
        public void sould_return_4()
        {
        		Assert.Equal("4",fizzBuzz(4));
        }      
        [Fact]
        public void sould_return_fizz_when_6()
        {
        		Assert.Equal("fizz",fizzBuzz(6));
        }      
        [Fact]
        public void sould_return_buzz_when_5()
        {
        		Assert.Equal("buzz",fizzBuzz(5));
        }      
        [Fact]
        public void sould_return_7()
        {
        		Assert.Equal("7",fizzBuzz(7));
        }      
        [Fact]
        public void sould_return_fizzbuzz_when_15()
        {
        		Assert.Equal("fizzbuzz",fizzBuzz(15));
        }      
        [Fact]
        public void sould_return_fizzbuzz_when_90()
        {
        		Assert.Equal("fizzbuzz",fizzBuzz(90));
        }      
        [Fact]
        public void sould_return_buzz_when_100()
        {
        		Assert.Equal("buzz",fizzBuzz(100));
        }
        string fizzBuzz(int nombre)
        {
        	string resteDivision=(string.valueOf(nombre/3))

        }
    }
}
