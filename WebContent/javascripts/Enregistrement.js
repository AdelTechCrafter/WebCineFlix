/**
 * 
 */
function Enregistrement(formulaire){
	var login= formulaire.login.value;
	var password=formulaire.password.value;
	var nom=formulaire.nom.value;
	var prenom=formulaire.prenom.value;
	var retapez=formulaire.retapez.value;
	
	var ok= verif_formulaire_enregistrement(login,password,nom,prenom,retapez);
	if(ok){
		Enregistre(login,password,nom,prenom)
	}
}
function verif_formulaire_enregistrement(login,password,retapez,nom,prenom,email){
	if(login.length==0){
		func_erreur("login obligatoire");
		return false;
	}
	if(password.length==0){
		func_erreur("password obligatoire");
		return false;
	}
	if(retapez.length==0){
		func_erreur("champs retapez obligatoire");
		return false;
	}
	if(nom.length==0){
		func_erreur("Nom obligatoire");
		return false;
	}
	if(prenom.length==0){
		func_erreur("Prenom obligatoire");
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
	if(retapez.length>255){
		func_erreur("Taille maximum password =255");
		return false;
	}
	if(password.length<8){
		func_erreur("Taille minimum password = 8");
		return false;
	}
	if(retapez.length<8){
		func_erreur("Taille minimum password = 8");
		return false;
	}
	if(retapez!=password){
		func_erreur("le mot de passe n'a pas été bien retaper");
		return false;
	}
	
	return true;
}
function Enregistre(login,password,nom,prenom)
{
	console.log("Enregistrment de " + prenom);
	var url = "CreateUser";
	if (!noConnection)
	{
		$.ajax({
			type:"GET",
			url:url,
			data:"login="+login+"&name="+nom+"&prenom="+prenom+"&password="+password,
			datatype: "JSON",
			success: function(rep)
			{ 
				func_pass("Bravo!Vous êtes enregistré");
			},
			error: function (jqXHR, textStatus, errorThrown){alert(textStatus);}
		});
	}
	else
		func_pass("mode dev, enregistrement");
}
