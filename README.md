# Morethansimplycode Framework

This is a "framework". This project contains classes to automatize some task. You got some persistence classes, data formatting, lambda functions or some GUI classes. These parts are modules, and they don't depends between them. 

## Formatting

**_Packages:_** <br />
*com.morethansimplycode.formatting* <br />
*com.morethansimplycode.formatting.formatters*

This package contains a class that wrap a StringBuilder, called Stringformatter, which adds a appendFormat method. appendFormat method is a C# like string format function.

#### How to format a String

**With normal objects:**

```
StringFormatter.format("Hi {0}", "John"); // Output -> "Hi John"
StringFormatter.format("Hi {0,10}", "John"); // Output -> "Hi       John"
StringFormatter.format("Hi {0,-10}", "John"); // Output -> "Hi John      "
```
> **Tip:** We can use 0..n args to format the string and {0}..{n} to positioning and format it in the string.

**Having a Person class which defines name, surname:**

We can implement the Formattable interface

```
public interface Formattable {

    public String toString(String format);

    public String toString(CustomFormatter formatter, String format);
}
```

And imagine our ToString() just replaces N with the name and SN with the surname so we can:

```
StringFormatter.format("Hi {0:N SN}", new Person("John", "Doe")); // Output -> "Hi John Doe"
StringFormatter.format("Hi, My name is: {0,10:N} and my surname is: {0,5:SN}", new Person("John", "Doe")); // Output -> "Hi, My name is:       John and my surname is:      Doe"
StringFormatter.format("{0: N N N N N SN N}", new Person("John", "Doe")); // Output -> "John John John John John Doe John"
```
> **Tip:** You can pass a CustomFormatter to choose how the arg will be formatted.

 And remember StringFormatter.format() calls the toString() method of the args if they are not Formattable.
