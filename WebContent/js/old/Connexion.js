/**
 * 
 */
function Connexion(formulaire){
	var login= formulaire.login.value;
	var password=formulaire.password.value;
	var ok= verif_formulaire_connexion(login,password);
	if(ok){
		Connecte(login,password)
	}
}

function verif_formulaire_connexion(login,password,){
	if(login.length==0){
		func_erreur("login obligatoire");
		return false;
	}
	if(password.length==0){
		func_erreur("password obligatoire");
		return false;
	}
	if(login.length>20){
		func_erreur("Taille maximum login = 20");
		return false;
	}
	if(password.length>255){
		func_erreur("Taille maximum password = 255");
		return false;
	}
	
	return true;
}

function Connecte(login,password){
	console.log("Connexion de " + prenom);
	var url = "Login";
	if (!noConnection)
	{
		$.ajax({
			type:"GET",
			url:url,
			data:"login="+login+"&password="+password,
			datatype: "JSON",
			success: function(rep)
			{ 
				func_pass("Connexion établie");
			},
			error: function (jqXHR, textStatus, errorThrown){alert(textStatus);}
		});
	}
	else
		func_pass("mode dev, connexion établie");
}
