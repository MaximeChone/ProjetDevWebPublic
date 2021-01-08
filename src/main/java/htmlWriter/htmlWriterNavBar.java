package htmlWriter;

import bean.UserBean;

/** @author - Maxime Choné **/

import javax.servlet.http.HttpServletRequest;

public class htmlWriterNavBar {

    public static String getUserInformationItem(HttpServletRequest request) {
        UserBean current_user = (UserBean) request.getSession().getAttribute("current_user");

        String retour = "";
        if (current_user != null) {

            String notifHTML =
                    "<!-- Nav Item - Alerts -->"+
                    "                        <li class=\"nav-item dropdown no-arrow mx-1\">"+
                    "                            <a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"alertsDropdown\" role=\"button\""+
                    "                                data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">"+
                    "                                <i class=\"fas fa-bell fa-fw\"></i>"+
                    "                                <!-- Counter - Alerts -->"+
                    "                                <span class=\"badge badge-danger badge-counter\">3+</span>"+
                    "                            </a>"+
                    "                            <!-- Dropdown - Alerts -->"+
                    "                            <div class=\"dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in\""+
                    "                                aria-labelledby=\"alertsDropdown\">"+
                    "                                <h6 class=\"dropdown-header\">"+
                    "                                    Alerts Center"+
                    "                                </h6>"+
                    "                                <a class=\"dropdown-item d-flex align-items-center\" href=\"#\">"+
                    "                                    <div class=\"mr-3\">"+
                    "                                        <div class=\"icon-circle bg-primary\">"+
                    "                                            <i class=\"fas fa-file-alt text-white\"></i>"+
                    "                                        </div>"+
                    "                                    </div>"+
                    "                                    <div>"+
                    "                                        <div class=\"small text-gray-500\">December 12, 2019</div>"+
                    "                                        <span class=\"font-weight-bold\">A new monthly report is ready to download!</span>"+
                    "                                    </div>"+
                    "                                </a>"+
                    "                                <a class=\"dropdown-item d-flex align-items-center\" href=\"#\">"+
                    "                                    <div class=\"mr-3\">"+
                    "                                        <div class=\"icon-circle bg-success\">"+
                    "                                            <i class=\"fas fa-donate text-white\"></i>"+
                    "                                        </div>"+
                    "                                    </div>"+
                    "                                    <div>"+
                    "                                        <div class=\"small text-gray-500\">December 7, 2019</div>"+
                    "                                        $290.29 has been deposited into your account!"+
                    "                                    </div>"+
                    "                                </a>"+
                    "                                <a class=\"dropdown-item d-flex align-items-center\" href=\"#\">"+
                    "                                    <div class=\"mr-3\">"+
                    "                                        <div class=\"icon-circle bg-warning\">"+
                    "                                            <i class=\"fas fa-exclamation-triangle text-white\"></i>"+
                    "                                        </div>"+
                    "                                    </div>"+
                    "                                    <div>"+
                    "                                        <div class=\"small text-gray-500\">December 2, 2019</div>"+
                    "                                        Spending Alert: We've noticed unusually high spending for your account."+
                    "                                    </div>"+
                    "                                </a>"+
                    "                                <a class=\"dropdown-item text-center small text-gray-500\" href=\"#\">Show All Alerts</a>"+
                    "                            </div>"+
                    "                        </li>";


            String name = current_user.getLastName();
            String firstname = current_user.getFirstName();
            String userHTML = "<div class=\"topbar-divider d-none d-sm-block\"></div>" +
                    "" +
                    "                        <!-- Nav Item - User Information -->" +
                    "                        <li class=\"nav-item dropdown no-arrow\">" +
                    "                            <a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"userDropdown\" role=\"button\"" +
                    "                                data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">" +
                    "                                <span class=\"mr-2 d-none d-lg-inline text-gray-600 small\">" + firstname + " " + name + "</span>" +
                    "                                <img class=\"img-profile rounded-circle\"" +
                    "                                    src=\"img/undraw_profile.svg\">" +
                    "                            </a>" +
                    "                            <!-- Dropdown - User Information -->" +
                    "                            <div class=\"dropdown-menu dropdown-menu-right shadow animated--grow-in\"" +
                    "                                aria-labelledby=\"userDropdown\">" +
                    "                                <a class=\"dropdown-item\" href=\"myProfile\">" +
                    "                                    <i class=\"fas fa-user fa-sm fa-fw mr-2 text-gray-400\"></i>" +
                    "                                    Mon compte" +
                    "                                <div class=\"dropdown-divider\"></div>" +
                    "                                <a class=\"dropdown-item\" href=\"logout\" data-toggle=\"modal\" data-target=\"#logoutModal\">" +
                    "                                    <i class=\"fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400\"></i>" +
                    "                                    Déconnexion" +
                    "                                </a>" +
                    "                            </div>" +
                    "                        </li>";


            retour = notifHTML + userHTML;
        } else {
            String connexionEtEnregitrement =
                    "                        <!-- Nav Item - User Information -->" +
                    "                         <a href=\"login\" class=\"mr-2 d-none d-lg-inline text-gray-600 btn btn-light btn-sm\">Se connecter</a>" +
                    "                                <div class=\"topbar-divider d-none d-sm-block\"></div>" +
                    "                         <a href=\"register\" class=\"mr-2 d-none d-lg-inline text-gray-600 btn btn-light btn-sm\">S'inscrire</a>";
            retour = connexionEtEnregitrement;
        }
        return retour;
    }

    public static String getSideBar(HttpServletRequest request){

        UserBean current_user = (UserBean) request.getSession().getAttribute("current_user");

        String sideBar = "<!-- Sidebar -->"+
                "        <ul class=\"navbar-nav bg-gradient-primary sidebar sidebar-dark accordion\" id=\"accordionSidebar\">"+
                ""+
                "            <!-- Sidebar - Brand -->"+
                "            <a class=\"sidebar-brand d-flex align-items-center justify-content-center\" href=\"index.jsp\">"+
                "                <div class=\"sidebar-brand-icon rotate-n-15\">"+
                "                    <i class=\"fas fa-viruses\"></i>"+
                "                </div>"+
                "                <div class=\"sidebar-brand-text mx-3\">Covid ou pas</div>"+
                "            </a>"+
                ""+
                "            <!-- Divider -->"+
                "            <hr class=\"sidebar-divider my-0\">"+
                ""+
                "            <!-- Nav Item - Accueil -->"+
                "            <li class=\"nav-item active\">"+
                "                <a class=\"nav-link\" href=\"index.jsp\">"+
                "                    <i class=\"fas fa-fw fa-tachometer-alt\"></i>"+
                "                    <span>Accueil</span></a>"+
                "            </li>"+
                ""+
                "            <!-- Divider -->"+
                "            <hr class=\"sidebar-divider\">"+
                ""+
                "            <!-- Heading -->"+
                "            <div class=\"sidebar-heading\">"+
                "                Utilisateur"+
                "            </div>"+
                ""+
                "            <!-- Nav Item - add activity -->"+
                "            <li class=\"nav-item\">"+
                "                <a class=\"nav-link\" href=\"#\">"+
                "                    <i class=\"fas fa-fw fa-plus\"></i>"+
                "                    <span>Ajouter une activités</span></a>"+
                "            </li>"+
                ""+
                "            <!-- Nav Item - my activities -->"+
                "            <li class=\"nav-item\">"+
                "                <a class=\"nav-link\" href=\"#\">"+
                "                    <i class=\"fas fa-fw fa-running\"></i>"+
                "                    <span>Mes activités</span></a>"+
                "            </li>"+
                ""+
                "            <!-- Nav Item - friends -->"+
                "            <li class=\"nav-item\">"+
                "                <a class=\"nav-link\" href=\"myFriends\">"+
                "                    <i class=\"fas fa-fw fa-user-friends\"></i>"+
                "                    <span>Mes amis</span></a>"+
                "            </li>"+
                ""+
                "            <!-- Divider -->"+
                "            <hr class=\"sidebar-divider\">";
        if(current_user != null){
            if(current_user.getRole().equals("admin")){
                sideBar +=                 "            <!-- Heading -->"+
                        "            <div class=\"sidebar-heading\">"+
                        "                Admin"+
                        "            </div>"+
                        ""+
                        "            <!-- Nav Item - Tables utilisateur-->"+
                        "            <li class=\"nav-item\">"+
                        "                <a class=\"nav-link\" href=\"adminUsers\">"+
                        "                    <i class=\"fas fa-fw fa-chart-area\"></i>"+
                        "                    <span>Utilisateurs</span></a>"+
                        "            </li>"+
                        ""+
                        "            <!-- Nav Item - Tables activités -->"+
                        "            <li class=\"nav-item\">"+
                        "                <a class=\"nav-link\" href=\"#\">"+
                        "                    <i class=\"fas fa-fw fa-table\"></i>"+
                        "                    <span>Activités</span></a>"+
                        "            </li>"+
                        ""+
                        "            <!-- Nav Item - Tables lieux-->"+
                        "            <li class=\"nav-item\">"+
                        "                <a class=\"nav-link\" href=\"#\">"+
                        "                    <i class=\"fas fa-fw fa-table\"></i>"+
                        "                    <span>Lieux</span></a>"+
                        "            </li>"+
                        "";
            }
        }
        sideBar +=
                "            <!-- Divider -->"+
                "            <hr class=\"sidebar-divider d-none d-md-block\">"+
                ""+
                "            <!-- Sidebar Toggler (Sidebar) -->"+
                "            <div class=\"text-center d-none d-md-inline\">"+
                "                <button class=\"rounded-circle border-0\" id=\"sidebarToggle\"></button>"+
                "            </div>"+
                ""+
                "        </ul>"+
                "        <!-- End of Sidebar -->";



        return sideBar;
    }

    public static String getLogoutModal(){

        String logoutModal = "<!-- Logout Modal-->"+
                "    <div class=\"modal fade\" id=\"logoutModal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleModalLabel\""+
                "        aria-hidden=\"true\">"+
                "        <div class=\"modal-dialog\" role=\"document\">"+
                "            <div class=\"modal-content\">"+
                "                <div class=\"modal-header\">"+
                "                    <h5 class=\"modal-title\" id=\"exampleModalLabel\">Ready to Leave?</h5>"+
                "                    <button class=\"close\" type=\"button\" data-dismiss=\"modal\" aria-label=\"Close\">"+
                "                        <span aria-hidden=\"true\">×</span>"+
                "                    </button>"+
                "                </div>"+
                "                <div class=\"modal-body\">Select \"Logout\" below if you are ready to end your current session.</div>"+
                "                <div class=\"modal-footer\">"+
                "                    <button class=\"btn btn-secondary\" type=\"button\" data-dismiss=\"modal\">Cancel</button>"+
                "                    <a class=\"btn btn-primary\" href=\"logout\">Logout</a>"+
                "                </div>"+
                "            </div>"+
                "        </div>"+
                "    </div>";

        return logoutModal;
    }


}
