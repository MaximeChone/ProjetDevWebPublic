<%@ page import="htmlWriter.htmlWriterNavBar" %>
<%@ page import="bean.UserBean" %>
<%@ page import="java.util.Map" %>
<%@ page import="dao.sql.DAOFactorySQL" %>
<%@ page import="htmlWriter.htmlWriterAdminUser" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UserBean user = null;
    String idString = request.getParameter("id_user");
    int id = -1;
    if(idString != null){
        id = Integer.parseInt(idString.trim());
        user = DAOFactorySQL.getInstance().getUserDAO().load(id);

    }
%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>CoP - Mon profil</title>

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <%
            out.print(htmlWriterNavBar.getSideBar(request));
        %>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    <!-- Sidebar Toggle (Topbar) -->
                    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>

                    <!-- Topbar Search -->
                    <form
                            class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                        <div class="input-group">
                            <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..."
                                   aria-label="Search" aria-describedby="basic-addon2">
                            <div class="input-group-append">
                                <button class="btn btn-primary" type="button">
                                    <i class="fas fa-search fa-sm"></i>
                                </button>
                            </div>
                        </div>
                    </form>

                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">

                        <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                        <li class="nav-item dropdown no-arrow d-sm-none">
                            <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-search fa-fw"></i>
                            </a>
                            <!-- Dropdown - Messages -->
                            <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                                 aria-labelledby="searchDropdown">
                                <form class="form-inline mr-auto w-100 navbar-search">
                                    <div class="input-group">
                                        <input type="text" class="form-control bg-light border-0 small"
                                               placeholder="Search for..." aria-label="Search"
                                               aria-describedby="basic-addon2">
                                        <div class="input-group-append">
                                            <button class="btn btn-primary" type="button">
                                                <i class="fas fa-search fa-sm"></i>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </li>

                        <%
                            out.print(htmlWriterNavBar.getUserInformationItem(request));
                        %>

                    </ul>

                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 mb-4 text-gray-800">Modification de l'utilisateur: id=<% out.print(request.getParameter("id_user"));%></h1>

                    <form method="post" action="adminModifyUser?id_user=<%out.print(id);%>" class="user">
                        <!-- EMAIL -->
                        <div class="form-group row">
                            <div class="col-sm-4">
                                <h5>Email:</h5>
                            </div>
                            <div class="col-sm-6 mb-3 mb-sm-0">
                                <input type="email" class="form-control "
                                       name="email" placeholder value="<%
                                       if(user != null)
                                        out.print(user.getMail());
                                       %>">
                            </div>
                        </div>
                        <h6 class="h6 alert-danger">
                        <%
                            @SuppressWarnings("unchecked")
                            Map<String,String> erreurs_modifyUser = (Map<String,String>)request.getAttribute("erreur_modifyUser");

                            if(erreurs_modifyUser != null){
                                String email = "email";
                                String erreur = erreurs_modifyUser.get(email);
                                if(erreur != null)
                                    out.print(erreur);
                            }
                        %>
                        </h6>
                        <!-- NOM -->
                        <div class="form-group row">
                            <div class="col-sm-4">
                                <h5>Nom:</h5>
                            </div>
                            <div class="col-sm-6 mb-3 mb-sm-0">
                                <input type="text" class="form-control "
                                       name="name" placeholder value="<%
                                       if(user != null)
                                       out.print(user.getLastName());
                                       %>">
                            </div>
                        </div>
                        <!-- PRENOM -->
                        <div class="form-group row">
                            <div class="col-sm-4">
                                <h5>Prénom:</h5>
                            </div>
                            <div class="col-sm-6 mb-3 mb-sm-0">
                                <input type="text" class="form-control "
                                       name="firstname" placeholder value="<%
                                       if(user != null)
                                       out.print(user.getFirstName());
                                       %>">
                            </div>
                        </div>
                        <h6 class="h6 alert-danger">
                            <%

                                if(erreurs_modifyUser != null){
                                    String name = "name";
                                    String erreur = erreurs_modifyUser.get(name);
                                    if(erreur != null)
                                        out.print(erreur);
                                }
                            %>
                        </h6>
                        <!-- MDP -->
                        <div class="form-group row">
                            <div class="col-sm-4">
                                <h5>Mot de passe:</h5>
                            </div>
                            <div class="col-sm-6 mb-3 mb-sm-0">
                                <input type="password" class="form-control "
                                       name="password">
                            </div>
                        </div>
                        <h6 class="h6 alert-danger">
                        <%
                            if(erreurs_modifyUser != null){
                                String password = "password";
                                String erreur = erreurs_modifyUser.get(password);
                                if(erreur != null)
                                    out.print(erreur);
                            }
                        %>
                        </h6>
                        <div class="form-group row">
                            <div class="col-sm-4">
                                <h5>Date de naissance:</h5>
                            </div>
                            <div class="col-sm-6 mb-3 mb-sm-0">
                                <input type="date" class="form-control "
                                       name="birthdate" placeholder value="<%
                                       if(user != null)
                                       out.print(user.getBirthdate());
                                       %>">
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-sm-2">
                                <a href="adminUsers" class="btn btn-outline-light btn-icon-split">
                                            <span class="icon text-gray-600">
                                                <i class="fas fa-arrow-left"></i>
                                            </span>
                                    <span class="text">Retour</span>
                                </a>
                            </div>
                            <div class="col-sm-3">
                                <button href="adminUserProfile?id_user=<%out.print(id);%>" class="btn btn-secondary btn-icon-split">
                                        <span class="icon text-white-50">
                                            <i class="fas fa-times"></i>
                                        </span>
                                    <span class="text">Annuler les changements</span>
                                </button>
                            </div>
                            <div class="col-sm-4 mb-3 mb-sm-0">
                                <button type="submit" class="btn btn-primary btn-icon-split">
                                        <span class="icon text-white-50">
                                            <i class="fas fa-check"></i>
                                        </span>
                                    <span class="text">Sauvegarder les changements</span>
                                </button>
                            </div>
                            <div class="col-sm-3 mb-3 mb-sm-0">
                                <a type="submit" class="btn btn-danger btn-icon-split" data-toggle="modal" data-target="#deleteAccountModal">
                                        <span class="icon text-white-50">
                                            <i class="fas fa-times"></i>
                                        </span>
                                    <span class="text">Supprimer le compte</span>
                                </a>
                            </div>
                        </div>
                    </form>
                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>Copyright &copy; Your Website 2020</span>
                    </div>
                </div>
            </footer>
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <%
        out.print(htmlWriterNavBar.getLogoutModal());
    %>

    <!-- delete Modal-->
    <%
        out.print(htmlWriterAdminUser.getDeleteAccountModal(request));
    %>

    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin-2.min.js"></script>

</body>

</html>