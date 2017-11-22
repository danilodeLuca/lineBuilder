A simple linebuilder.

Checkout tests to see how it works, its not difficult!!


## Example:
1. Simple line with 1 column and 4 lenght character


``` 
    String build = new LineBuilder(4)
                      .column("test").at(1, 4).value("test")
                      .build();
```
  
  Result: ```Assert.assertEquals("test", build);```


2. Line with 2 columns and 9 lenght character
  
```
    String build = new LineBuilder(9)
                      .column("test").at(1, 4).value("test")
                      .column("test2").at(5, 9).value("test2")
                      .build();
```
  
  Result:```Assert.assertEquals("testtest2", build);```
 
 
3. Line with 1 columns and 4 lenght character and defined a complementer to left that will not be used
  
```
    String build = new LineBuilder(4)
                      .complementColumn(Complementer.onLeft("|"))
                      .column("test").at(1, 4).value("test")
                      .build();
```
  
  Result:```Assert.assertEquals("test", build);```
 
 
4. Line with 1 columns and 5 lenght character and defined a complementer to left that will be used
  
```
    String build = new LineBuilder(5)
                      .complementColumn(Complementer.onLeft("|"))
                      .column("test").at(1, 5).value("test")
                      .build();
```
  
  Result:```Assert.assertEquals("|test", build);```


5. Line with 1 columns and 5 lenght character and defined a complementer to right that will be used
  
```
    String build = new LineBuilder(5)
                      .complementColumn(Complementer.onRight("|"))
                      .column("test").at(1, 5).value("test")
                      .build();
```
  
  Result:```Assert.assertEquals("test|", build);```

