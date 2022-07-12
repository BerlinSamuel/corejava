## Variable and statement
-----
### Declaring a variable:


**var, let, const**

`var first='john';`
`let first='john';`
`const first='jojn';`
(here value is 'john')

-------

- `let` and `const` were introduced in ES6(newer). since 2016
- `var` and `let` can be updated but not `const`.

   ```javascript
var x= 'hey;
x =  'hi';

let cool =  true;
cool =  false;

const age =  10;
age =  11;  // wrong: throws error
-----

- In **strict mode**, define a variable first before assigning a value to it.

   ``` javascript 
     dog = 'snickers';       // bad coding, don't do this
     console.log(dog);       // snickers(no error)

      'use strict';
      dog = 'snickers';
   ```
 -  ** Scoping:**
    -  ** var** : _function scoped_ (only available inside parent functions)
    -  ** let** and **const** : _block scoped_ (available inside a block denoted by _{}_)
    
 -  ** Option(what to use):**
    - Use `const` by defualt;
    - if the value of the variable needs to change then use `let`.  
    
 -  **AVOID `var` as far as possible**
    -  Should  not start with capital unless they are a class.
    -  Must start with **a-z** or **_** or **&**
    
    
 -   **Avoid naming variables as just or
 ----
 # Disallow Early Use (no-use-before-define)

In JavaScript, prior to ES6, variable and function declarations are hoisted to the top of a scope,
so it's possible to use identifiers before their formal declarations in code.
This can be confusing and some believe it is best to always declare
variables and functions before using them.

In ES6, block-level bindings (let and const) introduce a
'temporal dead zone" where a ReferenceError will be thrown
 with any attempt to access the variable before its declaration.
  
### Types in Java Script
BONNUSS
1. Boolean
2. Object
3. Number - All numbers like integers, decimals, and floats are all one type.
4. Null -
5. Undefined -
6. String
7. Symbol - (new into js) used for assigning unique id . Always gives a guaranteed uniqu
Everything in the above list are Primitive type except Object.
An Object is a special type.
Every thing other than primitive types in js are Objects.
At the end of the day, Functions, arrays, dates are all just objects.

String
    used for holding text		
    3 ways to create strings:
    1. using single quotes:
    const first = 'Soumya';
   2. using double quotes:
    const middle = "Ranjan";
    .single quotes and double quotes are the same thing.
   3. using backticks:
   const last = `Mohanty`;
   used for: "she's cool"
   or escaping: 'she\\'s cool'
   
  backticks:
 const sentence = she's so "cool"; console.log(sentence); // she's so "cool"
Multi-line string:
const song 'Oh \\ I like \\
pizza';


-   Multi-line string:
    ```javascript
       const song = 'on \\
       I like \\
       pizza';
       
       console.log(song); 
       /* Oh I Like pizza*/

    ```
***javascript const song = `Oh
I like
pizza`;
console.log(song);
Oh
I Like
pizza

     2nd one using backticks is mostly used.
 -   **String concatenation and interpolation**
     -  '+' is used for **concatenation**. It is also used for adding 2 nos.
     -   **Concatenation**: when 2 or more strings combined to one
     -   **Interpolation**: when you put a variable inside a string
     -   Example 1:
     
    `const name = 'Soumya';
    
    `const hello = 'Hello my name is + name + '. Nice to meet you."`
    
    _(can use double or single quotes)__

    - Example 2:
    
    -1+1 // 2`
    
    `'1'+'1' // 11`
    `'1'+'1' // 11
    `1 +'1' // 11`

    - Example 3:
    
    ```javascript
       const name = 'Soumya';
       const hello= "Hello my name is ${name}. Nice to meet you. I am ${100+1} years old. ;
       console.log(hello); // Hello my name is Soumya. Nice to meet you. I am 181 years old.
       
       ````
  -  Backticks also used for _tagged template literals_.
  
  -Backticks are helpful for creating HTML:
   ```javascript
      const html =`
      <div>
          <h2>Hey everyone!  I am ${name}.</h2>
      <div>
      `;
     ```
