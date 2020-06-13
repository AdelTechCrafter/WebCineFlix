/**
 * 
 */
function makeMainPanel(fromid,fromlogin,query)
{
	ch="<header>";
	ch+="<div id=\"logo\">";
	ch+="<img src=\"WebContent/logotwister.jpg\" alt=\"LogoTwister\" height=\"50\" width=\"50\"/>";
	ch+="</div>";
	ch+="<br/>";
	ch+="<div id=\"connect\">";
	ch+="<br/>";
	ch+="<a href=\"javascript:makeProfilPanel("+fromid+",'"+fromlogin+"')\"> Profil </a> | ";
	ch+="<a href=\"javascript:makeConnectionPanel()\"> Deconnexion </a> ";
	ch+="</div>";
	ch+="</header>";
	ch+="</head>";
	ch+="<br/>";
	ch+="<nav></nav>	";
	ch+="<section id=\"main\">";
	ch+="<div id=\"new_message\">";
	ch+="<br/>";
	ch+="<br/>";
	ch+="Message";
	ch+="<br/>";
	ch+="<br/>";
	ch+="<div id=\"bla\">";
	ch+="<form class =\"main_post\" action=\"javascript:(function(){return;})()\" onSubmit=\"javascript:new_message("+env.id+")\">";
	ch+="<input type=\"text\" id=\"main_message\"/>";
	ch+="<br/>";
	ch+="<br/>";
	ch+="<input type=\"submit\" id=\"poster\" value=\"Publier\"/>";
	ch+="</form>";
	ch+="<br/>";
	ch+="</div>";
	ch+="</div>";
	ch+="<div id=\"liste_message\">";
	
	if (noConnection)
	{
		if (main_message.length>0)
		{
			for (var i=0; i< main_message.length; i++)
				ch+=main_message[i].getHTML();
		}
	}
	ch+="</div>";
	ch+="</section>";
	$("body").html(s);
}
function makeConnectionPanel()
{
	$("body").load("html/connexion.html");
}
function makeEnregistrementPanel()
{
	$("body").load("html/Enregistrement.html");
}
function makeProfilPanel(fromId,fromLogin,query)
{
	$("body").load("html/Profil.html");
}
