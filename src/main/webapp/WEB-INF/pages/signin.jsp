<html>
  <head>
   <link rel="shortcut icon" href="/static/images/favicon.ico" type="image/x-icon" />
    <script
      src="https://code.jquery.com/jquery-3.6.1.min.js"
      integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ="
      crossorigin="anonymous">
    </script>
    <style>
    html{
      height:100%;
    }

    body{
      min-height:100vh;
    background-color:#3356FF;
      max-width:100vw;
    }

    .container{
    /**height:300px;
     background-color:lightcoral;
      border-radius:10px;**/

      display:flex;
      justify-content:center;
      align-items:center;
      margin-top:130px;
    }

    .child{
      background-color:white;
      height:auto;
      border-radius:15px;
      width:325px;
      margin-bottom:80px;


    }

    button .child{
    display:flex;
      justify-content:flex-end;
    }
    h1{
    font-weight:bold;
      text-align:center;
      font-family:font-family: 'Open Sans', sans-serif;
    }
    label{
    font-weight:bold;
      padding:15px;
      text-align:left;
      margin-left:25px;

    }
    .fields{ /** had been created to target the fields but i noticed i coukd just target them using input**/

    }

    input{
    box-sizing:border-box;
      height:30px;
      margin:15px 10px 15px 10px;
      display:inline-block;
      width:250px;
      margin-left:35px;
    }

    ::placeholder{
    color:#c1c1c1;
    transition:0.5s
    }

    button{
      width:130px;
      height:30px;
      background-color:green;
      text-align:center;
      color:white;
    margin: 10px 0 0 95px;
      border-radius:5px;
    }

    a{
    text-decoration:none;
      color:white;
    }

      /**display: flex;
      align-items: center;
      justify-content: center;
      background-color:white;**/
    }

    /**html{
    height:100%;
    }

    body{
    background-color:beige
      height:100vh;
    }
    .parent{
    background-color:blue;
    display:flex;
      justify-content:center;
      align-items:center;
    height:300px;

    }

    .child{
      width:100px;
      height:100px;
      background-color:green;
    }
    **/
    </style>
  </head>
  <body>
     <div class="container">
       <div class="child">
         <h1>SignIn</h1>

           <div class="text_fields">
             <label for="email">Email:</label>
             <input type="text" id="signup-mail" placeholder="name@gmail.com" class="fields" size="20">
             <br>
             <label for="password">Password:</label>
             <input type="text" id="signup-pswd" placeholder="we#are765" class="fields"  size="20"">
             <!--<p>Usernames must be lowercase and 4-8 characters in length.</p>-->
             <br>
           </div>
           <center><p id="signup-error" style="color:red;display:none"></p></center>
        <button id="signin-button">SignIn</button>
        <button id="signup-button">SignUp</button>

       </div>
     </div>


    <script >
       function validate()
       {


          var email = $("#signup-mail").val();

          var password = $("#signup-pswd").val();


          var error="";

          if(!email)
          error = error + "Email is not entered";
          if(!password)
          error = error + "password is not entered";
          $("#signup-error").html(error);
          console.log(error);

          if(error.length>0)
          return false;
          return true;
       }
       $("#signin-button").click(function(){

        var  valid = validate();
        if(valid)
        {
           var user = {

              "email" : $("#signup-mail").val(),
              "password" : $("#signup-pswd").val()
           };
           $.ajax({
             type:"POST",
             url :"/signin",
             data:JSON.stringify(user),
             success:function(response){
               if(!response.is_logined)
               {
                alert(response.message);
                }
                else
                {
                  location.href = "/welcome"
                }
             },
             contentType:"application/json"
           });
        }
        else
        {
           $("#signup-error").show();
        }
       });
       $("#signup-button").click(function(){
          location.href = "/signup";
       });
    </script>
  </body>
</html>