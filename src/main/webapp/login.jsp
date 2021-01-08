<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>CoP- Connexion</title>

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body class="bg-gradient-primary">

    <div class="container">

        <!-- Outer Row -->
        <div class="row justify-content-center">

            <div class="col-xl-10 col-lg-12 col-md-9">

                <div class="card o-hidden border-0 shadow-lg my-5">
                    <div class="card-body p-0">
                        <!-- Nested Row within Card Body -->
                        <div class="row justify-content-md-center">
                            <div class="col-lg-7">
                                <div class="p-5">
                                    <div class="text-center">
                                        <h1 class="h4 text-gray-900 mb-4">Connexion</h1>
                                    </div>
                                    <form method="post" class="user" action="login">
                                        <div class="form-group">
                                            <input type="email" class="form-control form-control-user"
                                                name="email" aria-describedby="emailHelp"
                                                placeholder="Adresse mail">
                                            <h6 class="h6 alert-danger">

                                                <%
                                                    @SuppressWarnings("unchecked")
                                                    Map<String,String> erreurs_login = (Map<String,String>)request.getAttribute("erreur_login");

                                                    if(erreurs_login != null){
                                                        String email = "email";
                                                        String erreur = erreurs_login.get(email);
                                                        if(erreur != null)
                                                        out.print(erreur);
                                                    }
                                                %>
                                            </h6>
                                        </div>
                                        <div class="form-group">
                                            <input type="password" class="form-control form-control-user"
                                                 name="password" placeholder="Mot de passe">
                                            <h6 class="h6 alert-danger">

                                            <%
                                                if(erreurs_login != null){
                                                    String password = "password";
                                                    String erreur = erreurs_login.get(password);
                                                    if(erreur != null)
                                                        out.print(erreur);
                                                }
                                            %>
                                            </h6>
                                        </div>
                                        <button type="submit" class="btn btn-primary btn-user btn-block">
                                            Se connecter
                                        </button>
                                        <h6 class="h6 alert-danger">
                                        <%
                                            if(erreurs_login != null){
                                                String match = "match";
                                                String erreur = erreurs_login.get(match);
                                                if(erreur != null)
                                                    out.print(erreur);
                                            }
                                        %>
                                        </h6>
                                    </form>
                                    <hr>
                                    <div class="text-center">
                                        <a class="small" href="register.jsp">Créer un compte!</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </div>

    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin-2.min.js"></script>

</body>

</html>