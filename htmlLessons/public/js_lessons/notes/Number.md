## Number


Only **one** type of number in JavaScript whether it has decimal point or not.

```javascript
const age = 100;
const money = 1000.50
console.log(typeof age); // number
console.log(typeof money); // number
```


-  `typeof is used to find out the 'type' of a variable.`

-   Various operations: `addition, subtraction, multiplication, division` can be done with numbers.

-   Example

    `"10" "10" // 100 (number) - converts the strings to number`
    But "10" + "10" // 1010
The above works with multiplication, division and subtraction and not addition.
because the + sign is also

-   Example
    
    `"10"*"10" // 100 (number) - converts the strings to number`
     But "10"+"10" // 1010
The above works with _multiplication, division and subtraction and not addition,_ 
     because the + sign is also used for **concatenation**.
     
-  **Math helper methods:**

    -   **Math.round, Math.floor, Math.ceil, Math.random** and many others
        
        ```javascript
           Math.round(2.5); // 3
           Math.floor(6.6); // 6
           Math.ceil(8.4); // 9
           Math.random(); // 0.565262543848269
           ```
random no. between 8 and 1
  -   **Modulo and Power operators:**

   ```javascript
      const sweets = 20;
      const kids = 3;
      const eachKidGets = Math.floor(sweets/kids); // 6
      console.log(eachKidGets);
      const leftsweets = sweets % kids; // 2-modulo operation
      console.log(leftsweets);
    
      let x = 2 ** 3; // 8 - power operation using power operator (**)
       console.log(x);
      // or
      X = Math.pow(2,3); // 8 power operation using Math.pow
      console.log(x);
   ```
 -   Example
     0.10.2 // 0.30000000000000004
     
  Why? [Explanation] (http://0.30000000000000004.com/)
  
  
  So, when working with money, don't store them as (Rupees.paise) or (dollars.cents ) .
  Store all of the money in      (paise/cents) as you won't have to deal with fractions only whole nos.
   When need   to display to user, just convert     them back.
  // let costOfProduct 100.95;
     let costOfProduct = 10095; // in paise / cents
     
 -  **Infinity and Negative Infinity:**
 
`typeof Infinity; // number`
`typeof -Infinity; // number`

 -   **Not a Number (NaN):**
 
 `10/'hello' // NaN`
 `typed NaN // number`

